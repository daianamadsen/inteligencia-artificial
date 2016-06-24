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

import java.io.FileNotFoundException;

/**
 * SSVReaderRA Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Atributos reducidos con valoración balanceada
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderBRA2 extends SSVReaderRA {
    
    public SSVReaderBRA2(String path) throws FileNotFoundException {
    	super(path);
        this.valuesGroups.removeAttributes();
        this.valuesGroups.addAttribute(MALA, new ArrayList(Arrays.asList("0.5", "1.0", "1.5", "2.0", "2.5")));  //suma 871
        this.valuesGroups.addAttribute(REGULAR, new ArrayList(Arrays.asList("3.0", "3.5")));                    //suma 715
        this.valuesGroups.addAttribute(BUENA, new ArrayList(Arrays.asList("4.0", "4.5", "5.0")));               //suma 897
    }
    
}