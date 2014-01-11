package com.dorand.gpsc.service.http.intf;

import java.util.List;

import com.dorand.gpsc.weather.intf.IGPWeather;
import com.dorand.gpsc.weather.intf.IGPWeatherMain;
import com.dorand.gpsc.weather.intf.IGPWeatherSun;
import com.dorand.gpsc.weather.intf.IGPWeatherWind;

public interface IGPWeatherResponse {

	List<IGPWeather> getWeather();
	
	IGPWeatherMain getWeatherMain();
	
	IGPWeatherSun getWeatherSun();
	
	IGPWeatherWind getWeatherWind();

}
