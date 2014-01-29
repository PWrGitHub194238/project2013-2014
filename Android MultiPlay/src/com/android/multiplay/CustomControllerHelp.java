package com.android.multiplay;

import java.util.Random;

import com.android.animations.DepthPageTransformer;
import com.android.animations.TransformZoomOut;
import com.android.controllers.help.keyboard.Keyboard_help_pager;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class CustomControllerHelp extends FragmentActivity {

	private Keyboard_help_pager pagerAdapter = null;
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_controller_help);
		
		pager = (ViewPager) findViewById(R.id.vp_help_custom);

		getRandomTransformation(new Random());

	    pagerAdapter = new Keyboard_help_pager(getSupportFragmentManager(),getResources());
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