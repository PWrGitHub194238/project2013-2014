package com.android.controllers.help.mouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.controllers.help.movie.Movie_Activity_Help;
import com.android.extendedWidgets.lists.ListOfVideo;
import com.android.extendedWidgets.lists.VideoListItem;
import com.android.multiplay.R;

/** Fragment describing the appearance of each page within a given activity. 
 * 
 * Creates a logic of movement between the parties through gestures move left and right. 
 * Describes a list of video tutorials that are available by default on the last page of this activity.
 * @author tomasz
 *
 */
public class Mouse_Fragment extends Fragment implements OnItemClickListener {
	
	/**
	 * 
	 */
	private int currentPage;
	
	/**
	 * 
	 */
	private ListOfVideo videoListAdapter;
	
	/**
	 * 
	 */
	private static final VideoListItem VIDEO_LIST[] = {
		new VideoListItem(0, 
				"Jakiœtam url", VideoListItem.VIDEO_MINIATURE.FIRST, 
				"Jakiœtam tytu³", 12, 
				"Jakiœtam opis")
	};
	
	/**
	 * 
	 */
	private Context context;
	
	/**
	 * 
	 */
	private View v = null;
	
	/**
	 * 
	 */
	private RelativeLayout screen_1 = null;
	
	/**
	 * 
	 */
	private TextView screen_1_help_1 = null;

	/**
	 * 
	 */
	private RelativeLayout screen_2 = null;

	/**
	 * 
	 */
	private ListView screen_2_video_list = null;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();
		/** Getting integer data of the key current_page from the bundle */
		currentPage = data.getInt("current_page", 0);
		
		context = getActivity().getApplicationContext();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		v = inflater.inflate(R.layout.help_view_mouse, container, false);

		initElements();

		switch (currentPage) {
			case 1:
				screen_1.setVisibility(RelativeLayout.VISIBLE);
				screen_2.setVisibility(RelativeLayout.GONE);
				break;
	
			case 2:
				screen_1.setVisibility(RelativeLayout.GONE);
				screen_2.setVisibility(RelativeLayout.VISIBLE);
				break;
		}
		
		return v;
	}

	/**
	 * 
	 */
	private void initElements() {
		screen_1 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_mouse_screen_1);
		screen_1_help_1 = (TextView) v.findViewById(R.id.tv_controller_help_mouse_screen_1_help_1);
			
		screen_2 = (RelativeLayout) v.findViewById(R.id.rl_controller_help_mouse_screen_2);
		screen_2_video_list = (ListView) v.findViewById(R.id.iv_controller_help_mouse_screen_2_video_list);
		screen_2_video_list.setOnItemClickListener(this);
	
		videoListAdapter = new ListOfVideo(context, VIDEO_LIST);
		screen_2_video_list.setAdapter(videoListAdapter);
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(context, Movie_Activity_Help.class);
		intent.putExtra("video_url", ((VideoListItem) parent.getItemAtPosition(position)).getVideo_url());
		super.startActivity(intent);
	}
}