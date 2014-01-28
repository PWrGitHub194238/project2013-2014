package com.android.controllers.help.mouse;

import com.android.multiplay.R;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Mouse_help_pager extends FragmentPagerAdapter {
	
	private final int PAGE_COUNT = 2;
	
	private final int[] PAGE_TITLES = {
			R.string.help_mouse_page_title_1,
			R.string.help_mouse_page_title_2
	};

	Resources resources = null;
	
	public Mouse_help_pager(FragmentManager fragmentManager, Resources resources) {
		super(fragmentManager);
		this.resources = resources;
	}

	@Override
	public Fragment getItem(int position) {
		Mouse_Fragment myFragment = new Mouse_Fragment();
		Bundle data = new Bundle();
		data.putInt("current_page", position + 1);
		myFragment.setArguments(data);
		return myFragment;
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	public CharSequence getPageTitle(int position) {
		return resources.getString(PAGE_TITLES[position]);
	}
}