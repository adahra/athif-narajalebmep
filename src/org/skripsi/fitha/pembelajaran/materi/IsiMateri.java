package org.skripsi.fitha.pembelajaran.materi;

import org.skripsi.fitha.pembelajaran.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IsiMateri extends Activity implements OnClickListener {
	private Button btnIsiMateriKembali;
	private TextView txtViewIsiMateri;
	private static final String ARRAY_ISI_MATERI = "isi_materi";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_isi_materi);
		
		Intent mIntent = getIntent();
		String isiMateri = mIntent.getStringExtra(ARRAY_ISI_MATERI);

		txtViewIsiMateri = (TextView) findViewById(R.id.txtViewIsiMateri);
		txtViewIsiMateri.setText(isiMateri);
		
		btnIsiMateriKembali = (Button) findViewById(R.id.btnIsiMateriKembali);
		btnIsiMateriKembali.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIsiMateriKembali:
			IsiMateri.this.finish();
			break;
		default:
			break;
		}
	}
}
