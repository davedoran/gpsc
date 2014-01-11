package com.dorand.gpsc.weather.impl;

import com.dorand.gpsc.weather.intf.IGPWeather;
import com.ibm.json.java.JSONObject;

public class GPWeather implements IGPWeather {

	private static final String ICON = "icon";
	private static final String DESCRIPTION = "description";
	private static final String MAIN = "main";
	private static final String ID = "id";
	private JSONObject mContent;

	public GPWeather(JSONObject _content) {
		mContent = _content;
	}

	@Override
	public Number getId() {
		if (mContent != null && mContent.containsKey(ID))
			return (Number) mContent.get(ID);
		return null;
	}

	@Override
	public String getType() {
		if (mContent != null && mContent.containsKey(MAIN))
			return (String) mContent.get(MAIN);
		return null;
	}

	@Override
	public String getDescription() {
		if (mContent != null && mContent.containsKey(DESCRIPTION))
			return (String) mContent.get(DESCRIPTION);
		return null;
	}

	@Override
	public String getIcon() {
		if (mContent != null && mContent.containsKey(ICON))
			return (String) mContent.get(ICON);
		return null;
	}

}
