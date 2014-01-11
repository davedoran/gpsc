package com.dorand.gpsc.weather.impl;

import com.dorand.gpsc.weather.intf.IGPWeatherMain;
import com.ibm.json.java.JSONObject;

public class GPWeatherMain implements IGPWeatherMain {

	private static final String TEMP_MAX = "temp_max";
	private static final String TEMP_MIN = "temp_min";
	private static final String PRESSURE = "pressure";
	private static final String HUMIDITY = "humidity";
	private static final String TEMP = "temp";

	private JSONObject mContent;

	public GPWeatherMain(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Number getTemp() {
		if (mContent != null && mContent.containsKey(TEMP))
			return (Number) mContent.get(TEMP);
		return null;
	}

	@Override
	public Number getHumidity() {
		if (mContent != null && mContent.containsKey(HUMIDITY))
			return (Number) mContent.get(HUMIDITY);
		return null;
	}

	@Override
	public Number getPressure() {
		if (mContent != null && mContent.containsKey(PRESSURE))
			return (Number) mContent.get(PRESSURE);
		return null;
	}

	@Override
	public Number getTempMin() {
		if (mContent != null && mContent.containsKey(TEMP_MIN))
			return (Number) mContent.get(TEMP_MIN);
		return null;
	}

	@Override
	public Number getTempMax() {
		if (mContent != null && mContent.containsKey(TEMP_MAX))
			return (Number) mContent.get(TEMP_MAX);
		return null;
	}

}
