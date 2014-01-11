package com.dorand.gpsc.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	private SectionsPagerAdapter mSectionsPagerAdapter;

	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String title = item.getTitle().toString();
		if (title.equals(getString(R.string.ncc_link))) {
			Log.d(getClass().getName(), "NCC Link Clicked!");
			launchNCCWebSite();
		} else if (title.equals(getString(R.string.about_app))) {
			Log.d(getClass().getName(), "About Link Clicked!");
			launchAboutActivity();
		}
		return true;
	}

	private void launchNCCWebSite() {

		try {
			Uri uri = Uri.parse(getString(R.string.ncc_url));
			Intent launchNCCWebsite = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(launchNCCWebsite);
		} catch (Exception e) {
			Log.e(getClass().getName(), "Unable to launch NCC", e);
		}
	}

	private void launchAboutActivity() {
		Intent intent = new Intent();
		intent.setClass(this, AboutActivity.class);
		startActivity(intent);
	}
}
