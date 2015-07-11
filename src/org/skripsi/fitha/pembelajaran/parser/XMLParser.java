package org.skripsi.fitha.pembelajaran.parser;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

/**
 * 
 * @author blackshadow
 *
 */
public class XMLParser {

	/**
	 * Konstruktor dari kelas
	 */
	public XMLParser() {

	}

	/**
	 * Mengambil XML dari URL menggunakan permintaan HTTP
	 * 
	 * @param url
	 *            link url
	 * @return xml
	 */
	public String getXmlFromUrl(String url) {
		String xml = null;

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return xml;
	}

	/**
	 * Mengambil elemen XML DOM
	 * 
	 * @param xml
	 *            string xml
	 * @return doc
	 */
	public Document getDomElement(String xml) {
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is);
		} catch (ParserConfigurationException pce) {
			Log.e("Error: ", pce.getMessage());
			return null;
		} catch (SAXException se) {
			Log.e("Error: ", se.getMessage());
			return null;
		} catch (IOException ioe) {
			Log.e("Error: ", ioe.getMessage());
			return null;
		}

		return doc;
	}

	/**
	 * Mengambil nilai node
	 * 
	 * @param elemen
	 *            elemen dari node
	 * @return none
	 */
	public final String ambilElemenNilai(Node elemen) {
		Node child;

		if (elemen != null) {
			if (elemen.hasChildNodes()) {
				for (child = elemen.getFirstChild(); child != null; child = child
						.getNextSibling()) {
					if (child.getNodeType() == Node.TEXT_NODE) {
						return child.getNodeValue();
					}
				}
			}
		}

		return "";
	}

	/**
	 * Mengambil nilai node
	 * 
	 * @param item
	 *            elemen dari node
	 * @param str
	 *            string
	 * @return ambil elemen nilai
	 */
	public String ambilNilai(Element item, String str) {
		NodeList nodeList = item.getElementsByTagName(str);
		return this.ambilElemenNilai(nodeList.item(0));
	}
}
