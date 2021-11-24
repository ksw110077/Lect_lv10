package kiosk;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.ImageIcon;

public class PanelTea extends MyPanelUtil {
	private OrderManager om = OrderManager.getInstance();
	
	private ItemPopUp itemP = null;

	private Vector<Item> items = null;
	
	public static boolean chk;

	public PanelTea(int x, int y, int w, int h) {
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
		File file = new File("tea.txt");
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
			t.setBorderPainted(false);
			t.setBackground(Color.white);
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
				if (t == item && chk == false) {
					chk = true;
					this.itemP = new ItemPopUp(new ItemPopPanel(item));
					PanelBill.getTable().revalidate();
					PanelBill.getTable().repaint();
				}
			}
		}
	}
}
