package kiosk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class ItemPopUp extends JFrame implements ActionListener,WindowListener{
	private OrderManager om = OrderManager.getInstance();
	private ItemPopPanel panel = null;
	public static boolean chk;
	
	public ItemPopUp(ItemPopPanel panel) {
		// 250 * 300
		this.panel = panel;
		setLayout(null);
		setBounds(MainFrame.W / 2 - 175, MainFrame.H / 2 - 250, 350 + 14, 500 + 37);
		
		this.panel.add.addActionListener(this);
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.panel.add) {
			this.panel.runAdd();

			this.om.updateTotal();
			PanelBill.getTotal().setText(this.om.getTotal() + " 원");
			PanelTea.chk = false;
			PanelCoffee.chk = false;
			this.dispose();
		}
	}

	
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		PanelTea.chk = false;
		PanelCoffee.chk = false;
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
