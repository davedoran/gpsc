package com.dorand.gpsc.service.cache.impl;

import java.io.File;

import com.dorand.gpsc.service.util.GPError;
import com.dorand.gpsc.service.util.GPJSONUtils;
import com.ibm.json.java.JSONObject;

public class GPFileSystemCache {

	private static final String FILE_FORMAT = "response/%s.json";

	public static void cacheResponse(String key, JSONObject jo) {
		File f = new File(String.format(FILE_FORMAT, key));
		if (f.exists()) {
			f.delete();
		}
		GPJSONUtils.storeToFile(jo, f, new GPError("GPCache-store"));
	}

	public static JSONObject loadFromCache(String key) {
		JSONObject ret = null;
		File f = new File(String.format(FILE_FORMAT, key));
		if (f.exists() && !f.isDirectory()) {
			ret = GPJSONUtils.parseFile(f, new GPError("GPCache-load"));
		}
		return ret;
	}

}
