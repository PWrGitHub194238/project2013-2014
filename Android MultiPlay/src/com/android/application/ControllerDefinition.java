package com.android.application;

import android.app.Activity;

public class ControllerDefinition {
	
	public static final boolean STAND_ALONE = true;
	public static final boolean NOT_STAND_ALONE = false;
	
	private int signal = 0;
	private String name = null;
	private int iconID = 0;
	private boolean isStandAlone = false;
	private Class<? extends Activity> nextActivity = null;
	private int systemRequirement = C.Requirements.OS_EVERY;
	private int[] requirements = null;
	
	public ControllerDefinition(int signal, String name, int iconID, boolean isStandAlone,
			Class<? extends Activity> nextActivity, int systemRequirement, int[] requirements) {
		super();
		this.signal = signal;
		this.name = name;
		this.iconID = iconID;
		this.isStandAlone = isStandAlone;
		this.nextActivity = nextActivity;
		this.systemRequirement = systemRequirement;
		this.requirements = requirements;
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

	public final Class<? extends Activity> getNextActivity() {
		return nextActivity;
	}

	/**
	 * @return the systemRequirement
	 */
	public final int getSystemRequirement() {
		return systemRequirement;
	}

	public final int[] getRequirements() {
		return requirements;
	}
	
	
	
	
}