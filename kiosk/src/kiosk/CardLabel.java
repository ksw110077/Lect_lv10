package kiosk;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardLabel extends JLabel implements Runnable {

	public CardLabel() {
		ImageIcon im = new ImageIcon("images/cashinfo.png");
		this.setIcon(im);
		this.setBounds(0,0,407,287);
		add(this);
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
		}
	}

}
