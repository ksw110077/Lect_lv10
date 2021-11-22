package kiosk;

import java.awt.Color;

public class PanelTea extends MyPanelUtil {
	private OrderManager om = OrderManager.getInstance();
	public PanelTea(int x, int y, int w, int h) {
		setLayout(null);
		setBounds(x, y, w, h);
		setBackground(Color.lightGray);
	}
}
