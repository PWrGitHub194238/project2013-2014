package com.android.extendedWidgets.lists;

import com.android.multiplay.R;
import com.android.services.ConnectionHelper;

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
	
	public ElementOfConnectionsList(String deviceName, String deviceDetail,
			int deviceDetailStatus, boolean isStored, byte system, boolean BTorWiFi) {
		this.icon_type_id = ICON[0];
		this.deviceName = deviceName;
		this.deviceDetailStatus = deviceDetailStatus;
		this.deviceDetail = deviceDetail;
		this.isStored = isStored;
		this.system = system;
		if (BTorWiFi == ConnectionHelper.CONNECTION_TYPE_BT) {
			deviceDetailPrefix = "MAC: ";
			connectionTypeIcon = ICON_BT;
		} else {
			deviceDetailPrefix = "IP: ";
			connectionTypeIcon = ICON_WIFI;
		}
	}
	
	
	private int icon_type_id = -1;
	private int connectionTypeIcon = -1;
	private String deviceName = null;
	private int deviceDetailStatus = -1;
	private String deviceDetailPrefix = null;
	private String deviceDetail = null;
	private boolean isStored = false;
	private byte system = 0;



	public final int getIcon_type_id() {
		return icon_type_id;
	}
	public final void setIcon_type_id(int icon_type_id) {
		this.icon_type_id = icon_type_id;
	}
	public final int getConnectionTypeIcon() {
		return connectionTypeIcon;
	}
	public final void setConnectionTypeIcon(int connectionTypeIcon) {
		this.connectionTypeIcon = connectionTypeIcon;
	}
	public final String getDeviceName() {
		return deviceName;
	}
	public final void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public final int getDeviceDetailStatus() {
		return deviceDetailStatus;
	}
	public final void setDeviceDetailStatus(int deviceDetailStatus) {
		this.deviceDetailStatus = deviceDetailStatus;
	}
	public final String getDeviceDetailPrefix() {
		return deviceDetailPrefix;
	}
	public final void setDeviceDetailPrefix(String deviceDetailPrefix) {
		this.deviceDetailPrefix = deviceDetailPrefix;
	}
	public final String getDeviceDetail() {
		return deviceDetail;
	}
	public final void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}
	public final boolean isStored() {
		return isStored;
	}
	public final void setStored(boolean isStored) {
		this.isStored = isStored;
	}
	public final byte getSystem() {
		return system;
	}
	public final void setSystem(byte system) {
		this.system = system;
	}
}
