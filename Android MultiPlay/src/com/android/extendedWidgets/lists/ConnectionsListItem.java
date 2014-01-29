package com.android.extendedWidgets.lists;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.ConnectionsConfigurationClass;
import com.android.application.MultiPlayApplication;
import com.android.application.WirelessConfigurationClass;
import com.android.multiplay.R;

/** Element list of available Bluetooth or Wifi connections.
 * 
 * @author tomasz
 *
 */
public class ConnectionsListItem {
	
	/**
	 * 
	 */
	public static int[] ICON = {
		R.drawable.connections_activity_icon_1
	};

	/**
	 * 
	 */
	public static final int ICON_BT = R.drawable.connections_activity_icon_bt_on;
	/**
	 * 
	 */
	public static final int ICON_WIFI = R.drawable.connections_activity_icon_wifi_on;
	
	/**
	 * 
	 */
	public static final boolean STORED_YES = true;
	/**
	 * 
	 */
	public static final boolean STORED_NO = false;
	
	/**
	 * 
	 */
	public static final int ICON_STORED_YES = R.drawable.connections_activity_stored_yes;
	/**
	 * 
	 */
	public static final int ICON_STORED_NO = R.drawable.connections_activity_stored_no;
	
	/**
	 * 
	 */
	public static final int ICON_BSD = R.drawable.connections_activity_os_bsd;
	/**
	 * 
	 */
	public static final int ICON_LINUX = R.drawable.connections_activity_os_linux;
	/**
	 * 
	 */
	public static final int ICON_WINDOWS = R.drawable.connections_activity_os_windows;
	/**
	 * 
	 */
	public static final int ICON_UNKNOW = R.drawable.connections_activity_os_unknow;
	
	/**
	 * @param index
	 * @param connectionConfiguration
	 */
	public ConnectionsListItem(int index, ConnectionsConfigurationClass connectionConfiguration ) {
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
	
	/**
	 * 
	 */
	private int index = 0;
	/**
	 * 
	 */
	private int icon_type_id = -1;
	/**
	 * 
	 */
	private int connectionTypeIcon = -1;
	/**
	 * 
	 */
	private String deviceName = null;
	/**
	 * 
	 */
	private int deviceDetailStatus = -1;
	/**
	 * 
	 */
	private String deviceDetailPrefix = null;
	/**
	 * 
	 */
	private String deviceDetail = null;
	/**
	 * 
	 */
	private boolean isStored = false;
	/**
	 * 
	 */
	private byte system = 0;
	/**
	 * 
	 */
	private int resolution_x = 0;
	/**
	 * 
	 */
	private int resolution_y = 0;
	/**
	 * 
	 */
	private boolean BTorWiFi = false;

	/**
	 * @return the index
	 */
	/**
	 * @return
	 */
	public final int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	/**
	 * @param index
	 */
	public final void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the icon_type_id
	 */
	/**
	 * @return
	 */
	public final int getIcon_type_id() {
		return icon_type_id;
	}
	/**
	 * @param icon_type_id the icon_type_id to set
	 */
	/**
	 * @param icon_type_id
	 */
	public final void setIcon_type_id(int icon_type_id) {
		this.icon_type_id = icon_type_id;
	}
	/**
	 * @return the connectionTypeIcon
	 */
	/**
	 * @return
	 */
	public final int getConnectionTypeIcon() {
		return connectionTypeIcon;
	}
	/**
	 * @param connectionTypeIcon the connectionTypeIcon to set
	 */
	/**
	 * @param connectionTypeIcon
	 */
	public final void setConnectionTypeIcon(int connectionTypeIcon) {
		this.connectionTypeIcon = connectionTypeIcon;
	}
	/**
	 * @return the deviceName
	 */
	/**
	 * @return
	 */
	public final String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	/**
	 * @param deviceName
	 */
	public final void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the deviceDetailStatus
	 */
	/**
	 * @return
	 */
	public final int getDeviceDetailStatus() {
		return deviceDetailStatus;
	}
	/**
	 * @param deviceDetailStatus the deviceDetailStatus to set
	 */
	/**
	 * @param deviceDetailStatus
	 */
	public final void setDeviceDetailStatus(int deviceDetailStatus) {
		this.deviceDetailStatus = deviceDetailStatus;
	}
	/**
	 * @return the deviceDetailPrefix
	 */
	/**
	 * @return
	 */
	public final String getDeviceDetailPrefix() {
		return deviceDetailPrefix;
	}
	/**
	 * @param deviceDetailPrefix the deviceDetailPrefix to set
	 */
	/**
	 * @param deviceDetailPrefix
	 */
	public final void setDeviceDetailPrefix(String deviceDetailPrefix) {
		this.deviceDetailPrefix = deviceDetailPrefix;
	}
	/**
	 * @return the deviceDetail
	 */
	/**
	 * @return
	 */
	public final String getDeviceDetail() {
		return deviceDetail;
	}
	/**
	 * @param deviceDetail the deviceDetail to set
	 */
	/**
	 * @param deviceDetail
	 */
	public final void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}
	/**
	 * @return the isStored
	 */
	/**
	 * @return
	 */
	public final boolean isStored() {
		return isStored;
	}
	/**
	 * @param isStored the isStored to set
	 */
	/**
	 * @param isStored
	 */
	public final void setStored(boolean isStored) {
		this.isStored = isStored;
	}
	/**
	 * @return the system
	 */
	/**
	 * @return
	 */
	public final byte getSystem() {
		return system;
	}
	/**
	 * @param system the system to set
	 */
	/**
	 * @param system
	 */
	public final void setSystem(byte system) {
		this.system = system;
	}
	/**
	 * @return the resolution_x
	 */
	/**
	 * @return
	 */
	public final int getResolution_x() {
		return resolution_x;
	}
	/**
	 * @param resolution_x the resolution_x to set
	 */
	/**
	 * @param resolution_x
	 */
	public final void setResolution_x(int resolution_x) {
		this.resolution_x = resolution_x;
	}
	/**
	 * @return the resolution_y
	 */
	/**
	 * @return
	 */
	public final int getResolution_y() {
		return resolution_y;
	}
	/**
	 * @param resolution_y the resolution_y to set
	 */
	/**
	 * @param resolution_y
	 */
	public final void setResolution_y(int resolution_y) {
		this.resolution_y = resolution_y;
	}
	/**
	 * @return the bTorWiFi
	 */
	/**
	 * @return
	 */
	public final boolean isBTorWiFi() {
		return BTorWiFi;
	}
	/**
	 * @param bTorWiFi the bTorWiFi to set
	 */
	/**
	 * @param bTorWiFi
	 */
	public final void setBTorWiFi(boolean bTorWiFi) {
		BTorWiFi = bTorWiFi;
	}
	
	
}
