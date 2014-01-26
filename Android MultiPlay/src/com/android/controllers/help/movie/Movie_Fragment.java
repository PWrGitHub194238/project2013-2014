package com.android.controllers.help.movie;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.multiplay.R;

public class Movie_Fragment extends Fragment {
        private int mCurrentPage;
        private VideoView video;

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
                View v = inflater.inflate(R.layout.movie_view, container, false);
                video = (VideoView) v.findViewById(R.id.video);
                
                String path = "http://androidmultiplay.url.ph/video_tutorials/scrennrecord.mp4"; // <-url
                if (mCurrentPage == 1) {
                        Uri uri = Uri.parse(path);
                        video.setVideoURI(uri);
                        video.start();
                } else if (mCurrentPage == 2) {
                        path = "http://androidmultiplay.url.ph/video_tutorials/scrennrecord.mp4"; // <-url2
                        Uri uri = Uri.parse(path);
                        video.setVideoURI(uri);
                        video.start();
                } else if (mCurrentPage == 3) {
                        path = "http://androidmultiplay.url.ph/video_tutorials/scrennrecord.mp4"; // <-url3
                        Uri uri = Uri.parse(path);
                        video.setVideoURI(uri);
                        video.start();
                }
                //...
                return v;

        }

}