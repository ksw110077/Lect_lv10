package kiosk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PaymentPanel extends MyPanelUtil{
	private JLabel payment;
	public JButton cash;
	public JButton card;
	
	
	
	public PaymentPanel() {
		setLayout(null);
		setBounds(0, 0, 430, 500);
		setBackground(Color.white);
		setLabel();
		setBtns();
	}
	
	private void setLabel() {
		ImageIcon im = new ImageIcon("images/payments.png");
		this.payment = new JLabel(im);
		this.payment.setBounds(215 - 190,10, 380,150);
		add(this.payment);
	}
	
	private void setBtns() {
		this.cash = new JButton();
		this.card = new JButton();
		
		int x = 10;
		int y = 200;
		
		this.cash.setBounds(x,y,200,200);
		this.cash.setBackground(Color.white);
		ImageIcon im = new ImageIcon("images/coins.png");
		this.cash.setIcon(im);
		
		
		x += 210;
		this.card.setBackground(Color.white);
		this.card.setBounds(x,y,200,200);
		im = new ImageIcon("images/card.png");
		this.card.setIcon(im);
		
		add(this.cash);
		add(this.card);
	}
}
