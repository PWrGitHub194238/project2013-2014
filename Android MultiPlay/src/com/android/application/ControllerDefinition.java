package com.android.application;

import android.app.Activity;

public class ControllerDefinition {
	
	public static final boolean STAND_ALONE = true;
	public static final boolean NOT_STAND_ALONE = false;
	
	private int signal = 0;
	private String name = null;
	private int iconID = 0;
	private boolean isStandAlone = false;
	private Class<? extends Activity> controllerActivity = null;
	private Class<? extends Activity> optionsActivity = null;
	private Class<? extends Activity> helperActivity = null;
	private int systemRequirement = C.Requirements.OS_EVERY;
	private String[] requirements = null;
	
	public ControllerDefinition(int signal, String name, int iconID, boolean isStandAlone,
			Class<? extends Activity> controllerActivity, Class<? extends Activity> optionsActivity,
			Class<? extends Activity> helperActivity, int systemRequirement, String[] requirements) {
		super();
		this.signal = signal;
		this.name = name;
		this.iconID = iconID;
		this.isStandAlone = isStandAlone;
		this.controllerActivity = controllerActivity;
		this.optionsActivity = optionsActivity;
		this.systemRequirement = systemRequirement;
		this.requirements = requirements;
		this.helperActivity = helperActivity;
	}

	public final int getSignal() {
		return signal;
	}

	public final String getName() {
		return name;
	}

	public final int getIconID() {
		return iconID;
	}

	public final boolean isStandAlone() {
		return isStandAlone;
	}


	/**
	 * @return the controllerActivity
	 */
	public final Class<? extends Activity> getControllerActivity() {
		return controllerActivity;
	}

	/**
	 * @return the optionsActivity
	 */
	public final Class<? extends Activity> getOptionsActivity() {
		return optionsActivity;
	}
	
	/**
	 * @return the helperActivity
	 */
	public final Class<? extends Activity> getHelperActivity() {
		return helperActivity;
	}

	/**
	 * @return the systemRequirement
	 */
	public final int getSystemRequirement() {
		return systemRequirement;
	}

	public final String[] getRequirements() {
		return requirements;
	}
	
}