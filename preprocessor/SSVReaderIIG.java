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
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * SSVReaderIIG Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Separa los múltiples generos de una película en una lista de generos individuales.
 * Si la película no tiene el género se coloca un signo de interrogación
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderIIG extends SSVReaderIG {
    
    protected static final String DATO_NO_DISPONIBLE = "?";
    
    public SSVReaderIIG(String path) throws FileNotFoundException {
        super(path);
    }

    //El sexo puede ser {M,F}
    @Override
    protected String parseSex(String sex) {
        String parsedSex = DATO_NO_DISPONIBLE;
        if (sex.equals("M") || sex.equals("F")) {
            parsedSex = sex;
            this.header.addItem(SEX, parsedSex);
        }
        return parsedSex;
    }
    
    //La edad puede ser {20,21,22,23,24,25,26}
    @Override
    protected String parseAge(String age) {
        String parsedAge = DATO_NO_DISPONIBLE;
        if (isNumeric(age)) {
            parsedAge = age;
            this.header.addItem(AGE, parsedAge);
        }
        return parsedAge;
    }
    
    //Año puede ser {1999,2000,2001,2002,2003,2004,2005,2006,2007,2008}
    @Override
    protected String parseYear(String year) {
        String parsedYear = DATO_NO_DISPONIBLE;
        if (isNumeric(year)) {
            parsedYear = year;
            this.header.addItem(YEAR, parsedYear);
        }
        return parsedYear;
    }
    
    //Valoracion puede ser {0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0}
    @Override
    protected String parseValoracion(String val) {
        String parsedVal = DATO_NO_DISPONIBLE;
        if (isValoracion(val)) {
            parsedVal = val;
            this.header.addItem(VALORACION, parsedVal);
        }
        return parsedVal;
    }

    //DEVUELVE DATOS: 
    //Antes de devolver cada opinion, los generos se hacen 1 o ?
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList();
        Iterator<Opinion> i = opinions.iterator();
        while (i.hasNext()) {
            data.add(i.next().toSplitedString(header.getAttribute(GENRE), true));
        }
        return data;
    }

    //DEVUELVE HEADER: 
    //Antes de devolver el encabezado, se modifican los generos como atributos individuales
    @Override
    public ArrayList<String> getHeader() {
        if (this.header.individualize(GENRE, true)) {
            return this.header.getHeader();
        }
        return null;
    }
    
}