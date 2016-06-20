/**
 * INTELIGENCIA ARTIFICIAL
 * Preprocesador
 * 
 * JUAN VALACCO - LU 
 * DAIANA MADSEN - LU 246826
 * Facultad de Cs. Exactas - UNICEN - Tandil
 */

package preprocessor;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ARFFWriter
 * Escribe un archivo en formato .arff (será utilizado desde Weka).
 * El archivo se construye a partir de un encabezado y datos.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class ARFFWriter implements Writer {
    
    ArrayList<String> header;
    ArrayList<String> data;
    
    public ARFFWriter() {
    }
    
    @Override
    public boolean insertData(ArrayList<String> d) {
        this.data = d;
        return true;
    }
    
    @Override
    public boolean insertHeader(ArrayList<String> h) {
        this.header = h;
        return true;
    }

    @Override
    public boolean buildFile(String path) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(path), '\n', CSVWriter.NO_QUOTE_CHARACTER);
            String[] headArr = new String[this.header.size()];
            writer.writeNext(this.header.toArray(headArr));
            String[] dataArr = new String[this.data.size()];
            writer.writeNext(this.data.toArray(dataArr));
            writer.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ARFFWriter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
