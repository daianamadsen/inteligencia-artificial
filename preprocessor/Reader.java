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
 * Reader
 * Lee a partir de un archivo.
 * Primero se debe inicializar, utilizando el método readData para leer todo el archivo.
 * Luego, se puede obtener encabezado y datos.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public interface Reader {
    
    public void initReader();
    
    public Opinion readData();
    
    public ArrayList<String> getData();
    
    public ArrayList<String> getHeader();
    
}
