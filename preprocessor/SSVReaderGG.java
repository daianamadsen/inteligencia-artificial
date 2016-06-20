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

import java.io.FileNotFoundException;

/**
 * SSVReaderGG Semicolon Separated Values Reader
 * Lee opiniones de películas separadas por punto y coma.
 * Clasifica los múltiples generos de una película en un único grupo de géneros.
 * 
 * @author Juan Valacco
 * @author Daiana Madsen
 */
public class SSVReaderGG extends SSVReaderIG {
    
    private Groups genresGroups;
    
    //Grupos predefinidos:
    protected static final String AAACF = "AAACF";
    protected static final String ATCWDS = "ATCWDS";
    protected static final String HM = "HM";
    protected static final String CRM = "CRM";
    protected static final String DMR = "DMR";
    protected static final String D = "D";
    
    public SSVReaderGG(String path) throws FileNotFoundException {
        super(path);
        this.genresGroups = new Groups();
        this.genresGroups.addAttribute(AAACF, new ArrayList(Arrays.asList("Action", "Animation", "Adventure", "Children", "Fantasy")));
        this.genresGroups.addAttribute(ATCWDS, new ArrayList(Arrays.asList("Action", "Thriller", "Crime", "War", "Drama", "Sci-Fi")));
        this.genresGroups.addAttribute(HM, new ArrayList(Arrays.asList("Horror", "Mystery")));
        this.genresGroups.addAttribute(CRM, new ArrayList(Arrays.asList("Comedy", "Romance", "Musical")));
        this.genresGroups.addAttribute(DMR, new ArrayList(Arrays.asList("Drama", "Mystery", "Romance")));
        this.genresGroups.addAttribute(D, new ArrayList(Arrays.asList("Documentary")));
    }

    //Se guarda el grupo de genero, en lugar de guardar cada genero individual
    @Override
    protected String parseGenres(String genre) {
        ArrayList<String> genres = new ArrayList<String>(Arrays.asList(genre.split("\\|")));
        genre = getClasificacion(genres);  
        super.header.addItem(SSVReaderIG.GENRE, genre);
        return genre;
    }
    
    //Para clasificar varios generos en un solo grupo,
    //se buscar el grupo con el que tenga más coincidencias.
    private String getClasificacion(ArrayList<String> genres) {
        Iterator<Header.Buffer> groups = genresGroups.iterator();
        int countMax = 0;
        String groupName = SSVReaderIG.INDEFINIDO;
        while (groups.hasNext()) {
            Iterator<String> genre = genres.iterator();
            Header.Buffer group = groups.next();
            int count = 0;
            while (genre.hasNext()) {
                if (group.contains(genre.next())) {
                    count++;
                }
            }
            if (countMax <= count) {
                //Si la cantidad de ocurrencias es igual para dos grupos, se elige al azar entre ambos grupos
                if ((countMax < count) || (Math.random() < 0.5)) {
                    countMax = count;
                    groupName = group.getName();
                }
            }
        }
        return groupName;
    }

    //DEVUELVE DATOS: 
    //Se devuelve cada opinion con sus atributos
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList();
        Iterator<Opinion> i = opinions.iterator();
        while (i.hasNext()) {
            data.add(i.next().toString());
        }
        return data;
    }

    //DEVUELVE HEADER: 
    //Se devuelve el encabezado
    @Override
    public ArrayList<String> getHeader() {
        return this.header.getHeader();
    }
    
}