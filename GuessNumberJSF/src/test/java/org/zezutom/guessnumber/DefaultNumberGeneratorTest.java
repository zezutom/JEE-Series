package org.zezutom.guessnumber;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 *
 * @author tom
 */
public class DefaultNumberGeneratorTest {

    NumberGenerator generator = new DefaultNumberGenerator();
    
    @Test
    public void random_number_is_with_boundaries() {        
        int i = 0, j = 10000;
        while (i < j) {
            final int x = generator.random(i, j);
            assertTrue(x + " is not within expected boundaries: [" + i + ", " + j + "]", x >= i && x <= j);
            i++; j--;
        }        
    }
}
