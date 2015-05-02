package org.zezutom.guessnumber;

import java.util.Random;

/**
 * A numeric range.
 * 
 * @author tom
 */
public class Range {

    public static final int MIN = 0;
    
    public static final int MAX = 1000;
    
    public static final int MIN_GAP = 10;
    
    private final int min;
    
    private final int max;
    
    public Range(NumberGenerator generator) {
        max = generator.random(MIN + MIN_GAP, MAX);
        min = generator.random(MIN, max - MIN_GAP);
    }

    /**
     * Generates a random number in a given interval, inclusive 
     * 
     * @param from
     * @param to
     * @return x, where: from <= x <= to
     */
    private int random(int from, int to) {
        return new Random().nextInt(to - from + 1) + from;
    }
    
    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }            
}
