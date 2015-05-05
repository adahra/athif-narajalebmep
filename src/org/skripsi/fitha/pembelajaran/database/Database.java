package org.skripsi.fitha.pembelajaran.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public Database(Context context) {
		super(context, "DB_PEMBELAJARAN", null, 3);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS MATERI id_subbab TEXT, id_bab TEXT, sub_judul TEXT, anak_subbab TEXT, isi_materi TEXT, gambar TEXT, suara TEXT, kurikulum TEXT");
		db.execSQL("CREATE TABLE IF NOT EXISTS LATIHAN id_soal_latihan TEXT, id_bab TEXT, soal TEXT, jawaban_a TEXT, jawaban_b TEXT, jawaban_c TEXT, jawaban_d TEXT, gambar_1 TEXT, gambar_2 TEXT, gambar_3 TEXT, gambar_4 TEXT, kunci TEXT");
		db.execSQL("CREATE TABLE IF NOT EXISTS KUIS id_soal TEXT, id_bab TExT, soal TEXT, jawaban_a TEXT, jawaban_b TEXT, jawaban_c TEXT, jawaban_d TEXT, gambar_1 TEXT, gambar_2 TEXT, gambar_3 TEXT, gambar_4 TEXT, tahun TEXT, kunci TEXT");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

}
