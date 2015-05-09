package org.zezutom.blog.series.jee.guessnumber.game;

/**
 * A helper class - pseudo random selection from predefined max range values.
 * 
 * @author tom
 */
public class RangeGenerator {

    // the higher the range the more difficult the game becomes
    private static final int[] RANGES = new int[] {10, 50, 100, 500, 1000};
    
    private final Randomizer randomizer;

    public RangeGenerator(Randomizer randomizer) {
        this.randomizer = randomizer;
    }
        
    public Range generate() {
        return new Range(RANGES[randomizer.randomize(0, RANGES.length - 1)]);               
    }
}
