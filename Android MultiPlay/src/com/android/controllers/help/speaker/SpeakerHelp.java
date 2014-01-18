package com.android.controllers.help.speaker;

import java.util.Random;

import com.android.animations.DepthPageTransformer;
import com.android.animations.TransformZoomOut;
import com.android.multiplay.R;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class SpeakerHelp extends FragmentActivity{

	private static final int NUM_PAGES = 3;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speaker_help);
		 ViewPager pager = (ViewPager) findViewById(R.id.pagerspeaker);
		 pager.setPageTransformer(true, new TransformZoomOut());
		 Random r = new Random();
			int numberrandom = r.nextInt() % 100;
			if (numberrandom % 2 == 0) {
				pager.setPageTransformer(true, new TransformZoomOut());
			}else{
				pager.setPageTransformer(true, new DepthPageTransformer());
			}
	        /** Getting fragment manager */
	        FragmentManager fm = getSupportFragmentManager();
	 
	        /** Instantiating FragmentPagerAdapter */
	        Speaker_help_pager pagerAdapter = new Speaker_help_pager(fm);

	        /** Setting the pagerAdapter to the pager object */
	        pager.setAdapter(pagerAdapter);
	    
	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.keyboard_help, menu);
		return true;
	}

}
