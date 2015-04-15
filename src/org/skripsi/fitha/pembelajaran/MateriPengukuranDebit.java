package org.skripsi.fitha.pembelajaran;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MateriPengukuranDebit extends Activity implements OnClickListener{
	private Button btnMateriPengukuranDebitKembali;
	private Button btnMateriPengukuranDebitSelanjutnya;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_pengukuran_debit);
		
		btnMateriPengukuranDebitKembali = (Button) findViewById(R.id.btnMateriPengukuranDebitKembali);
		btnMateriPengukuranDebitKembali.setOnClickListener(this);

		btnMateriPengukuranDebitSelanjutnya = (Button) findViewById(R.id.btnMateriPengukuranDebitSelanjutnya);
		btnMateriPengukuranDebitSelanjutnya.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriPengukuranDebitKembali:
			MateriPengukuranDebit.this.finish();
			break;
		case R.id.btnMateriPengukuranDebitSelanjutnya:
			break;
		default:
			break;
		}
	}
}
