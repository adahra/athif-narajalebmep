package org.skripsi.fitha.pembelajaran.suara;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Kelas yang digunakan untuk menangani bunyi
 * 
 * @author blackshadow
 *
 */
public class BunyiAndroid implements Bunyi {
	private AssetManager manajerAset;
	private SoundPool kolamSuara;

	/**
	 * Konstruktor dari kelas
	 * 
	 * @param activity
	 *            Activity yang akan memainkan bunyi
	 */
	public BunyiAndroid(Activity activity) {
		// TODO Auto-generated constructor stub
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.manajerAset = activity.getAssets();
		this.kolamSuara = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.skripsi.fitha.pembelajaran.suara.Bunyi#suaraBaru(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Suara suaraBaru(String lokasi, String namaberkas) {
		// TODO Auto-generated method stub
		try {
			AssetFileDescriptor dekripsiAset = manajerAset.openFd(lokasi + "/"
					+ namaberkas);
			int idSuara = kolamSuara.load(dekripsiAset, 0);
			return new SuaraAndroid(kolamSuara, idSuara);
		} catch (IOException ioe) {
			throw new RuntimeException("Tidak dapat memuat suara \""
					+ namaberkas + "\"");
		}
	}

}
