package org.zezutom.guessnumber.game;

/**
 * Generates a random number within given boundaries.
 * 
 * @author tom
 */
public interface NumberGenerator {
    
    /**
     * Generates a random number in a given interval, inclusive 
     * 
     * @param from
     * @param to
     * @return x, where: from <= x <= to
     */
    int random(int from, int to);
}
