package com.android.application;

import java.util.UUID;

public class BluetoothConfigurationClass  extends ConnectionsConfigurationClass {
	
	private UUID uuid = null;
	private  String adress = null;
	
	public BluetoothConfigurationClass(UUID uuid, String adress) {
		super();
		this.uuid = uuid;
		this.adress = adress;
	}
	
	public final UUID getUuid() {
		return uuid;
	}
	public final void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public final String getAdress() {
		return adress;
	}
	public final void setAdress(String adress) {
		this.adress = adress;
	}
	
	 
}
