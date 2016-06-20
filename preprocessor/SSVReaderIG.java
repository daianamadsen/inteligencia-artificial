/**
 * INTELIGENCIA ARTIFICIAL
 * Preprocesador
 * 
 * JUAN VALACCO - LU 
 * DAIANA MADSEN - LU 246826
 * Facultad de Cs. Exactas - UNICEN - Tandil
 */

package preprocessor;

import com.opencsv.CSVReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * SSVReaderIG Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Separa los múltiples generos de una película en una lista de generos individuales.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderIG implements Reader {
    
    protected CSVReader reader;
    protected Header header;
    protected ArrayList<Opinion> opinions;
    
    protected static final String INDEFINIDO = "N/A";
    
    protected static final String SEX = "sex";
    protected static final String AGE = "age";
    protected static final String ACTOR = "actor";
    protected static final String GENRE = "genre";
    protected static final String DIRECTOR = "director";
    protected static final String YEAR = "year";
    protected static final String VALORACION = "valoracion";
    
    public SSVReaderIG(String path) throws FileNotFoundException {
        this.reader = new CSVReader(new FileReader(path), '\n');
        this.opinions = new ArrayList();
        this.header = new Header();
        this.header.addAttribute(SEX);
        this.header.addAttribute(AGE);
        this.header.addAttribute(ACTOR);
        this.header.addAttribute(GENRE);
        this.header.addAttribute(DIRECTOR);
        this.header.addAttribute(YEAR);
        this.header.addAttribute(VALORACION);
    }

    @Override
    public void initReader() {
        Opinion o;
        while ((o = this.readData()) != null) {
            opinions.add(o);
        }
    }

    //Consume una opinión
    @Override
    public Opinion readData() {
    	Opinion o = new Opinion();
        try {
            String[] nextLine = reader.readNext();
            if (nextLine == null) {
                return null;
            }
            String opinion = nextLine[0];
            String[] attributes = opinion.split(";");
            System.out.println("OPINION: "+opinion);
            o.setSex(parseSex(attributes[1]));
            o.setAge(parseAge(attributes[2]));
            o.setActor(parseActor(attributes[5]));
            o.setGenre(parseGenres(attributes[6]));
            o.setDirector(parseDirector(attributes[7]));
            o.setYear(parseYear(attributes[8]));
            o.setValoracion(parseValoracion(attributes[9]));
            
        } catch (IOException e) {
            Logger.getLogger(SSVReaderIG.class.getName()).log(Level.WARNING, "Error de lectura en el archivo fuente.", e);
        }
        return o;
    }

    //El sexo puede ser {M,F,N/A}
    protected String parseSex(String sex) {
        String parsedSex = INDEFINIDO;
        if (sex.equals("M") || sex.equals("F")) {
            parsedSex = sex;
        }
        this.header.addItem(SEX, parsedSex);
        return parsedSex;
    }
    
    //La edad, por ahora, puede ser {20,21,22,23,24,25,26,N/A} luego vemos si lo hacemos por rango
    protected String parseAge(String age) {
        String parsedAge = INDEFINIDO;
        if (isNumeric(age)) {
            parsedAge = age;
        }
        this.header.addItem(AGE, parsedAge);
        return parsedAge;
    }
    
    //Actor: guardamos solo el primero. Supongo que es el más imortante, se puede cambiar
    protected String parseActor(String actor) {
        actor = actor.replace(" ", "_");
        String[] actors = actor.split("\\|");
        this.header.addItem(ACTOR, actors[0]);
        return actors[0];
    }

    //Los generos se guardan todos como String por ahora, y luego se hacen booleans (al terminar de leer todos)
    protected String parseGenres(String genre) {
        ArrayList<String> genres = new ArrayList<String>(Arrays.asList(genre.split("\\|")));
        this.header.addItem(GENRE, genres);
        return genre;
    }
    
    //Director: guardamos solo el primero igual que actor, se puede cambiar
    protected String parseDirector(String director) {
        director = director.replace(" ", "_");
        String[] directors = director.split("\\|");
        this.header.addItem(DIRECTOR, directors[0]);
        return directors[0];
    }
    
    //Año, por ahora, puede ser {1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,N/A} luego vemos si lo hacemos por rango
    protected String parseYear(String year) {
        String parsedYear = INDEFINIDO;
        if (isNumeric(year)) {
            parsedYear = year;
        }
        this.header.addItem(YEAR, parsedYear);
        return parsedYear;
    }
    
    //Valoracion, por ahora, puede ser {0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,N/A}
    protected String parseValoracion(String val) {
        String parsedVal = INDEFINIDO;
        if (isValoracion(val)) {
            parsedVal = val;
        }
        this.header.addItem(VALORACION, parsedVal);
        return parsedVal;
    }
    
    protected static boolean isNumeric(String s){
	try {
            Integer.parseInt(s);
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }
    
    protected static boolean isValoracion(String v){
	return (v.equals("0.5") || v.equals("1.0") || v.equals("1.5") || v.equals("2.0") || v.equals("2.5") || v.equals("3.0") || v.equals("3.5") || v.equals("4.0") || v.equals("4.5") || v.equals("5.0"));
    }

    //DEVUELVE DATOS: 
    //Antes de devolver cada opinion, se hacen booleans los generos
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList();
        Iterator<Opinion> i = opinions.iterator();
        while (i.hasNext()) {
            data.add(i.next().toSplitedString(header.getAttribute(GENRE), false));
        }
        return data;
    }

    //DEVUELVE HEADER: 
    //Antes de devolver el encabezado, se modifican los generos como atributos individuales
    @Override
    public ArrayList<String> getHeader() {
        if (this.header.individualize(GENRE, false)) {
            return this.header.getHeader();
        }
        return null;
    }
    
}