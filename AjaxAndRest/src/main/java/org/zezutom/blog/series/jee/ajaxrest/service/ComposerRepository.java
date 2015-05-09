package org.zezutom.blog.series.jee.ajaxrest.service;

import org.zezutom.blog.series.jee.ajaxrest.model.Composer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import org.zezutom.blog.series.jee.ajaxrest.model.Category;

/**
 *
 * @author tom
 */
@Stateless
public class ComposerRepository {

    private final Map<String, Composer> composers = new HashMap<>();

    public ComposerRepository() {
        for (Composer composer : new Composer[]{
            create("Johann Sebastian", "Bach", Category.BAROQUE),
            create("Arcangelo", "Corelli", Category.BAROQUE),
            create("George Frideric", "Handel", Category.BAROQUE),
            create("Henry", "Purcell", Category.BAROQUE),
            create("Jean-Philippe", "Rameau", Category.BAROQUE),
            create("Domenico", "Scarlatti", Category.BAROQUE),
            create("Antonio", "Vivaldi", Category.BAROQUE),
            create("Ludwig van", "Beethoven", Category.CLASSICAL),
            create("Johannes", "Brahms", Category.CLASSICAL),
            create("Francesco", "Cavalli", Category.CLASSICAL),
            create("Fryderyk Franciszek", "Chopin", Category.CLASSICAL),
            create("Antonin", "Dvorak", Category.CLASSICAL),
            create("Franz Joseph", "Haydn", Category.CLASSICAL),
            create("Gustav", "Mahler", Category.CLASSICAL),
            create("Wolfgang Amadeus", "Mozart", Category.CLASSICAL),
            create("Johann", "Pachelbel", Category.CLASSICAL),
            create("Gioachino", "Rossini", Category.CLASSICAL),
            create("Dmitry", "Shostakovich", Category.CLASSICAL),
            create("Richard", "Wagner", Category.CLASSICAL),
            create("Louis-Hector", "Berlioz", Category.ROMANTIC),
            create("Georges", "Bizet", Category.ROMANTIC),
            create("Cesar", "Cui", Category.ROMANTIC),
            create("Claude", "Debussy", Category.ROMANTIC),
            create("Edward", "Elgar", Category.ROMANTIC),
            create("Gabriel", "Faure", Category.ROMANTIC),
            create("Cesar", "Franck", Category.ROMANTIC),
            create("Edvard", "Grieg", Category.ROMANTIC),
            create("Nikolay", "Rimsky-Korsakov", Category.ROMANTIC),
            create("Franz Joseph", "Liszt", Category.ROMANTIC),
            create("Felix", "Mendelssohn", Category.ROMANTIC),
            create("Giacomo", "Puccini", Category.ROMANTIC),
            create("Sergei", "Rachmaninoff", Category.ROMANTIC),
            create("Camille", "Saint-Saens", Category.ROMANTIC),
            create("Franz", "Schubert", Category.ROMANTIC),
            create("Robert", "Schumann", Category.ROMANTIC),
            create("Jean", "Sibelius", Category.ROMANTIC),
            create("Bedrich", "Smetana", Category.ROMANTIC),
            create("Richard", "Strauss", Category.ROMANTIC),
            create("Pyotr Il'yich", "Tchaikovsky", Category.ROMANTIC),
            create("Guiseppe", "Verdi", Category.ROMANTIC),
            create("Bela", "Bartok", Category.POST_ROMANTIC),
            create("Leonard", "Bernstein", Category.POST_ROMANTIC),
            create("Benjamin", "Britten", Category.POST_ROMANTIC),
            create("John", "Cage", Category.POST_ROMANTIC),
            create("Aaron", "Copland", Category.POST_ROMANTIC),
            create("George", "Gershwin", Category.POST_ROMANTIC),
            create("Sergey", "Prokofiev", Category.POST_ROMANTIC),
            create("Maurice", "Ravel", Category.POST_ROMANTIC),
            create("Igor", "Stravinsky", Category.POST_ROMANTIC),
            create("Carl", "Orff", Category.POST_ROMANTIC)
        }) {
            composers.put(composer.getId(), composer);
        }
    }

    private Composer create(String firstName, String lastName, Category category) {
        return new Composer(firstName, lastName, category);
    }
    
    public List<Composer> findAll() {
         return composers.values()
                .stream()                
                .sorted((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()))
                .collect(Collectors.toList());
    }
}
