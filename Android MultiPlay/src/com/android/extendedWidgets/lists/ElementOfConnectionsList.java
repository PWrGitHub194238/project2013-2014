package com.android.extendedWidgets.lists;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.ConnectionsConfigurationClass;
import com.android.application.MultiPlayApplication;
import com.android.application.WirelessConfigurationClass;
import com.android.multiplay.R;

public class ElementOfConnectionsList {
	
	public static int[] ICON = {
		R.drawable.connections_activity_icon_1
	};

	public static final int ICON_BT = R.drawable.connections_activity_icon_bt_on;
	public static final int ICON_WIFI = R.drawable.connections_activity_icon_wifi_on;
	
	public static final boolean STORED_YES = true;
	public static final boolean STORED_NO = false;
	
	public static final int ICON_STORED_YES = R.drawable.connections_activity_stored_yes;
	public static final int ICON_STORED_NO = R.drawable.connections_activity_stored_no;
	
	public static final int ICON_BSD = R.drawable.connections_activity_os_bsd;
	public static final int ICON_LINUX = R.drawable.connections_activity_os_linux;
	public static final int ICON_WINDOWS = R.drawable.connections_activity_os_windows;
	public static final int ICON_UNKNOW = R.drawable.connections_activity_os_unknow;
	
	public ElementOfConnectionsList(int index, ConnectionsConfigurationClass connectionConfiguration ) {
		this.index = index;
		this.icon_type_id = ICON[0];
		this.deviceName = connectionConfiguration.getName();
		this.deviceDetailStatus = connectionConfiguration.getConnectionStatus();
		this.isStored = connectionConfiguration.isStored();
		this.system = connectionConfiguration.getSystem();
		this.resolution_x = connectionConfiguration.getSystemDimmensionX();
		this.resolution_y = connectionConfiguration.getSystemDimmensionY();
		
		if (connectionConfiguration instanceof WirelessConfigurationClass) {
			this.deviceDetail = ((WirelessConfigurationClass) connectionConfiguration).getIP();
			deviceDetailPrefix = "IP: ";
			connectionTypeIcon = ICON_WIFI;
			BTorWiFi = MultiPlayApplication.CONNECTION_TYPE_WIFI;
		} else if (connectionConfiguration instanceof BluetoothConfigurationClass) {
			this.deviceDetail = ((BluetoothConfigurationClass) connectionConfiguration).getAdress();
			deviceDetailPrefix = "IP: ";
			connectionTypeIcon = ICON_WIFI;
			BTorWiFi = MultiPlayApplication.CONNECTION_TYPE_BT;
		}
	}
	
	private int index = 0;
	private int icon_type_id = -1;
	private int connectionTypeIcon = -1;
	private String deviceName = null;
	private int deviceDetailStatus = -1;
	private String deviceDetailPrefix = null;
	private String deviceDetail = null;
	private boolean isStored = false;
	private byte system = 0;
	private int resolution_x = 0;
	private int resolution_y = 0;
	private boolean BTorWiFi = false;

	/**
	 * @return the index
	 */
	public final int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public final void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the icon_type_id
	 */
	public final int getIcon_type_id() {
		return icon_type_id;
	}
	/**
	 * @param icon_type_id the icon_type_id to set
	 */
	public final void setIcon_type_id(int icon_type_id) {
		this.icon_type_id = icon_type_id;
	}
	/**
	 * @return the connectionTypeIcon
	 */
	public final int getConnectionTypeIcon() {
		return connectionTypeIcon;
	}
	/**
	 * @param connectionTypeIcon the connectionTypeIcon to set
	 */
	public final void setConnectionTypeIcon(int connectionTypeIcon) {
		this.connectionTypeIcon = connectionTypeIcon;
	}
	/**
	 * @return the deviceName
	 */
	public final String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public final void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the deviceDetailStatus
	 */
	public final int getDeviceDetailStatus() {
		return deviceDetailStatus;
	}
	/**
	 * @param deviceDetailStatus the deviceDetailStatus to set
	 */
	public final void setDeviceDetailStatus(int deviceDetailStatus) {
		this.deviceDetailStatus = deviceDetailStatus;
	}
	/**
	 * @return the deviceDetailPrefix
	 */
	public final String getDeviceDetailPrefix() {
		return deviceDetailPrefix;
	}
	/**
	 * @param deviceDetailPrefix the deviceDetailPrefix to set
	 */
	public final void setDeviceDetailPrefix(String deviceDetailPrefix) {
		this.deviceDetailPrefix = deviceDetailPrefix;
	}
	/**
	 * @return the deviceDetail
	 */
	public final String getDeviceDetail() {
		return deviceDetail;
	}
	/**
	 * @param deviceDetail the deviceDetail to set
	 */
	public final void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}
	/**
	 * @return the isStored
	 */
	public final boolean isStored() {
		return isStored;
	}
	/**
	 * @param isStored the isStored to set
	 */
	public final void setStored(boolean isStored) {
		this.isStored = isStored;
	}
	/**
	 * @return the system
	 */
	public final byte getSystem() {
		return system;
	}
	/**
	 * @param system the system to set
	 */
	public final void setSystem(byte system) {
		this.system = system;
	}
	/**
	 * @return the resolution_x
	 */
	public final int getResolution_x() {
		return resolution_x;
	}
	/**
	 * @param resolution_x the resolution_x to set
	 */
	public final void setResolution_x(int resolution_x) {
		this.resolution_x = resolution_x;
	}
	/**
	 * @return the resolution_y
	 */
	public final int getResolution_y() {
		return resolution_y;
	}
	/**
	 * @param resolution_y the resolution_y to set
	 */
	public final void setResolution_y(int resolution_y) {
		this.resolution_y = resolution_y;
	}
	/**
	 * @return the bTorWiFi
	 */
	public final boolean isBTorWiFi() {
		return BTorWiFi;
	}
	/**
	 * @param bTorWiFi the bTorWiFi to set
	 */
	public final void setBTorWiFi(boolean bTorWiFi) {
		BTorWiFi = bTorWiFi;
	}
	
	
}
