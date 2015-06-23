package org.skripsi.fitha.pembelajaran.latihan;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.MenuUtama;
import org.skripsi.fitha.pembelajaran.R;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Latihan extends Activity implements OnClickListener {
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
	private String kunciJawaban;
	private String gambar7;
	private ProgressDialog mProgressDialog;
	private JSONArray latihan = null;

	private int indeklatihan = 0;
	private VarLatihan varlatihan;
	private int jumlahsoal = 0;

	private List<VarLatihan> listLatihan;

	private TextView tvTampilLatihan;
	private TextView tvTampilSoalLatihan;
	private ImageView ivTampilLatihanImg;
	private Button btnLatTamJawabanA;
	private Button btnLatTamJawabanB;
	private Button btnLatTamJawabanC;
	private Button btnLatTamJawabanD;
	private Button btnPertanyaanSelanjutnya;
	private TextView tvKunciJawaban;
	private ImageView ivKunciJawaban;
	private Button btnKomplit;

	private ImageButton ibLatihanJawabanA;
	private ImageButton ibLatihanJawabanB;
	private ImageButton ibLatihanJawabanC;
	private ImageButton ibLatihanJawabanD;

	private static final String LINK_URL = "http://elearningmath.nazuka.net/jsonlat.php";
	// private static final String LINK_URL =
	// "http://10.0.3.2/e-learningwebsite/jsonlat.php";
	private static final String ARRAY_LATIHAN = "latihan";
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
	private static final String ARRAY_JAWABAN = "jawaban";
	private static final String ARRAY_GAMBAR6 = "gambar6";
	private static final String ARRAY_KUNCI_JAWABAN = "kunci_jawaban";
	private static final String ARRAY_GAMBAR7 = "gambar7";

	private ArrayList<HashMap<String, String>> daftarLatihan;

	private int mIDKatagori;
	private List<Integer> mIndeksPertanyaan;
	private int mIndeksPertanyaanSelanjutnya = 0;
	private int mPertanyaanTotal = 0;
	private int mPertanyaanDiJawab = 0;
	private int mJawabanBenar = 0;
	private List<Button> buttonJawaban;
	private LinearLayout.LayoutParams mLayoutButtonJawaban;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_latihan);

		mLayoutButtonJawaban = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mLayoutButtonJawaban.setMargins(30, 20, 30, 0);

		daftarLatihan = new ArrayList<HashMap<String, String>>();

		tvTampilLatihan = (TextView) findViewById(R.id.tvTampilLatihan);

		ivTampilLatihanImg = (ImageView) findViewById(R.id.ivTampilLatihanImg);

		tvTampilSoalLatihan = (TextView) findViewById(R.id.tvTampilSoalLatihan);

		btnPertanyaanSelanjutnya = (Button) findViewById(R.id.btnPertanyaanSelanjutnya);
		btnPertanyaanSelanjutnya.setOnClickListener(this);

		btnLatTamJawabanA = (Button) findViewById(R.id.btnLatTamJawabanA);
		btnLatTamJawabanA.setOnClickListener(this);

		btnLatTamJawabanB = (Button) findViewById(R.id.btnLatTamJawabanB);
		btnLatTamJawabanB.setOnClickListener(this);

		btnLatTamJawabanC = (Button) findViewById(R.id.btnLatTamJawabanC);
		btnLatTamJawabanC.setOnClickListener(this);

		btnLatTamJawabanD = (Button) findViewById(R.id.btnLatTamJawabanD);
		btnLatTamJawabanD.setOnClickListener(this);

		ibLatihanJawabanA = (ImageButton) findViewById(R.id.ibLatihanJawabanA);
		ibLatihanJawabanA.setOnClickListener(this);

		ibLatihanJawabanB = (ImageButton) findViewById(R.id.ibLatihanJawabanB);
		ibLatihanJawabanB.setOnClickListener(this);

		ibLatihanJawabanC = (ImageButton) findViewById(R.id.ibLatihanJawabanC);
		ibLatihanJawabanC.setOnClickListener(this);

		ibLatihanJawabanD = (ImageButton) findViewById(R.id.ibLatihanJawabanD);
		ibLatihanJawabanD.setOnClickListener(this);

		tvKunciJawaban = (TextView) findViewById(R.id.tvKunciJawaban);

		ivKunciJawaban = (ImageView) findViewById(R.id.ivKunciJawaban);

		try {
			listLatihan = new GetLatihan().execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		if (listLatihan != null) {
			jumlahsoal = listLatihan.size();
			tampilkanlatihan();
		} else {

		}

		// sembunyikanKomponen();
	}

	private void tampilkanlatihan() {
		if (indeklatihan + 1 <= jumlahsoal) {
			varlatihan = listLatihan.get(indeklatihan);
			tvTampilLatihan.setText("Pertanyaan Ke " + (indeklatihan + 1)
					+ " Dari " + jumlahsoal);
			tvTampilSoalLatihan.setText(varlatihan.getSoal());
			btnLatTamJawabanA.setText(varlatihan.getJawabanA());
			btnLatTamJawabanB.setText(varlatihan.getJawabanB());
			btnLatTamJawabanC.setText(varlatihan.getJawabanC());
			btnLatTamJawabanD.setText(varlatihan.getJawabanD());
			enablebutton(true);
			tvKunciJawaban.setText("");

			if (varlatihan.getGambar() != null) {
				ivTampilLatihanImg.setVisibility(View.VISIBLE);

				try {
					Bitmap gam = new DownloadImageTask().execute(
							"http://elearningmath.nazuka.net/latihan/"
									+ varlatihan.getGambar()).get();
					Log.e("gambar", "http://elearningmath.nazuka.net/latihan/"
							+ varlatihan.getGambar());
					ivTampilLatihanImg.setImageBitmap(gam);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			} else {
				btnLatTamJawabanA.setVisibility(View.VISIBLE);
			}

			if (varlatihan.getGambar2() != null) {
				ibLatihanJawabanA.setVisibility(View.VISIBLE);

				try {
					Bitmap gam = new DownloadImageTask().execute(
							"http://elearningmath.nazuka.net/latihan/"
									+ varlatihan.getGambar2()).get();
					Log.e("gambar", "http://elearningmath.nazuka.net/latihan/"
							+ varlatihan.getGambar2());
					ibLatihanJawabanA.setImageBitmap(gam);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			} else {

			}

			if (varlatihan.getGambar3() != null) {
				ibLatihanJawabanB.setVisibility(View.VISIBLE);

				try {
					Bitmap gam = new DownloadImageTask().execute(
							"http://elearningmath.nazuka.net/latihan/"
									+ varlatihan.getGambar3()).get();
					Log.e("gambar", "http://elearningmath.nazuka.net/latihan/"
							+ varlatihan.getGambar3());
					ibLatihanJawabanB.setImageBitmap(gam);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			} else {
				btnLatTamJawabanB.setVisibility(View.VISIBLE);
			}

			if (varlatihan.getGambar4() != null) {
				ibLatihanJawabanC.setVisibility(View.VISIBLE);

				try {
					Bitmap gam = new DownloadImageTask().execute(
							"http://elearningmath.nazuka.net/latihan/"
									+ varlatihan.getGambar4()).get();
					Log.e("gambar", "http://elearningmath.nazuka.net/latihan/"
							+ varlatihan.getGambar4());
					ibLatihanJawabanC.setImageBitmap(gam);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			} else {
				btnLatTamJawabanC.setVisibility(View.VISIBLE);
			}

			if (varlatihan.getGambar5() != null) {
				ibLatihanJawabanD.setVisibility(View.VISIBLE);

				try {
					Bitmap gam = new DownloadImageTask().execute(
							
							"http://elearningmath.nazuka.net/latihan/"
									+ varlatihan.getGambar5()).get();
					Log.e("gambar", "http://elearningmath.nazuka.net/latihan/"
							+ varlatihan.getGambar5());
					ibLatihanJawabanD.setImageBitmap(gam);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			} else {

			}

		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Selamat Anda Telah Menyelesaikan Latihan")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(Latihan.this,
											MenuUtama.class);
									startActivity(intent);
									dialog.dismiss();

								}
							});

			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}

	private void aksiTombolBerikutnya(View v) {
		indeklatihan++;
		tampilkanlatihan();
	// sembunyikanKomponen();
	}

	/**
	private void sembunyikanKomponen() {
		btnLatTamJawabanA.setVisibility(View.GONE);
		btnLatTamJawabanB.setVisibility(View.GONE);
		btnLatTamJawabanC.setVisibility(View.GONE);
		btnLatTamJawabanD.setVisibility(View.GONE);
	}
*/
	private boolean cekjawaban(String pilihanjawaban, String jawaban) {
		if (pilihanjawaban.equals(jawaban)) {
			return true;
		} else
			return false;
	}

	public void jawabanAOnClick(View view) {
		String pilihanjawaban = btnLatTamJawabanA.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getJawaban());
		if (hasil == true) {
			btnLatTamJawabanA.setText("benar");
		} else {
			btnLatTamJawabanA.setText("salah");
		}
	}

	public void jawabanBOnClick(View view) {
		String pilihanjawaban = btnLatTamJawabanB.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getJawaban());
		if (hasil == true) {
			btnLatTamJawabanB.setText("benar");
		} else {
			btnLatTamJawabanB.setText("salah");
		}
	}

	public void jawabanCOnClick(View view) {
		String pilihanjawaban = btnLatTamJawabanC.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getJawaban());
		if (hasil == true) {
			btnLatTamJawabanC.setText("benar");
		} else {
			btnLatTamJawabanC.setText("salah");
		}
	}

	public void jawabanDOnClick(View view) {
		String pilihanjawaban = btnLatTamJawabanD.getText().toString();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getJawaban());
		if (hasil == true) {
			btnLatTamJawabanD.setText("benar");
		} else {
			btnLatTamJawabanD.setText("salah");
		}
	}

	public void jawabangamAOnClick(View view) {
		String pilihanjawaban = varlatihan.getGambar2();
		String jawaban = varlatihan.getGambar6();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getGambar2());
		if (hasil == true) {
			ibLatihanJawabanA.setImageResource(R.drawable.benar);
		} else {
			ibLatihanJawabanA.setImageResource(R.drawable.salah);
		}
	}

	public void jawabangamBOnClick(View view) {
		String pilihanjawaban = varlatihan.getGambar3();
		String jawaban = varlatihan.getGambar6();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getGambar3());
		if (hasil == true) {
			ibLatihanJawabanB.setImageResource(R.drawable.benar);
		} else {
			ibLatihanJawabanB.setImageResource(R.drawable.salah);
		}
	}

	public void jawabangamCOnClick(View view) {
		String pilihanjawaban = varlatihan.getGambar4();
		String jawaban = varlatihan.getGambar6();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getGambar4());
		if (hasil == true) {
			ibLatihanJawabanC.setImageResource(R.drawable.benar);
		} else {
			ibLatihanJawabanC.setImageResource(R.drawable.salah);
		}
	}

	public void jawabangamDOnClick(View view) {
		String pilihanjawaban = varlatihan.getGambar5();
		String jawaban = varlatihan.getGambar6();
		boolean hasil = cekjawaban(pilihanjawaban, varlatihan.getGambar5());
		if (hasil == true) {
			ibLatihanJawabanD.setImageResource(R.drawable.benar);
		} else {
			ibLatihanJawabanD.setImageResource(R.drawable.salah);
		}
	}

	protected class GetLatihan extends
			AsyncTask<String, String, List<VarLatihan>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(Latihan.this);
			mProgressDialog.setMessage("Memuat Soal");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(true);
			// mProgressDialog.show();
		}

		@Override
		protected List<VarLatihan> doInBackground(String... params) {
			List<VarLatihan> list = new ArrayList<VarLatihan>();

			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject json = jsonParser.ambilJSonUrl(LINK_URL);

				Log.e("json", ":" + json.toString());
				latihan = json.getJSONArray(ARRAY_LATIHAN);

				listLatihan = new ArrayList<VarLatihan>();

				for (int i = 0; i < latihan.length(); i++) {
					JSONObject arrayJson = latihan.getJSONObject(i);
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
					jawaban = arrayJson.has(ARRAY_JAWABAN) ? arrayJson
							.getString(ARRAY_JAWABAN) : null;
					kunciJawaban = arrayJson.has(ARRAY_KUNCI_JAWABAN) ? arrayJson
							.getString(ARRAY_KUNCI_JAWABAN) : null;
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
					gambar7 = arrayJson.has(ARRAY_GAMBAR7) ? arrayJson
							.getString(ARRAY_GAMBAR7) : null;

					VarLatihan latihan = new VarLatihan();
					latihan.setSoal(soal);
					latihan.setJawabanA(jawabanA);
					latihan.setJawabanB(jawabanB);
					latihan.setJawabanC(jawabanC);
					latihan.setJawabanD(jawabanD);
					latihan.setJawaban(jawaban);
					latihan.setKunci(kunciJawaban);
					latihan.setGambar(gambar);
					latihan.setGambar2(gambar2);
					latihan.setGambar3(gambar3);
					latihan.setGambar4(gambar4);
					latihan.setGambar5(gambar5);
					latihan.setGambar6(gambar6);
					latihan.setGambar7(gambar7);

					list.add(latihan);

					Log.d(TAG, "soal: " + soal + " jawabanA: " + jawabanA
							+ " jawabanB: " + jawabanB + " jawabanC: "
							+ jawabanC + " jawabanD: " + jawabanD);
				}
			} catch (JSONException je) {
				je.printStackTrace();
			}

			return list;
		}

		@Override
		protected void onPostExecute(List<VarLatihan> result) {
			super.onPostExecute(result);
			// mProgressDialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPertanyaanSelanjutnya:
			aksiTombolBerikutnya(v);
			break;
		case R.id.btnLatTamJawabanA:
			jawabanAOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.btnLatTamJawabanB:
			jawabanBOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.btnLatTamJawabanC:
			jawabanCOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.btnLatTamJawabanD:
			jawabanDOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.ibLatihanJawabanA:
			jawabangamAOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.ibLatihanJawabanB:
			jawabangamBOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.ibLatihanJawabanC:
			jawabangamCOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		case R.id.ibLatihanJawabanD:
			jawabangamDOnClick(v);
			enablebutton(false);
			tampilkankuncijawaban();
			break;
		default:
			break;

		}
	}

	private void enablebutton(boolean b) {
		btnLatTamJawabanA.setEnabled(b);
		btnLatTamJawabanB.setEnabled(b);
		btnLatTamJawabanC.setEnabled(b);
		btnLatTamJawabanD.setEnabled(b);
		ibLatihanJawabanA.setEnabled(b);
		ibLatihanJawabanB.setEnabled(b);
		ibLatihanJawabanC.setEnabled(b);
		ibLatihanJawabanD.setEnabled(b);
	}

	private void tampilkankuncijawaban() {
		tvKunciJawaban.setText(varlatihan.getKunci());
		if (varlatihan.getGambar7() != null) {
			ivKunciJawaban.setVisibility(View.VISIBLE);
			// tvKunciJawaban.setVisibility(View.GONE);

			try {
				Bitmap gam = new DownloadImageTask().execute(
						"http://elearningmath.nazuka.net/latihan/"
								+ varlatihan.getGambar7()).get();
				Log.e("gambar", "http://elearningmath.nazuka.net/latihan/"
						+ varlatihan.getGambar7());
				ivKunciJawaban.setImageBitmap(gam);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		} else {
			ivKunciJawaban.setVisibility(View.GONE);
			// tvKunciJawaban.setVisibility(View.VISIBLE);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

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
