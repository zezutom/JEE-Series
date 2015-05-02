package org.zezutom.guessnumber;

/**
 * Guess a number "I'm thinking of" in a given range.
 * 
 * @author tom
 */
public class Game {
   
    private final Range range;
    
    private  final int answer;
        
    private final int maxAttempts;
            
    private int attempts;
            
    public enum Response {
        BINGO, TOO_LOW, TOO_HIGH, GAME_OVER;
    }

    public Game(Range range, NumberGenerator generator) {
        this.range = range;
        answer = generator.random(range.getMin(), range.getMax());        
        maxAttempts = (int) (Math.log(range.getMax() - range.getMin()) / Math.log(2)) + 1;
    }
    
    public void reset() {
        attempts = 0;
    }
    
    public int getMaxAttempts() {
        return maxAttempts;
    }

    public Range getRange() {
        return range;
    }
        
    public Response guess(int guess) {
        if (attempts++ > maxAttempts) 
            return Response.GAME_OVER;        
        else if (guess == answer) 
            return Response.BINGO;
        else 
            return (guess > answer) ? Response.TOO_HIGH : Response.TOO_LOW;
    }
    
    public Score getScore() {
        return new Score(answer, attempts, attempts <= maxAttempts);
    }

    @Override
    public String toString() {
        return String.format("Range: [%d, %d], Answer: %d, Max attempts: %d", 
                range.getMin(), range.getMax(), answer, maxAttempts);
    }
    
    
}
