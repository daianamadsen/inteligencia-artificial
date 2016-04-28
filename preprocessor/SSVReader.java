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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * SSVReader Semicolon Separated Values Reader
 * Lee archivos separados por punto y coma
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReader implements Reader {
    
    private CSVReader reader;
    
    private static final String INDEFINIDO = "N/A";
    
    //Estos buffers sirven para armar el encabezado
    private ArrayList<String> sexesBuffer;
    private ArrayList<String> agesBuffer;
    //private ArrayList<String> filmsBuffer;
    private ArrayList<String> actorsBuffer;
    private ArrayList<String> genresBuffer;
    private ArrayList<String> directorsBuffer;
    private ArrayList<String> yearsBuffer;
    private ArrayList<String> valoracionesBuffer;
    
    public SSVReader(String path) throws FileNotFoundException  {
    	this.reader = new CSVReader(new FileReader(path), '\n');
        this.sexesBuffer = new ArrayList();
        this.agesBuffer = new ArrayList();
        //this.filmsBuffer = new ArrayList();
        this.actorsBuffer = new ArrayList();
        this.genresBuffer = new ArrayList();
        this.directorsBuffer = new ArrayList();
        this.yearsBuffer = new ArrayList();
        this.valoracionesBuffer = new ArrayList();
        this.sexesBuffer.add("sex");
        this.agesBuffer.add("age");
        //this.filmsBuffer.add("film");
        this.actorsBuffer.add("actor");
        this.directorsBuffer.add("director");
        this.yearsBuffer.add("year");
        this.valoracionesBuffer.add("valoracion");
    }

    //Consume de a una opinión por vez
    @Override
    public Opinion read() {
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
            //o.setFilm(parseFilm(attributes[4]));
            o.setActor(parseActor(attributes[5]));
            o.setGenres(parseGenres(attributes[6]));
            o.setDirector(parseDirector(attributes[7]));
            o.setYear(parseYear(attributes[8]));
            o.setValoracion(parseValoracion(attributes[9]));
            
        } catch (IOException e) {
            Logger.getLogger(SSVReader.class.getName()).log(Level.WARNING, "Error de lectura en el archivo fuente.", e);
        }
        return o;
    }

    //El sexo puede ser {M,F,N/A}
    private String parseSex(String sex) {
        String parsedSex = INDEFINIDO;
        if (sex.equals("M") || sex.equals("F")) {
            parsedSex = sex;
        }
        save(sexesBuffer, parsedSex);
        return parsedSex;
    }
    
    //La edad, por ahora, puede ser {20,21,22,23,24,25,26,N/A} luego vemos si lo hacemos por rango
    private String parseAge(String age) {
        String parsedAge = INDEFINIDO;
        if (isNumeric(age)) {
            parsedAge = age;
        }
        save(agesBuffer, parsedAge);
        return parsedAge;
    }
    
    /*//El film se guarda tal cual (se podría procesar)
    private String parseFilm(String film) {
        save(filmsBuffer, film);
        return film;
    }*/
    
    //Actor: guardamos solo el primero. Supongo que es el más imortante (?) se puede cambiar
    private String parseActor(String actor) {
        actor = actor.replace(" ", "_");
        String[] actors = actor.split("\\|");
        save(actorsBuffer, actors[0]);
        return actors[0];
    }

    //Los generos se guardan todos como String por ahora, y luego se hacen booleans (al terminar de leer todos)
    private ArrayList<String> parseGenres(String genre) {
        genre = genre.toLowerCase();
        ArrayList<String> genres = new ArrayList<String>(Arrays.asList(genre.split("\\|")));
        save(genresBuffer, genres);
        return genres;
    }
    
    //Director: guardamos solo el primero igual que actor, se puede cambiar
    private String parseDirector(String director) {
        director = director.replace(" ", "_");
        String[] directors = director.split("\\|");
        save(directorsBuffer, directors[0]);
        return directors[0];
    }
    
    //Año, por ahora, puede ser {1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,N/A} luego vemos si lo hacemos por rango
    private String parseYear(String year) {
        String parsedYear = INDEFINIDO;
        if (isNumeric(year)) {
            parsedYear = year;
        }
        save(yearsBuffer, parsedYear);
        return parsedYear;
    }
    
    //Valoracion, por ahora, puede ser {0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,N/A}
    private String parseValoracion(String val) {
        String parsedVal = INDEFINIDO;
        if (isValoracion(val)) {
            parsedVal = val;
        }
        save(valoracionesBuffer, parsedVal);
        return parsedVal;
    }

    private boolean save(ArrayList<String> buffer, String value) {
        if ((!value.equals("")) && (!buffer.contains(value))) {
            return buffer.add(value);
        }
        return false;
    }

    private boolean save(ArrayList<String> buffer, ArrayList<String> values) {
        Iterator<String> i = values.iterator();
        while (i.hasNext()) {
            save(buffer, i.next());
        }
        return true;
    }
    
    private static boolean isNumeric(String s){
	try {
            Integer.parseInt(s);
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }
    
    private static boolean isValoracion(String v){
	return (v.equals("0.5") || v.equals("1.0") || v.equals("1.5") || v.equals("2.0") || v.equals("2.5") || v.equals("3.0") || v.equals("3.5") || v.equals("4.0") || v.equals("4.5") || v.equals("5.0"));
    }

    @Override
    public ArrayList<ArrayList<String>> getHeader() {
        ArrayList<ArrayList<String>> header = new ArrayList();
        header.add(sexesBuffer);
        header.add(agesBuffer);
        //header.add(filmsBuffer);
        header.add(actorsBuffer);
        header.add(directorsBuffer);
        header.add(yearsBuffer);
        header.add(valoracionesBuffer);
        return header;
    }

    @Override
    public ArrayList<String> getGenres() {
        return genresBuffer;
    }
    
}