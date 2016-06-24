/**
 * INTELIGENCIA ARTIFICIAL
 * Preprocesador
 * 
 * JUAN VALACCO - LU 
 * DAIANA MADSEN - LU 246826
 * Facultad de Cs. Exactas - UNICEN - Tandil
 */

package preprocessor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Header
 * Representa el encabezado: una lista de atributos discretizados.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class Header implements Iterable {
    
    /**
    * Buffer
    * Representa un atributo del encabezado.
    */
    public class Buffer implements Iterable {
        
        private String name;
        private ArrayList<String> items;
        
        Buffer(String n) {
            name = n;
            items = new ArrayList();
        }

        public String getName() {
            return name;
        }

        public ArrayList<String> getItems() {
            return items;
        }
        
        boolean add(String item) {
            if ((!item.equals("")) && (!this.items.contains(item))) {
                return this.items.add(item);
            }
            return false;
        }

        boolean contains(String value) {
            return items.contains(value);
        }
        
        boolean equals(String n) {
            return name.equals(n);
        }

        @Override
        public Iterator iterator() {
            return items.iterator();
        }
        
    }
    
    ArrayList<Buffer> buffers;
    
    public Header() {
        buffers = new ArrayList();
    }
    
    public boolean addAttribute(String name) {
        Buffer b = new Buffer(name);
        return buffers.add(b);
    }
    
    public boolean removeAttribute(String attribute) {
        Iterator<Buffer> i = buffers.iterator();
        while (i.hasNext()) {
            Buffer b = i.next();
            if (b.getName().equals(attribute)) {
                return buffers.remove(b);
            }
        }
        return false;
    }
    
    public void removeAttributes() {
        buffers.clear();
    }

    public ArrayList<String> getAttribute(String attribute) {
        Iterator<Buffer> i = buffers.iterator();
        while (i.hasNext()) {
            Buffer b = i.next();
            if (b.getName().equals(attribute)) {
                return b.getItems();
            }
        }
        return null;
    }
    
    public boolean addItem(String attribute, String item) {
        for (Buffer b : buffers) {
            if (b.equals(attribute)) {
                return b.add(item);
            }
        }
        return true;
    }

    public boolean addItem(String attribute, ArrayList<String> items) {
        Iterator<String> i = items.iterator();
        while (i.hasNext()) {
            addItem(attribute, i.next());
        }
        return true;
    }

    //Este método permite individualizar un atributo
    //Ejemplo: @atributo nombre {a,b,c} de manera individual:
    //@atributo a {0,1}, @atributo b {0,1}, @atributo c {0,1}
    //Si la variable acceptND esta en true, permite no_data, entonce @atriuto c {1}
    public boolean individualize(String attribute, boolean acceptND) {
        ArrayList<Buffer> newBuffers = new ArrayList();
        Iterator<Buffer> i = buffers.iterator();
        while (i.hasNext()) {
            Buffer b = i.next();
            if (b.getName().equals(attribute)) {
                //Individualize
                Iterator<String> j = b.iterator();
                while (j.hasNext()) {
                    String a = j.next();
                    Buffer newBuffer = new Buffer(a);
                    if (!acceptND) {
                        newBuffer.add("0");
                    }
                    newBuffer.add("1");
                    newBuffers.add(newBuffer);
                }
            } else {
                newBuffers.add(b);
            }
        }
        buffers = newBuffers;
        return true;
    }

    public ArrayList<String> getHeader() {
        ArrayList<String> head = new ArrayList();
        head.add("@relation films");
        head.add("");
        //Atributos (todos)
        Iterator<Buffer> i = buffers.iterator();
        while (i.hasNext()) {
            Buffer buffer = i.next();
            String attribute = "@attribute ";
            attribute += buffer.getName();
            attribute += " {";
            Iterator<String> j = buffer.iterator();
            while (j.hasNext()) {
                attribute += j.next() + ",";
            }
            attribute = attribute.substring(0, attribute.length()-1);
            attribute += "}";
            head.add(attribute);
        }
        head.add("");
        head.add("@data");
        return head;
    }

    @Override
    public Iterator iterator() {
        return buffers.iterator();
    }
    
}
