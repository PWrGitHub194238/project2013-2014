package com.android.multiplay;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.android.application.MultiPlayApplication;
import com.android.carousel.CarouselActivity;

public class SystemControllerActivity extends CarouselActivity {

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
				R.layout.activity_system_controller, 
				R.id.rl_carousel_system_controllers, mainLayoutParams, R.color.transparent,
				0.7f, 1f, CarouselActivity.Color.GREEN);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MultiPlayApplication.closeThread();
	}
	
	
}