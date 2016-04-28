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
import java.util.Iterator;

/**
 * ARFFWriter
 * Escribe un archivo en formato .arff
 * Será utilizado desde Weca
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class ARFFWriter implements Writer {
    
    private CSVWriter writer;
    
    public ARFFWriter(String path) throws IOException {
    	this.writer = new CSVWriter(new FileWriter(path), '\n', CSVWriter.NO_QUOTE_CHARACTER);
    }

    @Override
    public boolean write(Opinion o, ArrayList<String> generos) {
        ArrayList<String> data = new ArrayList();
        data.add(o.toString(generos));
        String[] dataArr = new String[data.size()];
        writer.writeNext(data.toArray(dataArr));
        return true;
    }

    @Override
    public boolean insertHeader(ArrayList<ArrayList<String>> header, ArrayList<String> genres) {
        ArrayList<String> w = new ArrayList();
        w.add("@relation films");
        w.add("");
        //Imprime atributos (todos)
        Iterator<ArrayList<String>> i = header.iterator();
        while (i.hasNext()) {
            ArrayList<String> atribute = i.next();
            String h = "@attribute ";
            Iterator<String> j = atribute.iterator();
            if (j.hasNext()) {
                h += j.next() + " {";
            }
            while (j.hasNext()) {
                h += j.next() + ",";
            }
            h = h.substring(0, h.length()-1);
            h += "}";
            w.add(h);
        }
        //Imprime genres:
        Iterator<String> k = genres.iterator();
        while (k.hasNext()) {
            w.add("@attribute " + k.next() + " {0,1}");
        }
        w.add("");
        w.add("@data");
        String[] headerArr = new String[w.size()];
        writer.writeNext(w.toArray(headerArr));
        return true;
    }

}
