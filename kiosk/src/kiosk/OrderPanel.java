package kiosk;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OrderPanel extends MyPanelUtil{
	private JLabel hello;
	public JButton takeOut;
	public JButton inDrink;
	
	public OrderPanel() {
		setLayout(null);
		setBounds(0, 0, 430, 500);
		setBackground(Color.white);
		setLabel();
		setBtns();
	}
	
	private void setLabel() {
		ImageIcon im = new ImageIcon("images/hello.png");
		this.hello = new JLabel(im);
		this.hello.setBounds(215 - 190,10, 380,150);
		add(this.hello);
	}

	private void setBtns() {
		this.takeOut = new JButton();
		this.inDrink = new JButton();
		
		int x = 10;
		int y = 200;
		
		this.takeOut.setBounds(x,y,200,200);
		ImageIcon im = new ImageIcon("images/takeout.png");
		this.takeOut.setIcon(im);
		x += 210;
		im = new ImageIcon("images/inDrink.png");
		this.inDrink.setBounds(x,y,200,200);
		this.inDrink.setIcon(im);
		
		add(this.takeOut);
		add(this.inDrink);
	}
}
