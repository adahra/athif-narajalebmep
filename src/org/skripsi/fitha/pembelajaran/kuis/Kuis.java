package org.skripsi.fitha.pembelajaran.kuis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.R;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author blackshadow
 *
 */
public class Kuis extends Activity implements OnClickListener {
	private final String TAG = getClass().getSimpleName();

	private String soal;
	private String jawabanA;
	private String jawabanB;
	private String jawabanC;
	private String jawabanD;
	private String gambar;
	private String gambar2;
	private String gambar3;
	private String gambar4;
	private String gambar5;
	private String jawaban;
	private String gambar6;
	private String id_bab;
	private String idbab;
	private ProgressDialog mProgressDialog;
	private JSONArray kuis = null;

	private int indekkuis = 0;
	private VarKuis varkuis;
	private int jumlahsoal = 0;
	private int hasil1 = 0;
	private int hasil2 = 0;
	private int hasil3 = 0;
	private int hasil4 = 0;
	private int hasil5 = 0;
	private int hasil6 = 0;

	private List<VarKuis> listKuis;

	private Button btnPertanyaanSelanjutnya;
	private Button btnTampilJawabanA;
	private Button btnTampilJawabanB;
	private Button btnTampilJawabanC;
	private Button btnTampilJawabanD;
	private Button btnKomplit;

	private ImageButton ibTampilJawabanA;
	private ImageButton ibTampilJawabanB;
	private ImageButton ibTampilJawabanC;
	private ImageButton ibTampilJawabanD;
	private ImageView ivTampilSoal;

	private TextView tvTampilWaktu;
	private TextView tvTampilKuis;
	private TextView tvTampilSoal;
	private CountDownTimer mCountDownTimer;
	private final long waktuMulai = 121 * 60 * 1000;
	private final long interval = 1 * 1000;

	private static final String LINK_URL = "http://elearningmath.nazuka.net/jsonkuis.php";
	private static final String ARRAY_KUIS = "kuis";
	private static final String ARRAY_SOAL = "soal";
	private static final String ARRAY_JAWABAN_A = "jawabanA";
	private static final String ARRAY_JAWABAN_B = "jawabanB";
	private static final String ARRAY_JAWABAN_C = "jawabanC";
	private static final String ARRAY_JAWABAN_D = "jawabanD";
	private static final String ARRAY_GAMBAR = "gambar";
	private static final String ARRAY_GAMBAR2 = "gambar2";
	private static final String ARRAY_GAMBAR3 = "gambar3";
	private static final String ARRAY_GAMBAR4 = "gambar4";
	private static final String ARRAY_GAMBAR5 = "gambar5";
	private static final String ARRAY_KUNCI_JAWABAN = "jawaban";
	private static final String ARRAY_GAMBAR6 = "gambar6";
	private static final String ARRAY_ID_BAB = "id_bab";

	private ArrayList<HashMap<String, String>> daftarKuis;

	private int mIDKategori;
	private List<Integer> mIndeksPertanyaan;
	private int mIndeksPertanyaanSelanjutnya = 0;
	private int mPertanyaanTotal = 0;
	private int mPertanyaanDiJawab = 0;
	private int mJawabanBenar = 0;
	private List<Button> buttonJawaban;
	private LinearLayout.LayoutParams mLayoutButtonJawaban;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kuis);

		mCountDownTimer = new Waktu(waktuMulai, interval);
		mCountDownTimer.start();

		mLayoutButtonJawaban = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mLayoutButtonJawaban.setMargins(30, 20, 30, 0);

		daftarKuis = new ArrayList<HashMap<String, String>>();

		tvTampilKuis = (TextView) findViewById(R.id.tvTampilKuis);

		ivTampilSoal = (ImageView) findViewById(R.id.ivTampilImg);
		tvTampilSoal = (TextView) findViewById(R.id.tvTampilSoal);

		btnPertanyaanSelanjutnya = (Button) findViewById(R.id.btnPertanyaanSelanjutnya);
		btnPertanyaanSelanjutnya.setOnClickListener(this);

		btnTampilJawabanA = (Button) findViewById(R.id.btnTampilJawabanA);
		btnTampilJawabanA.setOnClickListener(this);

		btnTampilJawabanB = (Button) findViewById(R.id.btnTampilJawabanB);
		btnTampilJawabanB.setOnClickListener(this);

		btnTampilJawabanC = (Button) findViewById(R.id.btnTampilJawabanC);
		btnTampilJawabanC.setOnClickListener(this);

		btnTampilJawabanD = (Button) findViewById(R.id.btnTampilJawabanD);
		btnTampilJawabanD.setOnClickListener(this);

		ibTampilJawabanA = (ImageButton) findViewById(R.id.ibJawabanA);
		ibTampilJawabanA.setOnClickListener(this);

		ibTampilJawabanB = (ImageButton) findViewById(R.id.ibJawabanB);
		ibTampilJawabanB.setOnClickListener(this);

		ibTampilJawabanC = (ImageButton) findViewById(R.id.ibJawabanC);
		ibTampilJawabanC.setOnClickListener(this);

		ibTampilJawabanD = (ImageButton) findViewById(R.id.ibJawabanD);
		ibTampilJawabanD.setOnClickListener(this);

		tvTampilWaktu = (TextView) findViewById(R.id.tvTampilWaktu);
		tvTampilWaktu.setText(String.valueOf(waktuMulai / 1000));

		try {
			listKuis = new GetKuis().execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		if (listKuis != null) {
			jumlahsoal = listKuis.size();
			tampilkankuis();
		} else {

		}

		sembunyikanKomponen();
	}

	/**
	 * 
	 */
	private void tampilkankuis() {
		if (indekkuis + 1 <= jumlahsoal) {
			varkuis = listKuis.get(indekkuis);
			tvTampilKuis.setText("Pertanyaan Ke " + (indekkuis + 1) + " Dari "
					+ jumlahsoal);
			tvTampilSoal.setText(varkuis.getsoal());
			btnTampilJawabanA.setText(varkuis.getJawabanA());
			btnTampilJawabanB.setText(varkuis.getJawabanB());
			btnTampilJawabanC.setText(varkuis.getJawabanC());
			btnTampilJawabanD.setText(varkuis.getJawabanD());
			idbab = listKuis.get(indekkuis).getIdBab();
			enablebutton(true);

		} else {
			btnPertanyaanSelanjutnya.setText("Lihat Nilai");
		}
	}

	/**
	 * 
	 * @param v
	 */
	private void aksiTombolBerikutnya(View v) {
		indekkuis++;
		if (indekkuis + 1 <= jumlahsoal) {
			tampilkankuis();

		} else {
			btnPertanyaanSelanjutnya.setText("Lihat Nilai");
			Intent intent = new Intent(getApplicationContext(), Nilai.class);
			intent.putExtra("hasil1", hasil1);
			intent.putExtra("hasil2", hasil2);
			intent.putExtra("hasil3", hasil3);
			intent.putExtra("hasil4", hasil4);
			intent.putExtra("hasil5", hasil5);
			intent.putExtra("hasil6", hasil6);
			intent.putExtra("jumlahsoal", jumlahsoal);
			startActivity(intent);
		}
	}

	/**
	 * 
	 */
	private void sembunyikanKomponen() {
		ibTampilJawabanA.setVisibility(View.GONE);
		ibTampilJawabanB.setVisibility(View.GONE);
		ibTampilJawabanC.setVisibility(View.GONE);
		ibTampilJawabanD.setVisibility(View.GONE);
	}

	/**
	 * 
	 * @param pilihanjawaban
	 * @param jawaban
	 * @return
	 */
	private boolean cekjawaban(String pilihanjawaban, String jawaban) {
		if (pilihanjawaban.equals(jawaban)) {
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @param v
	 */
	public void jawabanAOnClick(View v) {
		String pilihanjawaban = btnTampilJawabanA.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varkuis.getJawaban());
		if (hasil == true) {
			if (idbab.equals("b1")) {
				hasil1++;
			} else if (idbab.equals("b2")) {
				hasil2++;
			} else if (idbab.equals("b3")) {
				hasil3++;
			} else if (idbab.equals("b4")) {
				hasil4++;
			} else if (idbab.equals("b5")) {
				hasil5++;
			} else if (idbab.equals("b6")) {
				hasil6++;
			}
		}
	}

	/**
	 * 
	 * @param v
	 */
	public void jawabanBOnClick(View v) {
		String pilihanjawaban = btnTampilJawabanB.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varkuis.getJawaban());
		if (hasil == true) {
			if (idbab.equals("b1")) {
				hasil1++;
			} else if (idbab.equals("b2")) {
				hasil2++;
			} else if (idbab.equals("b3")) {
				hasil3++;
			} else if (idbab.equals("b4")) {
				hasil4++;
			} else if (idbab.equals("b5")) {
				hasil5++;
			} else if (idbab.equals("b6")) {
				hasil6++;
			}
		}
	}

	/**
	 * 
	 * @param v
	 */
	public void jawabanCOnClick(View v) {
		String pilihanjawaban = btnTampilJawabanC.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varkuis.getJawaban());
		if (hasil == true) {
			if (idbab.equals("b1")) {
				hasil1++;
			} else if (idbab.equals("b2")) {
				hasil2++;
			} else if (idbab.equals("b3")) {
				hasil3++;
			} else if (idbab.equals("b4")) {
				hasil4++;
			} else if (idbab.equals("b5")) {
				hasil5++;
			} else if (idbab.equals("b6")) {
				hasil6++;
			}
		}
	}

	/**
	 * 
	 * @param v
	 */
	public void jawabanDOnClick(View v) {
		String pilihanjawaban = btnTampilJawabanD.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varkuis.getJawaban());
		if (hasil == true) {
			if (idbab.equals("b1")) {
				hasil1++;
			} else if (idbab.equals("b2")) {
				hasil2++;
			} else if (idbab.equals("b3")) {
				hasil3++;
			} else if (idbab.equals("b4")) {
				hasil4++;
			} else if (idbab.equals("b5")) {
				hasil5++;
			} else if (idbab.equals("b6")) {
				hasil6++;
			}
		}
	}

	/**
	 * 
	 * @author blackshadow
	 *
	 */
	protected class GetKuis extends AsyncTask<String, String, List<VarKuis>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(Kuis.this);
			mProgressDialog.setMessage("Memuat Soal");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		protected List<VarKuis> doInBackground(String... params) {
			List<VarKuis> list = new ArrayList<VarKuis>();

			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject json = jsonParser.ambilJSonUrl(LINK_URL);

				Log.e("json", ":" + json.toString());
				kuis = json.getJSONArray(ARRAY_KUIS);

				listKuis = new ArrayList<VarKuis>();

				for (int i = 0; i < kuis.length(); i++) {
					JSONObject arrayJson = kuis.getJSONObject(i);
					soal = arrayJson.has(ARRAY_SOAL) ? arrayJson
							.getString(ARRAY_SOAL) : null;
					jawabanA = arrayJson.has(ARRAY_JAWABAN_A) ? arrayJson
							.getString(ARRAY_JAWABAN_A) : null;
					jawabanB = arrayJson.has(ARRAY_JAWABAN_B) ? arrayJson
							.getString(ARRAY_JAWABAN_B) : null;
					jawabanC = arrayJson.has(ARRAY_JAWABAN_C) ? arrayJson
							.getString(ARRAY_JAWABAN_C) : null;
					jawabanD = arrayJson.has(ARRAY_JAWABAN_D) ? arrayJson
							.getString(ARRAY_JAWABAN_D) : null;
					jawaban = arrayJson.has(ARRAY_KUNCI_JAWABAN) ? arrayJson
							.getString(ARRAY_KUNCI_JAWABAN) : null;
					id_bab = arrayJson.has(ARRAY_ID_BAB) ? arrayJson
							.getString(ARRAY_ID_BAB) : null;
					gambar = arrayJson.has(ARRAY_GAMBAR) ? arrayJson
							.getString(ARRAY_GAMBAR) : null;
					gambar2 = arrayJson.has(ARRAY_GAMBAR2) ? arrayJson
							.getString(ARRAY_GAMBAR2) : null;
					gambar3 = arrayJson.has(ARRAY_GAMBAR3) ? arrayJson
							.getString(ARRAY_GAMBAR3) : null;
					gambar4 = arrayJson.has(ARRAY_GAMBAR4) ? arrayJson
							.getString(ARRAY_GAMBAR4) : null;
					gambar5 = arrayJson.has(ARRAY_GAMBAR5) ? arrayJson
							.getString(ARRAY_GAMBAR5) : null;
					gambar6 = arrayJson.has(ARRAY_GAMBAR6) ? arrayJson
							.getString(ARRAY_GAMBAR6) : null;

					VarKuis kuis = new VarKuis();
					kuis.setSoal(soal);
					kuis.setJawabanA(jawabanA);
					kuis.setJawabanB(jawabanB);
					kuis.setJawabanC(jawabanC);
					kuis.setJawabanD(jawabanD);
					kuis.setJawaban(jawaban);
					kuis.setIdBab(id_bab);
					kuis.setGambar(gambar);
					kuis.setGambar2(gambar2);
					kuis.setGambar3(gambar3);
					kuis.setGambar4(gambar4);
					kuis.setGambar5(gambar5);
					kuis.setGambar6(gambar6);

					list.add(kuis);

					Log.d(TAG, "soal : " + soal + " jawabanA: " + jawabanA
							+ " jawabanB: " + jawabanB + " jawabanC: "
							+ jawabanC + "jawabanD: " + jawabanD);
				}
			} catch (JSONException je) {
				je.printStackTrace();
			}

			return list;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		protected void onPostExecute(List<VarKuis> result) {
			super.onPostExecute(result);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPertanyaanSelanjutnya:
			aksiTombolBerikutnya(v);
			break;
		case R.id.btnTampilJawabanA:
			jawabanAOnClick(v);
			enablebutton(false);
			break;
		case R.id.btnTampilJawabanB:
			jawabanBOnClick(v);
			enablebutton(false);
			break;
		case R.id.btnTampilJawabanC:
			jawabanCOnClick(v);
			enablebutton(false);
			break;
		case R.id.btnTampilJawabanD:
			jawabanDOnClick(v);
			enablebutton(false);
			break;
		case R.id.ibJawabanA:
			break;
		case R.id.ibJawabanB:
			break;
		case R.id.ibJawabanC:
			break;
		case R.id.ibJawabanD:
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @param b
	 */
	private void enablebutton(boolean b) {
		btnTampilJawabanA.setEnabled(b);
		btnTampilJawabanB.setEnabled(b);
		btnTampilJawabanC.setEnabled(b);
		btnTampilJawabanD.setEnabled(b);
	}

	/**
	 * 
	 * @param nomor
	 */
	private void sembunyikanKomponen(int nomor) {
		ivTampilSoal.setVisibility(nomor);
		tvTampilKuis.setVisibility(nomor);
		tvTampilSoal.setVisibility(nomor);
		btnPertanyaanSelanjutnya.setVisibility(nomor);
		btnTampilJawabanA.setVisibility(nomor);
		btnTampilJawabanB.setVisibility(nomor);
		btnTampilJawabanC.setVisibility(nomor);
		btnTampilJawabanD.setVisibility(nomor);
		ibTampilJawabanA.setVisibility(nomor);
		ibTampilJawabanB.setVisibility(nomor);
		ibTampilJawabanC.setVisibility(nomor);
		ibTampilJawabanD.setVisibility(nomor);
	}

	/**
	 * 
	 */
	private void tampilDialog() {
		AlertDialog mAlertDialog;
		AlertDialog.Builder mAlertDialogBuilder = new AlertDialog.Builder(
				Kuis.this);
		mAlertDialogBuilder.setTitle("Pesan");
		mAlertDialogBuilder.setCancelable(true);
		mAlertDialogBuilder.setMessage("Waktu Telah Habis");
		mAlertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Kuis.this.finish();
						dialog.dismiss();
					}

				});

		mAlertDialog = mAlertDialogBuilder.create();
		mAlertDialog.show();
	}

	/**
	 * 
	 * @author blackshadow
	 *
	 */
	private class Waktu extends CountDownTimer {

		/**
		 * 
		 * @param millisInFuture
		 * @param countDownInterval
		 */
		public Waktu(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.CountDownTimer#onTick(long)
		 */
		@Override
		public void onTick(long millisUntilFinished) {
			long detik = millisUntilFinished / 1000;
			tvTampilWaktu.setText(String.format("%02d:%02d:%02d", detik / 3600,
					(detik % 3600) / 60, (detik % 60)));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.CountDownTimer#onFinish()
		 */
		@Override
		public void onFinish() {
			tvTampilWaktu.setText("Waktu Habis");
			sembunyikanKomponen(View.GONE);
			tampilDialog();
		}

		/**
		 * 
		 * @author blackshadow
		 *
		 */
		private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
			 */
			protected Bitmap doInBackground(String... urls) {
				String urldisplay = urls[0];
				Log.e("Error", urldisplay);
				Bitmap mIcon11 = null;
				try {
					InputStream in = new java.net.URL(urldisplay).openStream();
					mIcon11 = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return mIcon11;
			}
		}
	}
}
