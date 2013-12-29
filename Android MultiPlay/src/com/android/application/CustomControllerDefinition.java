package com.android.application;

import android.app.Activity;

public class CustomControllerDefinition extends ControllerDefinition {
	
	private int controllerDataBaseID = 0;
	
	public CustomControllerDefinition(int signal, String name, int iconID, boolean isStandAlone,
			Class<? extends Activity> nextActivity, int[] requirements, int controllerDataBaseID) {
		
		super(signal, name, iconID, isStandAlone, nextActivity, requirements);
		this.controllerDataBaseID = controllerDataBaseID;
	}

	public final int getControllerDataBaseID() {
		return controllerDataBaseID;
	}
}