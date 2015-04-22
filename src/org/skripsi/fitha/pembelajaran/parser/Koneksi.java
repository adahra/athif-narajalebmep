package org.skripsi.fitha.pembelajaran.parser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Koneksi {
	private String urlLearning = null;

	public Koneksi(String url) {
		urlLearning = url;
	}

	public Koneksi() {
		this.getUrl();
	}

	public void setUrl(String url) {
		urlLearning = url;
	}

	public String getUrl() {
		return urlLearning;
	}

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
