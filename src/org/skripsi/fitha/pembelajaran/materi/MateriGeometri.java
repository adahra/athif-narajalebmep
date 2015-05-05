package org.skripsi.fitha.pembelajaran.materi;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.R;
import org.skripsi.fitha.pembelajaran.database.Database;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.app.Activity;
// import android.app.AlertDialog;
// import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
// import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MateriGeometri extends Activity implements OnClickListener,
		OnItemClickListener {
	private final String TAG = getClass().getSimpleName();
	
	// private final String sql = "";

	private String subJudul;
	private String anakSubBab;
	private String isiMateri;
	
	private Button btnMateriGeometriKembali;
	private Button btnMateriGeometriSelanjutnya;
	private ListView lvViewMateri;

	// private static final int tampilError = 1;
	private ProgressDialog mProgressDialog;

	private JSONArray materi = null;

	private static final String LINK_URL = "http://pejuangcinta.nazuka.net/jsongeometri.php";
	private static final String ARRAY_SUB_JUDUL = "sub_judul";
	private static final String ARRAY_ANAK_SUB_BAB = "anak_subbab";
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_MATERI = "materi";

	private ArrayList<HashMap<String, String>> daftarMateri;
	
	private Database database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_geometri);

		daftarMateri = new ArrayList<HashMap<String, String>>();
		
		database = new Database(getApplicationContext());

		btnMateriGeometriKembali = (Button) findViewById(R.id.btnMateriGeometriKembali);
		btnMateriGeometriKembali.setOnClickListener(this);

		btnMateriGeometriSelanjutnya = (Button) findViewById(R.id.btnMateriGeometriSelanjutnya);
		btnMateriGeometriSelanjutnya.setOnClickListener(this);

		lvViewMateri = (ListView) findViewById(R.id.lvTest);
		lvViewMateri.setOnItemClickListener(this);

		new GetMateriGeometri().execute();

		// database.getWritableDatabase().execSQL(sql);
		
		/*
		 * if (koneksi.cekStatus(this.getApplicationContext())) { koneksi = new
		 * Koneksi(LINK_URL); new GetMateriGeometri().execute(); } else {
		 * showDialog(tampilError); }
		 */
	}

	/*
	 * @Override protected Dialog onCreateDialog(int id) { Dialog dialog = null;
	 * switch (id) { case tampilError: AlertDialog.Builder errorDialog = new
	 * AlertDialog.Builder( getApplicationContext());
	 * errorDialog.setTitle("Kesalahan Koneksi");
	 * errorDialog.setMessage("Tidak ditemukan koneksi internet");
	 * errorDialog.setNegativeButton("OK", new DialogInterface.OnClickListener()
	 * {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * dialog.dismiss(); } });
	 * 
	 * AlertDialog errorAlert = errorDialog.create(); return errorAlert;
	 * default: break; }
	 * 
	 * return dialog; }
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriGeometriKembali:
			MateriGeometri.this.finish();
			break;
		default:
			break;
		}
	}

	protected class GetMateriGeometri extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MateriGeometri.this);
			mProgressDialog
					.setMessage("Memuat Materi Geometri. Silahkan Tunggu!");
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

					HashMap<String, String> map = new HashMap<String, String>();
					map.put(ARRAY_SUB_JUDUL, subJudul);
					map.put(ARRAY_ANAK_SUB_BAB, anakSubBab);
					map.put(ARRAY_ISI_MATERI, isiMateri);
					daftarMateri.add(map);

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
					ListAdapter adapter = new SimpleAdapter(
							MateriGeometri.this, daftarMateri,
							R.layout.list_item, new String[] {
									ARRAY_ANAK_SUB_BAB, ARRAY_SUB_JUDUL },
							new int[] { R.id.sub_judul, R.id.materi, });

					lvViewMateri.setAdapter(adapter);
				}
			});
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		HashMap<String, String> map = daftarMateri.get(position);
		Intent mIntent = new Intent(getApplicationContext(), IsiMateri.class);
		mIntent.putExtra(ARRAY_ISI_MATERI, map.get(ARRAY_ISI_MATERI));
		startActivity(mIntent);
	}
}
