package org.skripsi.fitha.pembelajaran;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class IsiMateri extends Activity implements OnClickListener {
	private Button btnIsiMateriKembali;
	private Button btnIsiMateriSelanjutnya;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_isi_materi);

		btnIsiMateriKembali = (Button) findViewById(R.id.btnIsiMateriKembali);
		btnIsiMateriKembali.setOnClickListener(this);

		btnIsiMateriSelanjutnya = (Button) findViewById(R.id.btnIsiMateriSelanjutnya);
		btnIsiMateriSelanjutnya.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIsiMateriKembali:
			IsiMateri.this.finish();
			break;
		case R.id.btnIsiMateriSelanjutnya:
			Toast.makeText(getApplicationContext(), "Masih belum dibuat",
					Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
