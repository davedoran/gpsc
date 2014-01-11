package com.dorand.gpsc.service.http.impl;

import android.util.Log;

import com.dorand.gpsc.service.http.intf.IGPTrailResponseHandler;
import com.dorand.gpsc.service.impl.GPTrailConditionsResponse;
import com.dorand.gpsc.service.intf.IGPError;
import com.dorand.gpsc.service.util.GPError;
import com.dorand.gpsc.service.util.GPJSONUtils;
import com.ibm.json.java.JSONObject;

public class GPTrailRequest extends GPJSONRequest {

	private static final String NEW_DATE_REGEX = "\\{\"v\"\\s*:\\s*new\\s+Date\\([0-9][0-9][0-9][0-9],[0-9][0-9]?,[0-9][0-9]?\\),";

	private IGPTrailResponseHandler mResponseHandler;

	public GPTrailRequest(String _uri, boolean _useCache, IGPTrailResponseHandler _handler) {
		super(_uri, _useCache);
		mResponseHandler = _handler;
	}

	@Override
	protected void onResponse(JSONObject response) {
		mResponseHandler.onResponse(new GPTrailConditionsResponse(response));
	}

	@Override
	protected void onError(IGPError err) {
		mResponseHandler.onError(err);
	}

	@Override
	protected JSONObject sanitizeResponse(String responseStr) {
		JSONObject contentJson = null;
		int start = responseStr.indexOf("{");
		int end = responseStr.lastIndexOf("}") + 1;
		String jsonResponse = responseStr.substring(start, end);
		jsonResponse = jsonResponse.replaceAll(NEW_DATE_REGEX, "{");
		jsonResponse = jsonResponse.replaceAll(",,", ",");
		IGPError err = new GPError("GPResponseSanitizer - trail response");
		JSONObject json = GPJSONUtils.parse(jsonResponse.getBytes(), err);
		if (json != null && !err.isError()) {
			contentJson = json;
		} else {
			Log.i("Trail Response Failed: ", jsonResponse);
		}
		return contentJson;
	}

}
