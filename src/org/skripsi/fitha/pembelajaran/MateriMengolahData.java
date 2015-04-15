package org.skripsi.fitha.pembelajaran;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MateriMengolahData extends Activity implements OnClickListener{
	private Button btnMateriMengolahDataKembali;
	private Button btnMateriMengolahDataSelanjutnya;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_mengolah_data);
		
		btnMateriMengolahDataKembali = (Button) findViewById(R.id.btnMateriMengolahDataKembali);
		btnMateriMengolahDataKembali.setOnClickListener(this);

		btnMateriMengolahDataSelanjutnya = (Button) findViewById(R.id.btnMateriMengolahDataSelanjutnya);
		btnMateriMengolahDataSelanjutnya.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriMengolahDataKembali:
			MateriMengolahData.this.finish();
			break;
		case R.id.btnMateriMengolahDataSelanjutnya:
			break;
		default:
			break;
		}
	}
}
