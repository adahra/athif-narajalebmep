package org.skripsi.fitha.pembelajaran.downloader;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class FileCache {
	private File cacheDirectory;

	public FileCache(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			cacheDirectory = new File(
					Environment.getExternalStorageDirectory(), "Pembelajaran");
		} else {
			cacheDirectory = context.getCacheDir();
		}

		if (!cacheDirectory.exists()) {
			cacheDirectory.mkdirs();
		}
	}

	public File getFile(String url) {
		String filename = String.valueOf(url.hashCode());
		// String filename = URLEncoder.encode(url);
		File file = new File(cacheDirectory, filename);
		return file;
	}

	public void clear() {
		File files[] = cacheDirectory.listFiles();
		if (files == null) {
			return;
		}

		for (File f : files) {
			f.delete();
		}
	}
}
