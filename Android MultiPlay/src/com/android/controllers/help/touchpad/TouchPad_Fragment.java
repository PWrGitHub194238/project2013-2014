package com.android.controllers.help.touchpad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.multiplay.R;

public class TouchPad_Fragment extends Fragment {
	private int mCurrentPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();
		/** Getting integer data of the key current_page from the bundle */
		mCurrentPage = data.getInt("current_page", 0);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.touchpad_help_view, container, false);
		ImageView tv = (ImageView) v.findViewById(R.id.image1);
		TextView tx = (TextView) v.findViewById(R.id.th1);

		if (mCurrentPage == 1) {
			tv.setImageResource(R.drawable.activity_title_glow_green);
		} else if (mCurrentPage == 2) {
			tv.setImageResource(R.drawable.activity_title_glow_pink);
			tx.setVisibility(View.GONE);
		} else {
			tv.setVisibility(View.GONE);

		}
		return v;

	}

}
