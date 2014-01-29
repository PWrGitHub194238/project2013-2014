package com.android.controllers.help.speaker;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.android.animations.DepthPageTransformer;
import com.android.animations.TransformZoomOut;
import com.android.multiplay.R;

/** Uses, appropriate to the class name, adapter to create the help view, based on the {@link RelativeLayout}'s, which can be scrolled using gestures to the left, to the right in the one activity.
*
* @author tomasz
*
*/
public class SpeakerHelp extends FragmentActivity {

	/**
	 * 
	 */
	private Speaker_help_pager pagerAdapter = null;
	/**
	 * 
	 */
	private ViewPager pager;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speaker_help);
		
		pager = (ViewPager) findViewById(R.id.vp_help_speacker);

		getRandomTransformation(new Random());

	    pagerAdapter = new Speaker_help_pager(getSupportFragmentManager(),getResources());
	    pager.setAdapter(pagerAdapter);
	    	 
	}


	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	protected void onResume() {
        super.onResume();
		
        getRandomTransformation(new Random());
    }

	/**
	 * @param random
	 */
	private void getRandomTransformation( Random random) {
		if (random.nextInt() % 2 == 0) {
			pager.setPageTransformer(true, new TransformZoomOut());
		} else {
			pager.setPageTransformer(true, new DepthPageTransformer());
		}
	}
}