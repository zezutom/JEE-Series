package org.zezutom.jeeseries.web.jsf.ejb.service;

import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.zezutom.jeeseries.web.jsf.ejb.model.Message;

@RunWith(Arquillian.class)
public class MessageFacadeIT {
    
    @EJB
    private MessageFacade messageFacade;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
                .create(JavaArchive.class, "web-jsf-ejb.jar")
                .addPackage(MessageFacade.class.getPackage())
                .addPackage(Message.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @After
    public void tearDown() {
        List<Message> messages = messageFacade.findAll();
        if (messages != null) {
            messages.stream().forEach(message -> messageFacade.remove(message));
        }
        Assert.assertEquals(0, messageFacade.count());
    }
    
    @Test
    public void createdMessageHasIdAndCanBeFound() {
        Message message = newMessage("test");
        messageFacade.create(message);
        Assert.assertNotNull(message.getId());
        Assert.assertEquals(message, messageFacade.find(message.getId()));        
    }
    
    @Test
    public void createdMessageCanBeChanged() {
        Message message = newMessage("test");
        messageFacade.create(message);

        String newText = "test 1";
        message = messageFacade.find(message.getId());
        message.setMessage(newText);
        
        messageFacade.edit(message);
        Assert.assertEquals(message, messageFacade.find(message.getId()));        
    }
   
    @Test
    public void everyMessageCounts() {
        Message[] messages = {
            newMessage("one"), newMessage("two"), newMessage("three")
        };
        for (Message message : messages) {
            messageFacade.create(message);
        }
        Assert.assertEquals(messages.length, messageFacade.count());
    }
    
    private Message newMessage(String text) {
        Message message = new Message();
        message.setMessage(text);
        Assert.assertNull(message.getId());
        return message;
    }
}
