package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemPopUp extends JFrame implements WindowListener{
	private OrderManager om = OrderManager.getInstance();
	private Item item = null;
	private ItemPopPanel panel = null;
	public ItemPopUp(Item item) {
		// 250 * 300
		this.item = item;
		setLayout(null);
		setBounds(MainFrame.W / 2 - 175, MainFrame.H / 2 - 250, 350 + 14, 500 + 37);
		this.panel = new ItemPopPanel(item);
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
