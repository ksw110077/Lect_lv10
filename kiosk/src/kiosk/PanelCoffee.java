package kiosk;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.ImageIcon;

public class PanelCoffee extends MyPanelUtil {
	private OrderManager om = OrderManager.getInstance();

	private Vector<Item> items = null; // jbutton
	// 700 * 640
	// 버튼에서 40
	// 660 - 400 - 55*2 - 50 * 3
	// 240 - 75 * 2 - 30 * 3

	public PanelCoffee(int x, int y, int w, int h) {
		System.out.println(w);
		System.out.println(h);
		init();
		setLayout(null);
		setBounds(x, y, w, h);
		setBtn();
	}

	private void init() { // 4 * 4
		load();
		if(this.items != null && this.items.size() > 0) {
			setBtn();
		}
	}

	private void load() {
		File file = new File("coffee.txt");
		if (file.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			try {
				// 경로/ name/ price
				fr = new FileReader(file);
				br = new BufferedReader(fr);

				String item = br.readLine();

				while (item != null) {
					String[] t = item.split("/");
					String url = t[0];
					String name = t[1];
					int price = Integer.parseInt(t[2]);
					Item ti = new Item(url, name, price);
					this.items.add(ti);
					br.readLine();
				}
			} catch (Exception e) {
			}
		}
	}

	private void setBtn() {
		int y = 95;
		int x = 75;
		
		for(int i = 0 ; i < this.items.size(); i++) {
			String url = this.items.get(i).getUrl();
			ImageIcon im = new ImageIcon(url);
			this.items.get(i).setIcon(im);
		}
	}

}
