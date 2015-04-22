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
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MateriPengukuranDebit extends Activity implements OnClickListener,
		OnItemClickListener {
	private final String TAG = getClass().getSimpleName();

	private Button btnMateriPengukuranDebitKembali;
	private Button btnMateriPengukuranDebitSelanjutnya;
	private TextView txtViewJudul;
	private TextView txtViewMateri;
	private ListView lvViewMateri;

	private ProgressDialog mProgressDialog;

	private JSONArray materi = null;

	private static final String LINK_URL = "http://pejuangcinta.nazuka.net/jsonpengukuran.php";
	private static final String ARRAY_SUB_JUDUL = "sub_judul";
	private static final String ARRAY_ANAK_SUB_BAB = "anak_subbab";
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_MATERI = "materi";

	// private ArrayList<HashMap<String, String>> daftarMateri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_pengukuran_debit);

		// daftarMateri = new ArrayList<HashMap<String, String>>();

		btnMateriPengukuranDebitKembali = (Button) findViewById(R.id.btnMateriPengukuranDebitKembali);
		btnMateriPengukuranDebitKembali.setOnClickListener(this);

		btnMateriPengukuranDebitSelanjutnya = (Button) findViewById(R.id.btnMateriPengukuranDebitSelanjutnya);
		btnMateriPengukuranDebitSelanjutnya.setOnClickListener(this);

		lvViewMateri = (ListView) findViewById(R.id.lvTest);
		lvViewMateri.setOnItemClickListener(this);

		txtViewMateri = (TextView) findViewById(R.id.txtViewMateriPengukuranDebit);
		txtViewJudul = (TextView) findViewById(R.id.txtViewJudul);

		new GetMateriPengukuranDebit().execute();

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

	private class GetMateriPengukuranDebit extends
			AsyncTask<String, String, String> {
		private String subJudul;
		private String anakSubBab;
		private String isiMateri;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MateriPengukuranDebit.this);
			mProgressDialog
					.setMessage("Loading Materi Pengukuran Debit. Please wait...");
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
