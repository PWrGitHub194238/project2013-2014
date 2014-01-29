package com.android.controllers.help.touchpad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.controllers.help.movie.Movie_Activity_Help;
import com.android.extendedWidgets.lists.ListOfVideo;
import com.android.extendedWidgets.lists.VideoListItem;
import com.android.multiplay.MainActivity;
import com.android.multiplay.R;

public class TouchPad_Fragment extends Fragment implements OnItemClickListener {
	
	private int currentPage;
	
	private ListOfVideo listOfElementsAdpater;
	
	private static final VideoListItem VIDEO_LIST[] = {
		new VideoListItem(0, 
				"Jakiœtam url", VideoListItem.VIDEO_MINIATURE.FIRST, 
				"Jakiœtam tytu³", 12, 
				"Jakiœtam opis"),
		new VideoListItem(0, 
				"Jakiœtam url", VideoListItem.VIDEO_MINIATURE.FIRST, 
				"Jakiœtam tytu³", 12, 
				"Jakiœtam opis")
	};
	
	private Context context;
	private View v = null;
	
	private RelativeLayout screen_1 = null;
	
	private ImageView screen_1_img1 = null;
	
	private RelativeLayout screen_2 = null;
	
	private ImageView screen_2_img1 = null;
	private ImageView screen_2_img2 = null;

	private RelativeLayout screen_3 = null;
	
	private ImageView screen_3_img1 = null;
	private ImageView screen_3_img2 = null;
	private ImageView screen_3_img3 = null;
	
	private RelativeLayout screen_4 = null;

	private ImageView screen_4_img1 = null;
	private ImageView screen_4_img2 = null;
	private ImageView screen_4_img3 = null;
	private ImageView screen_4_img4 = null;

	
	private RelativeLayout screen_5 = null;

	
	private ListView screen_5_listView1 = null;
	
	boolean init = true;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();
		/** Getting integer data of the key current_page from the bundle */
		currentPage = data.getInt("current_page", 0);
		
		context = getActivity().getApplicationContext();


	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		v = inflater.inflate(R.layout.help_view_touchpad, container, false);

		initElements();

		switch (currentPage) {
			case 1:
				Log.d("APP","1");
				screen_1.setVisibility(RelativeLayout.VISIBLE);
				screen_2.setVisibility(RelativeLayout.GONE);
				screen_3.setVisibility(RelativeLayout.GONE);
				screen_4.setVisibility(RelativeLayout.GONE);
				screen_5.setVisibility(RelativeLayout.GONE);
				break;
	
			case 2:
				Log.d("APP","2");
				screen_1.setVisibility(RelativeLayout.GONE);
				screen_2.setVisibility(RelativeLayout.VISIBLE);
				screen_3.setVisibility(RelativeLayout.GONE);
				screen_4.setVisibility(RelativeLayout.GONE);
				screen_5.setVisibility(RelativeLayout.GONE);
				break;
	
			case 3:
				Log.d("APP","3");
				screen_1.setVisibility(RelativeLayout.GONE);
				screen_2.setVisibility(RelativeLayout.GONE);
				screen_3.setVisibility(RelativeLayout.VISIBLE);
				screen_4.setVisibility(RelativeLayout.GONE);
				screen_5.setVisibility(RelativeLayout.GONE);
				break;
				
			case 4:
				Log.d("APP","4");
				screen_1.setVisibility(RelativeLayout.GONE);
				screen_2.setVisibility(RelativeLayout.GONE);
				screen_3.setVisibility(RelativeLayout.GONE);
				screen_4.setVisibility(RelativeLayout.VISIBLE);
				screen_5.setVisibility(RelativeLayout.GONE);
				break;
				
			case 5:
				Log.d("APP","5");
				screen_1.setVisibility(RelativeLayout.GONE);
				screen_2.setVisibility(RelativeLayout.GONE);
				screen_3.setVisibility(RelativeLayout.GONE);
				screen_4.setVisibility(RelativeLayout.GONE);
				screen_5.setVisibility(RelativeLayout.VISIBLE);
				break;
		}
		
		return v;

	}

	private void initElements() {
		screen_1 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_touchpad_screen_1);
		screen_1_img1 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_1_img1);
			
		screen_2 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_touchpad_screen_2);
		screen_2_img1 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_2_img1);
		screen_2_img2 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_2_img2);

		screen_3 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_touchpad_screen_3);
		screen_3_img1 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_3_img1);
		screen_3_img2 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_3_img2);
		screen_3_img3 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_3_img3);

		screen_4 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_touchpad_screen_4);
		screen_4_img1 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_4_img1);
		screen_4_img2 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_4_img2);
		screen_4_img3 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_4_img3);
		screen_4_img4 = (ImageView) v.findViewById(R.id.iv_controller_help_touchpad_screen_4_img4);
		
		screen_5 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_touchpad_screen_5);

		screen_5_listView1 = (ListView) v.findViewById(R.id.iv_controller_help_touchpad_screen_5_listView1);
		screen_5_listView1.setOnItemClickListener(this);
	
		listOfElementsAdpater = new ListOfVideo(context, VIDEO_LIST);
		screen_5_listView1.setAdapter(listOfElementsAdpater);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(context, Movie_Activity_Help.class);
		intent.putExtra("video_url", ((VideoListItem) parent.getItemAtPosition(position)).getVideo_url());
		super.startActivity(intent);
	}

}