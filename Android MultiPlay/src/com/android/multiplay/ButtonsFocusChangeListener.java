package com.android.multiplay;

import android.app.Activity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class ButtonsFocusChangeListener implements OnFocusChangeListener {

	private TextView title_of_selected_item = null;
	private TextView describtion_of_selected_item = null;
	private int sourseID;
	
	public ButtonsFocusChangeListener( Activity activity, int sourseID ) {
		this.sourseID = sourseID;
		title_of_selected_item = (TextView) activity.findViewById(R.id.tv_title_of_selected_item);
		describtion_of_selected_item = (TextView) activity.findViewById(R.id.tv_describtion_of_selected_item);
	}
	@Override
	public void onFocusChange( View view, boolean hasFocus ) {
		if ( hasFocus ) {
			switch (sourseID) {
			case R.id.b_main_activity_multiplay_explorer_icon:
				multiplay_explorer_hasFocus();
				break;
			case R.id.b_main_activity_system_controller_icon:
				system_controller_hasFocus();
				break;
			case R.id.b_main_activity_help_icon:
				help_hasFocus();
				break;
			case R.id.b_main_activity_options_icon:
				options_hasFocus();
				break;
			case R.id.b_options_activity_connecions_icon:
				connections_hasFocus();
				break;
			case R.id.b_options_activity_controllers_icon:
				controllers_hasFocus();
				break;
			case R.id.b_options_activity_resetdata_icon:
				resetdata_hasFocus();
				break;
			}
		}
	}
	
	private void multiplay_explorer_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_blue,
				R.string.tv_option_0_title_in_main_activity,
				R.drawable.activity_title_glow_blue,
				R.drawable.main_activity_0_title_big_background_icon,
				R.string.tv_option_0_title_description_in_main_activity);
	}
	
	private void system_controller_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_red,
				R.string.tv_option_1_title_in_main_activity,
				R.drawable.activity_title_glow_red,
				R.drawable.main_activity_1_title_big_background_icon,
				R.string.tv_option_1_title_description_in_main_activity);
	}

	private void help_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_green,
				R.string.tv_option_2_title_in_main_activity,
				R.drawable.activity_title_glow_green,
				R.drawable.main_activity_2_title_big_background_icon,
				R.string.tv_option_2_title_description_in_main_activity);
	}
	
	private void options_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_pink,
				R.string.tv_option_3_title_in_main_activity,
				R.drawable.activity_title_glow_pink,
				R.drawable.main_activity_3_title_big_background_icon,
				R.string.tv_option_3_title_description_in_main_activity);
	}
	
	private void connections_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_blue,
				R.string.tv_option_0_title_in_options_activity,
				R.drawable.activity_title_glow_blue,
				R.drawable.main_activity_0_title_big_background_icon,
				R.string.tv_option_0_title_description_in_options_activity);
	}
	
	private void controllers_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_red,
				R.string.tv_option_1_title_in_options_activity,
				R.drawable.activity_title_glow_red,
				R.drawable.main_activity_1_title_big_background_icon,
				R.string.tv_option_1_title_description_in_options_activity);
	}
	
	private void resetdata_hasFocus() {
		hasFocus(
				R.drawable.activity_title_icon_green,
				R.string.tv_option_2_title_in_options_activity,
				R.drawable.activity_title_glow_green,
				R.drawable.main_activity_2_title_big_background_icon,
				R.string.tv_option_2_title_description_in_options_activity);
	}
	
	private void hasFocus(int titleIconID, int titleTextID, int titleBackgroundID, int descBackgroundID, int descTextID ) {
		title_of_selected_item.setCompoundDrawablesWithIntrinsicBounds(
				titleIconID, 0, 0, 0);
		title_of_selected_item.setText(titleTextID);
		title_of_selected_item.setBackgroundResource(titleBackgroundID);
		describtion_of_selected_item.setBackgroundResource(descBackgroundID);
		describtion_of_selected_item.setText(descTextID);
	}
}
