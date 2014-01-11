package com.dorand.gpsc.service.http.intf;

import com.ibm.json.java.JSONObject;

public interface IGPCachedResponse {

	JSONObject getResponse();
	boolean isExpired();
	
}
