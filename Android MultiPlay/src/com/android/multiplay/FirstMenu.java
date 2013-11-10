package com.android.multiplay;

import java.util.ArrayList;

import com.android.controllers.mouse.Gyromouse;

import Carousel.AppUtils;
import Carousel.CarouselDataItem;
import Carousel.CarouselView;
import Carousel.CarouselViewAdapter;
import Carousel.Singleton;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

public class FirstMenu extends Activity implements OnItemSelectedListener,
		TextWatcher {
	CarouselDataItem docu;
	Singleton m_Inst = Singleton.getInstance();
	CarouselViewAdapter m_carouselAdapter = null;
	private final int m_nFirstItem = 1000;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		Singleton.getInstance().InitGUIFrame(this);

		int padding = m_Inst.Scale(10);
		RelativeLayout panel = new RelativeLayout(this);
		
		

		panel.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		panel.setPadding(padding, padding, padding, padding);
		panel.setBackgroundDrawable(new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						Color.WHITE, Color.GRAY }));
		setContentView(panel);
	

		// copy images from assets to sdcard
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/Gyroscope_operation.gif",
				"Gyroscope_operation.gif", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/kierownica.jpg",
				"kierownica.jpg", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/klawiatura.jpg",
				"klawiatura.jpg", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/mysz.png", "mysz.png", false);

		
		ArrayList<CarouselDataItem> Docus = new ArrayList<CarouselDataItem>();
		for (int i = 0; i < 1000; i++) {
			CarouselDataItem docu;
			if (i % 4 == 0)
				docu = new CarouselDataItem(
						"/mnt/sdcard/Gyroscope_operation.gif", 0, "Gyroscope");
			else if (i % 4 == 1) {
				docu = new CarouselDataItem("/mnt/sdcard/kierownica.jpg", 0,
						"kierownica");
			} else if (i % 4 == 2)
				docu = new CarouselDataItem("/mnt/sdcard/klawiatura.jpg", 0,
						"klawiatura");
			else
				docu = new CarouselDataItem("/mnt/sdcard/mysz.png", 0, "Mysz");
			Docus.add(docu);
		}

		
		// create the carousel
		CarouselView coverFlow = new CarouselView(this);
		

		
		m_carouselAdapter = new CarouselViewAdapter(this, Docus,
				m_Inst.Scale(400), m_Inst.Scale(300));
		coverFlow.setAdapter(m_carouselAdapter);
		coverFlow.setSpacing(-1 * m_Inst.Scale(150));
		coverFlow.setSelection(Integer.MAX_VALUE / 2, true);
		coverFlow.setAnimationDuration(1000);
		coverFlow.setOnItemSelectedListener((OnItemSelectedListener) this);

		AppUtils.AddView(panel, coverFlow, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT,
				new int[][] { new int[] { RelativeLayout.CENTER_IN_PARENT } },
				-1, -1);
	}

	public void afterTextChanged(Editable arg0) {
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}
//test
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// CarouselDataItem docu = (CarouselDataItem)
		// m_carouselAdapter.getItem((int) arg3);
		// if (docu!=null){
	//}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

}
