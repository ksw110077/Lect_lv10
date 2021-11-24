package kiosk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OrderFrame extends JFrame implements ActionListener, WindowListener {
	private OrderPanel panel = null;
	private OrderManager om = OrderManager.getInstance();
	public OrderFrame() {
		setLayout(null);
		setBounds(MainFrame.W / 2 - 200, MainFrame.H / 2 - 250, 430 + 14, 500 + 37);
		
		this.panel = new OrderPanel();
		this.panel.inDrink.addActionListener(this);
		this.panel.takeOut.addActionListener(this);
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if(t == this.panel.inDrink) {
				this.om.setTakeOut(1);
				new PaymentFrame();
				dispose();
			}
			else if (t == this.panel.takeOut) {
				this.om.setTakeOut(2);
				new PaymentFrame();
				dispose();
			}
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}
