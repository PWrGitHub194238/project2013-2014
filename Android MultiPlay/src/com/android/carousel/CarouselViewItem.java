package com.android.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CarouselViewItem extends RelativeLayout {
	int m_w, m_h;

	public CarouselViewItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public int getMaxW() {
		return m_w;
	}

	public int getMaxH() {
		return m_h;
	}

	public CarouselViewItem(Context context, Bitmap img, String text, int w,
			int h) {
		super(context);
		m_w = w;
		m_h = h;

		ScalableImageView scaIv = new ScalableImageView(context, w, h);
		scaIv.setId(100);
		scaIv.setImageBitmap(img);
		AppUtils.AddView(this, scaIv, w, h,
				new int[][] { new int[] { RelativeLayout.CENTER_IN_PARENT } },
				-1, -1);

		TextView tv = new TextView(context);
		tv.setText(AppUtils.ShortText(text, 18));
		tv.setTextColor(Color.BLACK);
		tv.setTextSize(Constants.m_nTextSizeMedium);

		AppUtils.AddView(this, tv, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, new int[][] {
						new int[] { RelativeLayout.CENTER_HORIZONTAL },
						new int[] { RelativeLayout.ALIGN_BOTTOM, 100 } }, -1,
				-1);
	}

}
