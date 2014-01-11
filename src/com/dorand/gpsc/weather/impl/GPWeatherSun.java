package com.dorand.gpsc.weather.impl;

import java.util.Date;

import com.dorand.gpsc.weather.intf.IGPWeatherSun;
import com.ibm.json.java.JSONObject;

public class GPWeatherSun implements IGPWeatherSun {

//	{
//		 "message":0.0978,
//		 "country":"CA",
//		 "sunrise":1389357745,
//		 "sunset":1389390041
//	}
	private JSONObject mContent;
	
	public GPWeatherSun(JSONObject _content) {
		mContent = _content;
	}
	
	@Override
	public Date getSunrise() {
		Date ret = null;
		if ( mContent != null && mContent.containsKey("sunrise")) {
			Number sunrise = (Number)mContent.get("sunrise");
			if (sunrise != null ) {
				ret = new Date(sunrise.longValue());
			}
		}
		return ret;
	}

	@Override
	public Date getSunset() {
		Date ret = null;
		if ( mContent != null && mContent.containsKey("sunset")) {
			Number sunrise = (Number)mContent.get("sunset");
			if (sunrise != null ) {
				ret = new Date(sunrise.longValue());
			}
		}
		return ret;
	}

}
