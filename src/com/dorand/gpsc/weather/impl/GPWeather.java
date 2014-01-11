package com.dorand.gpsc.weather.impl;

import com.dorand.gpsc.weather.intf.IGPWeather;
import com.ibm.json.java.JSONObject;

public class GPWeather implements IGPWeather {

	private JSONObject mContent;

	public GPWeather(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Number getId() {
		if (mContent != null && mContent.containsKey("id"))
			return (Number) mContent.get("id");
		return null;
	}

	@Override
	public String getType() {
		if (mContent != null && mContent.containsKey("main"))
			return (String) mContent.get("main");
		return null;
	}

	@Override
	public String getDescription() {
		if (mContent != null && mContent.containsKey("description"))
			return (String) mContent.get("description");
		return null;
	}

	@Override
	public String getIcon() {
		if (mContent != null && mContent.containsKey("icon"))
			return (String) mContent.get("icon");
		return null;
	}

}
