package org.skripsi.fitha.pembelajaran.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {
	private final int state_unknown = 0;
	private final int state_title = 1;
	private final int state_description = 9;
	private final int state_link = 3;
	private final int state_pubdate = 4;
	private final int state_category = 5;
	private int currentState = state_unknown;

	private RSSFeed feed;
	private RSSItem item;

	private boolean itemFound = false;

	public RSSHandler() {
	}

	public RSSFeed getFeed() {
		return feed;
	}

	@Override
	public void startDocument() throws SAXException {
		feed = new RSSFeed();
		item = new RSSItem();
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (localName.equalsIgnoreCase("item")) {
			itemFound = true;
			item = new RSSItem();
			currentState = state_unknown;
		} else if (localName.equalsIgnoreCase("title")) {
			currentState = state_title;
		} else if (localName.equalsIgnoreCase("description")) {
			currentState = state_description;
		} else if (localName.equalsIgnoreCase("link")) {
			currentState = state_link;
		} else if (localName.equalsIgnoreCase("pubDate")) {
			currentState = state_pubdate;
		} else if (localName.equalsIgnoreCase("category")) {
			currentState = state_category;
		} else {
			currentState = state_unknown;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equalsIgnoreCase("item")) {
			feed.addItem(item);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String strCharacters = new String(ch, start, length);

		if (itemFound == true) {
			switch (currentState) {
			case state_title:
				item.setTitle(strCharacters);
				break;
			case state_description:
				item.setDescription(strCharacters);
				break;
			case state_link:
				item.setLink(strCharacters);
				break;
			case state_pubdate:
				item.setPubdate(strCharacters);
				break;
			case state_category:
				item.setCategory(strCharacters);
				break;
			default:
				break;
			}
		} else {
			switch (currentState) {
			case state_title:
				feed.setTitle(strCharacters);
				break;
			case state_description:
				feed.setDescription(strCharacters);
				break;
			case state_link:
				feed.setLink(strCharacters);
				break;
			case state_pubdate:
				feed.setPubdate(strCharacters);
				break;
			case state_category:
				feed.setCategory(strCharacters);
				break;
			default:
				break;
			}
		}

		currentState = state_unknown;
	}
}
