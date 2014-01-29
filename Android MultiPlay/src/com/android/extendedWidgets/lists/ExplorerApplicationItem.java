package com.android.extendedWidgets.lists;

/** A list item downloaded applications on the server, which is connected to the application.
 * 
 * @author tomasz
 *
 */
public class ExplorerApplicationItem {
	
	/**
	 * @param applicationID
	 * @param applicationName
	 */
	public ExplorerApplicationItem(int applicationID, String applicationName ) {
		this.applicationID = applicationID;
		this.applicationName = applicationName;
		this.applicationIconID = null;
	}
	
	/**
	 * 
	 */
	private int applicationID = 0;
	/**
	 * 
	 */
	private String applicationName = null;
	/**
	 * 
	 */
	private Integer applicationIconID = null;
	
	/**
	 * @return the applicationID
	 */
	/**
	 * @return
	 */
	public final int getApplicationID() {
		return applicationID;
	}
	/**
	 * @param applicationID the applicationID to set
	 */
	/**
	 * @param applicationID
	 */
	public final void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	/**
	 * @return the applicationName
	 */
	/**
	 * @return
	 */
	public final String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName the applicationName to set
	 */
	/**
	 * @param applicationName
	 */
	public final void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * @return the applicationIconID
	 */
	/**
	 * @return
	 */
	public final Integer getApplicationIconID() {
		return applicationIconID;
	}
	/**
	 * @param applicationIconID the applicationIconID to set
	 */
	/**
	 * @param applicationIconID
	 */
	public final void setApplicationIconID(Integer applicationIconID) {
		this.applicationIconID = applicationIconID;
	}
	
	
}
