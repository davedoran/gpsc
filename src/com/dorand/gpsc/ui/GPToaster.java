package com.dorand.gpsc.ui;

import java.util.Calendar;

import android.app.Activity;
import android.util.SparseArray;
import android.widget.Toast;

public class GPToaster {

	private static long TOAST_TIMEOUT = 1000 * 60 * 60 * 2;

	private static SparseArray<Long> dates = new SparseArray<Long>();

	public static void toast(final Activity activity, final int messageId, final int length) {
		boolean showToast = false;
		synchronized (dates) {
			Integer i = Integer.valueOf(messageId);
			Long now = Long.valueOf(Calendar.getInstance().getTimeInMillis());
			Long lastToast = dates.get(i);
			if (lastToast == null) {
				showToast = true;
				dates.put(i, now);
			} else if (now.longValue() - lastToast.longValue() > TOAST_TIMEOUT) {
				dates.put(i, now);
				showToast = true;

			}
		}

		if (showToast) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast t = Toast.makeText(activity, messageId, Toast.LENGTH_SHORT);
					t.show();
				}
			});
		}
	}

}
