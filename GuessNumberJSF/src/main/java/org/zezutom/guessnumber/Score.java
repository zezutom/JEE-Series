package org.zezutom.guessnumber;

/**
 * 
 * @author tom
 */
public class Score {

    private final int answer;
    
    private final int attempts;
    
    private final boolean pass;

    public Score(int answer, int attempts, boolean pass) {
        this.answer = answer;
        this.attempts = attempts;
        this.pass = pass;
    }

    public int getAnswer() {
        return answer;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isPass() {
        return pass;
    }
}
