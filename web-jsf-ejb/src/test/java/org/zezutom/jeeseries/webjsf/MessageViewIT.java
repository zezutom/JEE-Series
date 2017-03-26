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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.zezutom.jeeseries.webjsf.model.Message;
import org.zezutom.jeeseries.webjsf.service.AbstractFacade;
import org.zezutom.jeeseries.webjsf.service.MessageFacade;

@RunWith(Arquillian.class)
public class MessageViewIT {
    
    private static final String WEBAPP_SRC = "src/main/webapp";
    
    private static final String RESOURCES_SRC = "src/main/resources";
    
    @Drone
    private WebDriver browser;
    
    @ArquillianResource
    private URL deploymentURL;
    
    // Runs in a client mode
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "message.war")                
                .addPackage(Message.class.getPackage())
                .addPackage(MessageFacade.class.getPackage())
                .addPackage(MessageView.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC, "result.xhtml"))                
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test
    public void sentMessageShouldCount() {
        browser.get(deploymentURL + "index.xhtml");
    }
}
