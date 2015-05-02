package org.zezutom.guessnumber;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.zezutom.guessnumber.game.DefaultNumberGenerator;
import org.zezutom.guessnumber.game.Game;
import org.zezutom.guessnumber.game.NumberGenerator;
import org.zezutom.guessnumber.game.Range;

/**
 *
 * @author tom
 */
@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean implements Serializable {
    
    private final Game game;
    
    private Integer guess;

    private Integer attemptsLeft;
    
    public UserBean() {
        final NumberGenerator generator = new DefaultNumberGenerator();
        game = new Game(new Range(generator), generator);
        attemptsLeft = game.getMaxAttempts();
    }
    
    public Integer getGuess() {
        return guess;
    }

    public void setGuess(Integer guess) {
        this.guess = guess;
    }
    
    public Integer getMin() {
        return game.getRange().getMin();
    }
    
    public Integer getMax() {
        return game.getRange().getMax();
    }
    
    public Integer getAttemptsLeft() {
        return attemptsLeft;
    }
   
    public void actionGuessed() {
        if (attemptsLeft > 0)
            attemptsLeft--;   
    }
    
    public void resetGame() {
        //invalidate user session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();    
        attemptsLeft = 0;
        guess = null;
    }
    
    public String getResponse() {        
        if (guess == null)
            return ""; //throw new IllegalArgumentException("Please guess a number.");
        
        StringBuilder sb = new StringBuilder();
        final Game.Response response = game.guess(guess);
        
        switch(response) {
            case BINGO:
            case GAME_OVER:
                resetGame();
                // either a victory or a loss
                sb.append(response == Game.Response.BINGO ? 
                        "Yay, you nailed it!" : "Game over my friend.");
                break;
            default:
                // keep trying..
                sb.append("Nope, ")
                        .append(response == Game.Response.TOO_LOW ? 
                                "try a higher number." : "try a lower number");                
        }
        
        return sb.toString();
    }
    
}
