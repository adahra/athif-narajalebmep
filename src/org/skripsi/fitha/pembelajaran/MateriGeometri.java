package org.skripsi.fitha.pembelajaran;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MateriGeometri extends Activity implements OnClickListener {
	private Button btnMateriGeometriKembali;
	private Button btnMateriGeometriSelanjutnya;
	private TextView txtViewMateri;

	private ProgressDialog mProgressDialog;

	// private JSONParser mJsonParser = new JSONParser();
	private JSONArray materi = null;

	private static final String LINK_URL = "http://www.e-learningmatematika.esy.es/jsonmateri.php";
	private static final String ARRAY_SUB_JUDUL = "sub_judul";
	private static final String ARRAY_ANAK_SUB_BAB = "anak_subbab";
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_MATERI = "materi";

	private ArrayList<HashMap<String, String>> daftarMateri = new ArrayList<HashMap<String, String>>();
	// private String bab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_geometri);

		btnMateriGeometriKembali = (Button) findViewById(R.id.btnMateriGeometriKembali);
		btnMateriGeometriKembali.setOnClickListener(this);

		btnMateriGeometriSelanjutnya = (Button) findViewById(R.id.btnMateriGeometriSelanjutnya);
		btnMateriGeometriSelanjutnya.setOnClickListener(this);
		
		txtViewMateri = (TextView) findViewById(R.id.txtViewMateriGeometri);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriGeometriKembali:
			MateriGeometri.this.finish();
			break;
		case R.id.btnMateriGeometriSelanjutnya:
			new GetMateriGeometri().execute();
			break;
		default:
			break;
		}
	}

	class GetMateriGeometri extends AsyncTask<String, String, String> {
		private String subJudul;
		private String anakSubBab;
		private String isiMateri;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MateriGeometri.this);
			mProgressDialog
					.setMessage("Loading Materi Geometri. Please wait...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			/*
			 * runOnUiThread(new Runnable() {
			 * 
			 * @Override public void run() { int success; try {
			 * List<NameValuePair> params = new ArrayList<NameValuePair>();
			 * params.add(new BasicNameValuePair("id_bab", bab));
			 * 
			 * JSONObject json = mJsonParser.makeHttpRequest( mKoneksi.getUrl(),
			 * "GET", params);
			 * 
			 * Log.d("Materi Geometri", json.toString());
			 * 
			 * success = json.getInt(TAG_SUCCESS);
			 * 
			 * if (success == 1) { JSONArray materiGeometri = json
			 * .getJSONArray(TAG_MATERI_GEOMETRI); JSONObject materi =
			 * materiGeometri.getJSONObject(0); } else {
			 * 
			 * } } catch (JSONException je) { je.printStackTrace(); } } });
			 */

			JSONParser jsonParser = new JSONParser();
			JSONObject json = jsonParser.ambilJSonUrl(LINK_URL);

			try {
				materi = json.getJSONArray(ARRAY_MATERI);

				for (int i = 0; i < materi.length(); i++) {
					JSONObject arrayJson = materi.getJSONObject(i);
					subJudul = arrayJson.getString(ARRAY_SUB_JUDUL);
					anakSubBab = arrayJson.getString(ARRAY_ANAK_SUB_BAB);
					isiMateri = arrayJson.getString(ARRAY_ISI_MATERI);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(ARRAY_SUB_JUDUL, subJudul);
					map.put(ARRAY_ANAK_SUB_BAB, anakSubBab);
					map.put(ARRAY_MATERI, isiMateri);
					daftarMateri.add(map);
				}
			} catch (Exception e) {
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
					txtViewMateri.setText("Judul: " + subJudul);
				}
			});

		}

	}
}
