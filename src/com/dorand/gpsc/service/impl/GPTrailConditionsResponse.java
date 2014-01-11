package com.dorand.gpsc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dorand.gpsc.service.http.intf.IGPTrailConditionsResponse;
import com.dorand.gpsc.service.intf.IGPTrailStatus;
import com.dorand.gpsc.service.util.GPJSON;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class GPTrailConditionsResponse implements IGPTrailConditionsResponse {

	private JSONObject mContent;

	public GPTrailConditionsResponse(JSONObject jo) {
		mContent = jo;
	}

	@Override
	public List<IGPTrailStatus> getTrailStatus() {
		List<IGPTrailStatus> ret = new ArrayList<IGPTrailStatus>();
		if (mContent != null) {
			JSONObject table = (JSONObject) mContent.get(GPJSON.TABLE);
			if (table != null) {
				JSONArray rows = (JSONArray) table.get(GPJSON.ROWS);
				if (rows != null) {
					for (Object o : rows) {
						if (o instanceof JSONObject) {
							JSONObject c = (JSONObject) o;
							JSONArray content = (JSONArray) c.get(GPJSON.C);
							if (content != null) {
								IGPTrailStatus status = GPTrailStatus.fromJSONArray(content);
								if (status != null) {
									ret.add(status);
								}
							}
						}
					}
				}
			}
		}
		return ret;

	}

}
