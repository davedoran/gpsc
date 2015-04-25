package com.dorand.gpsc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dorand.gpsc.service.http.intf.IGPSummaryResponse;
import com.dorand.gpsc.service.intf.IGPSummaryEntry;
import com.dorand.gpsc.service.util.GPJSON;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class GPSummaryConditionsResponse implements IGPSummaryResponse {

	private JSONObject mConditions;

	public GPSummaryConditionsResponse(JSONObject jo) {
		mConditions = jo;
	}

	@Override
	public List<IGPSummaryEntry> getSummaryInfo() {
		List<IGPSummaryEntry> ret = new ArrayList<IGPSummaryEntry>();
		if (mConditions != null && mConditions.containsKey(GPJSON.TABLE)) {
			JSONObject table = (JSONObject) mConditions.get(GPJSON.TABLE);
			if (table.containsKey(GPJSON.COLS)) {
				parseCols((JSONArray) table.get(GPJSON.COLS), ret);
			}

			if (table.containsKey(GPJSON.ROWS)) {
				parseRows((JSONArray) table.get(GPJSON.ROWS), ret);
			}
		}
		return ret;
	}

	private void parseCols(JSONArray jsonArray, List<IGPSummaryEntry> ret) {
		String name = null;
		String value = null;
		if (jsonArray != null && jsonArray.size() == 2) {
			JSONObject key = (JSONObject) jsonArray.get(0);
			name = key.get(GPJSON.LABEL).toString();

			JSONObject val = (JSONObject) jsonArray.get(1);
			value = val.get(GPJSON.LABEL).toString();
		}

		if (name != null && value != null) {
			ret.add(new GPSummaryEntry(name.trim(), value.trim()));
		}
	}

	private void parseRows(JSONArray jsonArray, List<IGPSummaryEntry> ret) {
		for (Object o : jsonArray) {
			if (o instanceof JSONObject) {
				JSONObject jo = (JSONObject) o;
				if (jo.containsKey(GPJSON.C)) {
					JSONArray ja = (JSONArray) jo.get(GPJSON.C);
					if (ja != null && ja.size() == 2) {
						JSONObject key = (JSONObject) ja.get(0);
						String name = key.get(GPJSON.V).toString();

						try {
							JSONObject val = (JSONObject) ja.get(1);
							Object vl = val.get(GPJSON.V);
							String value = null;
							if ( vl instanceof Double ) {
								value = vl.toString();
							} else if ( vl instanceof String ) {
								value = (String) vl;
							}

							if (name != null && value != null) {
								ret.add(new GPSummaryEntry(name.trim(), value.trim()));
							}
						} catch (Exception e) {
							
						}
						
					}
				}
			}
		}
	}

}
