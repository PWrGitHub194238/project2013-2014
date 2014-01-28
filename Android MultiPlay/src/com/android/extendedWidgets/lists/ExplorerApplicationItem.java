package com.android.extendedWidgets.lists;


public class ExplorerApplicationItem {
	
	public ExplorerApplicationItem(int applicationID, String applicationName ) {
		this.applicationID = applicationID;
		this.applicationName = applicationName;
		this.applicationIconID = null;
	}
	
	private int applicationID = 0;
	private String applicationName = null;
	private Integer applicationIconID = null;
	
	/**
	 * @return the applicationID
	 */
	public final int getApplicationID() {
		return applicationID;
	}
	/**
	 * @param applicationID the applicationID to set
	 */
	public final void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	/**
	 * @return the applicationName
	 */
	public final String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName the applicationName to set
	 */
	public final void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * @return the applicationIconID
	 */
	public final Integer getApplicationIconID() {
		return applicationIconID;
	}
	/**
	 * @param applicationIconID the applicationIconID to set
	 */
	public final void setApplicationIconID(Integer applicationIconID) {
		this.applicationIconID = applicationIconID;
	}
	
	
}
