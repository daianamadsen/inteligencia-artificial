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
public class SSVReaderBRA3 extends SSVReaderRA {
    
    //Grupos predefinidos:
    protected static final String MUY_MALA = "Muy_Mala";
    protected static final String MUY_BUENA = "Muy_Buena";
    
    public SSVReaderBRA3(String path) throws FileNotFoundException {
    	super(path);
        this.valuesGroups.removeAttributes();
        this.valuesGroups.addAttribute(MUY_MALA,  new ArrayList(Arrays.asList("0.5", "1.0")));    //suma 362
        this.valuesGroups.addAttribute(MALA,      new ArrayList(Arrays.asList("1.5", "2.0")));    //suma 337
        this.valuesGroups.addAttribute(REGULAR,   new ArrayList(Arrays.asList("2.5", "3.0")));    //suma 625
        this.valuesGroups.addAttribute(BUENA,     new ArrayList(Arrays.asList("3.5", "4.0")));    //suma 714
        this.valuesGroups.addAttribute(MUY_BUENA, new ArrayList(Arrays.asList("4.5", "5.0")));    //suma 445
    }
    
}