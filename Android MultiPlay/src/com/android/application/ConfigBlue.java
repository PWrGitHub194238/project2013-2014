package com.android.application;

import java.util.UUID;

public class ConfigBlue {
	private UUID uuid=null;
	private  String adress=null;
	 public void setuidd(UUID uidd){
		this.uuid=uidd;
	}
	 public void setadress(String adress){
			this.adress=adress;
		}

	public String getadress() {
		return adress;
	}

	public UUID getuuid() {
		return uuid;
	}
	 
}
