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
import java.util.Arrays;
import java.util.Iterator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SSVReaderRA Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Atributos reducidos.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderRA extends SSVReaderGG {
    
    protected Groups valuesGroups;
    
    //Grupos predefinidos:
    protected static final String MALA = "Mala";
    protected static final String REGULAR = "Regular";
    protected static final String BUENA = "Buena";
    
    public SSVReaderRA(String path) throws FileNotFoundException {
    	super(path);
        this.header.removeAttribute(ACTOR);
        this.header.removeAttribute(DIRECTOR);
        this.valuesGroups = new Groups();
        this.valuesGroups.addAttribute(MALA, new ArrayList(Arrays.asList("0.5", "1.0", "1.5", "2.0")));
        this.valuesGroups.addAttribute(REGULAR, new ArrayList(Arrays.asList("2.5", "3.0", "3.5")));
        this.valuesGroups.addAttribute(BUENA, new ArrayList(Arrays.asList("4.0", "4.5", "5.0")));
    }
    
    //Consume una opinión
    //No se setea actor ni director
    @Override
    public Opinion readData() {
    	Opinion o = new Opinion();
        try {
            String[] nextLine = reader.readNext();
            if (nextLine == null) {
                return null;
            }
            String opinion = nextLine[0];
            String[] attributes = opinion.split(";");
            System.out.println("OPINION: "+opinion);
            o.setSex(parseSex(attributes[1]));
            o.setAge(parseAge(attributes[2]));
            o.setGenre(parseGenres(attributes[6]));
            o.setYear(parseYear(attributes[8]));
            o.setValoracion(parseValoracion(attributes[9]));
            
        } catch (IOException e) {
            Logger.getLogger(SSVReaderIG.class.getName()).log(Level.WARNING, "Error de lectura en el archivo fuente.", e);
        }
        return o;
    }

    //Se guarda el grupo de valoracion
    @Override
    protected String parseValoracion(String val) {
        val = getClasificacion(val);  
        super.header.addItem(SSVReaderIG.VALORACION, val);
        return val;
    }
    
    //Se busca el grupo que contiene el valor
    private String getClasificacion(String val) {
        Iterator<Header.Buffer> i = valuesGroups.iterator();
        while (i.hasNext()) {
            Header.Buffer group = i.next();
            if (group.contains(val)) {
                return group.getName();
            }
        }
        return SSVReaderIG.INDEFINIDO;
    }

    //DEVUELVE DATOS: 
    //Se devuelve cada opinion con sus atributos
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList();
        Iterator<Opinion> i = opinions.iterator();
        while (i.hasNext()) {
            data.add(i.next().toReducedString());
        }
        return data;
    }

    //DEVUELVE HEADER: 
    //Se devuelve el encabezado
    @Override
    public ArrayList<String> getHeader() {
        return this.header.getHeader();
    }
    
}