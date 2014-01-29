package com.android.multiplay;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.application.MultiPlayApplication;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.WelcomeDialogCheckRequirements_1Step;
import com.android.dialogs.WelcomeDialogCheckRequirements_2Step;
import com.android.dialogs.WelcomeDialogCheckRequirements_3Step;
import com.android.dialogs.elements.DialogListCore;
import com.android.multiplay.fragments.ConnectionPanel;
import com.android.services.ConnectionService;

/** Main {@link Activity} for MultiPlay application.
 * 
 * When you first start the device scans for the presence of service that uses the application by calling TODO. 
 * In their absence, the relevant part of the software functionality will be disabled. 
 * Re-test the device can be called from the {@link OptionsActivity}. 
 * Activity implements {@link DialogButtonClickListener} which enables it to respond 
 * appropriately to closing the dialog box by tapping one of three buttons (see {@link AlertDialogs}). 
 * In the activity's background works {@link ConnectionService} which is responsible for the initial 
 * configuration of the application. Activity consist of menu elements which lead to other activities:
 * 
 * <ul>
 * 	<li>{@link MultiplayExplorerActivity} </li>
 * 	<li>{@link SystemControllerActivity} </li>
 * 	<li>{@link OptionsActivity} </li>
 * 	<li>{@link HelpActivity} </li>
 * </ul>
 * 
 * @see AlertDialogs
 * @see DialogButtonClickListener
 * @see ConnectionService
 *
 */
public class MainActivity extends Activity implements DialogButtonClickListener {

	
	
////////////////////Fields
	
	
	
	/** Menu button. It leaves to next activity: {@link MultiplayExplorerActivity}.
	 * 
	 * This button is not active if no wireless or bluetooth connections were found.
	 * On click it calls method {@link #multiplay_explorer_OnClick(Viev)}.
	 */
	private ImageButton b_multiplay_explorer = null;
	
	/** Menu button. It leaves to next activity: {@link SystemControllerActivity}.
	 * 
	 * This button is not active if no wireless or bluetooth connections were found.
	 * On click it call method {@link #system_controller_OnClick(View)}.
	 */
	private ImageButton b_system_controller = null;
	
	/** Menu button. It leaves to next activity: {@link HelpActivity}.
	 * 
	 * On click it call method {@link #help_OnClick(Viev)}.
	 */
	private ImageButton b_help = null;
	
	/** Menu button. It leaves to next activity: {@link OptionsActivity}.
	 * 
	 * On click it call method {@link #options_OnClick(Viev)}.
	 */
	private ImageButton b_options = null;
	
	private ImageButton b_exit = null;

	
	
////////////////////Methods
	
	
	
	/** Called when the activity is starting.
	 * 
	 * TODO
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initB_exit(R.id.b_main_activity_icon_exit);
		initB_multiplay_explorer(R.id.b_main_activity_multiplay_explorer_icon);
		initB_system_controller(R.id.b_main_activity_system_controller_icon);
		initB_help(R.id.b_main_activity_help_icon);
		initB_options(R.id.b_main_activity_options_icon);
		
		if (MultiPlayApplication.isFirstStart()) {
			WelcomeDialogCheckRequirements_1Step.showDialog(this,
					MainActivity.DialogList.TAG_WELCOME_1,
					DialogListCore.ID_TITLE_ICON_APPLICATION,
					MainActivity.DialogList.ID_TITLE_WELCOME_1,
					WelcomeDialogCheckRequirements_1Step.getViewFromResource(this,R.layout.dialog_check_requirements_1),
					DialogListCore.ID_BUTTON_NEXT,
					null,
					null);
			}
		init();
	
	}

	/** Called on activity resume.
	 * 
	 * TODO
	 * 
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Main", "Resume");
		init();
	}
	
	/** Called on activity restart.
	 * 
	 * TODO
	 * 
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		init();
	}

	/** Called before destroying activity.
	 * 
	 * TODO
	 * 
	 */
	@Override 
	protected void onDestroy() {
		super.onDestroy();
		MultiPlayApplication.getDbHelper().closeConnection();
	}
	
	
	
//////////////////// Methods for onClick events

	
	
	/** Method starts new activity: {@link OptionsActivity}.
	 * 
	 * It can be trigger by tapping {@link #b_options} button.
	 * 
	 * @param view TODO
	 */
	public void exit_OnClick(View view) {
		super.finish();
	}
	
	/** Method starts new activity: {@link MultiplayExplorerActivity}.
	 * 
	 * It can be trigger by tapping {@link #b_multiplay_explorer} button.
	 * 
	 * @param view TODO
	 */
	public void multiplay_explorer_OnClick(View view) {
		super.startActivity(
				new Intent(this, MultiplayExplorerActivity.class));
	}
	
	/** Method starts new activity: {@link SystemControllerActivity}.
	 * 
	 * It can be trigger by tapping {@link #b_system_controller} button.
	 * 
	 * @param view TODO
	 */
	public void system_controller_OnClick(View view) {
		super.startActivity(
				new Intent(this, SystemControllerActivity.class));
	}
	
	/** Method starts new activity: {@link HelpActivity}.
	 * 
	 * It can be trigger by tapping {@link #b_help} button.
	 * 
	 * @param view TODO
	 */
	public void help_OnClick(View view) {
		super.startActivity(
				new Intent(this, HelpActivity.class));
	}
	
	/** Method starts new activity: {@link OptionsActivity}.
	 * 
	 * It can be trigger by tapping {@link #b_options} button.
	 * 
	 * @param view TODO
	 */
	public void options_OnClick(View view) {
		super.startActivity(
				new Intent(this, OptionsActivity.class));
	}
	
	
	
////////////////////DialogButtonClickListener's methods for dialog interaction
	
	
	
	public final static class DialogList {

		private static final String PACKAGE = "com.android.multiplay.mainactivity";
		
		public static final String TAG_WELCOME_1 = PACKAGE + "WELCOME_1";
		public static final String TAG_WELCOME_2 = PACKAGE + "WELCOME_2";
		public static final String TAG_WELCOME_3 = PACKAGE + "WELCOME_3";

		public static final String TAG_BLUETOOTH_CONNECTED = PACKAGE + "BLUETOOTH_CONNECTED";
		public static final String TAG_BLUETOOTH_ENABLED = PACKAGE + "BLUETOOTH_ENABLED";
		public static final String TAG_WIFI_CONNECTED = PACKAGE + "WIFI_CONNECTED";
		public static final String TAG_WIFI_ENABLED = PACKAGE + "WIFI_ENABLED";
		
		public static final int ID_TITLE_WELCOME_1 =
				R.string.dialog_ID_TITLE_WELCOME_1;
		public static final int ID_TITLE_WELCOME_2 =
				R.string.dialog_ID_TITLE_WELCOME_2;
		public static final int ID_TITLE_WELCOME_3 =
				R.string.dialog_ID_TITLE_WELCOME_3;
		public static final int ID_TITLE_BLUETOOTH_CONNECTED =
				R.string.dialog_ID_TITLE_BLUETOOTH_CONNECTED;
		public static final int ID_TITLE_BLUETOOTH_ENABLED =
				R.string.dialog_ID_TITLE_BLUETOOTH_ENABLED;
		public static final int ID_TITLE_WIFI_CONNECTED =
				R.string.dialog_ID_TITLE_WIFI_CONNECTED;
		public static final int ID_TITLE_WIFI_ENABLED =
				R.string.dialog_ID_TITLE_WIFI_ENABLED;
		
		public static final int ID_MESSAGE_BLUETOOTH_CONNECTED =
				R.string.dialog_ID_MESSAGE_BLUETOOTH_CONNECTED;
		public static final int ID_MESSAGE_BLUETOOTH_ENABLED =
				R.string.dialog_ID_MESSAGE_BLUETOOTH_ENABLED;
		public static final int ID_MESSAGE_WIFI_CONNECTED =
				R.string.dialog_ID_MESSAGE_WIFI_CONNECTED;
		public static final int ID_MESSAGE_WIFI_ENABLED =
				R.string.dialog_ID_MESSAGE_WIFI_ENABLED;
	}
	
	/** Method triggered by tapping on a {@link DialogInterface#BUTTON_POSITIVE}.
	 * 
	 * Method handles {@link AlertDialogs#positiveButton} tapping and executes appropriate code 
	 * based on received dialog tag (see {@link AlertDialogs#showDialog(Activity, String, Integer, Integer, Integer, Integer, Integer, Integer)}).
	 * 
	 * It handles dialogs with tags:
	 * <ul>
	 * 	<li>{@link MainActivity.DialogList#TAG_BLUETOOTH_CONNECTED} - if {@link Context#BLUETOOTH_SERVICE} is enabled and it is set 
	 * as a service with the highest priority (see {@link ConnectionsActivity#b_connections_activity_bluetooth_switcher}) 
	 * method redirects user to {@link ConnectionsActivity}. It also automatically trigger {@link ConnectionsActivity#bluetooth_switcher_onClick}
	 * to search for bluetooth devices. Other options are handle by other methods: {@link #onDialogNeutralClick(DialogFragment)} and 
	 * {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * 
	 * 	<li>{@link MainActivity.DialogList#TAG_BLUETOOTH_ENABLED} - if {@link Context#BLUETOOTH_SERVICE} is enabled by tapping {@link ConnectionPanel#b_bluetooth_switch}
	 * user can be redirected to {@link ConnectionsActivity} in the same way as in the above case. Other options are handle by other methods: 
	 * {@link #onDialogNeutralClick(DialogFragment)} and 
	 * {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * 
	 * 	<li>{@link MainActivity.DialogList#TAG_WIFI_CONNECTED} - if {@link Context#WIFI_SERVICE} and/or {@link Context#WIFI_P2P_SERVICE} is enabled and it is set 
	 * as a service with the highest priority (see {@link ConnectionsActivity#b_connections_activity_wireless_switcher}) 
	 * method redirects user to {@link ConnectionsActivity}. It also automatically trigger {@link ConnectionsActivity#wireless_switcher_onClick}
	 * to search for available devices by using {@link Context#WIFI_P2P_SERVICE} or {@link ConnectionsActivity#new_connection_onClick} otherwise. 
	 * Other options are handle by other methods: {@link #onDialogNeutralClick(DialogFragment)} and {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * 
	 * 	<li>{@link MainActivity.DialogList#TAG_WIFI_ENABLED} - if {@link Context#WIFI_SERVICE} is enabled by tapping {@link ConnectionPanel#b_wireless_network_switch}
	 * user can be redirected to {@link ConnectionsActivity} in the same way as in the above case. Other options are handle by other methods: 
	 * {@link #onDialogNeutralClick(DialogFragment)} and 
	 * {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * </ul>
	 * 
	 * @see DialogListCore
	 * @see MainActivity.DialogList
	 * @see AlertDialogs
	 * @see DialogButtonClickListener
	 * 
	 */
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();

		if ( dialogTag.equals(MainActivity.DialogList.TAG_BLUETOOTH_CONNECTED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_BLUETOOTH_ENABLED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WIFI_CONNECTED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WIFI_ENABLED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WELCOME_1)) {
			WelcomeDialogCheckRequirements_2Step.showDialog(this,
					MainActivity.DialogList.TAG_WELCOME_2,
					DialogListCore.ID_TITLE_ICON_APPLICATION,
					MainActivity.DialogList.ID_TITLE_WELCOME_2,
					WelcomeDialogCheckRequirements_1Step.getViewFromResource(this,R.layout.dialog_check_requirements_2),
					DialogListCore.ID_BUTTON_NEXT,
					null,
					null);
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WELCOME_2)) {
			WelcomeDialogCheckRequirements_3Step.showDialog(this,
					MainActivity.DialogList.TAG_WELCOME_3,
					DialogListCore.ID_TITLE_ICON_APPLICATION,
					MainActivity.DialogList.ID_TITLE_WELCOME_3,
					WelcomeDialogCheckRequirements_1Step.getViewFromResource(this,R.layout.dialog_check_requirements_3),
					DialogListCore.ID_BUTTON_FINISH,
					null,
					DialogListCore.ID_BUTTON_ADD);
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WELCOME_3)) {
			//TODO
			return;
		}
	}
	
	/** Method triggered by tapping on a {@link DialogInterface#BUTTON_NEUTRAL}.
	 * 
	 * Method handles {@link AlertDialogs#neutralButton} tapping and executes appropriate code 
	 * based on received dialog tag (see {@link AlertDialogs#showDialog(Activity, String, Integer, Integer, Integer, Integer, Integer, Integer)}).
	 * 
	 * It handles dialogs with tags:
	 * <ul>
	 * 	<li>{@link MainActivity.DialogList#TAG_BLUETOOTH_CONNECTED} - if {@link Context#BLUETOOTH_SERVICE} is enabled and it is set 
	 * as a service with the highest priority (see {@link ConnectionsActivity#b_connections_activity_bluetooth_switcher}) 
	 * and also there is default bluetooth connection stored in application memory method will check it and set as a default for entire application. 
	 * Other options are handle by other methods: {@link #onDialogPositiveClick(DialogFragment)} and 
	 * {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * 
	 * 	<li>{@link MainActivity.DialogList#TAG_BLUETOOTH_ENABLED} - if {@link Context#BLUETOOTH_SERVICE} is enabled by tapping {@link ConnectionPanel#b_bluetooth_switch}
	 * and also there is default bluetooth connection stored in application memory method will check it and set as a default for entire application. 
	 * Other options are handle by other methods: {@link #onDialogPositiveClick(DialogFragment)} and 
	 * {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * 
	 * 	<li>{@link MainActivity.DialogList#TAG_WIFI_CONNECTED} - if {@link Context#WIFI_SERVICE} and/or {@link Context#WIFI_P2P_SERVICE} is enabled and it is set 
	 * as a service with the highest priority (see {@link ConnectionsActivity#b_connections_activity_wireless_switcher}) 
	 * and also there is default wireless connection stored in application memory method will check it and set as a default for entire application.
	 * Other options are handle by other methods: {@link #onDialogNeutralClick(DialogFragment)} and {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * 	<li>{@link MainActivity.DialogList#TAG_WIFI_ENABLED} - if {@link Context#WIFI_SERVICE} is enabled by tapping {@link ConnectionPanel#b_wireless_network_switch}
	 * and also there is default wireless connection stored in application memory method will check it and set as a default for entire application. 
	 * Other options are handle by other methods: {@link #onDialogPositiveClick(DialogFragment)} and 
	 * {@link #onDialogNegativeClick(DialogFragment)}.</li>
	 * </ul>
	 * 
	 *
	 * @see DialogListCore
	 * @see MainActivity.DialogList
	 * @see AlertDialogs
	 * @see DialogButtonClickListener
	 * 
	 */
	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();

		if ( dialogTag.equals(MainActivity.DialogList.TAG_BLUETOOTH_CONNECTED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_BLUETOOTH_ENABLED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WIFI_CONNECTED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WIFI_ENABLED)) {
			//TODO
			return;
		}
	}
	
	/** Method triggered by tapping on a {@link DialogInterface#BUTTON_NEGATIVE}.
	 * 
	 * Method handles {@link AlertDialogs#negativeButton} tapping and executes appropriate code 
	 * based on received dialog tag (see {@link AlertDialogs#showDialog(Activity, String, Integer, Integer, Integer, Integer, Integer, Integer)}).
	 * 
	 * It handles dialogs with tags:
	 * <ul>
	 * 	<li>{@link MainActivity.DialogList#TAG_BLUETOOTH_CONNECTED},</li>
	 * 	<li>{@link MainActivity.DialogList#TAG_BLUETOOTH_ENABLED},</li>
	 * 	<li>{@link MainActivity.DialogList#TAG_WIFI_CONNECTED},</li>
	 * 	<li>{@link MainActivity.DialogList#TAG_WIFI_ENABLED}.</li>
	 * </ul>
	 * 
	 * When above dialogs are called and users leaves that dialogs without any action or by tapping {@link DialogListCore#ID_BUTTON_CANCEL}
	 * this method are triggered and it returns no data.
	 * 
	 * @see DialogListCore
	 * @see MainActivity.DialogList
	 * @see AlertDialogs
	 * @see DialogButtonClickListener
	 * 
	 */
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();

		if ( dialogTag.equals(MainActivity.DialogList.TAG_BLUETOOTH_CONNECTED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_BLUETOOTH_ENABLED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WIFI_CONNECTED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WIFI_ENABLED)) {
			//TODO
			return;
		}
		
		if ( dialogTag.equals(MainActivity.DialogList.TAG_WELCOME_3)) {
			//TODO
			return;
		}
	}

	
	
////////////////////Methods for initialize objects
	
	
	
	/** Default initial method that constrains all tasks that should be done either {@link #onCreate(Bundle)} or {@link #onRestart()}.
	 *  It requests back focus to unfocused any menu items.
	 */
	private void init() {
		b_exit.requestFocus();
	}
	
	/** Initialize method that links {@link #b_multiplay_explorer} object with correct {@link View} by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)}.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_multiplay_explorer( int id) {
		this.b_multiplay_explorer = (ImageButton) super.findViewById(id);
		this.b_multiplay_explorer.setOnFocusChangeListener(
				new ButtonsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_system_controller} object with correct {@link View} by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)}.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_system_controller( int id ) {
		this.b_system_controller = (ImageButton) super.findViewById(id);
		this.b_system_controller.setOnFocusChangeListener(
				new ButtonsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_help} object with correct {@link View} by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)}.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_help( int id ) {
		this.b_help = (ImageButton) super.findViewById(id);
		this.b_help.setOnFocusChangeListener(
				new ButtonsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_options} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)}.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_options( int id ) {
		this.b_options = (ImageButton) super.findViewById(id);
		this.b_options.setOnFocusChangeListener(
				new ButtonsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_exit} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)}.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_exit( int id ) {
		this.b_exit = (ImageButton) super.findViewById(id);
		this.b_exit.setOnFocusChangeListener(
				new ButtonsFocusChangeListener(this,id));
		b_exit.requestFocus();
	}
	
}