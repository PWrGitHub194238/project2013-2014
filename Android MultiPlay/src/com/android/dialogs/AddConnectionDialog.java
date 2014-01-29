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
 * Dialog that extends {@link ScrollViewSwitchDialog}
 *
 * @author tomasz
 */
public class AddConnectionDialog extends ScrollViewSwitchDialog implements TextWatcher {

	/**
	 * 
	 */
	public static final String DEVICE_NAME = "device_name";
	/**
	 * 
	 */
	public static final String DEVICE_IS_STORED = "device_is_stored";

	/**
	 * 
	 */
	public static final String DEVICE_IP = "device_ip";
	/**
	 * 
	 */
	public static final String DEVICE_PORT = "device_port";
	/**
	 * 
	 */
	public static final String DEVICE_MAC = "device_mac";
	
	/**
	 * 
	 */
	private static final Pattern PATTERN_NAME = Pattern.compile("^.+?$");
	/**
	 * 
	 */
	private static final Pattern PATTERN_IP = Pattern.compile("^([0-9]{1,3}[.]){3}[0-9]{1,3}$");
	/**
	 * 
	 */
	private static final Pattern PATTERN_PORT = Pattern.compile("^[0-9]{4,5}$");
	/**
	 * 
	 */
	private static final Pattern PATTERN_MAC = Pattern.compile("^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$");
	
	/**
	 * 
	 */
	private static final String PATTERN_NAME_ERROR = "Device name cannot be empty.";
	/**
	 * 
	 */
	private static final String PATTERN_IP_ERROR = "Device IP address is incorrect.";
	/**
	 * 
	 */
	private static final String PATTERN_PORT_ERROR = "Incorrect port value.";
	/**
	 * 
	 */
	private static final String PATTERN_MAC_ERROR = "Device hardware address is incorrect.";

	/**
	 * 
	 */
	private static final int BT_ICON_ON = R.drawable.connections_activity_icon_bt_on;
	/**
	 * 
	 */
	private static final int BT_ICON_OFF = R.drawable.connections_activity_icon_bt_off;
	/**
	 * 
	 */
	private static final int WIFI_ICON_ON = R.drawable.connections_activity_icon_wifi_on;
	/**
	 * 
	 */
	private static final int WIFI_ICON_OFF = R.drawable.connections_activity_icon_wifi_off;
	
	/**
	 * 
	 */
	private ImageView iv_connections_activity_icon_wifi = null;
	/**
	 * 
	 */
	private ImageView iv_connections_activity_icon_bt = null;
	/**
	 * 
	 */
	private EditText et_connections_activity_device_name_wifi = null;
	/**
	 * 
	 */
	private EditText et_connections_activity_device_name_bt = null;
	/**
	 * 
	 */
	private EditText et_connections_activity_device_ip = null;
	/**
	 * 
	 */
	private EditText et_connections_activity_device_port = null;
	/**
	 * 
	 */
	private EditText et_connections_activity_device_mac = null;
	/**
	 * 
	 */
	private CheckBox cb_save_connection_wifi = null;
	/**
	 * 
	 */
	private CheckBox cb_save_connection_bt = null;
	/**
	 * 
	 */
	private CheckBox cb_set_as_default_wifi = null;
	/**
	 * 
	 */
	private CheckBox cb_set_as_default_bt = null;
	
	/**
	 * @param activity
	 * @param dialogIDTag
	 * @param titleIconID
	 * @param titleID
	 * @param view
	 * @param switcherID
	 * @param view_switchOffID
	 * @param view_switchOnID
	 * @param positiveButtonID
	 * @param neutralButtonID
	 * @param negativeButtonID
	 */
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer switcherID, Integer view_switchOffID, Integer view_switchOnID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		
		AddConnectionDialog dialog = new AddConnectionDialog();
    	Bundle args = ScrollViewSwitchDialog.newInstance(
    			titleIconID, titleID, view, switcherID, view_switchOffID, 
    			view_switchOnID, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();
        
        dialog.setArguments(args);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}

	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewSwitchDialog#buildDialogContent(android.app.AlertDialog.Builder)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.android.dialogs.AlertDialogs#onShow(android.content.DialogInterface)
	 */
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
		super.getPositiveButton().setEnabled(false);
	}
	
    /* (non-Javadoc)
     * @see com.android.dialogs.ScrollViewSwitchDialog#dialogInnerViewLogic()
     */
    @Override
    public void dialogInnerViewLogic() {
    	super.dialogInnerViewLogic();
	    iv_connections_activity_icon_wifi = (ImageView) dialogInnerView.findViewById(
	    		R.id.iv_connections_activity_icon_wifi);
	    iv_connections_activity_icon_bt = (ImageView) dialogInnerView.findViewById(
	    		R.id.iv_connections_activity_icon_bt);
	    
	    et_connections_activity_device_name_wifi = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_name_wifi);
	    et_connections_activity_device_name_wifi.setTag(DEVICE_NAME);
	    et_connections_activity_device_name_wifi.addTextChangedListener(this);
	    
	    et_connections_activity_device_name_bt = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_name_bt);
	    et_connections_activity_device_name_bt.setTag(DEVICE_NAME);
	    et_connections_activity_device_name_bt.addTextChangedListener(this);
	    
	    et_connections_activity_device_ip = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_ip);
	    et_connections_activity_device_ip.setTag(DEVICE_IP);
	    et_connections_activity_device_ip.addTextChangedListener(this);
	    
	    et_connections_activity_device_mac = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_mac);
	    et_connections_activity_device_mac.setTag(DEVICE_MAC);
	    et_connections_activity_device_mac.addTextChangedListener(this);
	    
	    et_connections_activity_device_port = (EditText) dialogInnerView.findViewById(
	    		R.id.et_connections_activity_device_port);
	    et_connections_activity_device_port.setTag(DEVICE_PORT);
	    et_connections_activity_device_port.addTextChangedListener(this);
	    
		cb_save_connection_wifi = (CheckBox) dialogInnerView.findViewById(
	    		R.id.cb_save_connection_wifi);
		cb_save_connection_wifi.setOnCheckedChangeListener(this);
		cb_save_connection_bt = (CheckBox) dialogInnerView.findViewById(
	    		R.id.cb_save_connection_bt);
		cb_save_connection_bt.setOnCheckedChangeListener(this);
	    super.getReturnedData().put(
				AddConnectionDialog.DEVICE_IS_STORED,Boolean.toString(false));
	    
	    cb_set_as_default_wifi = (CheckBox) dialogInnerView.findViewById(
	    		R.id.cb_set_as_default_wifi);
	    cb_set_as_default_wifi.setOnCheckedChangeListener(this);
	    cb_set_as_default_bt = (CheckBox) dialogInnerView.findViewById(
	    		R.id.cb_set_as_default_bt);
	    cb_set_as_default_bt.setOnCheckedChangeListener(this);
    }
    
	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewSwitchDialog#onCheckedChanged(android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton view, boolean switchState) {
		super.onCheckedChanged(view, switchState);
		switch (view.getId()) {
		case R.id.cb_save_connection_wifi:
			if ( switchState == true ) {
				Log.d("APP","switch true");
				super.getReturnedData().put(
						AddConnectionDialog.DEVICE_IS_STORED,Boolean.toString(true));
			} else {
				Log.d("APP","switch");

				super.getReturnedData().put(
						AddConnectionDialog.DEVICE_IS_STORED,Boolean.toString(false));
			}
			break;
		case R.id.cb_save_connection_bt:
			if ( switchState == true ) {
				Log.d("APP","switch true");
				super.getReturnedData().put(
						AddConnectionDialog.DEVICE_IS_STORED,Boolean.toString(true));
			} else {
				Log.d("APP","switch");

				super.getReturnedData().put(
						AddConnectionDialog.DEVICE_IS_STORED,Boolean.toString(false));
			}
			break;
		}
	}

	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewSwitchDialog#loadCurrentViewOff()
	 */
	@Override
	protected void loadCurrentViewOff() {
		super.loadCurrentViewOff();
		iv_connections_activity_icon_wifi.setBackgroundResource(WIFI_ICON_ON);
		iv_connections_activity_icon_bt.setBackgroundResource(BT_ICON_OFF);
	}

	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewSwitchDialog#loadCurrentViewOn()
	 */
	@Override
	protected void loadCurrentViewOn() {
		super.loadCurrentViewOn();
		iv_connections_activity_icon_wifi.setBackgroundResource(WIFI_ICON_OFF);
		iv_connections_activity_icon_bt.setBackgroundResource(BT_ICON_ON);
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
	 */
	@Override
	public void afterTextChanged(Editable arg0) {
		Log.d("Dialogs","Validatation 1");
		if ( super.getPositiveButton() != null ) {
			boolean isValid = true;
	
			Log.d("Dialogs","Validatation");
	
		
			if ( super.isViewSwitcherState() == false ) {
				isValid &= validation(et_connections_activity_device_name_wifi,PATTERN_NAME,PATTERN_NAME_ERROR);
				isValid &= super.validation(et_connections_activity_device_ip,PATTERN_IP,PATTERN_IP_ERROR);
				isValid &= super.validation(et_connections_activity_device_port,PATTERN_PORT,PATTERN_PORT_ERROR);
			} else {
				Log.d("Connections","BT VALIDATION " + isValid);
				isValid &= validation(et_connections_activity_device_name_bt,PATTERN_NAME,PATTERN_NAME_ERROR);
				isValid &= super.validation(et_connections_activity_device_mac,PATTERN_MAC,PATTERN_MAC_ERROR);
				Log.d("Connections","BT VALIDATION after" + isValid);
			}

			super.getPositiveButton().setEnabled(isValid);
		}
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	} 
}