package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.application.MultiPlayApplication;
import com.android.database.tables.General;
import com.android.multiplay.R;

/** Displays the alert dialog box that contains {@link ScrollView}. It shows 2nd welcome screen to new user.
 * 
 * @author tomasz
 *
 */
public class WelcomeDialogCheckRequirements_2Step extends ScrollViewDialog implements OnClickListener {
	
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_bluetooth_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_bluetooth_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_bluetooth_help = null;

	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_bluetooth_low_energy_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_bluetooth_low_energy_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_bluetooth_low_energy_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_wifi_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_wifi_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_wifi_help = null;
 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_wifi_direct_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_wifi_direct_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_wifi_direct_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_accelerometer_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_accelerometer_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_accelerometer_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_gyroscope_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_gyroscope_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_gyroscope_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_gravity_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_gravity_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_gravity_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_rotation_vector_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_rotation_vector_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_rotation_vector_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_linear_acceleration_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_linear_acceleration_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_linear_acceleration_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_microphone_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_microphone_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_microphone_help = null;
	 
	/**
	 * 
	 */
	private ImageView iv_dialog_welcome_requirements_multitouch_true_false = null;
	/**
	 * 
	 */
	private TextView tv_dialog_welcome_requirements_multitouch_detail = null;
	/**
	 * 
	 */
	private ImageButton ib_dialog_welcome_requirements_multitouch_help = null;
	
	/**
	 * 
	 */
	private String queryResult = null;
	
    /**
     * @param titleIconID
     * @param titleID
     * @param view
     * @param positiveButtonID
     * @param neutralButtonID
     * @param negativeButtonID
     * @return
     */
    protected static WelcomeDialogCheckRequirements_2Step newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	WelcomeDialogCheckRequirements_2Step dialog = new WelcomeDialogCheckRequirements_2Step();
        Bundle args = ScrollViewDialog.newInstance(titleIconID, titleID, view, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();
        
        dialog.setArguments(args);
        return dialog;
    }

	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewDialog#buildDialogContent(android.app.AlertDialog.Builder)
	 */
	@Override
	public void buildDialogContent(Builder builder) {
		super.buildDialogContent(builder);
		this.setCancelable(false);
		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.android.dialogs.AlertDialogs#onShow(android.content.DialogInterface)
	 */
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
		super.setWindowFullHorizontal();
	}

	/**
	 * @param activity
	 * @param dialogIDTag
	 * @param titleIconID
	 * @param titleID
	 * @param view
	 * @param positiveButtonID
	 * @param neutralButtonID
	 * @param negativeButtonID
	 */
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		WelcomeDialogCheckRequirements_2Step dialog = WelcomeDialogCheckRequirements_2Step.newInstance(
				titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
    
	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewDialog#dialogInnerViewLogic()
	 */
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();
		
		iv_dialog_welcome_requirements_bluetooth_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_bluetooth_true_false);
		tv_dialog_welcome_requirements_bluetooth_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_bluetooth_detail);
		ib_dialog_welcome_requirements_bluetooth_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_bluetooth_help);

		ib_dialog_welcome_requirements_bluetooth_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_5);

		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_bluetooth_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_bluetooth_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_bluetooth_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_bluetooth_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_bluetooth_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_bluetooth_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_disabled);
		}
		
		iv_dialog_welcome_requirements_bluetooth_low_energy_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_bluetooth_low_energy_true_false);
		tv_dialog_welcome_requirements_bluetooth_low_energy_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_bluetooth_low_energy_detail);
		ib_dialog_welcome_requirements_bluetooth_low_energy_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_bluetooth_low_energy_help);

		ib_dialog_welcome_requirements_bluetooth_low_energy_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_6);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_bluetooth_low_energy_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_bluetooth_low_energy_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_low_energy_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_bluetooth_low_energy_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_bluetooth_low_energy_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_low_energy_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_bluetooth_low_energy_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_bluetooth_low_energy_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_low_energy_disabled);
		}
		
		iv_dialog_welcome_requirements_wifi_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_wifi_true_false);
		tv_dialog_welcome_requirements_wifi_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_wifi_detail);
		ib_dialog_welcome_requirements_wifi_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_wifi_help);

		ib_dialog_welcome_requirements_wifi_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_7);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_wifi_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_wifi_detail.setText(R.string.tv_dialog_welcome_requirements_wifi_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_wifi_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_wifi_detail.setText(R.string.tv_dialog_welcome_requirements_wifi_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_wifi_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_wifi_detail.setText(R.string.tv_dialog_welcome_requirements_wifi_disabled);
		}
		
		iv_dialog_welcome_requirements_wifi_direct_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_wifi_direct_true_false);
		tv_dialog_welcome_requirements_wifi_direct_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_wifi_direct_detail);
		ib_dialog_welcome_requirements_wifi_direct_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_wifi_direct_help);

		ib_dialog_welcome_requirements_wifi_direct_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_8);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_wifi_direct_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_wifi_direct_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_wifi_direct_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_wifi_direct_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_wifi_direct_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_wifi_direct_detail.setText(R.string.tv_dialog_welcome_requirements_bluetooth_disabled);
		}
		
		iv_dialog_welcome_requirements_accelerometer_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_accelerometer_true_false);
		tv_dialog_welcome_requirements_accelerometer_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_accelerometer_detail);
		ib_dialog_welcome_requirements_accelerometer_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_accelerometer_help);

		ib_dialog_welcome_requirements_accelerometer_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_9);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_accelerometer_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_accelerometer_detail.setText(R.string.tv_dialog_welcome_requirements_accelerometer_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_accelerometer_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_accelerometer_detail.setText(R.string.tv_dialog_welcome_requirements_accelerometer_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_accelerometer_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_accelerometer_detail.setText(R.string.tv_dialog_welcome_requirements_accelerometer_disabled);
		}
		
		iv_dialog_welcome_requirements_gyroscope_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_gyroscope_true_false);
		tv_dialog_welcome_requirements_gyroscope_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_gyroscope_detail);
		ib_dialog_welcome_requirements_gyroscope_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_gyroscope_help);

		ib_dialog_welcome_requirements_gyroscope_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_10);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_gyroscope_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_gyroscope_detail.setText(R.string.tv_dialog_welcome_requirements_gyroscope_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_gyroscope_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_gyroscope_detail.setText(R.string.tv_dialog_welcome_requirements_gyroscope_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_gyroscope_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_gyroscope_detail.setText(R.string.tv_dialog_welcome_requirements_gyroscope_disabled);
		}
		
		iv_dialog_welcome_requirements_gravity_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_gravity_true_false);
		tv_dialog_welcome_requirements_gravity_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_gravity_detail);
		ib_dialog_welcome_requirements_gravity_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_gravity_help);

		ib_dialog_welcome_requirements_gravity_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_11);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_gravity_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_gravity_detail.setText(R.string.tv_dialog_welcome_requirements_gravity_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_gravity_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_gravity_detail.setText(R.string.tv_dialog_welcome_requirements_gravity_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_gravity_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_gravity_detail.setText(R.string.tv_dialog_welcome_requirements_gravity_disabled);
		}
		
		iv_dialog_welcome_requirements_rotation_vector_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_rotation_vector_true_false);
		tv_dialog_welcome_requirements_rotation_vector_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_rotation_vector_detail);
		ib_dialog_welcome_requirements_rotation_vector_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_rotation_vector_help);

		ib_dialog_welcome_requirements_rotation_vector_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_12);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_rotation_vector_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_rotation_vector_detail.setText(R.string.tv_dialog_welcome_requirements_rotation_vector_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_rotation_vector_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_rotation_vector_detail.setText(R.string.tv_dialog_welcome_requirements_rotation_vector_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_rotation_vector_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_rotation_vector_detail.setText(R.string.tv_dialog_welcome_requirements_rotation_vector_disabled);
		}
		
		iv_dialog_welcome_requirements_linear_acceleration_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_linear_acceleration_true_false);
		tv_dialog_welcome_requirements_linear_acceleration_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_linear_acceleration_detail);
		ib_dialog_welcome_requirements_linear_acceleration_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_linear_acceleration_help);

		ib_dialog_welcome_requirements_linear_acceleration_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_13);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_linear_acceleration_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_linear_acceleration_detail.setText(R.string.tv_dialog_welcome_requirements_linear_acceleration_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_linear_acceleration_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_linear_acceleration_detail.setText(R.string.tv_dialog_welcome_requirements_linear_acceleration_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_linear_acceleration_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_linear_acceleration_detail.setText(R.string.tv_dialog_welcome_requirements_linear_acceleration_disabled);
		}
		
		iv_dialog_welcome_requirements_microphone_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_microphone_true_false);
		tv_dialog_welcome_requirements_microphone_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_microphone_detail);
		ib_dialog_welcome_requirements_microphone_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_microphone_help);
		
		ib_dialog_welcome_requirements_microphone_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_14);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_microphone_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_microphone_detail.setText(R.string.tv_dialog_welcome_requirements_microphone_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_microphone_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_microphone_detail.setText(R.string.tv_dialog_welcome_requirements_microphone_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_microphone_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_microphone_detail.setText(R.string.tv_dialog_welcome_requirements_microphone_disabled);
		}
		
		iv_dialog_welcome_requirements_multitouch_true_false = (ImageView) dialogInnerView.findViewById(R.id.iv_dialog_welcome_requirements_multitouch_true_false);
		tv_dialog_welcome_requirements_multitouch_detail = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_requirements_multitouch_detail);
		ib_dialog_welcome_requirements_multitouch_help = (ImageButton) dialogInnerView.findViewById(R.id.ib_dialog_welcome_requirements_multitouch_help);

		ib_dialog_welcome_requirements_multitouch_help.setOnClickListener(this);
		
		queryResult = MultiPlayApplication.getMultiPlayRequirements().get(General.DBSchema.COLUMN_15);
		if ( queryResult.equals("2")) {
			iv_dialog_welcome_requirements_multitouch_true_false.setBackgroundResource(R.drawable.dialog_tick);
			tv_dialog_welcome_requirements_multitouch_detail.setText(R.string.tv_dialog_welcome_requirements_multitouch_enabled);
		} else if ( queryResult.equals("1")) {
			iv_dialog_welcome_requirements_multitouch_true_false.setBackgroundResource(R.drawable.dialog_warning);
			tv_dialog_welcome_requirements_multitouch_detail.setText(R.string.tv_dialog_welcome_requirements_multitouch_warning);
		} else if ( queryResult.equals("0")) {
			iv_dialog_welcome_requirements_multitouch_true_false.setBackgroundResource(R.drawable.dialog_cross);
			tv_dialog_welcome_requirements_multitouch_detail.setText(R.string.tv_dialog_welcome_requirements_multitouch_disabled);
		}
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
}