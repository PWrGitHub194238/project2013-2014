package com.android.carousel;

public class CarouselDataItem {
	String m_szImgPath;
	long m_nDocDate;
	String m_szDocName;
	
	public CarouselDataItem(String path, long date, String name) {
		m_szImgPath = path;
		m_nDocDate = date;
		m_szDocName = name;
	}

	
	public String getImgPath() {
		return m_szImgPath;
	}
	public String getDocText() {
		return m_szDocName;
	}

}
