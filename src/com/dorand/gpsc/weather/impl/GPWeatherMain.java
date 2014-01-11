package com.dorand.gpsc.weather.impl;

import com.dorand.gpsc.weather.intf.IGPWeatherMain;
import com.ibm.json.java.JSONObject;


public class GPWeatherMain implements IGPWeatherMain {

//	 {
//	 	"temp":262.66,
//	 	"humidity":86,
//	 	"pressure":1028.374,
//	 	"temp_min":261.48,
//	 	"temp_max":263.71
//	 }
	private JSONObject mContent;

	public GPWeatherMain(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Number getTemp() {
		if (mContent != null && mContent.containsKey("temp"))
			return (Number) mContent.get("temp");
		return null;
	}

	@Override
	public Number getHumidity() {
		if (mContent != null && mContent.containsKey("humidity"))
			return (Number) mContent.get("humidity");
		return null;
	}

	@Override
	public Number getPressure() {
		if (mContent != null && mContent.containsKey("pressure"))
			return (Number) mContent.get("pressure");
		return null;
	}

	@Override
	public Number getTempMin() {
		if (mContent != null && mContent.containsKey("temp_min"))
			return (Number) mContent.get("temp_min");
		return null;
	}

	@Override
	public Number getTempMax() {
		if (mContent != null && mContent.containsKey("temp_max"))
			return (Number) mContent.get("temp_max");
		return null;
	}

}
