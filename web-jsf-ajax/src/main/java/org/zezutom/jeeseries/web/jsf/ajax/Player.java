package org.zezutom.jeeseries.web.jsf.ajax;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "PlayerBean")
@SessionScoped
public class Player implements Serializable {
    
    public static final int MIN = 0;
    
    public static final int MAX = 10;
    
    public static final String SUCCESS = "You nailed it!";
    
    public static final String FAILURE = "Keep trying!";
    
    public static final String GAME_OVER = "Game over!";
    
    public static final int MAX_ATTEMPTS = 3;
    
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    
    private Integer randomInt;
    
    private Integer guessedInt;
    
    private int attemptsLeft = MAX_ATTEMPTS;
        
    public Player() {
        init();
    }
    
    private void init() {
        randomInt = ThreadLocalRandom.current().nextInt(MAX);
        LOGGER.log(Level.INFO, "Number to guess: {0}", randomInt);
        attemptsLeft = MAX_ATTEMPTS;
        guessedInt = null;
    }

    public void resetGame() {
        init();
        invalidateSession();
    }

    public int getMin() {
        return MIN;
    }

    public int getMax() {
        return MAX;
    }
                    
    public Integer getGuessedInt() {
        return guessedInt;
    }

    public void setGuessedInt(Integer guessedInt) {
        this.guessedInt = guessedInt;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void actionGuessed() {
        if (attemptsLeft > 0) attemptsLeft--;
    }
    
    public String getResponse() {
        if (guessedInt == null) return "";
        
        if (attemptsLeft <= 0) {
            resetGame();
            return GAME_OVER;
        }       
        
        
        String response;
        if (randomInt.compareTo(guessedInt) == 0) {
            response = SUCCESS;
            invalidateSession();
        } else {
            response = FAILURE;
        }
        return response;
    }         
    
    private void invalidateSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
            httpSession.invalidate();    
        }
    }    
}
