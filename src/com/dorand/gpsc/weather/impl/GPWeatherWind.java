package com.dorand.gpsc.weather.impl;

import com.dorand.gpsc.weather.intf.IGPWeatherWind;
import com.ibm.json.java.JSONObject;

public class GPWeatherWind implements IGPWeatherWind {

	private JSONObject mContent;

	public GPWeatherWind(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Number getSpeed() {
		if (mContent != null && mContent.containsKey("speed"))
			return (Number) mContent.get("speed");
		return null;
	}

	@Override
	public Number getGust() {
		if (mContent != null && mContent.containsKey("gust"))
			return (Number) mContent.get("gust");
		return null;
	}

	@Override
	public Number getDirection() {
		if (mContent != null && mContent.containsKey("direction"))
			return (Number) mContent.get("direction");
		return null;
	}

}
