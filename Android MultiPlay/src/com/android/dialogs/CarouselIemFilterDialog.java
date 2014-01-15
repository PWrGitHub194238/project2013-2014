package com.android.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.android.multiplay.R;


public class CarouselIemFilterDialog extends AlertDialogs implements OnCheckedChangeListener, TextWatcher {

	private static final String VIEW_ID = "viewID";
	private static View dialogInnerView = null;
	
	private Map<String,String> returnedData = null;
	
	public static final String DEVICE_NAME = "device_name";
	public static final String DEVICE_IS_STORED = "device_is_stored";

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
	
	private Switch s_connections_activity_connection_type_switch = null;
	private boolean s_connections_activity_connection_type_switch_state = false;
	private RelativeLayout rl_connections_activity_wifi_configurator_layout = null;
	private RelativeLayout rl_connections_activity_bt_configurator_layout = null;
	private ImageView iv_connections_activity_icon_wifi = null;
	private ImageView iv_connections_activity_icon_bt = null;
	private EditText et_connections_activity_device_name = null;
	private EditText et_connections_activity_device_ip = null;
	private EditText et_connections_activity_device_port = null;
	private EditText et_connections_activity_device_uuid = null;
	private EditText et_connections_activity_device_mac = null;
	private CheckBox cb_save_connection = null;

    public static CarouselIemFilterDialog newInstance( Integer titleIconID, Integer titleID, View view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	CarouselIemFilterDialog dialog = new CarouselIemFilterDialog();

        Bundle args = AlertDialogs.newInstance(titleIconID, titleID, null, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();

        args.putInt(CarouselIemFilterDialog.VIEW_ID, ( view != null ) ? view.getId() : 0);

        dialog.setArguments(args);
        return dialog;
    }

	@Override
	public void buildDialogContent(Builder builder) {
		Integer argsID = super.getArguments().getInt(CarouselIemFilterDialog.VIEW_ID);
		if ( argsID !=  0 ) {
			builder.setView(dialogInnerView);
			dialogInnerViewLogic();
			loadCurrentView(false);
		}
	}

	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, View view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		CarouselIemFilterDialog dialog = CarouselIemFilterDialog.newInstance(titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
	
    public static View getViewFromResource(Activity activity, int id) {
    	dialogInnerView = activity.getLayoutInflater().inflate(id, null);
		return dialogInnerView;
    }
    
    public void dialogInnerViewLogic() {
    	s_connections_activity_connection_type_switch = (Switch) dialogInnerView.findViewById(
    			R.id.s_connections_activity_connection_type_switch);
    	
    	if (s_connections_activity_connection_type_switch != null) {
	    	loadCurrentView(s_connections_activity_connection_type_switch.isChecked());
	    	
	    	s_connections_activity_connection_type_switch.setOnCheckedChangeListener(this);

	    	rl_connections_activity_wifi_configurator_layout = (RelativeLayout) dialogInnerView.findViewById(
	    			R.id.rl_connections_activity_wifi_configurator_layout);
	    	rl_connections_activity_bt_configurator_layout = (RelativeLayout) dialogInnerView.findViewById(
	    			R.id.rl_connections_activity_bt_configurator_layout);
	    	
	    	iv_connections_activity_icon_wifi = (ImageView) dialogInnerView.findViewById(
	    			R.id.iv_connections_activity_icon_wifi);
	    	iv_connections_activity_icon_bt = (ImageView) dialogInnerView.findViewById(
	    			R.id.iv_connections_activity_icon_bt);

	    	
	    	et_connections_activity_device_ip = (EditText) dialogInnerView.findViewById(
	    			R.id.et_connections_activity_device_ip);
	    	et_connections_activity_device_ip.setTag(DEVICE_IP);
	    	et_connections_activity_device_ip.addTextChangedListener(this);

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
	    	
	    	returnedData = new HashMap<String,String>();
    	}
    }
    
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
		super.getPositiveButton().setEnabled(false);
	//	super.getNeutralButton().setBackgroundResource(R.drawable.connections_activity_icon_bt_on);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean switchState) {
		loadCurrentView(switchState);
	}
	
	private void loadCurrentView(boolean switchState) {
		s_connections_activity_connection_type_switch_state = switchState;
		if (s_connections_activity_connection_type_switch != null && rl_connections_activity_wifi_configurator_layout != null) {
			if (switchState == false) {
				rl_connections_activity_wifi_configurator_layout.setVisibility(RelativeLayout.VISIBLE);
				iv_connections_activity_icon_wifi.setBackgroundResource(WIFI_ICON_ON);
				rl_connections_activity_bt_configurator_layout.setVisibility(RelativeLayout.INVISIBLE);
				iv_connections_activity_icon_bt.setBackgroundResource(BT_ICON_OFF);
			} else {
				rl_connections_activity_wifi_configurator_layout.setVisibility(RelativeLayout.INVISIBLE);
				iv_connections_activity_icon_wifi.setBackgroundResource(WIFI_ICON_OFF);
				rl_connections_activity_bt_configurator_layout.setVisibility(RelativeLayout.VISIBLE);
				iv_connections_activity_icon_bt.setBackgroundResource(BT_ICON_ON);
			}
		}
	}

	public final Map<String, String> getReturnedData() {
		return returnedData;
	}

	public final void setReturnedData(Map<String, String> returnedData) {
		this.returnedData = returnedData;
	}
	

	public final boolean isS_connections_activity_connection_type_switch_state() {
		return s_connections_activity_connection_type_switch_state;
	}

	public final View getDialogInnerView() {
		return dialogInnerView;
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		if ( super.getPositiveButton() != null ) {
			boolean isValid = true;
	
			Log.d("Dialogs","Validatation");
	
			isValid &= validation(et_connections_activity_device_name,PATTERN_NAME,PATTERN_NAME_ERROR);
		
			if ( s_connections_activity_connection_type_switch_state == false ) {
				isValid &= validation(et_connections_activity_device_ip,PATTERN_IP,PATTERN_IP_ERROR);
				isValid &= validation(et_connections_activity_device_port,PATTERN_PORT,PATTERN_PORT_ERROR);
			} else {
				isValid &= validation(et_connections_activity_device_uuid,PATTERN_UUID,PATTERN_UUID_ERROR);
				isValid &= validation(et_connections_activity_device_mac,PATTERN_UUID,PATTERN_UUID_ERROR);
				isValid &= validation(et_connections_activity_device_mac,PATTERN_MAC,PATTERN_MAC_ERROR);
			}
	
			returnedData.put(DEVICE_IS_STORED, (cb_save_connection.isChecked())?
					Boolean.toString(true):Boolean.toString(false));
			super.getPositiveButton().setEnabled(isValid);
		}
	}
	
	private boolean validation(EditText toValid, Pattern regexp, String raiseError) {
		String text = toValid.getText().toString();
		String tag = toValid.getTag().toString();
		
		Log.d("Dialogs",tag+" validatation");
		
		if (regexp.matcher(text).matches() == true) {
			returnedData.put(tag, text);
			Log.d("Dialogs","validatation: OK ("+text+")");
			return true;
		} else {
			toValid.setError(raiseError);
			Log.d("Dialogs",tag+" validatation: FAIL");
			return false;
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