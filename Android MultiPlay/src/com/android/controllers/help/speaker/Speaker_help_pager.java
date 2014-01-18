package com.android.controllers.help.speaker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Speaker_help_pager extends FragmentPagerAdapter {
	private final int PAGE_COUNT = 3;

	public Speaker_help_pager(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Speaker_Fragment myFragment = new Speaker_Fragment();
		Bundle data = new Bundle();
		data.putInt("current_page", arg0 + 1);
		myFragment.setArguments(data);
		return myFragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}

	public CharSequence getPageTitle(int position) {
		return "Step:" + (position + 1);
	}
}


