package br.ufes.dwws.util;

public class MenuItem {
	private String title;
	private String icon;
	private String url;
	
	public MenuItem(String title, String icon, String url) {
		super();
		this.title = title;
		this.icon = icon;
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
