package org.skripsi.fitha.pembelajaran.kuis;

/**
 * 
 * @author blackshadow
 *
 */
public class VarJawaban {
	private String pilihanjawaban;
	private String jawabanbenar;
	private double nilai;

	/**
	 * 
	 * @return
	 */
	public String getPilihanjawaban() {
		return pilihanjawaban;
	}

	/**
	 * 
	 * @param pilihanjawaban
	 */
	public void setPilihanjawaban(String pilihanjawaban) {
		this.pilihanjawaban = pilihanjawaban;
	}

	/**
	 * 
	 * @return
	 */
	public String getJawabanbenar() {
		return jawabanbenar;
	}

	/**
	 * 
	 * @param jawabanbenar
	 */
	public void setJawabanbenar(String jawabanbenar) {
		this.jawabanbenar = jawabanbenar;
	}

	/**
	 * 
	 * @return
	 */
	public double getNilai() {
		return nilai;
	}

	/**
	 * 
	 * @param nilai
	 */
	public void setNilai(double nilai) {
		this.nilai = nilai;
	}

}
