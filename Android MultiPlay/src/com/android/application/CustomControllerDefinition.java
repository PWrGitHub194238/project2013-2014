package com.android.application;

import android.app.Activity;

/** Extension of {@link ControllerDefinition}, containing an additional field for an identifier of custom controller in the database, which is stored additional information about it.
 * 
 * @author tomasz
 *
 */
public class CustomControllerDefinition extends ControllerDefinition {
	
	private int controllerDataBaseID = 0;
	
	/**
	 * 
	 * @param signal
	 * @param name
	 * @param iconID
	 * @param isStandAlone
	 * @param controllerActivity
	 * @param optionsActivity
	 * @param helperActivity
	 * @param systemRequirement
	 * @param requirements
	 * @param controllerDataBaseID
	 */
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