/**
 * INTELIGENCIA ARTIFICIAL
 * Preprocesador
 * 
 * JUAN VALACCO - LU 
 * DAIANA MADSEN - LU 246826
 * Facultad de Cs. Exactas - UNICEN - Tandil
 */

package preprocessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    public static boolean preprocess(String sourcePath, String arffPath, int type) throws FileNotFoundException {
        //Reader: generos individuales o agrupados
        Reader r;
        switch (type) {
            case 1: { r = new SSVReaderIG(sourcePath); break; }
            case 2: { r = new SSVReaderIIG(sourcePath); break; }
            case 3: { r = new SSVReaderGG(sourcePath); break; }
            case 4: { r = new SSVReaderRA(sourcePath); break; }
            default: { r = null; break; }
        }
        if (r!=null) {
            //Writer
            Writer w = new ARFFWriter();
            //Preprocess
            return preprocess(arffPath, r, w);
        }
        return false;
    }
    
    public static boolean preprocess(String arffPath, Reader r, Writer w) {
        try {
            //Proceso lectura
            r.initReader();
            ArrayList<String> data = r.getData();
            ArrayList<String> head = r.getHeader();
            
            //Proceso escritura
            w.insertHeader(head);
            w.insertData(data);
            w.buildFile(arffPath);
            
            System.out.println("Se escribieron "+data.size()+" opiniones.");
            Logger.getLogger(Preprocessor.class.getName()).log(Level.FINE, "Se escribieron {0} opiniones.", data.size());
        } catch (Exception e) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, "Error al preprocesar.", e);
            return false;
        }
        return true;
    }
    
    //Para testing
    public static void main(String args[]) throws FileNotFoundException {
        String sourcePath = "C:\\Users\\A\\Desktop\\dataSet-p1.csv";
        String arffPath = "C:\\Users\\A\\Desktop\\prueba1.arff";
        preprocess(sourcePath, arffPath, 1);
    }
    
}
