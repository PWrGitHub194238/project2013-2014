package com.android.multiplay;

import android.app.Activity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class MainActivityFocusChangeListener implements OnFocusChangeListener {

	private TextView title_of_selected_item = null;
	private TextView describtion_of_selected_item = null;
	private int sourseID;
	
	public MainActivityFocusChangeListener( Activity activity, int sourseID ) {
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
			}
		}
	}
	
	private void multiplay_explorer_hasFocus() {
		title_of_selected_item.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.main_activity_0_title_icon, 0, 0, 0);
		title_of_selected_item.setText(R.string.option_0_title_in_main_activity);
		title_of_selected_item.setBackgroundResource(R.drawable.main_activity_0_title_glow);
		describtion_of_selected_item.setBackgroundResource(R.drawable.main_activity_0_title_big_background_icon);
		describtion_of_selected_item.setText(R.string.option_0_title_description_in_main_activity);
	}
	
	private void system_controller_hasFocus() {
		title_of_selected_item.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.main_activity_1_title_icon, 0, 0, 0);
		title_of_selected_item.setText(R.string.option_1_title_in_main_activity);
		title_of_selected_item.setBackgroundResource(R.drawable.main_activity_1_title_glow);
		describtion_of_selected_item.setBackgroundResource(R.drawable.main_activity_1_title_big_background_icon);
		describtion_of_selected_item.setText(R.string.option_1_title_description_in_main_activity);
	}
	
	private void help_hasFocus() {
		title_of_selected_item.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.main_activity_2_title_icon, 0, 0, 0);
		title_of_selected_item.setText(R.string.option_2_title_in_main_activity);
		title_of_selected_item.setBackgroundResource(R.drawable.main_activity_2_title_glow);
		describtion_of_selected_item.setBackgroundResource(R.drawable.main_activity_2_title_big_background_icon);
		describtion_of_selected_item.setText(R.string.option_2_title_description_in_main_activity);
	}
	
	private void options_hasFocus() {
		title_of_selected_item.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.main_activity_3_title_icon, 0, 0, 0);
		title_of_selected_item.setText(R.string.option_3_title_in_main_activity);
		title_of_selected_item.setBackgroundResource(R.drawable.main_activity_3_title_glow);
		describtion_of_selected_item.setBackgroundResource(R.drawable.main_activity_3_title_big_background_icon);
		describtion_of_selected_item.setText(R.string.option_3_title_description_in_main_activity);
	}
}
