package org.skripsi.fitha.pembelajaran.suara;

import android.media.SoundPool;

/**
 * Kelas implementasi dari suara yang digunakan untuk menangani suara yang
 * dimainkan
 * 
 * @author blackshadow
 *
 */
public class SuaraAndroid implements Suara {
	private int idSuara;
	private SoundPool kolamSuara;

	/**
	 * Konstruktor dari kelas
	 * 
	 * @param kolamSuara
	 *            Parameter yang digunakan untuk menyimpan suara
	 * @param idSuara
	 *            Identitas dari suara
	 */
	public SuaraAndroid(SoundPool kolamSuara, int idSuara) {
		// TODO Auto-generated constructor stub
		this.idSuara = idSuara;
		this.kolamSuara = kolamSuara;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.skripsi.fitha.pembelajaran.suara.Suara#mainkan(float)
	 */
	@Override
	public void mainkan(float volume) {
		// TODO Auto-generated method stub
		kolamSuara.play(idSuara, volume, volume, 0, 0, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.skripsi.fitha.pembelajaran.suara.Suara#aturUlang()
	 */
	@Override
	public void aturUlang() {
		// TODO Auto-generated method stub
		kolamSuara.unload(idSuara);
	}

}
