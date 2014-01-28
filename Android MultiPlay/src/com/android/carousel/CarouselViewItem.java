package com.android.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CarouselViewItem extends RelativeLayout {
	
	int maxHeight = 0;
	int maxWeight = 0;
	ScalableImageView scalableImageView = null;
	TextView icon_title = null;
/**
 * 
 * @param context
 * @param attrs
 */
	public CarouselViewItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
/**
 * 
 * @param context
 * @param icon
 * @param title
 * @param icon_title_colorID
 * @param icon_title_size
 * @param maxHeight
 * @param maxWeight
 */
	public CarouselViewItem(Context context, Bitmap icon, String title, int icon_title_colorID, int icon_title_size, int maxHeight, int maxWeight) {
		super(context);
		this.maxHeight = maxHeight;
		this.maxWeight = maxWeight;

		scalableImageView = new ScalableImageView(context, maxHeight, maxWeight);
		
		scalableImageView.setId(100);
		scalableImageView.setImageBitmap(icon);
		
		AppUtils.AddView(this, scalableImageView, maxWeight, maxHeight, 
				new int[][] { new int[] { RelativeLayout.CENTER_IN_PARENT } },
				-1, -1);

		icon_title = new TextView(context);
		
		icon_title.setText(AppUtils.ShortText(title, 18));
		icon_title.setTextColor(icon_title_colorID);
		icon_title.setTextSize(icon_title_size);

		AppUtils.AddView(this, icon_title, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, new int[][] {
						new int[] { RelativeLayout.CENTER_HORIZONTAL },
						new int[] { RelativeLayout.ALIGN_BOTTOM, 100 } }, 
				-1, -1);
	}

	public final int getMaxHeight() {
		return maxHeight;
	}

	public final int getMaxWeight() {
		return maxWeight;
	}
}
