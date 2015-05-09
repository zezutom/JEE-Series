package org.zezutom.blog.series.jee.guessnumber.game;

import org.zezutom.blog.series.jee.guessnumber.game.Range;
import org.zezutom.blog.series.jee.guessnumber.game.RangeGenerator;
import org.zezutom.blog.series.jee.guessnumber.game.DefaultRandomizer;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 *
 * @author tom
 */
public class RangeTest {
        
    @Test
    public void range_within_boundaries() {       
        final RangeGenerator generator = 
                new RangeGenerator(new DefaultRandomizer());
        
        for (int i = 0; i < 10000; i++)
            verify(generator.generate());        
    }
    
    private void verify(Range range) {
        final int min = range.getMin();
        final int max = range.getMax();        
        assertTrue("Min too low: " + min + " > " + Range.MIN, min >= Range.MIN);
        assertTrue("Max too high: " + max + " > " + Range.MAX, max <= Range.MAX);
        assertTrue("Min too high: min: " + min + ", max: " + max, min <= max - Range.MIN_GAP);
    }
}
