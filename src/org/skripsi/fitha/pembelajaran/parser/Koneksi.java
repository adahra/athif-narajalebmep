package org.skripsi.fitha.pembelajaran.parser;

public class Koneksi {
	String urlLearning = null;

	public Koneksi(String url) {
		urlLearning = urlLearning + url;
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
}
