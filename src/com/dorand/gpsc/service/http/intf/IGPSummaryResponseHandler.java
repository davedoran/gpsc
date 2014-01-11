package com.dorand.gpsc.service.http.intf;

import com.dorand.gpsc.service.intf.IGPError;


public interface IGPSummaryResponseHandler {

	void onResponse(IGPSummaryResponse response);
	void onError(IGPError gpError);

}
