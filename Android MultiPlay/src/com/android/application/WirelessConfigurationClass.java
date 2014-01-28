package com.android.application;

/** Stores structured information about each Wi-fi connection. 
 * 
 * 
 * @author tomasz
 */
public class WirelessConfigurationClass  extends ConnectionsConfigurationClass {
	
	private String IP = null;
	private  Integer port = null;
	
	/**
	 * 
	 * @param iP
	 * @param port
	 */
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
