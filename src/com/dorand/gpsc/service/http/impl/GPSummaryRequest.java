package com.dorand.gpsc.service.http.impl;

import android.util.Log;

import com.dorand.gpsc.service.http.intf.IGPSummaryResponseHandler;
import com.dorand.gpsc.service.impl.GPSummaryConditionsResponse;
import com.dorand.gpsc.service.intf.IGPError;
import com.dorand.gpsc.service.util.GPError;
import com.dorand.gpsc.service.util.GPJSONUtils;
import com.ibm.json.java.JSONObject;

public class GPSummaryRequest extends GPJSONRequest {

	private IGPSummaryResponseHandler mResponseHandler;

	public GPSummaryRequest(String _url, boolean _useCache, IGPSummaryResponseHandler _handler) {
		super(_url, _useCache);
		mResponseHandler = _handler;
	}

	@Override
	protected void onResponse(JSONObject response) {
		mResponseHandler.onResponse(new GPSummaryConditionsResponse(response));
	}

	@Override
	protected void onCachedResponse(JSONObject cachedResponse) {
		mResponseHandler.onCachedResponse(new GPSummaryConditionsResponse(cachedResponse));
	}

	@Override
	protected void onError(IGPError err) {
		mResponseHandler.onError(err);
	}

	@Override
	protected JSONObject sanitizeResponse(String str) {
		JSONObject contentJson = null;
		int start = str.indexOf("{");
		int end = str.lastIndexOf("}") + 1;
		String jsonResponse = str.substring(start, end);
		IGPError err = new GPError(getClass().getName());
		JSONObject json = GPJSONUtils.parse(jsonResponse.getBytes(), err);
		if (json != null && !err.isError()) {
			contentJson = json;
		} else {
			Log.i("Summary Response Failed: ", jsonResponse);
		}
		return contentJson;
	}

}
