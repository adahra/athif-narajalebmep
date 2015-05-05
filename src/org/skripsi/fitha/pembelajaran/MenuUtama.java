package org.skripsi.fitha.pembelajaran;

import org.skripsi.fitha.pembelajaran.materi.Materi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuUtama extends Activity implements OnClickListener {
	private Button btnMenuUtamaMateri;
	private Button btnMenuUtamaLatihan;
	private Button btnMenuUtamaKuis;
	private Button btnMenuUtamaKeluar;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_utama);

		btnMenuUtamaKeluar = (Button) findViewById(R.id.btnMenuUtamaKeluar);
		btnMenuUtamaKeluar.setOnClickListener(this);

		btnMenuUtamaKuis = (Button) findViewById(R.id.btnMenuUtamaKuis);
		btnMenuUtamaKuis.setOnClickListener(this);

		btnMenuUtamaLatihan = (Button) findViewById(R.id.btnMenuUtamaLatihan);
		btnMenuUtamaLatihan.setOnClickListener(this);

		btnMenuUtamaMateri = (Button) findViewById(R.id.btnMenuUtamaMateri);
		btnMenuUtamaMateri.setOnClickListener(this);

		intent = null;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMenuUtamaKeluar:
			MenuUtama.this.finish();
			break;
		case R.id.btnMenuUtamaKuis:
			Toast.makeText(getApplicationContext(), "Masih belum dibuat",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnMenuUtamaLatihan:
			Toast.makeText(getApplicationContext(), "Masih belum dibuat",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnMenuUtamaMateri:
			intent = new Intent(getApplicationContext(), Materi.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
