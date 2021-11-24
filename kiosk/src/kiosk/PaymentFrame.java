package kiosk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PaymentFrame extends JFrame implements ActionListener, WindowListener {
	private PaymentPanel panel = null;
	private OrderManager om = OrderManager.getInstance();
	
	private CardInfo card  = null;
	private CashInfo cash  = null;
	
	public static boolean close;
	public PaymentFrame() {
		setLayout(null);
		setBounds(MainFrame.W / 2 - 200, MainFrame.H / 2 - 250, 430 + 14, 500 + 37);
		
		this.panel = new PaymentPanel();
		this.panel.cash.addActionListener(this);
		this.panel.card.addActionListener(this);
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if(t == this.panel.cash) {
				this.om.setPayment(1);
				this.cash = new CashInfo();
				dispose();
			}
			else if (t == this.panel.card) {
				this.om.setPayment(2);
				this.card = new CardInfo();
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
