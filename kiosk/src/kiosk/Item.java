package kiosk;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Item extends JButton {
	private String url;
	private String name;
	private int price;
	
	public Item(String url, String name, int price) {
		this.url = url;
		this.name = name;
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
