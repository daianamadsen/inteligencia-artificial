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
    private ArrayList<String> genres;
    private String director;
    private String year;
    private String valoracion;

    public Opinion() {
    }
    
    public String toString(ArrayList<String> allGenres) {
        return sex + "," + age + "," + actor + "," + director + "," + year + "," + valoracion + splitGenres(allGenres);
    }
    
    private String splitGenres(ArrayList<String> allGenres) {
        Iterator<String> i = allGenres.iterator();
        String g = ",";
        while (i.hasNext()) {
            if (genres.contains(i.next())) {
                g += "1,";
            } else {
                g += "0,";
            }
        }
        g = g.substring(0, g.length()-1);
        return g;
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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
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
    
}