package com.android.application;


public class WirelessConfigurationClass  extends ConnectionsConfigurationClass {
	
	private String IP = null;
	private  Integer port = null;
	
	public WirelessConfigurationClass(String iP, Integer port) {
		super();
		IP = iP;
		this.port = port;
	}
	
	public final String getIP() {
		return IP;
	}
	
	public final void setIP(String iP) {
		IP = iP;
	}
	
	public final Integer getPort() {
		return port;
	}
	
	public final void setPort(Integer port) {
		this.port = port;
	}
}
