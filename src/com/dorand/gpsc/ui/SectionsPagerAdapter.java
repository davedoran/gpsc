package com.dorand.gpsc.ui;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dorand.gpsc.ui.fragments.BackCountryTrailFragment;
import com.dorand.gpsc.ui.fragments.ClassicTrailFragment;
import com.dorand.gpsc.ui.fragments.ConditionsFragment;
import com.dorand.gpsc.ui.fragments.SkateTrailFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
 * of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private Activity activity;

	public SectionsPagerAdapter(Activity a, FragmentManager fm) {
		super(fm);
		activity = a;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment frag = null;
		switch (position) {
		case 0:
			frag = new ConditionsFragment();
			break;
		case 1:
			frag = new ClassicTrailFragment();
			break;
		case 2:
			frag = new SkateTrailFragment();
			break;
		case 3:
			frag = new BackCountryTrailFragment();
			break;
		}
		frag.setArguments(new Bundle());
		return frag;
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		CharSequence ret = null;

		switch (position) {
		case 0:
			ret = activity.getString(R.string.title_conditions).toUpperCase(l);
			break;
		case 1:
			ret = activity.getString(R.string.title_classic).toUpperCase(l);
			break;
		case 2:
			ret = activity.getString(R.string.title_skate).toUpperCase(l);
			break;
		case 3:
			ret = activity.getString(R.string.title_backcountry).toUpperCase(l);
			break;
		}

		return ret;
	}
}
