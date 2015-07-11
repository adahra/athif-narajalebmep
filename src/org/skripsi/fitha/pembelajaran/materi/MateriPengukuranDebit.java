package org.skripsi.fitha.pembelajaran.materi;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.R;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
// import android.widget.TextView;
import android.widget.SimpleAdapter;

/**
 * 
 * @author blackshadow
 *
 */
public class MateriPengukuranDebit extends Activity implements OnClickListener,
		OnItemClickListener {
	private final String TAG = getClass().getSimpleName();

	private String subJudul;
	private String anakSubBab;
	private String isiMateri;
	private String gambar;
	private String gambar2;
	private String gambar3;
	private String gambar4;
	private String gambar5;
	private String suara;

	private Button btnMateriPengukuranDebitKembali;
	private Button btnMateriPengukuranDebitSelanjutnya;
	// private TextView txtViewJudul;
	// private TextView txtViewMateri;
	private ListView lvViewMateri;

	private ProgressDialog mProgressDialog;

	private JSONArray materi = null;

	private static final String LINK_URL = "http://elearningmath.nazuka.net/jsonpengukuran.php";
	private static final String ARRAY_SUB_JUDUL = "sub_judul";
	private static final String ARRAY_ANAK_SUB_BAB = "anak_subbab";
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_MATERI = "materi";
	private static final String ARRAY_GAMBAR = "gambar";
	private static final String ARRAY_GAMBAR2 = "gambar2";
	private static final String ARRAY_GAMBAR3 = "gambar3";
	private static final String ARRAY_GAMBAR4 = "gambar4";
	private static final String ARRAY_GAMBAR5 = "gambar5";
	private static final String ARRAY_SUARA = "suara";

	private ArrayList<HashMap<String, String>> daftarMateri;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_pengukuran_debit);

		daftarMateri = new ArrayList<HashMap<String, String>>();

		btnMateriPengukuranDebitKembali = (Button) findViewById(R.id.btnMateriPengukuranDebitKembali);
		btnMateriPengukuranDebitKembali.setOnClickListener(this);

		btnMateriPengukuranDebitSelanjutnya = (Button) findViewById(R.id.btnMateriPengukuranDebitSelanjutnya);
		btnMateriPengukuranDebitSelanjutnya.setOnClickListener(this);

		lvViewMateri = (ListView) findViewById(R.id.lvMateriPengukuranDebit);
		lvViewMateri.setOnItemClickListener(this);

		// txtViewMateri = (TextView)
		// findViewById(R.id.txtViewMateriPengukuranDebit);
		// txtViewJudul = (TextView) findViewById(R.id.txtViewJudul);

		new GetMateriPengukuranDebit().execute();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriPengukuranDebitKembali:
			MateriPengukuranDebit.this.finish();
			break;
		// case R.id.btnMateriPengukuranDebitSelanjutnya:
		// break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @author blackshadow
	 *
	 */
	@SuppressLint("NewApi")
	private class GetMateriPengukuranDebit extends
			AsyncTask<String, String, String> {
		// private String subJudul;
		// private String anakSubBab;
		// private String isiMateri;

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MateriPengukuranDebit.this);
			mProgressDialog
					.setMessage("Memuat Materi Pengukuran Debit. Silahkan Tunggu!");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
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
					gambar = arrayJson.getString(ARRAY_GAMBAR);
					gambar2 = arrayJson.getString(ARRAY_GAMBAR2);
					gambar3 = arrayJson.getString(ARRAY_GAMBAR3);
					gambar4 = arrayJson.getString(ARRAY_GAMBAR4);
					gambar5 = arrayJson.getString(ARRAY_GAMBAR5);
					suara = arrayJson.getString(ARRAY_SUARA);

					HashMap<String, String> map = new HashMap<String, String>();
					map.put(ARRAY_SUB_JUDUL, subJudul);
					map.put(ARRAY_ANAK_SUB_BAB, anakSubBab);
					map.put(ARRAY_ISI_MATERI, isiMateri);
					map.put(ARRAY_GAMBAR, gambar);
					map.put(ARRAY_GAMBAR2, gambar2);
					map.put(ARRAY_GAMBAR3, gambar3);
					map.put(ARRAY_GAMBAR4, gambar4);
					map.put(ARRAY_GAMBAR5, gambar5);
					map.put(ARRAY_SUARA, suara);

					daftarMateri.add(map);

					Log.d(TAG, "sub judul: " + subJudul + ", anak sub bab: "
							+ anakSubBab + ", isi materi: " + isiMateri);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					ListAdapter adapter = new SimpleAdapter(
							MateriPengukuranDebit.this, daftarMateri,
							R.layout.list_item, new String[] {
									ARRAY_ANAK_SUB_BAB, ARRAY_SUB_JUDUL },
							new int[] { R.id.sub_judul, R.id.materi, });

					lvViewMateri.setAdapter(adapter);

					// txtViewJudul.setText(subJudul + "");
					// txtViewMateri.setText(anakSubBab + "\n" + isiMateri +
					// "\n");
				}
			});
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		HashMap<String, String> map = daftarMateri.get(position);
		Intent mIntent = new Intent(getApplicationContext(), IsiMateri.class);
		mIntent.putExtra(ARRAY_ISI_MATERI, map.get(ARRAY_ISI_MATERI));
		mIntent.putExtra(ARRAY_GAMBAR, map.get(ARRAY_GAMBAR));
		mIntent.putExtra(ARRAY_GAMBAR2, map.get(ARRAY_GAMBAR2));
		mIntent.putExtra(ARRAY_GAMBAR3, map.get(ARRAY_GAMBAR3));
		mIntent.putExtra(ARRAY_GAMBAR4, map.get(ARRAY_GAMBAR4));
		mIntent.putExtra(ARRAY_GAMBAR5, map.get(ARRAY_GAMBAR5));
		mIntent.putExtra(ARRAY_SUARA, map.get(ARRAY_SUARA));

		startActivity(mIntent);

	}
}
