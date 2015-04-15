package org.skripsi.fitha.pembelajaran;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MateriPecahan extends Activity implements OnClickListener {
	private Button btnMateriPecahanKembali;
	private Button btnMateriPecahanSelanjutnya;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_pecahan);
		
		btnMateriPecahanKembali = (Button) findViewById(R.id.btnMateriPecahanKembali);
		btnMateriPecahanKembali.setOnClickListener(this);

		btnMateriPecahanSelanjutnya = (Button) findViewById(R.id.btnMateriPecahanSelanjutnya);
		btnMateriPecahanSelanjutnya.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriPecahanKembali:
			MateriPecahan.this.finish();
			break;
		case R.id.btnMateriPecahanSelanjutnya:
			break;
		default:
			break;
		}
	}
}
	
