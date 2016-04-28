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

public interface Writer {
    
    public boolean write(Opinion opinion, ArrayList<String> genres);

    public boolean insertHeader(ArrayList<ArrayList<String>> header,  ArrayList<String> genres);
    
}
