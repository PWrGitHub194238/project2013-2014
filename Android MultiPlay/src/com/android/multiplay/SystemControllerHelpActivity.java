package com.android.multiplay;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.android.application.ControllerDefinition;
import com.android.application.MultiPlayApplication;
import com.android.carousel.CarouselActivity;
import com.android.carousel.CarouselDataItem;
import com.android.dialogs.DialogButtonClickListener;

public class SystemControllerHelpActivity extends CarouselActivity implements DialogButtonClickListener {

	private RelativeLayout.LayoutParams mainLayoutParams = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			
		mainLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		mainLayoutParams.setMargins(0, 0, 0, 40);
		mainLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		super.initCarouselView(
				R.layout.activity_system_controller_help, 
				R.id.rl_carousel_system_controllers_help,
				R.id.iv_system_controllers_help_miniature_icon,
				R.id.tv_activity_subtitle_system_controllers_help,
				R.id.b_system_controllers_help_search,
				R.id.b_system_controllers_help_cancel, mainLayoutParams, R.color.transparent,
				0.7f, 1f, CarouselActivity.Color.BLUE);
	}

	public final class DialogList {
		
		private static final String PACKAGE = "com.android.multiplay.systemcontrolerhelpactivity";
		
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
	
	@Override
	protected Class<? extends Activity> toActivity(CarouselDataItem item) {
		return item.getHelperActivity();
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Intent intent = new Intent(this, ConnectionsActivity.class);
		String url = "http://androidmultiplay.url.ph/video_tutorials/scrennrecord.mp4";
		intent.putExtra("url", url);
		super.startActivity(intent);
		this.finish();
	}

	/* (non-Javadoc)
	 * @see com.android.carousel.CarouselActivity#chechRequirements(com.android.application.ControllerDefinition)
	 */
	@Override
	protected boolean chechRequirements(ControllerDefinition controller) {
		return true;
	}

	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		this.finish();
	}
}