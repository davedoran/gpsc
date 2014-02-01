package com.dorand.gpsc.service.http.impl;

import com.dorand.gpsc.service.http.intf.IGPWeatherResponseHandler;
import com.dorand.gpsc.service.impl.GPWeatherResponse;
import com.dorand.gpsc.service.intf.IGPError;
import com.dorand.gpsc.service.util.GPError;
import com.dorand.gpsc.service.util.GPJSONUtils;
import com.ibm.json.java.JSONObject;

public class GPWeatherRequest extends GPJSONRequest {

	private IGPWeatherResponseHandler mResponseHandler;

	public GPWeatherRequest(String url, boolean b, IGPWeatherResponseHandler _handler) {
		super(url, b);
		mResponseHandler = _handler;
	}

	@Override
	protected JSONObject sanitizeResponse(String result) {
		JSONObject ret = null;
		IGPError err = new GPError(getClass().getName());
		ret = GPJSONUtils.parse(result.getBytes(), err);
		if (err.isError()) {
			ret = null;
		}
		return ret;
	}

	@Override
	protected void onResponse(JSONObject response) {
		mResponseHandler.onResponse(new GPWeatherResponse(response));
	}

	@Override
	protected void onError(IGPError err) {
		mResponseHandler.onError(err);
	}

	@Override
	protected void onCachedResponse(JSONObject cachedResponse) {
		mResponseHandler.onCachedResponse(new GPWeatherResponse(cachedResponse));
	}

}
