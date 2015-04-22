package org.skripsi.fitha.pembelajaran.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageLoader {
	private MemoryCache memoryCache = new MemoryCache();
	private FileCache fileCache;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map imageViews = Collections.synchronizedMap(new WeakHashMap());
	private ExecutorService executorService;
	private final int STUB_ID = android.R.drawable.star_on;
	private static final int TIME_CONNECTION = 30000;

	public ImageLoader(Context context) {
		fileCache = new FileCache(context);
		executorService = Executors.newFixedThreadPool(5);
	}

	@SuppressWarnings("unchecked")
	public void displayImage(String url, ImageView imageView) {
		imageViews.put(imageView, url);
		Bitmap bitmap = memoryCache.get(url);

		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			queuePhoto(url, imageView);
			imageView.setImageResource(STUB_ID);
		}
	}

	private void queuePhoto(String url, ImageView imageView) {
		PhotoToLoad photo = new PhotoToLoad(url, imageView);
		executorService.submit(new PhotosLoader(photo));
	}

	@SuppressWarnings("unused")
	private Bitmap getBitmap(String url) {
		File file = fileCache.getFile(url);
		Bitmap bitmap = decodeFile(file);

		if (bitmap != null) {
			return bitmap;
		}

		try {
			Bitmap bit = null;
			URL imageURL = new URL(url);
			HttpsURLConnection connection = (HttpsURLConnection) imageURL
					.openConnection();
			connection.setConnectTimeout(TIME_CONNECTION);
			connection.setReadTimeout(TIME_CONNECTION);
			connection.setInstanceFollowRedirects(true);
			InputStream is = connection.getInputStream();
			OutputStream os = new FileOutputStream(file);
			Utils.copyStream(is, os);
			os.close();
			bit = decodeFile(file);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Bitmap decodeFile(File file) {
		try {
			BitmapFactory.Options option = new BitmapFactory.Options();
			option.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(file), null, option);

			final int REQUIRED_SIZE = 70;
			int widthTemp = option.outWidth;
			int heightTemp = option.outHeight;
			int scale = 1;

			while (true) {
				if (widthTemp / 2 < REQUIRED_SIZE
						|| heightTemp / 2 < REQUIRED_SIZE) {
					break;
				}

				widthTemp /= 2;
				heightTemp /= 2;
				scale *= 2;
			}

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(file), null,
					options);
		} catch (FileNotFoundException fnfe) {

		}

		return null;
	}

	private class PhotoToLoad {
		public String url;
		public ImageView imageView;

		public PhotoToLoad(String s, ImageView iv) {
			url = s;
			imageView = iv;
		}
	}

	private class PhotosLoader implements Runnable {
		private PhotoToLoad photoToLoad;

		public PhotosLoader(PhotoToLoad photoToLoad) {
			this.photoToLoad = photoToLoad;
		}

		@Override
		public void run() {
			if (imageViewReused(photoToLoad)) {
				return;
			}

			Bitmap bmp = getBitmap(photoToLoad.url);
			memoryCache.put(photoToLoad.url, bmp);

			if (imageViewReused(photoToLoad)) {
				return;
			}

			BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
			Activity a = (Activity) photoToLoad.imageView.getContext();
			a.runOnUiThread(bd);
		}

	}

	private boolean imageViewReused(PhotoToLoad photoToLoad) {
		String tag = (String) imageViews.get(photoToLoad.imageView);

		if (tag == null || !tag.equals(photoToLoad.url)) {
			return true;
		}

		return false;
	}

	private class BitmapDisplayer implements Runnable {
		private Bitmap bitmap;
		private PhotoToLoad photoToLoad;

		public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
			bitmap = b;
			photoToLoad = p;
		}

		@Override
		public void run() {
			if (imageViewReused(photoToLoad)) {
				return;
			}

			if (bitmap != null) {
				photoToLoad.imageView.setImageBitmap(bitmap);
			} else {
				photoToLoad.imageView.setImageResource(STUB_ID);
			}
		}

	}

	public void clearCache() {
		memoryCache.clear();
		fileCache.clear();
	}
}
