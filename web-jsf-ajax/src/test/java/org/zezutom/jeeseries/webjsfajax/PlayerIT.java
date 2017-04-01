package org.zezutom.jeeseries.webjsfajax;

import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
@RunAsClient
public class PlayerIT {

    static final String WEBAPP_SRC = "src/main/webapp";

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentURL;

    @FindBy(id = "attempts")
    private WebElement attemptsTextField;

    @FindBy(id = "gameForm:guessedInt")
    private WebElement guessedIntTextField;

    @FindBy(id = "gameForm:response")
    private WebElement responseTextField;

    @FindBy(id = "gameForm:submit")
    private WebElement submitButton;
    
    @FindBy(id = "gameForm:reset")
    private WebElement resetButton;    
        
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "web-jsf-ajax.war")
                .addPackage(Player.class.getPackage())
                .addAsWebResource(new File(WEBAPP_SRC, "duke.png"))
                .addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC, "template.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC + "/css", "main.css"))                
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML(new File(WEBAPP_SRC + "/WEB-INF/web.xml"));
    }

    @Test
    public void shouldStartNewGame() {
        browser.get(deploymentURL.toString());
        Assert.assertEquals(Integer.toString(Player.MAX_ATTEMPTS),
                attemptsTextField.getText());
    }

    @Test
    public void shouldHandleFailedAttempt() {
        browser.get(deploymentURL.toString());
        guessedIntTextField.sendKeys(Integer.toString(Integer.MIN_VALUE));
        submitButton.click();
        Graphene.waitAjax()
                .until()
                .element(responseTextField)
                .text().equalTo(Player.FAILURE);
        Assert.assertEquals(Integer.toString(Player.MAX_ATTEMPTS - 1),
                attemptsTextField.getText());
    }
    
    @Test
    public void shouldBeAbleToResetGame() {
        browser.get(deploymentURL.toString());     
        guessedIntTextField.sendKeys(Integer.toString(Integer.MIN_VALUE));
        submitButton.click();
        Graphene.waitAjax()
                .until()
                .element(responseTextField)
                .text().equalTo(Player.FAILURE);
        resetButton.click();
        Graphene.waitAjax()
                .until()
                .element(responseTextField)
                .text().equalTo("");
        Assert.assertEquals(Integer.toString(Player.MAX_ATTEMPTS),
                attemptsTextField.getText());        
    }
}
