package com.android.carousel;

import android.view.View;
import android.widget.RelativeLayout;

public class AppUtils {
	final String LOG_TAG = Singleton.PRJNAME + "::AppUtils";

	public static int MAX_TEXT_LEN = 18;

	public static String ShortText(String label, int max) {
		if (label != null && label.length() > max)
			return label.substring(0, max) + "..";
		else
			return label;
	}

	public static void Sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// interface elements
	public static RelativeLayout.LayoutParams GetRLP(int vw, int vh,
			int styles[][], int left, int top) {
		RelativeLayout.LayoutParams lpv = new RelativeLayout.LayoutParams(vw,
				vh);
		if (styles != null)
			for (int i = 0; i < styles.length; i++) {
				if (styles[i] != null) {
					if (styles[i].length == 1)
						lpv.addRule(styles[i][0]);
					if (styles[i].length == 2)
						lpv.addRule(styles[i][0], styles[i][1]);
				}
			}
		if (top != -1)
			lpv.topMargin = top;
		if (left != -1)
			lpv.leftMargin = left;
		return lpv;
	}

	public static void AddView(RelativeLayout panel, View v, int vw, int vh,
			int styles[][], int left, int top) {
		panel.addView(v, GetRLP(vw, vh, styles, left, top));
	}

	public static void UpdateView(RelativeLayout panel, View v, int vw, int vh,
			int styles[][], int left, int top) {
		panel.updateViewLayout(v, GetRLP(vw, vh, styles, left, top));
	}

}
