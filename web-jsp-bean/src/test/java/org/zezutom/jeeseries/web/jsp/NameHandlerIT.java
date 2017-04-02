package org.zezutom.jeeseries.web.jsp;

import org.zezutom.jeeseries.web.jsp.NameHandler;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
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
public class NameHandlerIT {
    
    private static final String WEBAPP_SRC = "src/main/webapp";
    
    @Drone
    private WebDriver browser;
    
    @ArquillianResource
    private URL deploymentURL;
    
    @FindBy(css = "input[type=text]")
    private WebElement textField;

    @FindBy(css = "input[type=submit]")
    private WebElement submitButton;
    
    @FindBy(tagName = "h1")
    private WebElement welcomeMessage;
    
    // Runs in a client mode
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "helloweb.war")
                .addClass(NameHandler.class)
                .addAsWebResource(new File(WEBAPP_SRC, "index.jsp"))
                .addAsWebResource(new File(WEBAPP_SRC, "response.jsp"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test
    public void shouldSetName() {
        browser.get(deploymentURL + "index.jsp");
        textField.sendKeys("Test");
        submitButton.click();
        Assert.assertEquals("Hello Test!", welcomeMessage.getText());
    }
}
