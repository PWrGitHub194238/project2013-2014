package com.android.multiplay;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.carousel.AppUtils;
import com.android.carousel.CarouselDataItem;
import com.android.carousel.CarouselView;
import com.android.carousel.CarouselViewAdapter;
import com.android.carousel.Singleton;
import com.android.controllers.keyboard.KeyboardActivity;
import com.android.controllers.mouse.Gyromouse;
import com.android.controllers.mouse.MouseActivity;
import com.android.controllers.mouse.TouchPadActivity;
import com.android.controllers.steeringwheel.SteeringwheelActivity;

public class FirstMenu extends Activity implements OnItemSelectedListener,
		TextWatcher {
	Context context;
	CarouselDataItem docu;
	Singleton m_Inst = Singleton.getInstance();
	CarouselViewAdapter m_carouselAdapter = null;
	private final int m_nFirstItem = 1000;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();

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
						Color.WHITE, Color.BLACK }));
		setContentView(panel);

		// copy images from assets to sdcard
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/Gyroscope_operation.gif",
				"Gyroscope_operation.gif", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/kierownica.jpg",
				"kierownica.jpg", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/klawiatura.jpg",
				"klawiatura.jpg", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/mysz.png", "mysz.png", false);
		AppUtils.AssetFileCopy(this, "/mnt/sdcard/touchpad.jpg",
				"touchpad.jpg", false);

		ArrayList<CarouselDataItem> Docus = new ArrayList<CarouselDataItem>();
		for (int i = 0; i < 1000; i++) {
			CarouselDataItem docu;
			if (i % 5 == 0)
				docu = new CarouselDataItem(
						"/mnt/sdcard/Gyroscope_operation.gif", 0, "Gyroscope");
			else if (i % 5 == 1) {
				docu = new CarouselDataItem("/mnt/sdcard/kierownica.jpg", 0,
						"kierownica");
			} else if (i % 5 == 2)
				docu = new CarouselDataItem("/mnt/sdcard/klawiatura.jpg", 0,
						"klawiatura");
			else if (i % 5 == 3)
				docu = new CarouselDataItem("/mnt/sdcard/touchpad.jpg", 0,
						"Touchpad");
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

		// Create Carousel Click Listener
		if (coverFlow != null) {
			coverFlow.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					if (position % 5 == 1) {
						// Toast.makeText(FirstMenu.this, "Position=kierownica",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context,
								SteeringwheelActivity.class);
						startActivity(intent);
					} else if (position % 5 == 0) {
						// Toast.makeText(FirstMenu.this, "Position=Gyroskope",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context, Gyromouse.class);
						startActivity(intent);
					} else if (position % 5 == 2) {
						// Toast.makeText(FirstMenu.this, "Position=Klawiatura",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context,
								KeyboardActivity.class);
						startActivity(intent);
					} else if (position % 5 == 3) {
						// Toast.makeText(FirstMenu.this, "Position=Touchpad",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context,
								TouchPadActivity.class);
						startActivity(intent);
					} else {
						// Toast.makeText(FirstMenu.this, "Position=Mysz",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context, MouseActivity.class);
						startActivity(intent);
					}
				}
			});
		}
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

	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

}
