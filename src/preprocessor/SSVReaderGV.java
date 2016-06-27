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

/**
 * SSVReaderRA Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Valoración agrupada.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderGV extends SSVReaderGG {
    
    protected Groups valuesGroups;
    
    //Grupos predefinidos:
    protected static final String MALA = "Mala";
    protected static final String REGULAR = "Regular";
    protected static final String BUENA = "Buena";
    
    public SSVReaderGV(String path) throws FileNotFoundException {
    	super(path);
        this.valuesGroups = new Groups();
        this.valuesGroups.addAttribute(MALA, new ArrayList(Arrays.asList("0.5", "1.0", "1.5", "2.0")));
        this.valuesGroups.addAttribute(REGULAR, new ArrayList(Arrays.asList("2.5", "3.0", "3.5")));
        this.valuesGroups.addAttribute(BUENA, new ArrayList(Arrays.asList("4.0", "4.5", "5.0")));
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
    
}