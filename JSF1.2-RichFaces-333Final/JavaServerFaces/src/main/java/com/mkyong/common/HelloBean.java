package com.mkyong.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ajax4jsf.event.PushEventListener;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

@ManagedBean
@SessionScoped
public class HelloBean extends PircBot implements Serializable {

	private static final long serialVersionUID = 1L;

	PushEventListener listener;  
    private List<String[]> out = new ArrayList<String[]>();  
 
    public HelloBean() {  
         try {  
              Settings settings = new Settings();  
              this.setName(settings.getNick());  
              this.setEncoding(settings.getEncoding());  
              if (settings.getRealname() != null  
                        && settings.getRealname().length() != 0) {  
                   this.setLogin(settings.getRealname());  
              }  
              this.setVersion(settings.getIrcClient());  
              this.setAutoNickChange(settings.isAutoNickMode());  
              this.setVerbose(settings.isVerboseMode());  
 
              this.connect(settings.getHost());  
              if (this.isConnected()) {  
                   this.joinChannel(settings.getChannel());  
              }  
         } catch (NickAlreadyInUseException e) {  
              FacesContext.getCurrentInstance().addMessage(  
                        null,  
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e  
                                  .getMessage(), null));  
         } catch (IOException e) {  
              FacesContext.getCurrentInstance().addMessage(  
                        null,  
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e  
                                  .getMessage(), null));  
         } catch (IrcException e) {  
              FacesContext.getCurrentInstance().addMessage(  
                        null,  
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, e  
                                  .getMessage(), null));  
         }  
    }  
      
    public void sendEvent(String user, String message) {  
         out.add(new String[] { user, message });  
         listener.onEvent(new EventObject(this));  
    }  
      
    @Override  
    protected void onMessage(String channel, String sender, String login,  
              String hostname, String message) {  
         sendEvent(sender, message);  
    }  
 
    public void addListener(EventListener listener) {  
         if (this.listener != listener) {  
              this.listener = (PushEventListener) listener;  
         }  
    } 

}