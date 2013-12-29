package com.android.multiplay;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout;

import com.android.carousel.AppUtils;
import com.android.carousel.CarouselDataItem;
import com.android.carousel.CarouselView;
import com.android.carousel.CarouselViewAdapter;
import com.android.carousel.Singleton;
import com.android.controllers.keyboard.KeyboardActivity;
import com.android.controllers.keyboard.Speaker;
import com.android.controllers.mouse.Gyromouse;
import com.android.controllers.mouse.MouseActivity;
import com.android.controllers.mouse.TouchPadActivity;
import com.android.controllers.steeringwheel.SteeringwheelActivity;

public class FirstMenu extends Activity implements OnItemSelectedListener {
	Context context;
	CarouselDataItem docu;
	Singleton m_Inst = Singleton.getInstance();
	CarouselViewAdapter m_carouselAdapter = null;
	ArrayList<CarouselDataItem> Docus;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		Singleton.getInstance().InitGUIFrame(this);

		int padding = m_Inst.Scale(10);
		RelativeLayout panel = new RelativeLayout(this);

		panel.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		panel.setPadding(padding, padding, padding, padding);
		panel.setBackgroundResource(R.drawable.background);
		setContentView(panel);

		Docus = new ArrayList<CarouselDataItem>();
		Docus.add(new CarouselDataItem("Your", 
				R.drawable.carousel_controller_icon_steering_wheel_accel, 
				SteeringwheelActivity.class));
		Docus.add(new CarouselDataItem("Speaker", 
				R.drawable.carousel_controller_icon_steering_wheel_accel, 
				KeyboardActivity.class));
		Docus.add(new CarouselDataItem("Mouse", 
				R.drawable.carousel_controller_icon_steering_wheel_accel, 
				MouseActivity.class));
		Docus.add(new CarouselDataItem("Gyromouse", 
				R.drawable.carousel_controller_icon_steering_wheel_accel, 
				SteeringwheelActivity.class));
		Docus.add(new CarouselDataItem("Wheel", 
				R.drawable.carousel_controller_icon_steering_wheel_accel, 
				SteeringwheelActivity.class));
		Docus.add(new CarouselDataItem("Keyboard", 
				R.drawable.carousel_controller_icon_steering_wheel_accel, 
				SteeringwheelActivity.class));
		Docus.add(new CarouselDataItem("Touchpad", 
				R.drawable.carousel_controller_icon_mouse, 
				MouseActivity.class));

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
					if (position % 7 == 4) {
						// Toast.makeText(FirstMenu.this, "Position=kierownica",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context,
								SteeringwheelActivity.class);
						startActivity(intent);
					} else if (position % 7 == 3) {
						// Toast.makeText(FirstMenu.this, "Position=Gyroskope",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context, Gyromouse.class);
						startActivity(intent);
					} else if (position % 7 == 5) {
						// Toast.makeText(FirstMenu.this, "Position=Klawiatura",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(context,
								KeyboardActivity.class);
						startActivity(intent);
					} else if (position % 7 == 2) {
						// Toast.makeText(FirstMenu.this, "Position=Touchpad",
						// Toast.LENGTH_SHORT).show();

						Intent intent = new Intent(context, MouseActivity.class);
						startActivity(intent);
					} else if (position % 7 == 6) {
						// Toast.makeText(FirstMenu.this, "Position=Touchpad",
						// Toast.LENGTH_SHORT).show();

						// Toast.makeText(FirstMenu.this, "Position=Mysz",
						// Toast.LENGTH_SHORT).show();

						Intent intent = new Intent(context,
								TouchPadActivity.class);
						startActivity(intent);
					} else if (position % 7 == 1) {
						// Toast.makeText(FirstMenu.this, "Position=Touchpad",
						// Toast.LENGTH_SHORT).show();

						// Toast.makeText(FirstMenu.this, "Position=Mysz",
						// Toast.LENGTH_SHORT).show();

						Intent intent = new Intent(context,
								Speaker.class);
						startActivity(intent);
					} else {

						Intent intent = new Intent(context, Your.class);
						startActivity(intent);
					}
				}
			});
		}
		AppUtils.AddView(panel, coverFlow, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT,
				new int[][] { new int[] { RelativeLayout.CENTER_IN_PARENT } },
				-1, -1);
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

}
