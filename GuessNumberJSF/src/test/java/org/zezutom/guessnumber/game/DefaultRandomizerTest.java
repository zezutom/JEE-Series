package org.zezutom.guessnumber.game;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 *
 * @author tom
 */
public class DefaultRandomizerTest {

    Randomizer generator = new DefaultRandomizer();
    
    @Test
    public void random_number_is_with_boundaries() {        
        int i = 0, j = 10000;
        while (i < j) {
            final int x = generator.randomize(i, j);
            assertTrue(x + " is not within expected boundaries: [" + i + ", " + j + "]", x >= i && x <= j);
            i++; j--;
        }        
    }
}
