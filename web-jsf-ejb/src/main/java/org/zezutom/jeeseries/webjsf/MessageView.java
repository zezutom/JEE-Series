package org.zezutom.jeeseries.webjsf;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.zezutom.jeeseries.webjsf.model.Message;
import org.zezutom.jeeseries.webjsf.service.MessageFacade;

@Named(value = "messageView")
@RequestScoped
public class MessageView {

    @EJB
    private MessageFacade messageFacade;
    
    private final Message message;

    public MessageView() {
        this.message = new Message();
    }

    public Message getMessage() {
        return message;
    }    
    public int getNumberOfMessages() {
        return messageFacade.count();
    }
    
    public String postMessage() {
        messageFacade.create(message);
        return "result";
    }
}
