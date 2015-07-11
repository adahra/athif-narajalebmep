package org.skripsi.fitha.pembelajaran.suara;

/**
 * Interface yang digunakan untuk membuat suara
 * 
 * @author blackshadow
 *
 */
public interface Bunyi {

	/**
	 * Method yang digunakan untuk mengambil suara yang ada
	 * 
	 * @param lokasi
	 *            Lokasi dari suara dimana berada
	 * @param namaberkas
	 *            Nama dari berkas suara
	 * @return suara
	 */
	public Suara suaraBaru(String lokasi, String namaberkas);
}
