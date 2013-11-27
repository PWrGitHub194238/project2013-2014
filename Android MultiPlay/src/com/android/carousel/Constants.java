package com.android.carousel;

import android.graphics.Color;

public class Constants {
	public static Singleton m_Inst = Singleton.getInstance();

	public static final int m_nTextSizeSmall = m_Inst.Scale(12),
			m_nTextSizeMedium = m_Inst.Scale(20), m_nTextSizeBig = m_Inst
					.Scale(25), m_nSmallShadow = m_Inst.Scale(4),
			m_nCornerPaddings = m_Inst.Scale(30),

			m_nRoundCornerRadius = m_Inst.Scale(20),

			m_nClrTextBlack = Color.BLACK, m_nClrTextPurple = Color.rgb(120,
					60, 95),

			m_nCardWhitePadding = 0;
	public static final int JPG_QUALITY = 95, THUMB_MAX_WIDTH = 80,
			THUMB_MAX_HEIGHT = 100;

}
