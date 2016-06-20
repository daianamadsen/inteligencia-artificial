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

/**
 * Groups
 * Permite crear grupos predefinidos.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class Groups extends Header {
    
    public Groups() {
        super();
    }
    
    public boolean addAttribute(String name, ArrayList<String> items) {
        Buffer b = new Buffer(name);
        if (buffers.add(b)) {
            for (String i: items) {
                b.add(i);
            }
        }
        return true;
    }
    
}
