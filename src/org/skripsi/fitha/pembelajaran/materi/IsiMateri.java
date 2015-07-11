package org.skripsi.fitha.pembelajaran.materi;

import java.io.InputStream;

import org.skripsi.fitha.pembelajaran.R;
import org.skripsi.fitha.pembelajaran.suara.Bunyi;
import org.skripsi.fitha.pembelajaran.suara.BunyiAndroid;
import org.skripsi.fitha.pembelajaran.suara.PeralatanBunyi;

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
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author blackshadow
 *
 */
public class IsiMateri extends Activity implements OnClickListener,
		PeralatanBunyi {
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
	private Bunyi bunyi;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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

		// audioPlayer("http://elearningmath.nazuka.net/suara/", suara);

		bunyi = new BunyiAndroid(this);
		bunyi.suaraBaru("http://elearningmath.nazuka.net/suara/", suara);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
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

	/**
	 * 
	 * @author blackshadow
	 *
	 */
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		/**
		 * 
		 * @param bmImage
		 */
		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 
	 * @param path
	 * @param fileName
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.skripsi.fitha.pembelajaran.suara.PeralatanBunyi#getBunyi()
	 */
	@Override
	public Bunyi getBunyi() {
		// TODO Auto-generated method stub
		return bunyi;
	}
}
