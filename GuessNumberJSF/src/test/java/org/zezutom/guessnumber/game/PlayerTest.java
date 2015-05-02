package org.zezutom.guessnumber.game;

import java.util.logging.Logger;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author tom
 */
public class PlayerTest {

    private final static Logger logger = Logger.getLogger(PlayerTest.class.getName());
    
    private final Player player = new Player();
    
    @Test
    public void success() {
        verifyScore(player.play(getGame(Game.Response.BINGO)), true);
    }
    
    @Test
    public void failure() {
        verifyScore(player.play(getGame(Game.Response.GAME_OVER)), false); 
    }
    
    @Test
    public void play_for_real() {
        
        // a set of real games
        for (int i = 0; i < 10; i++) {
            final NumberGenerator generator = new DefaultNumberGenerator();
            final Game game = new Game(new Range(generator), generator);            
            logger.info(game.toString());
            
            // a series of attempts to solve the given game            
            final int total = 1000;
            
            // keep track of the best score and failures
            int bestScore = Integer.MAX_VALUE, failures = 0;
            
            for (int j = 0; j < total; j++) {
                Score score = player.play(game);
                if (score.isPass()) {
                    final int attempts = score.getAttempts();
                    
                    // no cheating!
                    assertTrue("Max attempts exceeded.", attempts <= game.getMaxAttempts());                    
                    
                    final int answer = score.getAnswer();
                    final Range range = game.getRange();
                    
                    assertTrue("The answer is below the lower bound.", answer >= range.getMin());
                    assertTrue("The answer is above the upper bound.", answer <= range.getMax());
                    
                    if (attempts < bestScore)
                        bestScore = attempts;
                } else 
                    failures++;
                game.reset();
            }
            logger.info(String.format("Best score: %s, Pass: %d out of %d", 
                    bestScore == Integer.MAX_VALUE ? '-' : bestScore, (total - failures), total));
            
            // verify there are no failures
            assertTrue("100% success rate expected.", failures == 0);
            
            // no cheating!
            assertTrue("Max attempts exceeded.", bestScore <= game.getMaxAttempts());
        }
    }
    
    private Game getGame(Game.Response response) {
        Game game = mock(Game.class);
        when(game.guess(anyInt())).thenReturn(response);
        
        Range range = mock(Range.class);
        when(range.getMin()).thenReturn(0);
        when(range.getMax()).thenReturn(10);
        
        when(game.getRange()).thenReturn(range);
        
        Score score = mock(Score.class);
        when(score.getAnswer()).thenReturn(10);
        when(score.isPass()).thenReturn(response == Game.Response.BINGO);
        
        when(game.getScore()).thenReturn(score);
        
        return game;
    }
    
    private void verifyScore(Score score, boolean pass) {
        assertNotNull("Oops, there is no score.", score);
        assertTrue((pass ? "Pass" : "Failure") + " expected.", score.isPass() == pass);
    }
    
}
