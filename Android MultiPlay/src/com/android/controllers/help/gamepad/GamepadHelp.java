package com.android.controllers.help.gamepad;

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
import android.widget.TextView;

public class GamepadHelp extends FragmentActivity {
	private static final int NUM_PAGES = 3;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamepad_help);
		ViewPager pager = (ViewPager) findViewById(R.id.pagergamepad);
		Random r = new Random();
		int numberrandom = r.nextInt() % 100;
		if (numberrandom % 2 == 0) {
			pager.setPageTransformer(true, new TransformZoomOut());
		}else{
			pager.setPageTransformer(true, new DepthPageTransformer());
		}
		this.pager=pager;
		/** Getting fragment manager */
		FragmentManager fm = getSupportFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		Gamepad_help_pager pagerAdapter = new Gamepad_help_pager(fm);

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gamepad_help, menu);
		return true;
	}
	protected void onResume() {
        super.onResume();
		 Random r = new Random();

        int numberrandom = r.nextInt() % 100;
		if (numberrandom % 2 == 0) {
			pager.setPageTransformer(true, new TransformZoomOut());
		}else{
			pager.setPageTransformer(true, new DepthPageTransformer());
		}
    }

}
