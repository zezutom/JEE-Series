package org.zezutom.jeeseries.web.jsf.ajax;

import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author tom
 */
public class PlayerTest {
    
    private final Player player = new Player();
         
    @Test
    public void shouldHaveAttemptsLeftByDefault() {
        Assert.assertTrue(player.getAttemptsLeft() > 0);
    }
    
    @Test
    public void shouldNotGoBeyondMaxAllowedAttempts() {
        // Go one attempt way too far
        for (int i = 0; i <= player.getAttemptsLeft() + 1; i++) {
            player.actionGuessed();
        }
        
        // Attempts left should remain at zero
        Assert.assertTrue(player.getAttemptsLeft() == 0);
    }
    
    @Test
    public void shouldNotSetAnyResponseIfNoGuessHasBeenMade() {
        Assert.assertEquals("", player.getResponse());
    }
    
    @Test
    public void shouldEncourageToContinueDespiteFailure() {
        player.setGuessedInt(-10);
        Assert.assertEquals(Player.FAILURE, player.getResponse());
    }
    
    @Test
    public void shouldAnnounceSuccessfullGuess() {
        player.setGuessedInt(getSuccessfulGuess());
        Assert.assertEquals(Player.SUCCESS, player.getResponse());
    }
    
    @Test
    public void shouldResetGameWhenAllAttemptsExhausted() {
        while (player.getAttemptsLeft() > 0) {
            player.setGuessedInt(Integer.MIN_VALUE);
            player.actionGuessed();            
        }
        Assert.assertEquals(Player.GAME_OVER, player.getResponse());
    }
    
    private Integer getSuccessfulGuess() {
        Integer randomInt = null;
        try {
            // This is fragile. Is a package-private getter a better approach?
            Field randomIntField = Player.class.getDeclaredField("randomInt");
            randomIntField.setAccessible(true);
            randomInt = (Integer) randomIntField.get(player);
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException ex) {
            Assert.fail(ex.getMessage());
        }
        return randomInt;
    }
}
