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
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SSVReaderRA Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Valoración en grupos balanceados
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderBGV2 extends SSVReaderGV {
    
    public SSVReaderBGV2(String path) throws FileNotFoundException {
    	super(path);
        this.valuesGroups.removeAttributes();
        this.valuesGroups.addAttribute(MALA, new ArrayList(Arrays.asList("0.5", "1.0", "1.5", "2.0", "2.5")));  //suma 871
        this.valuesGroups.addAttribute(REGULAR, new ArrayList(Arrays.asList("3.0", "3.5")));                    //suma 715
        this.valuesGroups.addAttribute(BUENA, new ArrayList(Arrays.asList("4.0", "4.5", "5.0")));               //suma 897
    }
    
    //DEVUELVE DATOS: 
    //Antes de devolver cada opinion, se hacen booleans los generos
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList();
        Iterator<Opinion> i = opinions.iterator();
        while (i.hasNext()) {
            data.add(i.next().toString());
        }
        return data;
    }
    
}