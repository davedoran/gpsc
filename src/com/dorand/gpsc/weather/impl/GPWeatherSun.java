package com.dorand.gpsc.weather.impl;

import java.util.Date;

import com.dorand.gpsc.weather.intf.IGPWeatherSun;
import com.ibm.json.java.JSONObject;

public class GPWeatherSun implements IGPWeatherSun {

	private static final String SUNSET = "sunset";
	private static final String SUNRISE = "sunrise";

	private JSONObject mContent;

	public GPWeatherSun(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Date getSunrise() {
		Date ret = null;
		if (mContent != null && mContent.containsKey(SUNRISE)) {
			Number sunrise = (Number) mContent.get(SUNRISE);
			if (sunrise != null) {
				ret = new Date(sunrise.longValue());
			}
		}
		return ret;
	}

	@Override
	public Date getSunset() {
		Date ret = null;
		if (mContent != null && mContent.containsKey(SUNSET)) {
			Number sunrise = (Number) mContent.get(SUNSET);
			if (sunrise != null) {
				ret = new Date(sunrise.longValue());
			}
		}
		return ret;
	}

}
