package org.skripsi.fitha.pembelajaran;

// import java.util.ArrayList;
// import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MateriOperasiHitungBilanganBulat extends Activity implements
		OnClickListener, OnItemClickListener {
	private final String TAG = getClass().getSimpleName();

	private Button btnMateriOperasiHitungBilanganBulatKembali;
	private Button btnMateriOperasiHitungBilanganBulatSelanjutnya;
	private TextView txtViewJudul;
	private TextView txtViewMateri;
	private ListView lvViewMateri;

	private ProgressDialog mProgressDialog;

	private JSONArray materi = null;

	private static final String LINK_URL = "http://pejuangcinta.nazuka.net/jsonoperasihitung.php";
	private static final String ARRAY_SUB_JUDUL = "sub_judul";
	private static final String ARRAY_ANAK_SUB_BAB = "anak_subbab";
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_MATERI = "materi";

	// private ArrayList<HashMap<String, String>> daftarMateri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_operasi_hitung_bilangan_bulat);

		// daftarMateri = new ArrayList<HashMap<String, String>>();

		btnMateriOperasiHitungBilanganBulatKembali = (Button) findViewById(R.id.btnMateriOperasiHitungBilanganBulatKembali);
		btnMateriOperasiHitungBilanganBulatKembali.setOnClickListener(this);

		btnMateriOperasiHitungBilanganBulatSelanjutnya = (Button) findViewById(R.id.btnMateriOperasiHitungBilanganBulatSelanjutnya);
		btnMateriOperasiHitungBilanganBulatSelanjutnya.setOnClickListener(this);

		lvViewMateri = (ListView) findViewById(R.id.lvTest);
		lvViewMateri.setOnItemClickListener(this);

		txtViewMateri = (TextView) findViewById(R.id.txtViewMateriOperasiHitungBilanganBulat);
		txtViewJudul = (TextView) findViewById(R.id.txtViewJudul);

		// this.testJson();
		new GetMateriOperasiHitungBilanganBulat().execute();
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

	private class GetMateriOperasiHitungBilanganBulat extends
			AsyncTask<String, String, String> {
		private String subJudul;
		private String anakSubBab;
		private String isiMateri;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(
					MateriOperasiHitungBilanganBulat.this);
			mProgressDialog
					.setMessage("Loading Materi Operasi Hitung Bilangan Bulat. Please wait...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject json = jsonParser.ambilJSonUrl(LINK_URL);

				materi = json.getJSONArray(ARRAY_MATERI);

				for (int i = 0; i < materi.length(); i++) {
					JSONObject arrayJson = materi.getJSONObject(i);
					subJudul = arrayJson.getString(ARRAY_SUB_JUDUL);
					anakSubBab = arrayJson.getString(ARRAY_ANAK_SUB_BAB);
					isiMateri = arrayJson.getString(ARRAY_ISI_MATERI);

					Log.d(TAG, "sub judul: " + subJudul + ", anak sub bab: "
							+ anakSubBab + ", isi materi: " + isiMateri);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					txtViewJudul.setText(subJudul + "");
					txtViewMateri.setText(anakSubBab + "\n" + isiMateri + "\n");
				}
			});
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}
}
