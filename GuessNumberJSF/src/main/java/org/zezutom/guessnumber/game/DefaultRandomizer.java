package org.zezutom.guessnumber.game;

import java.util.Random;

/**
 *
 * @author tom
 */
public class DefaultRandomizer implements Randomizer {

    @Override
    public int randomize(int from, int to) {
        return new Random().nextInt(to - from + 1) + from;
    }    
    
}
