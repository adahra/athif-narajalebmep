package org.skripsi.fitha.pembelajaran.materi;

import java.io.InputStream;
import java.net.URI;

import org.skripsi.fitha.pembelajaran.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class IsiMateri extends Activity implements OnClickListener {
	private Button btnIsiMateriKembali;
	private TextView txtViewIsiMateri;
	private ImageView ivTampilGambar;
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_GAMBAR = "gambar";
	private static final String ARRAY_GAMBAR2 = "gambar2";
	private static final String ARRAY_GAMBAR3 = "gambar3";
	private static final String ARRAY_GAMBAR4 = "gambar4";
	private static final String ARRAY_GAMBAR5 = "gambar5";
	private static final String ARRAY_SUARA = "suara";

	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mProgressDialog = new ProgressDialog(IsiMateri.this);
		mProgressDialog.setMessage("Memuat Gambar. Silahkan Tunggu!");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setCancelable(true);
		mProgressDialog.show();
		setContentView(R.layout.activity_isi_materi);

		Intent mIntent = getIntent();
		String isiMateri = mIntent.getStringExtra(ARRAY_ISI_MATERI);
		String gambar = mIntent.getStringExtra(ARRAY_GAMBAR);
		String gambar2 = mIntent.getStringExtra(ARRAY_GAMBAR2);
		String gambar3 = mIntent.getStringExtra(ARRAY_GAMBAR3);
		String gambar4 = mIntent.getStringExtra(ARRAY_GAMBAR4);
		String gambar5 = mIntent.getStringExtra(ARRAY_GAMBAR5);
		String suara = mIntent.getStringExtra(ARRAY_SUARA);

		txtViewIsiMateri = (TextView) findViewById(R.id.txtViewIsiMateri);
		txtViewIsiMateri.setText(isiMateri);

		ivTampilGambar = (ImageView) findViewById(R.id.ivIsiMateriTampil);

		btnIsiMateriKembali = (Button) findViewById(R.id.btnIsiMateriKembali);
		btnIsiMateriKembali.setOnClickListener(this);

		new DownloadImageTask(ivTampilGambar)
				.execute("http://elearningmath.nazuka.net/materi/" + gambar);
		
		audioPlayer("http://elearningmath.nazuka.net/suara/", suara);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIsiMateriKembali:
			IsiMateri.this.finish();
			break;
		case R.id.imgBtnIsiMateriPlay:
			IsiMateri.this.finish();
			break;
		case R.id.imgBtnIsiMateriStop:
			IsiMateri.this.finish();
			break;
		default:
			break;
		}
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

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

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
			mProgressDialog.dismiss();
		}
	}

	public void audioPlayer(String path, String fileName) {
		// set up MediaPlayer
		MediaPlayer mp = new MediaPlayer();

		try {
			mp.setDataSource(path + "/" + fileName);
			mp.prepare();
			mp.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
