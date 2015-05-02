 package org.zezutom.guessnumber;

import org.junit.Test;
 
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
 
/**
 *
 * @author tom
 */
public class GameTest {
            
    @Test
    public void correct_guess() {
        final int answer = 10;
        final Game game = getGame(answer);
        assertEquals(Game.Response.BINGO, game.guess(answer));
        verifyScore(game, true, answer);
    }
    
    @Test
    public void guess_too_low() {
        assertEquals(Game.Response.TOO_LOW, getGame(10).guess(5));
    }

    @Test
    public void guess_too_high() {
        assertEquals(Game.Response.TOO_HIGH, getGame(10).guess(20));
    }
  
    @Test
    public void game_over() {
        // 0 to 10 -> max 4 attempts, correct answer is 5
        Game game = getGame(0, 10, 5);
        
        // let's waste time and effort by applying a linear search
        for (int i = 0; i <= 4; i++)
            assertEquals(Game.Response.TOO_LOW, game.guess(i));
                
        // now we arrived at the correct answer, but it's too late
        assertEquals("All attempts should have been exhausted!", 
                Game.Response.GAME_OVER, game.guess(5));
        
        // check the score
        verifyScore(game, false, 5);
    }
    
    @Test
    public void max_attempts_based_on_binary_search() {
        
        // 2^3 = 8 -> max 4 attempts to guess a number between 0 and 10
        verifyMaxAttempts(10, 4);
       
        // 2^6 = 64 -> max 7 attempts to guess a number between 0 and 100
        verifyMaxAttempts(100, 7);
        
        // 2^8 = 256 -> max 9 attempts to guess a number between 0 and 300
        verifyMaxAttempts(300, 9);
    }
    
    private void verifyMaxAttempts(int count, int expected) {
        final int maxAttempts = getGame(0, count).getMaxAttempts();
        assertTrue("Max " + expected + 
            " attempts to guess a number between 0 and " + count + 
            ", but got " + maxAttempts, 
            expected == maxAttempts);
    }
    
    private void verifyScore(Game game, boolean pass, int answer) {
        Score score = game.getScore();
        assertNotNull("Oops, there is no score.", score);
        assertTrue((pass ? "Pass" : "Failure") + " expected.", score.isPass() == pass); 
        assertTrue("Wrong answer.", score.getAnswer() == answer);
    }
    
    private Game getGame(int answer) {        
        return new Game(getRange(0, 1000), getGenerator(answer));        
    }
    
    private Game getGame(int from, int to) {
        return getGame(from, to, to - 1);
    }

    private Game getGame(int from, int to, int answer) {
        return new Game(getRange(from, to), getGenerator(answer));
    }
    
    private NumberGenerator getGenerator(int answer) {
        NumberGenerator generator = mock(NumberGenerator.class);
        when(generator.random(anyInt(), anyInt())).thenReturn(answer);    
        return generator;
    }
    
    private Range getRange(int from, int to) {
        Range range = mock(Range.class);
        when(range.getMin()).thenReturn(from);
        when(range.getMax()).thenReturn(to);        
        return range;
    }
}
