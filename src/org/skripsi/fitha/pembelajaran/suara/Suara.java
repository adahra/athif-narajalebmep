package org.skripsi.fitha.pembelajaran.suara;

/**
 * Interface untuk menangani suara
 * 
 * @author blackshadow
 *
 */
public interface Suara {

	/**
	 * Method yang menangani atau digunakan untuk memainkan suara
	 * 
	 * @param volume
	 *            Tingkat volume dari suara yang dimainkan
	 */
	public void mainkan(float volume);

	/**
	 * Method yang digunakan untuk mengatur ulang suara yang sudah dimainkan
	 * atau method yang menangani suara agar dapat ditutup/dihentikan ketika
	 * aplikasi di tutup
	 */
	public void aturUlang();
}
