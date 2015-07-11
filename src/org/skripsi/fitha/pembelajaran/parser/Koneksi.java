package org.skripsi.fitha.pembelajaran.parser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author blackshadow
 *
 */
public class Koneksi {
	private String urlLearning = "http://elearningmath.nazuka.net/";

	/**
	 * 
	 * @param url
	 */
	public Koneksi(String url) {
		urlLearning = url;
	}

	/**
	 * 
	 */
	public Koneksi() {
		this.getUrl();
	}

	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		urlLearning = url;
	}

	/**
	 * 
	 * @return
	 */
	public String getUrl() {
		return urlLearning;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public boolean cekStatus(Context context) {
		ConnectivityManager koneksi = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo koneksiInfo = koneksi.getActiveNetworkInfo();

		if (koneksiInfo != null && koneksiInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}
