package org.zezutom.blog.series.jee.ajaxrest.service;

import org.zezutom.blog.series.jee.ajaxrest.model.Composer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import org.zezutom.blog.series.jee.ajaxrest.model.Genre;

/**
 *
 * @author tom
 */
@Stateless
public class ComposerRepository {

    private final Map<String, Composer> composers = new HashMap<>();

    public ComposerRepository() {
        for (Composer composer : new Composer[]{
            create("Johann Sebastian", "Bach", Genre.BAROQUE),
            create("Arcangelo", "Corelli", Genre.BAROQUE),
            create("George Frideric", "Handel", Genre.BAROQUE),
            create("Henry", "Purcell", Genre.BAROQUE),
            create("Jean-Philippe", "Rameau", Genre.BAROQUE),
            create("Domenico", "Scarlatti", Genre.BAROQUE),
            create("Antonio", "Vivaldi", Genre.BAROQUE),
            create("Ludwig van", "Beethoven", Genre.CLASSICAL),
            create("Johannes", "Brahms", Genre.CLASSICAL),
            create("Francesco", "Cavalli", Genre.CLASSICAL),
            create("Fryderyk Franciszek", "Chopin", Genre.CLASSICAL),
            create("Antonin", "Dvorak", Genre.CLASSICAL),
            create("Franz Joseph", "Haydn", Genre.CLASSICAL),
            create("Gustav", "Mahler", Genre.CLASSICAL),
            create("Wolfgang Amadeus", "Mozart", Genre.CLASSICAL),
            create("Johann", "Pachelbel", Genre.CLASSICAL),
            create("Gioachino", "Rossini", Genre.CLASSICAL),
            create("Dmitry", "Shostakovich", Genre.CLASSICAL),
            create("Richard", "Wagner", Genre.CLASSICAL),
            create("Louis-Hector", "Berlioz", Genre.ROMANTIC),
            create("Georges", "Bizet", Genre.ROMANTIC),
            create("Cesar", "Cui", Genre.ROMANTIC),
            create("Claude", "Debussy", Genre.ROMANTIC),
            create("Edward", "Elgar", Genre.ROMANTIC),
            create("Gabriel", "Faure", Genre.ROMANTIC),
            create("Cesar", "Franck", Genre.ROMANTIC),
            create("Edvard", "Grieg", Genre.ROMANTIC),
            create("Nikolay", "Rimsky-Korsakov", Genre.ROMANTIC),
            create("Franz Joseph", "Liszt", Genre.ROMANTIC),
            create("Felix", "Mendelssohn", Genre.ROMANTIC),
            create("Giacomo", "Puccini", Genre.ROMANTIC),
            create("Sergei", "Rachmaninoff", Genre.ROMANTIC),
            create("Camille", "Saint-Saens", Genre.ROMANTIC),
            create("Franz", "Schubert", Genre.ROMANTIC),
            create("Robert", "Schumann", Genre.ROMANTIC),
            create("Jean", "Sibelius", Genre.ROMANTIC),
            create("Bedrich", "Smetana", Genre.ROMANTIC),
            create("Richard", "Strauss", Genre.ROMANTIC),
            create("Pyotr Il'yich", "Tchaikovsky", Genre.ROMANTIC),
            create("Guiseppe", "Verdi", Genre.ROMANTIC),
            create("Bela", "Bartok", Genre.POST_ROMANTIC),
            create("Leonard", "Bernstein", Genre.POST_ROMANTIC),
            create("Benjamin", "Britten", Genre.POST_ROMANTIC),
            create("John", "Cage", Genre.POST_ROMANTIC),
            create("Aaron", "Copland", Genre.POST_ROMANTIC),
            create("George", "Gershwin", Genre.POST_ROMANTIC),
            create("Sergey", "Prokofiev", Genre.POST_ROMANTIC),
            create("Maurice", "Ravel", Genre.POST_ROMANTIC),
            create("Igor", "Stravinsky", Genre.POST_ROMANTIC),
            create("Carl", "Orff", Genre.POST_ROMANTIC)
        }) {
            composers.put(composer.getId(), composer);
        }
    }

    private Composer create(String firstName, String lastName, Genre category) {
        return new Composer(firstName, lastName, category);
    }
    
    public List<Composer> findAll() {
         return composers.values()
                .stream()                
                .sorted((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()))
                .collect(Collectors.toList());
    }
}
