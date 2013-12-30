package com.android.dialogs;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.android.multiplay.R;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog that extends AlertDialogs
 *
 */
public class AddConnectionDialog extends ScrollViewSwitchDialog implements TextWatcher {

	public static final String DEVICE_NAME = "device_name";
	public static final String DEVICE_IS_STORED = "device_is_stored";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String DEVICE_IP = "device_ip";
	public static final String DEVICE_PORT = "device_port";
	public static final String DEVICE_UUID = "device_uuid";
	public static final String DEVICE_MAC = "device_mac";
	
	private static final Pattern PATTERN_NAME = Pattern.compile("^.+?$");
	private static final Pattern PATTERN_IP = Pattern.compile("^([0-9]{1,3}[.]){3}[0-9]{1,3}$");
	private static final Pattern PATTERN_PORT = Pattern.compile("^[0-9]{4,5}$");
	private static final Pattern PATTERN_UUID = Pattern.compile("^[0-9A-Fa-f]{8}:([0-9A-Fa-f]{4}:){3}[0-9A-Fa-f]{12}$");
	private static final Pattern PATTERN_MAC = Pattern.compile("^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$");
	
	private static final String PATTERN_NAME_ERROR = "Device name cannot be empty.";
	private static final String PATTERN_IP_ERROR = "Device IP address is incorrect.";
	private static final String PATTERN_PORT_ERROR = "Incorrect port value.";
	private static final String PATTERN_UUID_ERROR = "Universally unique identifier is incorrect.";
	private static final String PATTERN_MAC_ERROR = "Device hardware address is incorrect.";

	private static final int BT_ICON_ON = R.drawable.connections_activity_icon_bt_on;
	private static final int BT_ICON_OFF = R.drawable.connections_activity_icon_bt_off;
	private static final int WIFI_ICON_ON = R.drawable.connections_activity_icon_wifi_on;
	private static final int WIFI_ICON_OFF = R.drawable.connections_activity_icon_wifi_off;
	
	private boolean s_connections_activity_connection_type_switch_state = false;
	private ImageView iv_connections_activity_icon_wifi = null;
	private ImageView iv_connections_activity_icon_bt = null;
	private EditText et_connections_activity_device_name = null;
	private EditText et_connections_activity_device_ip = null;
	private EditText et_connections_activity_device_port = null;
	private EditText et_connections_activity_device_uuid = null;
	private EditText et_connections_activity_device_mac = null;
	private CheckBox cb_save_connection = null;
    
	
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer switcherID, Integer view_switchOffID, Integer view_switchOnID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		
		AddConnectionDialog dialog = new AddConnectionDialog();
    	Bundle args = ScrollViewSwitchDialog.newInstance(
    			titleIconID, titleID, view, switcherID, view_switchOffID, 
    			view_switchOnID, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();
        
        dialog.setArguments(args);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}

	@Override
	public void buildDialogContent(Builder builder) {
		Integer argsID = super.getArguments().getInt(ScrollViewDialog.VIEW_ID);
		if ( argsID !=  0 ) {
			builder.setView(dialogInnerView);
		}
		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
			loadCurrentView(false);
		}
	}
	
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
		super.getPositiveButton().setEnabled(false);
	}
	
    @Override
    public void dialogInnerViewLogic() {
    	super.dialogInnerViewLogic();
	    iv_connections_activity_icon_wifi = (ImageView) dialogInnerView.findViewById(
	    		R.id.iv_connections_activity_icon_wifi);
	    iv_connections_activity_icon_bt = (ImageView) dialogInnerView.findViewById(
	    		R.id.iv_connections_activity_icon_bt);
	    
	    et_connections_activity_device_name = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_name);
	    et_connections_activity_device_name.setTag(DEVICE_NAME);
	    et_connections_activity_device_name.addTextChangedListener(this);
	    
	    et_connections_activity_device_ip = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_ip);
	    et_connections_activity_device_ip.setTag(DEVICE_IP);
	    et_connections_activity_device_ip.addTextChangedListener(this);
	    
	    et_connections_activity_device_uuid = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_uuid);
	    et_connections_activity_device_uuid.setTag(DEVICE_UUID);
	    et_connections_activity_device_uuid.addTextChangedListener(this);
	    
	    et_connections_activity_device_mac = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_mac);
	    et_connections_activity_device_mac.setTag(DEVICE_MAC);
	    et_connections_activity_device_mac.addTextChangedListener(this);
	    
	    et_connections_activity_device_port = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_port);
	    et_connections_activity_device_port.setTag(DEVICE_PORT);
	    et_connections_activity_device_port.addTextChangedListener(this);
	    
	    cb_save_connection = (CheckBox) dialogInnerView.findViewById(
	    		R.id.cb_save_connection);
    }
    
	@Override
	public void onCheckedChanged(CompoundButton view, boolean switchState) {
		super.onCheckedChanged(view, switchState);
		switch (view.getId()) {
		case R.id.cb_save_connection:
			if ( switchState == true ) {
				super.getReturnedData().put(
						DEVICE_IS_STORED,AddConnectionDialog.TRUE);
			} else {
				super.getReturnedData().put(
						DEVICE_IS_STORED,AddConnectionDialog.FALSE);
			}
			break;
		}
	}

	@Override
	protected void loadCurrentViewOff() {
		super.loadCurrentViewOff();
		iv_connections_activity_icon_wifi.setBackgroundResource(WIFI_ICON_ON);
		iv_connections_activity_icon_bt.setBackgroundResource(BT_ICON_OFF);
	}

	@Override
	protected void loadCurrentViewOn() {
		super.loadCurrentViewOn();
		iv_connections_activity_icon_wifi.setBackgroundResource(WIFI_ICON_OFF);
		iv_connections_activity_icon_bt.setBackgroundResource(BT_ICON_ON);
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		Log.d("Dialogs","Validatation 1");
		if ( super.getPositiveButton() != null ) {
			boolean isValid = true;
	
			Log.d("Dialogs","Validatation");
	
			isValid &= validation(et_connections_activity_device_name,PATTERN_NAME,PATTERN_NAME_ERROR);
		
			if ( s_connections_activity_connection_type_switch_state == false ) {
				isValid &= super.validation(et_connections_activity_device_ip,PATTERN_IP,PATTERN_IP_ERROR);
				isValid &= super.validation(et_connections_activity_device_port,PATTERN_PORT,PATTERN_PORT_ERROR);
			} else {
				isValid &= super.validation(et_connections_activity_device_uuid,PATTERN_UUID,PATTERN_UUID_ERROR);
				isValid &= super.validation(et_connections_activity_device_mac,PATTERN_UUID,PATTERN_UUID_ERROR);
				isValid &= super.validation(et_connections_activity_device_mac,PATTERN_MAC,PATTERN_MAC_ERROR);
			}

			super.getPositiveButton().setEnabled(isValid);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	} 
}