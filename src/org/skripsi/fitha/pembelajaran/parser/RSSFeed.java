package org.skripsi.fitha.pembelajaran.parser;

import java.util.List;
import java.util.Vector;

public class RSSFeed {
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubDate = null;
	private String category = null;
	private List<RSSItem> itemList;

	public RSSFeed() {
		itemList = new Vector<RSSItem>(0);
	}

	public void addItem(RSSItem item) {
		itemList.add(item);
	}

	public RSSItem getItem(int location) {
		return itemList.get(location);
	}

	public List<RSSItem> getList() {
		return itemList;
	}

	public void setTitle(String value) {
		title = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setLink(String value) {
		link = value;
	}

	public void setPubdate(String value) {
		pubDate = value;
	}

	public void setCategory(String value) {
		category = value;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLink() {
		return link;
	}

	public String getPubdate() {
		return pubDate;
	}

	public String getCategory() {
		return category;
	}
}