package com.android.controllers.help.touchpad;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.android.animations.DepthPageTransformer;
import com.android.animations.TransformZoomOut;
import com.android.multiplay.R;

public class TouchPadHelp extends FragmentActivity {

	private TouchPad_help_pager pagerAdapter = null;
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touchpad_help);
		
		pager = (ViewPager) findViewById(R.id.vp_help_touchpad);

		getRandomTransformation(new Random());

	    pagerAdapter = new TouchPad_help_pager(getSupportFragmentManager(),getResources());
	    pager.setAdapter(pagerAdapter);
	    	 
	}


	protected void onResume() {
        super.onResume();
		
        getRandomTransformation(new Random());
    }

	private void getRandomTransformation( Random random) {
		if (random.nextInt() % 2 == 0) {
			pager.setPageTransformer(true, new TransformZoomOut());
		} else {
			pager.setPageTransformer(true, new DepthPageTransformer());
		}
	}
}