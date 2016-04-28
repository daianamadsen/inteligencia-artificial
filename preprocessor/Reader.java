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

public interface Reader {
    
    public Opinion read();
    
    public ArrayList<ArrayList<String>> getHeader();
    
    public ArrayList<String> getGenres();
    
}
