package com.android.controllers.help.movie;

import java.net.URL;

import com.android.multiplay.R;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Movie_Activity_Help extends Activity {
VideoView video;
String urlstring;
Uri url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie__activity__help);
		Bundle bundle = getIntent().getExtras();
		urlstring = bundle.getString("video_url");
		url=Uri.parse(urlstring);
		video = (VideoView)findViewById(R.id.videohelp);   
	        try {
	            // Start the MediaController
	            MediaController mediacontroller = new MediaController(
	            		Movie_Activity_Help.this);
	            mediacontroller.setAnchorView(video);
	        
	            // Get the URL from String VideoURL
	            video.setMediaController(mediacontroller);
	            video.setVideoURI(url);
	            video.requestFocus();
	            video.start();
	          
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie__activity__help, menu);
		return true;
	}

}
