package kiosk;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class PanelCoffee extends MyPanelUtil {
	private OrderManager om = OrderManager.getInstance();

	private Vector<Item> items = null; // jbutton
//	x 700 - 400 60 60 60 60 60
//	y 600 - 40 - 400 80 40 40 40

	public PanelCoffee(int x, int y, int w, int h) {
		System.out.println(w); // x
		System.out.println(h); // y
		init();
		setLayout(null);
		setBounds(x, y, w, h);
		setBackground(Color.white);
	}

	private void init() { // 4 * 4
		load();
		if (this.items != null && this.items.size() > 0) {
			setBtn();
		}
	}

	private void load() {
		File file = new File("coffee.txt");
		if (file.exists()) {
			this.items = new Vector<>();
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
					item = br.readLine();
				}
			} catch (Exception e) {
			}
		}
	}

	private void setBtn() {
		int y = 80;
		int x = 60;

		for (int i = 0; i < this.items.size(); i++) {
			Item t = this.items.get(i);
			String url = this.items.get(i).getUrl();
			ImageIcon im = new ImageIcon(url);
			t.setIcon(im);
			t.setBounds(x, y, 100, 100);
			t.setBackground(new Color(249, 243, 223));
			add(t);
			t.addActionListener(this);
			x += 160;
			if (i % 4 == 3) {
				x = 60;
				y += 140;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Item) {
			Item t = (Item) e.getSource();
			for (int i = 0; i < this.items.size(); i++) {
				Item item = this.items.get(i); // 버튼
				if (t == item) {
					
					ItemPopUp itemP = new ItemPopUp(item);
					
					PanelBill.getTable().revalidate();
					PanelBill.getTable().repaint();
				}
			}
		}
	}
}
