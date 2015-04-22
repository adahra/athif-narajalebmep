package org.skripsi.fitha.pembelajaran;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestJson extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_json);
		
		TextView wid = (TextView) findViewById(R.id.wid);
		TextView name = (TextView) findViewById(R.id.name);
		TextView url = (TextView) findViewById(R.id.url);
		
		JSONObject json = null;
		String str = "";
		HttpResponse response;
		HttpClient myClient = new DefaultHttpClient();
		HttpPost myConnection = new HttpPost("http://demos.tricksofit.com/files/json.php");
		
		try {
			response = myClient.execute(myConnection);
			str = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			JSONArray jArray = new JSONArray(str);
			json = jArray.getJSONObject(0);
			
			wid.setText(json.getString("id"));
			name.setText(json.getString("name"));
			url.setText(json.getString("url"));
		} catch (JSONException je) {
			je.printStackTrace();
		}
	}
}
