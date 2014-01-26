package com.android.application;

import android.app.Activity;

public class CustomControllerDefinition extends ControllerDefinition {
	
	private int controllerDataBaseID = 0;
	
	public CustomControllerDefinition(int signal, String name, int iconID, boolean isStandAlone,
			Class<? extends Activity> controllerActivity, Class<? extends Activity> optionsActivity, 
			Class<? extends Activity> helperActivity, int systemRequirement, String[] requirements, int controllerDataBaseID) {
		
		super(signal, name, iconID, isStandAlone, controllerActivity, optionsActivity, helperActivity, systemRequirement, requirements);
		this.controllerDataBaseID = controllerDataBaseID;
	}

	public final int getControllerDataBaseID() {
		return controllerDataBaseID;
	}
}