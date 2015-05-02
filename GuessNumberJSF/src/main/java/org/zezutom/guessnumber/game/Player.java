package org.zezutom.guessnumber.game;

/**
 * A virtual player based on Divide and Conquer algorithm.
 * 
 * @author tom
 */
public class Player {
    
    public Score play(Game game) {
    
        final Range range = game.getRange();
        int min = range.getMin();
        int max = range.getMax();
        
        boolean keepTrying = true;
        
        do {
            int guess = (int) Math.ceil((max + min - 1) / 2d);
            
            switch(game.guess(guess)) {
                case BINGO:
                case GAME_OVER:
                    keepTrying = false;
                    break;
                case TOO_LOW:
                    min = guess + 1;
                    break;
                case TOO_HIGH:
                    max = guess - 1;
                    break;
                default:
                    throw new RuntimeException("Don't know what to do!");
            }
        } while (keepTrying);
                                        
        return game.getScore();
    }
}
