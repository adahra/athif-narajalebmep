package org.skripsi.fitha.pembelajaran.parser;

public class RSSItem {
	private String title = null;
	private String description = null;
	private String link = null;
	private String pubDate = null;
	private String category = null;

	public RSSItem() {
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

	@Override
	public String toString() {
		if (title.length() > 100) {
			return title.substring(0, 100) + "...";
		}

		return title;
	}

	public String toString1() {
		if (description.length() > 100) {
			return description.substring(0, 100);
		}

		return description;
	}
}