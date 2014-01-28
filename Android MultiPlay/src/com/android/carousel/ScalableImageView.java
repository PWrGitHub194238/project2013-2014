package com.android.carousel;

import android.content.Context;
import android.widget.ImageView;

public class ScalableImageView extends ImageView {
	int m_w, m_h;
/**
 * 
 * @param context
 * @param w
 * @param h
 */
	public ScalableImageView(Context context, int w, int h) {
		super(context);
		m_w = w;
		m_h = h;
	}

	public int getMaxW() {
		return m_w;
	}

	public int getMaxH() {
		return m_h;
	}
/**
 * @see android.widget.ImageView#onMeasure(int, int)
 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.setMeasuredDimension(m_w, m_h);
	}

}
