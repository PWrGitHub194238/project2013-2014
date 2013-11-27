package com.android.carousel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

public class AppUtils {
	final String LOG_TAG = Singleton.PRJNAME + "::AppUtils";

	public static int MAX_TEXT_LEN = 18;


	public static void AssetFileCopy(Context context, String PathDest,
			String assetName, boolean gunzip) {
		try {
			File fdOut = new File(PathDest);
			fdOut.createNewFile();
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(fdOut));
			InputStream in = null;
			if (gunzip)
				in = new GZIPInputStream(context.getAssets().open(assetName));
			else
				in = new BufferedInputStream(context.getAssets()
						.open(assetName));
			// copy file content
			int length;
			byte buffer[] = new byte[4096];
			while ((length = in.read(buffer, 0, 4096)) != -1) {
				out.write(buffer, 0, length);
			}
			out.flush();
			out.close();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

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
