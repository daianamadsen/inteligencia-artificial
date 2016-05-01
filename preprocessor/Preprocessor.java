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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Preprocesador
 * Provee un metodo para obtener un archivo .arff a partir de un archivo .csv
 * Solicita path origen y destino
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class Preprocessor {
    
    private static int errorCounter;
    private static int okCounter;
    
    private static ArrayList<Opinion> opiniones = new ArrayList();

    public static boolean preprocess(String sourcePath, String arffPath) {
    	errorCounter = okCounter = 0;
        try {
            Reader r = new SSVReader(sourcePath);
            Writer w = new ARFFWriter(arffPath);
            Opinion o;
            while ((o = r.read())!=null) {
                opiniones.add(o);
            }
            ArrayList<String> genres = r.getGenres();
            w.insertHeader(r.getHeader(), genres);
            Iterator<Opinion> i = opiniones.iterator();
            while (i.hasNext()) {
                if (w.write(i.next(), genres)) {
                    okCounter++;
                } else {
                    errorCounter++;
                }
            }
            w.close();
            System.out.println("Se escribieron correctamente "+okCounter+" opiniones. ("+errorCounter+" opiniones con error)");
            Logger.getLogger(Preprocessor.class.getName()).log(Level.FINE, "Se escribieron correctamente {0} opiniones. ({1} opiniones con error)", new Object[]{okCounter, errorCounter});
        } catch (Exception e) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, "Error al preprocesar.", e);
            return false;
        }
        return true;
    }
    
    //Para testing
    public static void main(String args[]) {
        String sourcePath = "C:\\Users\\A\\Desktop\\dataSet-p1.csv";
        String arffPath = "C:\\Users\\A\\Desktop\\prueba1.arff";
        preprocess(sourcePath, arffPath);
    }
    
}
