package org.skripsi.fitha.pembelajaran;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MateriSistemKoordinat extends Activity implements OnClickListener {
	private Button btnMateriSistemKoordinatKembali;
	private Button btnMateriSistemKoordinatSelanjutnya;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_sistem_koordinat);
		
		btnMateriSistemKoordinatKembali = (Button) findViewById(R.id.btnMateriSistemKoordinatKembali);
		btnMateriSistemKoordinatKembali.setOnClickListener(this);

		btnMateriSistemKoordinatSelanjutnya = (Button) findViewById(R.id.btnMateriSistemKoordinatSelanjutnya);
		btnMateriSistemKoordinatSelanjutnya.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriSistemKoordinatKembali:
			MateriSistemKoordinat.this.finish();
			break;
		case R.id.btnMateriPengukuranDebitSelanjutnya:
			break;
		default:
			break;
		}

	}
}
