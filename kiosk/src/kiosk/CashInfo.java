package kiosk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class CashInfo extends JFrame implements WindowListener, Runnable{
	private JLabel image;
	public boolean chk;
	public CashInfo() {
		setLayout(null);
		setBounds(MainFrame.W / 2 - 204, MainFrame.H / 2 - 144, 407 + 14, 287 + 37);
		//407 287
		
		setLabel();
		setVisible(true);
		revalidate();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				dispose();
				
				new PrintBill();
			}
		}
		);
	}
	
	private void setLabel() {
		ImageIcon im = new ImageIcon("images/cashinfo.png");
		this.image = new JLabel(im);
		this.image.setBackground(Color.black);
		this.image.setBounds(0,0,407,287);
		getContentPane().add(this.image);
	}
	
	public void closer() {
		dispose();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void run() { 
	}

}
