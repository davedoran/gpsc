package com.dorand.gpsc.service.cache.impl;

import java.io.File;

import com.dorand.gpsc.service.util.GPError;
import com.dorand.gpsc.service.util.GPJSONUtils;
import com.dorand.gpsc.ui.MainActivity;
import com.ibm.json.java.JSONObject;

public class GPFileSystemCache {

	private static final String FILE_FORMAT = "%s/response/%s.json";

	public static void cacheResponse(String key, JSONObject jo) {
		File f = new File(getFilePath(key));
		if (!f.exists() && !f.getParentFile().exists() ) {
			f.mkdirs();
		}
		GPJSONUtils.storeToFile(jo, f, new GPError("GPCache-store"));
	}

	public static JSONObject loadFromCache(String key) {
		JSONObject ret = null;
		File f = new File(getFilePath(key));
		if (f.exists() && !f.isDirectory()) {
			ret = GPJSONUtils.parseFile(f, new GPError("GPCache-load"));
		}
		return ret;
	}
	
	private static String getFilePath(String key) {
		return String.format(FILE_FORMAT, MainActivity.ROOT_DIR, key);
	}

}
