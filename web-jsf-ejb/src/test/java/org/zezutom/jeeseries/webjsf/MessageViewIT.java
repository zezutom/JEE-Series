package org.zezutom.jeeseries.webjsf;

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
import org.zezutom.jeeseries.webjsf.model.Message;
import org.zezutom.jeeseries.webjsf.service.MessageFacade;

@RunWith(Arquillian.class)
public class MessageViewIT {
    
    private static final String WEBAPP_SRC = "src/main/webapp";
        
    @Drone
    private WebDriver browser;
    
    @ArquillianResource
    private URL deploymentURL;
    
    @FindBy(css = "input[type=text]")
    private WebElement textField;

    @FindBy(css = "input[type=submit]")
    private WebElement submitButton;
    
    @FindBy(id = "numberOfMessages")
    private WebElement outputText;
    
    // Runs in a client mode
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "web-jsf-ejb.war")                
                .addPackage(Message.class.getPackage())
                .addPackage(MessageFacade.class.getPackage())
                .addPackage(MessageView.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC, "result.xhtml")) 
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML(new File(WEBAPP_SRC + "/WEB-INF/web.xml"));
    }
    
    @Test
    public void sentMessageShouldCount() {
        browser.get(deploymentURL.toString());
        textField.sendKeys("Test message");
        submitButton.click();
        Assert.assertEquals("1", outputText.getText());        
    }
}
