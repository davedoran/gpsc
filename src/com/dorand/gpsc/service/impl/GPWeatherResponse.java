package com.dorand.gpsc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dorand.gpsc.service.http.intf.IGPWeatherResponse;
import com.dorand.gpsc.weather.impl.GPWeather;
import com.dorand.gpsc.weather.impl.GPWeatherMain;
import com.dorand.gpsc.weather.impl.GPWeatherSun;
import com.dorand.gpsc.weather.impl.GPWeatherWind;
import com.dorand.gpsc.weather.intf.IGPWeather;
import com.dorand.gpsc.weather.intf.IGPWeatherMain;
import com.dorand.gpsc.weather.intf.IGPWeatherSun;
import com.dorand.gpsc.weather.intf.IGPWeatherWind;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/*
 {
 "coord":{
 "lon":-75.94,
 "lat":45.63
 },
 "sys":{
 "message":0.0978,
 "country":"CA",
 "sunrise":1389357745,
 "sunset":1389390041
 },
 "weather":[
 {
 "id":600,
 "main":"Snow",
 "description":"light snow",
 "icon":"13d"
 }
 ],
 "base":"gdps stations",
 "main":{
 "temp":262.66,
 "humidity":86,
 "pressure":1028.374,
 "temp_min":261.48,
 "temp_max":263.71
 },
 "wind":{
 "speed":1,
 "gust":1,
 "deg":64
 },
 "snow":{
 "3h":0.25
 },
 "clouds":{
 "all":80
 },
 "dt":1389364571,
 "id":6175108,
 "name":"Wakefield",
 "cod":200
 }
 */
public class GPWeatherResponse implements IGPWeatherResponse {

	private JSONObject mContent;

	public GPWeatherResponse(JSONObject jo) {
		mContent = jo;
	}

	@Override
	public List<IGPWeather> getWeather() {
		List<IGPWeather> ret = new ArrayList<IGPWeather>();
		if (mContent != null && mContent.containsKey("weather")) {
			JSONArray weather = (JSONArray)mContent.get("weather");
			for ( Object o : weather ) {
				if ( o instanceof JSONObject ) {
					ret.add(new GPWeather((JSONObject)o));
				}
			}
		}
		return ret;
	}

	@Override
	public IGPWeatherMain getWeatherMain() {
		IGPWeatherMain ret = null;
		if ( mContent != null && mContent.containsKey("main")) {
			JSONObject main = (JSONObject)mContent.get("main");
			if ( main != null ) {
				ret = new GPWeatherMain(main);
			}
		}
		return ret;
	}

	@Override
	public IGPWeatherSun getWeatherSun() {
		IGPWeatherSun ret = null;
		if ( mContent != null && mContent.containsKey("sys")) {
			JSONObject sys = (JSONObject)mContent.get("sys");
			if ( sys != null ) {
				ret = new GPWeatherSun(sys);
			}
		}
		return ret;
	}

	@Override
	public IGPWeatherWind getWeatherWind() {
		IGPWeatherWind ret = null;
		if ( mContent != null && mContent.containsKey("wind")) {
			JSONObject wind = (JSONObject)mContent.get("wind");
			if ( wind != null ) {
				ret = new GPWeatherWind(wind);
			}
		}
		return ret;
	}

	
}
