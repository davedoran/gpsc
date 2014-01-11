package com.dorand.gpsc.service.http.impl;

import java.util.Calendar;

import com.dorand.gpsc.service.http.intf.IGPCachedResponse;
import com.ibm.json.java.JSONObject;

public class GPCachedResponse implements IGPCachedResponse {

	private static final int FIVE_MIN = 1000 * 60 * 5;
	private final long mExpires;
	private final JSONObject mJSONObject;

	public GPCachedResponse(JSONObject jo) {
		mJSONObject = jo;
		mExpires = Calendar.getInstance().getTimeInMillis() + FIVE_MIN;
	}

	@Override
	public JSONObject getResponse() {
		return mJSONObject;
	}

	@Override
	public boolean isExpired() {
		return Calendar.getInstance().getTimeInMillis() > mExpires;
	}

}
