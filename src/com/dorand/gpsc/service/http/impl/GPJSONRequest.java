package com.dorand.gpsc.service.http.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Base64;

import com.dorand.gpsc.service.cache.impl.GPFileSystemCache;
import com.dorand.gpsc.service.http.intf.IGPCachedResponse;
import com.dorand.gpsc.service.intf.IGPError;
import com.dorand.gpsc.service.util.GPError;
import com.ibm.json.java.JSONObject;

/**
 * Issues an HTTP Request that expects a response that can be parsed into a
 * JSONObject. Briefly caches responses...
 */
public abstract class GPJSONRequest implements Runnable {

	private String mUrl;
	private boolean mUseCache;

	private static final Map<String, IGPCachedResponse> CACHE = new HashMap<String, IGPCachedResponse>();

	protected GPJSONRequest(String _url, boolean _useCache) {
		mUrl = _url;
		mUseCache = _useCache;
	}

	@Override
	public void run() {
		if (mUseCache) {
			IGPCachedResponse fromCache = null;
			synchronized (CACHE) {
				fromCache = CACHE.get(mUrl);
			}
			if (fromCache != null && !fromCache.isExpired()) {
				onResponse(fromCache.getResponse());
			} else {
				runRequest();
			}
		} else {
			runRequest();
		}

	}

	private void runRequest() {
		URI uri = null;
		try {
			uri = new URI(mUrl);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		HttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(uri);
		HttpResponse response = null;
		try {
			response = client.execute(getRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String result = convertStreamToString(instream);
					instream.close();
					JSONObject resultJSON = sanitizeResponse(result);
					if (resultJSON != null) {
						cacheToFileSystem(resultJSON);
						onResponse(resultJSON);
						synchronized (CACHE) {
							CACHE.put(mUrl, new GPCachedResponse(resultJSON));
						}
					} else {
						onError(new GPError(getClass().getName(), "Failed to parse JSON response..."));
					}
				} else {
					noEntity();
				}
			} else {
				badResponseCode(response);
			}
		} catch (IOException e) {
			onError(new GPError(getClass().getName(), e.getMessage()));
		}
	}

	private void noEntity() {
		JSONObject fromCache = loadFromFileSystem();
		if (fromCache != null) {
			onResponse(fromCache);
		} else {
			onError(new GPError(getClass().getName(), "Entity not found..."));
		}
	}

	private void badResponseCode(HttpResponse response) {
		JSONObject fromCache = loadFromFileSystem();
		if (fromCache != null) {
			onResponse(fromCache);
		} else {
			onError(new GPError(getClass().getName(), "Response code: " + response.getStatusLine().getStatusCode()));
		}
	}

	protected abstract JSONObject sanitizeResponse(String result);

	protected abstract void onResponse(JSONObject response);

	protected abstract void onError(IGPError err);

	protected void cacheToFileSystem(JSONObject response) {
		GPFileSystemCache.cacheResponse(Base64.encodeToString(mUrl.getBytes(), 0), response);
	}

	protected JSONObject loadFromFileSystem() {
		return GPFileSystemCache.loadFromCache(Base64.encodeToString(mUrl.getBytes(), 0));
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
