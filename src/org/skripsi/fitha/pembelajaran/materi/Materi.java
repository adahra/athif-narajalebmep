package org.skripsi.fitha.pembelajaran.materi;

import org.skripsi.fitha.pembelajaran.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author blackshadow
 *
 */
public class Materi extends Activity implements OnClickListener {
	private Button btnMateriOperasiHitungBilanganBulat;
	private Button btnMateriPengukuranDebit;
	private Button btnMateriGeometri;
	private Button btnMateriMengolahData;
	private Button btnMateriPecahan;
	private Button btnMateriSistemKoordinat;
	private Button btnMateriKembali;
	private Intent intent;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi);

		btnMateriGeometri = (Button) findViewById(R.id.btnMateriGeometri);
		btnMateriGeometri.setOnClickListener(this);

		btnMateriKembali = (Button) findViewById(R.id.btnMateriKembali);
		btnMateriKembali.setOnClickListener(this);

		btnMateriMengolahData = (Button) findViewById(R.id.btnMateriMengolahData);
		btnMateriMengolahData.setOnClickListener(this);

		btnMateriOperasiHitungBilanganBulat = (Button) findViewById(R.id.btnMateriOperasiHitungBilanganBulat);
		btnMateriOperasiHitungBilanganBulat.setOnClickListener(this);

		btnMateriPecahan = (Button) findViewById(R.id.btnMateriPecahan);
		btnMateriPecahan.setOnClickListener(this);

		btnMateriPengukuranDebit = (Button) findViewById(R.id.btnMateriPengukuranDebit);
		btnMateriPengukuranDebit.setOnClickListener(this);

		btnMateriSistemKoordinat = (Button) findViewById(R.id.btnMateriSistemKoordinat);
		btnMateriSistemKoordinat.setOnClickListener(this);

		intent = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriGeometri:
			intent = new Intent(getApplicationContext(), MateriGeometri.class);
			startActivity(intent);
			break;
		case R.id.btnMateriKembali:
			Materi.this.finish();
			break;
		case R.id.btnMateriMengolahData:
			intent = new Intent(getApplicationContext(),
					MateriMengolahData.class);
			startActivity(intent);
			break;
		case R.id.btnMateriOperasiHitungBilanganBulat:
			intent = new Intent(getApplicationContext(),
					MateriOperasiHitungBilanganBulat.class);
			startActivity(intent);
			break;
		case R.id.btnMateriPecahan:
			intent = new Intent(getApplicationContext(), MateriPecahan.class);
			startActivity(intent);
			break;
		case R.id.btnMateriPengukuranDebit:
			intent = new Intent(getApplicationContext(),
					MateriPengukuranDebit.class);
			startActivity(intent);
			break;
		case R.id.btnMateriSistemKoordinat:
			intent = new Intent(getApplicationContext(),
					MateriSistemKoordinat.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
