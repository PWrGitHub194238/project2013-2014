package com.android.multiplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.android.extendedWidgets.ImageToogleButton;

public class MainActivity extends Activity {

	private ImageButton b_multiplay_explorer = null;
	private ImageButton b_system_controller = null;
	private ImageButton b_help = null;
	private ImageButton b_options = null;
	
	private ImageToogleButton b_wireless_network_switch = null;
	private ImageToogleButton b_bluetooth_switch = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initB_multiplay_explorer(R.id.b_multiplay_explorer_icon);
		initB_system_controller(R.id.b_system_controller_icon);
		initB_help(R.id.b_help_icon);
		initB_options(R.id.b_options_icon);
		
		initB_bluetooth_switch(R.id.b_bluetooth_switch);
		initB_wireless_network_switch(R.id.b_wireless_network_switch);
		
		init();
	}

	
//////////////////// Methods for onClick events
	
	
	
	public void multiplay_explorer_OnClick( View view ) {
		Intent intent = new Intent(this, MultiplayExplorerActivity.class);
		super.startActivity(intent);
	}
	
	public void system_controller_OnClick( View view ) {
		Intent intent = new Intent(this, SystemControllerActivity.class);
		super.startActivity(intent);
	}
	
	public void help_OnClick( View view ) {
		Intent intent = new Intent(this, HelpActivity.class);
		super.startActivity(intent);
	}
	
	public void options_OnClick( View view ) {
		Intent intent = new Intent(this, OptionsActivity.class);
		super.startActivity(intent);
	}
	
	public void toogleBluetooth_onClick( View view ) {
		b_bluetooth_switch.setBackgroundResource(R.drawable.main_activity_button_pending);
		b_bluetooth_switch.toogleButton();
	}
	
	public void toogleWirelessNetwork_onClick( View view ) {
		b_wireless_network_switch.setBackgroundResource(R.drawable.main_activity_button_pending);
		b_wireless_network_switch.toogleButton();
	}
	
	
	
////////////////////Methods for init objects
	
	
	
	private void init() {
		super.findViewById(R.id.tv_title_of_selected_item).requestFocus();
	}
	
	private void initB_multiplay_explorer( int id) {
		this.b_multiplay_explorer = (ImageButton) super.findViewById(id);
		this.b_multiplay_explorer.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	private void initB_system_controller( int id ) {
		this.b_system_controller = (ImageButton) super.findViewById(id);
		this.b_system_controller.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	private void initB_help( int id ) {
		this.b_help = (ImageButton) super.findViewById(id);
		this.b_help.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	private void initB_options( int id ) {
		this.b_options = (ImageButton) super.findViewById(id);
		this.b_options.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	private void initB_wireless_network_switch( int id ) {
		this.b_wireless_network_switch = (ImageToogleButton) super.findViewById(id);
	}

	private void initB_bluetooth_switch( int id ) {
		this.b_bluetooth_switch = (ImageToogleButton) super.findViewById(id);
	}
}