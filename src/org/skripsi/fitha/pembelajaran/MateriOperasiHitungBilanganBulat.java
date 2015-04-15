package org.skripsi.fitha.pembelajaran;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MateriOperasiHitungBilanganBulat extends Activity implements OnClickListener {
	private Button btnMateriOperasiHitungBilanganBulatKembali;
	private Button btnMateriOperasiHitungBilanganBulatSelanjutnya;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_operasi_hitung_bilangan_bulat);
		
		btnMateriOperasiHitungBilanganBulatKembali = (Button) findViewById(R.id.btnMateriOperasiHitungBilanganBulatKembali);
		btnMateriOperasiHitungBilanganBulatKembali.setOnClickListener(this);

		btnMateriOperasiHitungBilanganBulatSelanjutnya = (Button) findViewById(R.id.btnMateriOperasiHitungBilanganBulatSelanjutnya);
		btnMateriOperasiHitungBilanganBulatSelanjutnya.setOnClickListener(this);
	
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriOperasiHitungBilanganBulatKembali:
			MateriOperasiHitungBilanganBulat.this.finish();
			break;
		case R.id.btnMateriOperasiHitungBilanganBulatSelanjutnya:
			break;
		default:
			break;
		}
	}	
}

