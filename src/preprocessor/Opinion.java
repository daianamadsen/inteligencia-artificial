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
import java.util.Arrays;
import java.util.Iterator;

/**
 * Opinion
 * Representa opiniones de usuarios sobre películas
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
class Opinion {
    
    private String sex;
    private String age;
    private String film;
    private String actor;
    private String genre;
    private String director;
    private String year;
    private String valoracion;
    
    @Override
    public String toString() {
        return sex + "," + age + "," + actor + "," + genre + "," + director + "," + year + "," + valoracion;
    }
    
    public String toSplitedString(ArrayList<String> genres, boolean acceptND) {
        return sex + "," + age + "," + actor + "," + split(genres, acceptND) + "," + director + "," + year + "," + valoracion;
    }
    
    public String toReducedString() {
        return sex + "," + age + "," + genre + "," + year + "," + valoracion;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
    
    private String split(ArrayList<String> allGenres, boolean acceptND) {
        String no_data = "0,";
        if (acceptND) {
            no_data = "?,";
        }
        ArrayList<String> opGenres = new ArrayList<String>(Arrays.asList(genre.split("\\|")));
        Iterator<String> i = allGenres.iterator();
        String g = ",";
        while (i.hasNext()) {
            if (opGenres.contains(i.next())) {
                g += "1,";
            } else {
                g += no_data;
            }
        }
        g = g.substring(0, g.length()-1);
        return g;
    }
    
}