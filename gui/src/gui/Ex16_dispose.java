package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ImagePanel extends JPanel{
	
	ImageIcon icon = new ImageIcon("images/루피.png");
	JLabel image = new JLabel();
	
	int x = 0;
	
	public ImagePanel() {
		setLayout(null);
		setBounds(0,0,200,200);
		
//		image.setBounds(0,0,200,200);
//		add(image);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(icon.getImage(), x, 0, null);
		
		try {
			Thread.sleep(100);
			x --;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(x == -10) {
			DispoPanel.pop.dispose();
		}
		repaint();
	}
}

class DispoFrame2 extends JFrame {
	
	
	public DispoFrame2() {
		setLayout(null);
		setBounds(200,200,200,200);
		
		add(new ImagePanel());
		
		setVisible(true);
		revalidate();

//		count();
	}

	private void count() {
		
		while(true) {
			try {
				Thread.sleep(1000);
				this.dispose();
			} catch (Exception e) {
			}
			break;
		}
		
	}
	
	
}

class DispoPanel extends JPanel implements ActionListener{
	
	public static DispoFrame2 pop = null;
	
	JButton bt = new JButton();
	
	public DispoPanel() {
		setLayout(null);
		setBounds(0,0,400,400);
		setBackground(Color.orange);
		
		setButton();
		
	}

	private void setButton() {
		bt.setText("click");
		bt.setBounds(100,100,100,100);
		bt.addActionListener(this);
		add(bt);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bt) {
			pop = new DispoFrame2();
		}
		
	}
}

class DispoFrame extends JFrame{
	
	DispoPanel panel = new DispoPanel();
	
	public DispoFrame() {
		setLayout(null);
		setBounds(100,100,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(panel);
		
		setVisible(true);
		revalidate();
	}
}

public class Ex16_dispose {
	public static void main(String[] args) {
		
		new DispoFrame();
		
	}

}