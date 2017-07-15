package com.mkyong.common;

import java.io.IOException;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Settings {
	private String host = new String();
	private String nick = new String();
	private String encoding = new String();
	private String realname = new String();
	private String ircClient = new String();
	private String channel = new String();
	private boolean autoNickMode;
	private boolean verboseMode;

	public Settings() {
		Properties p = new Properties();
		try {
			p.load(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("settings.properties"));
			host = p.getProperty("host");
			nick = p.getProperty("nick");
			encoding = p.getProperty("encoding");
			realname = p.getProperty("realname");
			ircClient = p.getProperty("ircClient");
			channel = p.getProperty("channel");
			autoNickMode = Boolean.parseBoolean(p.getProperty("autoNickMode"));
			verboseMode = Boolean.parseBoolean(p.getProperty("verboseMode"));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIrcClient() {
		return ircClient;
	}

	public void setIrcClient(String ircClient) {
		this.ircClient = ircClient;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public boolean isAutoNickMode() {
		return autoNickMode;
	}

	public void setAutoNickMode(boolean autoNickMode) {
		this.autoNickMode = autoNickMode;
	}

	public boolean isVerboseMode() {
		return verboseMode;
	}

	public void setVerboseMode(boolean verboseMode) {
		this.verboseMode = verboseMode;
	}

}
