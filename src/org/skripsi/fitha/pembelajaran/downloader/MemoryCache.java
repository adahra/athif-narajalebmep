package org.skripsi.fitha.pembelajaran.downloader;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class MemoryCache {
	@SuppressWarnings("rawtypes")
	private Map<String, SoftReference> cache = Collections
			.synchronizedMap(new HashMap<String, SoftReference>());

	@SuppressWarnings("rawtypes")
	public Bitmap get(String id) {
		if (!cache.containsKey(id)) {
			return null;
		}

		SoftReference ref = cache.get(id);
		return (Bitmap) ref.get();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void put(String id, Bitmap bitmap) {
		cache.put(id, new SoftReference(bitmap));
	}

	public void clear() {
		cache.clear();
	}
}
