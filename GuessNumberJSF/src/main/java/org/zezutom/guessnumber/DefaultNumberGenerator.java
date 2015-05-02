package org.zezutom.guessnumber;

import java.util.Random;

/**
 *
 * @author tom
 */
public class DefaultNumberGenerator implements NumberGenerator {

    @Override
    public int random(int from, int to) {
        return new Random().nextInt(to - from + 1) + from;
    }    
    
}
