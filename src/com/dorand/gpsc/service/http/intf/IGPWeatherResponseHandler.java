package com.dorand.gpsc.service.http.intf;

import com.dorand.gpsc.service.intf.IGPError;

public interface IGPWeatherResponseHandler {

	void onResponse(IGPWeatherResponse response);

	void onError(IGPError err);

}
