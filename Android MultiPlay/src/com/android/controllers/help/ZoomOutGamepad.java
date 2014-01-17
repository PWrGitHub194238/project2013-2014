package com.android.controllers.help;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class ZoomOutGamepad implements PageTransformer {
	private static float MIN_SCALE = 0.85f;
	private static float MIN_ALPHA = 0.5f;

	@Override
	public void transformPage(View arg0, float arg1) {
		int pageWidth = arg0.getWidth();
		int pageHeight = arg0.getHeight();
		if (arg1 < -1) { // [-Infinity,-1)
			// This page is way off-screen to the left.
			arg0.setAlpha(0);
		} else if (arg1 <= 1) { // [-1,1]
			// Modify the default slide transition to shrink the page as well
			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(arg1));
			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;
			if (arg1 < 0) {
				arg0.setTranslationX(horzMargin - vertMargin / 2);
			} else {
				arg0.setTranslationX(-horzMargin + vertMargin / 2);
			}
			// Scale the page down (between MIN_SCALE and 1)
			arg0.setScaleX(scaleFactor);
			arg0.setScaleY(scaleFactor);

			// Fade the page relative to its size.
			arg0.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
					/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));
		} else {
			// This page is way off-screen to the right.
			arg0.setAlpha(0);
		}
	}
}
