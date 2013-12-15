package com.android.application;

public class ConnectionsConfigurationClass {

	private String name = null;
	private String MACAdress = null;
	private int connectionStatus = -1;
	private boolean isStored = false;
	private int storedIndex = -1;
	
	
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getMACAdress() {
		return MACAdress;
	}
	
	public final void setMACAdress(String mACAdress) {
		MACAdress = mACAdress;
	}
	
	public final int getConnectionStatus() {
		return connectionStatus;
	}
	
	public final void setConnectionStatus(int connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
	public final boolean isStored() {
		return isStored;
	}
	
	public final void setStored(boolean isStored) {
		this.isStored = isStored;
	}
	
	public final int getStoredIndex() {
		return storedIndex;
	}
	
	public final void setStoredIndex(int storedIndex) {
		this.storedIndex = storedIndex;
	}
}
