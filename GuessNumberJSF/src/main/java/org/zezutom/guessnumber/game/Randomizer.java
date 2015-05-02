package org.zezutom.guessnumber.game;

/**
 * Generates a random number within given boundaries.
 * 
 * @author tom
 */
public interface Randomizer {
    
    /**
     * Generates a random number in a given interval, inclusive 
     * 
     * @param from
     * @param to
     * @return x, where: from <= x <= to
     */
    int randomize(int from, int to);
}
