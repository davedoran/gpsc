package com.dorand.gpsc.weather.impl;

import com.dorand.gpsc.weather.intf.IGPWeatherWind;
import com.ibm.json.java.JSONObject;

public class GPWeatherWind implements IGPWeatherWind {

	private static final String DIRECTION = "direction";
	private static final String GUST = "gust";
	private static final String SPEED = "speed";
	private JSONObject mContent;

	public GPWeatherWind(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Number getSpeed() {
		if (mContent != null && mContent.containsKey(SPEED))
			return (Number) mContent.get(SPEED);
		return null;
	}

	@Override
	public Number getGust() {
		if (mContent != null && mContent.containsKey(GUST))
			return (Number) mContent.get(GUST);
		return null;
	}

	@Override
	public Number getDirection() {
		if (mContent != null && mContent.containsKey(DIRECTION))
			return (Number) mContent.get(DIRECTION);
		return null;
	}

}
