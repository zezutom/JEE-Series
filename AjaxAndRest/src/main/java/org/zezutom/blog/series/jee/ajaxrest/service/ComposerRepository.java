package org.zezutom.blog.series.jee.ajaxrest.service;

import java.util.Collections;
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

    public static final int PAGE_SIZE = 5;
    
    private final Map<String, Composer> composers = new HashMap<>();

    public ComposerRepository() {
        for (Composer composer : new Composer[]{
            create("Johann Sebastian", "Bach", 1685, 1750,
                    Genre.BAROQUE,
                    "http://upload.wikimedia.org/wikipedia/commons/a/ac/Bach_face.jpg",
                    "Toccata and Fugue in D minor, BWV 565", "St Matthew Passion"),
            create("Arcangelo", "Corelli", 1653, 1713, 
                    Genre.BAROQUE, 
                    "http://upload.wikimedia.org/wikipedia/commons/2/20/Corelli.jpg",
                    "Opus 1: 12 sonate da chiesa", "Opus 2: 12 sonate da camera"),
            create("George Frideric", "Handel", 1685, 1759, 
                    Genre.BAROQUE, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/George_Frideric_Handel_by_Balthasar_Denner.jpg/198px-George_Frideric_Handel_by_Balthasar_Denner.jpg",
                    "Rinaldo", "Alcina"),
            create("Henry", "Purcell", 1659, 1695, 
                    Genre.BAROQUE, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Henry_Purcell.jpg/142px-Henry_Purcell.jpg",
                    "Dido and Aeaneas", "King Arthur"),
            create("Jean-Philippe", "Rameau", 1683, 1764, 
                    Genre.BAROQUE, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Jean-Philippe_Rameau_by_Jean-Jacques_Caffieri_-_20080203-02.jpg/240px-Jean-Philippe_Rameau_by_Jean-Jacques_Caffieri_-_20080203-02.jpg",
                    "Les Indes galantes", "Platée"),
            create("Domenico", "Scarlatti", 1685, 1757,
                    Genre.BAROQUE,
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Domenico_Scarlatti_%28detalle%29.jpg/163px-Domenico_Scarlatti_%28detalle%29.jpg",
                    "Sonata in A major, K 208, L 238: Adagio e cantabile", "Sonata in D minor, K 1, L 366: Allegro"),
            create("Antonio", "Vivaldi", 1678, 1741, 
                    Genre.BAROQUE, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Vivaldi.jpg/201px-Vivaldi.jpg",
                    "The Four Seasons", "Gloria"),
            create("Ludwig van", "Beethoven", 1770, 1827, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/b/b4/Ludwig_van_Beethoven.jpg",
                    "Bagatelle in A minor", "Piano Sonata No.14"),
            create("Johannes", "Brahms", 1833, 1897, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Johannes_Brahms.gif/166px-Johannes_Brahms.gif",
                    "Hungarian Dances", "A German Requiem"),
            create("Francesco", "Cavalli", 1602, 1676, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/e/ef/Francesco_Cavalli.png",
                    "La Calisto", "Giasone"),
            create("Fryderyk Franciszek", "Chopin", 1810, 1849, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Fr%C3%A9d%C3%A9ric_Chopin_by_Bisson%2C_1849.png/173px-Fr%C3%A9d%C3%A9ric_Chopin_by_Bisson%2C_1849.png",
                    "Preludes", "Waltzes"),
            create("Antonin", "Dvorak", 1841, 1904, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Dvorak1.jpg/152px-Dvorak1.jpg",
                    "Symphony No. 9", "Rusalka"),
            create("Franz Joseph", "Haydn", 1732, 1809, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Joseph_Haydn.jpg/190px-Joseph_Haydn.jpg",
                    "The Creation", "Symphony No. 94"),
            create("Gustav", "Mahler", 1860, 1911, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Mahler-signature.svg/320px-Mahler-signature.svg.png",
                    "Symphony no. 2", "Symphony no. 5"),
            create("Wolfgang Amadeus", "Mozart", 1756, 1791, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Croce-Mozart-Detail.jpg/185px-Croce-Mozart-Detail.jpg",
                    "Eine kleine Nachtmusik", "Don Giovanni"),
            create("Johann", "Pachelbel", 1653, 1706, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Pachelbels_tomb.jpg/194px-Pachelbels_tomb.jpg",
                    "Pachelbel", "Christmas Canon"),
            create("Gioachino", "Rossini", 1792, 1868, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/e/e4/Rossini_c._1850-litho-F_Perrin.jpeg",
                    "The Barber of Seville", "La Cenerentola"),
            create("Dmitry", "Shostakovich", 1906, 1975, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Dmitri1.jpg/157px-Dmitri1.jpg",
                    "Symphony No. 7", "Lady Macbeth of the Mtsensk District"),
            create("Richard", "Wagner", 1813, 1883, 
                    Genre.CLASSICAL, 
                    "http://upload.wikimedia.org/wikipedia/commons/9/9d/RichardWagner.jpg",
                    "Der Ring des Nibelungen", "Tristan und Isolde"),
            create("Louis-Hector", "Berlioz", 1803, 1869, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/e/ee/Hector_Berlioz_Crop.jpg",
                    "Symphonie fantastique", "La damnation de Faust"),
            create("Georges", "Bizet", 1838, 1875, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Georges_bizet.jpg/174px-Georges_bizet.jpg",
                    "Carmen", "L'Arlésienne"),
            create("Cesar", "Cui", 1835, 1918, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/d/d0/Cesar_cui.jpg",
                    "Feast in Time of Plague", "Prisoner of the Caucasus"),
            create("Claude", "Debussy", 1862, 1918, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Claude_Debussy_ca_1908%2C_foto_av_F%C3%A9lix_Nadar.jpg/178px-Claude_Debussy_ca_1908%2C_foto_av_F%C3%A9lix_Nadar.jpg",
                    "Deux arabesques", "La mer"),
            create("Edward", "Elgar", 1857, 1934, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Edward_Elgar.jpg/240px-Edward_Elgar.jpg",
                    "Pomp and Circumstance Marches", "Salut d'Amour"),
            create("Cesar", "Franck", 1822, 1890, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/C%C3%A9sar_Franck_by_Pierre_Petit.jpg/187px-C%C3%A9sar_Franck_by_Pierre_Petit.jpg",
                    "Symphony in D minor", "Panis Angelicus"),
            create("Edvard", "Grieg", 1843, 1907, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Edvard_Grieg_%281888%29_by_Elliot_and_Fry_-_02.jpg/158px-Edvard_Grieg_%281888%29_by_Elliot_and_Fry_-_02.jpg",
                    "In the Hall of the Mountain King", "Peer Gynt"),
            create("Nikolay", "Rimsky-Korsakov", 1844, 1908, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Rimsky-Korsakov_Serow_crop.png/218px-Rimsky-Korsakov_Serow_crop.png",
                    "Scheherazade", "Flight of the Bumblebee"),
            create("Franz Joseph", "Liszt", 1811, 1886, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/c/cd/Ary_Scheffer_-_Franz_Liszt.jpg",
                    "Hungarian Rhapsodies", "La campanella"),
            create("Felix", "Mendelssohn", 1809, 1847, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Mendelssohn_Bartholdy.jpg/180px-Mendelssohn_Bartholdy.jpg",
                    "Wedding March", "Elijah"),
            create("Giacomo", "Puccini", 1858, 1924, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/9/9b/GiacomoPuccini.jpg",
                    "Tosca", "La bohème"),
            create("Sergei", "Rachmaninoff", 1873, 1943, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/b/be/Sergei_Rachmaninoff_cph.3a40575.jpg",
                    "Piano Concerto No. 2", "Vocalise"),
            create("Camille", "Saint-Saens", 1835, 1921, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/en/thumb/9/98/Saint-Sa%C3%ABns-circa-1880.jpg/142px-Saint-Sa%C3%ABns-circa-1880.jpg",
                    "The Carnival of the Animals", "Samson and Delilah"),
            create("Franz", "Schubert", 1797, 1828, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/0/0d/Franz_Schubert_by_Wilhelm_August_Rieder_1875.jpg",
                    "Winterreise", "Erlkönig, D. 328"),
            create("Robert", "Schumann", 1810, 1856, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Schumann-photo1850.jpg/176px-Schumann-photo1850.jpg",
                    "Dichterliebe", "Fantasiestücke"),
            create("Jean", "Sibelius", 1865, 1957, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/a/aa/Jean_sibelius.jpg",
                    "Sibelius", "Finlandia"),
            create("Bedrich", "Smetana", 1824, 1884, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/b/b3/Bedrich_Smetana.jpg",
                    "Má vlast", "The Bartered Bride"),
            create("Richard", "Strauss", 1864, 1949, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/c/c8/Max_Liebermann_Bildnis_Richard_Strauss.jpg",
                    "Der Rosenkavalier", "Salome"),
            create("Pyotr Il'yich", "Tchaikovsky", 1840, 1893, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Portr%C3%A4t_des_Komponisten_Pjotr_I._Tschaikowski_%281840-1893%29.jpg/187px-Portr%C3%A4t_des_Komponisten_Pjotr_I._Tschaikowski_%281840-1893%29.jpg",
                    "The Nutcracker", "Swan Lake"),
            create("Guiseppe", "Verdi", 1813, 1901, 
                    Genre.ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Verdi.jpg/175px-Verdi.jpg",
                    "La traviata", "Aida"),
            create("Bela", "Bartok", 1881, 1945, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/6/66/Bart%C3%B3k_B%C3%A9la_1927.jpg",
                    "Mikrokosmos", "Bluebeard's Castle"),
            create("Leonard", "Bernstein", 1918, 1990, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/6/6a/Leonard_Bernstein_by_Jack_Mitchell.jpg",
                    "Candide", "Chichester Psalms"),
            create("Benjamin", "Britten", 1913, 1976, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Benjamin_Britten%2C_London_Records_1968_publicity_photo_for_Wikipedia.jpg/198px-Benjamin_Britten%2C_London_Records_1968_publicity_photo_for_Wikipedia.jpg",
                    "Peter Grimes", "The Turn of the Screw"),
            create("Aaron", "Copland", 1900, 1990, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Aaron_Copland_1970.JPG/182px-Aaron_Copland_1970.JPG",
                    "Rodeo", "Appalachian Spring"),
            create("George", "Gershwin", 1898, 1937, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/6/68/George_Gershwin_1937.jpg",
                    "Porgy and Bess", "Summertime"),
            create("Sergey", "Prokofiev", 1891, 1953, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Sergei_Prokofiev_02.jpg/222px-Sergei_Prokofiev_02.jpg",
                    "Peter and the Wolf", "Romeo and Juliet"),
            create("Maurice", "Ravel", 1875, 1937, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Maurice_Ravel_1925.jpg/181px-Maurice_Ravel_1925.jpg",
                    "Boléro", "L'enfant et les sortilèges"),
            create("Igor", "Stravinsky", 1882, 1971, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Igor_Stravinsky_LOC_32392u.jpg/175px-Igor_Stravinsky_LOC_32392u.jpg",
                    "The Rite of Spring", "The Firebird"),
            create("Carl", "Orff", 1895, 1982, 
                    Genre.POST_ROMANTIC, 
                    "http://upload.wikimedia.org/wikipedia/commons/e/e8/Carl_Orff.jpg",
                    "Carmina Burana", "Fortune, Empress of the World: I. O Fortuna")
        }) {
            composers.put(composer.getId(), composer);
        }
    }

    private Composer create(String firstName, String lastName, 
            int born, int died, Genre genre, String thumbnail, 
            String... masterpieces) {
        return new Composer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBorn(born)
                .setDied(died)
                .setGenre(genre)
                .setThumbnail(thumbnail)
                .setMasterpieces(masterpieces)
                .build();
    }

    public List<Composer> findAll(int page) {
        final int start = page * PAGE_SIZE;
        final int finish = start + PAGE_SIZE;
        
        if (start < 0 || finish <= 0 || finish >= composers.size()) 
            return Collections.EMPTY_LIST;        
        
        return composers.values()
                .stream()                                
                .sorted((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()))
                .collect(Collectors.toList())
                .subList(start, finish);
    }
}
