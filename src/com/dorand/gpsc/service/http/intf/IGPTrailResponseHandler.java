package com.dorand.gpsc.service.http.intf;

import com.dorand.gpsc.service.intf.IGPError;

public interface IGPTrailResponseHandler {

	void onResponse(IGPTrailConditionsResponse response);
	void onError(IGPError error);
	
}
