package com.android.multiplay;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.android.application.MultiPlayApplication;
import com.android.carousel.CarouselActivity;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.elements.DialogListCore;

public class SystemControllerActivity extends CarouselActivity {

	private RelativeLayout.LayoutParams mainLayoutParams = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if (MultiPlayApplication.getMainNetworkConfiguration() != null) {
			
			mainLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			mainLayoutParams.setMargins(0, 0, 0, 40);
			mainLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			
			super.initCarouselView(
					R.layout.activity_system_controller, 
					R.id.rl_carousel_system_controllers, mainLayoutParams, R.color.transparent,
					0.7f, 1f, CarouselActivity.Color.GREEN);
			
		} else {
			
			AlertDialogs.showDialog(this,
					SystemControllerActivity.DialogList.TAG_NO_CONNECTION_FOUND,
					DialogListCore.IT_TITLE_ICON_WARNING,
					SystemControllerActivity.DialogList.ID_TITLE_NO_CONNECTION_FOUND,
					SystemControllerActivity.DialogList.ID_MESSAGE_NO_CONNECTION_FOUND,
					DialogListCore.ID_BUTTON_OPTIONS,
					null,
					DialogListCore.ID_BUTTON_CANCEL);
		}
	}

	public final class DialogList {
		
		private static final String PACKAGE = "com.android.multiplay.systemcontroleractivity";
		
		public static final String TAG_NO_CONNECTION_FOUND = PACKAGE + "NO_CONNECTION_FOUND";
		
		public static final int ID_TITLE_NO_CONNECTION_FOUND =
				R.string.dialog_ID_TITLE_NO_CONNECTION_FOUND;
		
		public static final int ID_MESSAGE_NO_CONNECTION_FOUND =
				R.string.dialog_ID_MESSAGE_NO_CONNECTION_FOUND;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MultiPlayApplication.closeThread();
	}
	
	
}