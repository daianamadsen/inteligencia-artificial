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
 * Writer
 * Escribe en un archivo.
 * Primero se debe insertar encabezado y datos.
 * Luego, se puede construir el archivo en un path dado.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public interface Writer {
    
    public boolean insertData(ArrayList<String> data);

    public boolean insertHeader(ArrayList<String> header);
    
    public boolean buildFile(String path);
    
}
