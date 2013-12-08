package com.android.extendedWidgets.lists;

import com.android.multiplay.R;

public class ElementOfConnectionsList {
	
	public static int[] ICON = {
		R.drawable.connections_activity_icon_1
	};

	public static final int ICON_BT = R.drawable.connections_activity_bt_list_icon;
	public static final int ICON_WIFI = R.drawable.connections_activity_wifi_list_icon;
	
	public static final int STATUS_ON = R.drawable.activity_button_on;
	public static final int STATUS_WARNING = R.drawable.activity_button_warning;
	public static final int STATUS_NOT_IN_RANGE = R.drawable.activity_button_not_in_range;

	public static final boolean TYPE_BT = true;
	public static final boolean TYPE_WIFI = false;
	
	public static final int STORED_YES = R.drawable.connections_activity_stored_yes;
	public static final int STORED_NO = R.drawable.connections_activity_stored_no;
	
	
	
	public ElementOfConnectionsList(String deviceName, String deviceDetail,
			int deviceDetailStatus, int isStored, boolean BTorWiFi) {
		this.icon_type_id = ICON[0];
		this.deviceName = deviceName;
		this.deviceDetailStatus = deviceDetailStatus;
		this.deviceDetail = deviceDetail;
		this.isStored = isStored;
		if (BTorWiFi == TYPE_BT) {
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
	private int isStored = -1;



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
	public final int getIsStored() {
		return isStored;
	}
	public final void setIsStored(int isStored) {
		this.isStored = isStored;
	}
	
	
}
