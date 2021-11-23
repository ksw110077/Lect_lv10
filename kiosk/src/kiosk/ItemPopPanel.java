package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemPopPanel extends MyPanelUtil {
	private Item item = null;
	private JPanel panel = null;
	private JLabel num = null;
	private JButton plus = null;
	private JButton minus = null;
	private JButton add = null;
	private boolean chk;
	
	
	public ItemPopPanel(Item item) {
		setLayout(null);
		setBounds(0, 0, 350, 500);
		setBackground(Color.white);
		this.item = item;
		setBtn();
		setNum();
	}
	
	private void setBtn() {
		this.plus = new JButton();
		this.minus = new JButton();
		
		int x = 50;
		int y = 375;
		
		this.minus.setBounds(x, y, 50, 50);
		String url = "images\\마이너스.png";
		ImageIcon im = new ImageIcon(url);
		this.minus.setIcon(im);
		this.minus.addActionListener(this);

		
		x += 200;
		url = "images\\플러스.png";
		im = new ImageIcon(url);
		this.plus.setIcon(im);
		this.plus.setBounds(x, y, 50, 50);
		this.plus.addActionListener(this);
		
		x = 350 / 2 - 50;
		y += 60;
		
		this.add = new JButton();
		this.add.setBackground(new Color(221, 221, 221));
		this.add.setText("추가하기");
		this.add.setFont(new Font("", Font.BOLD, 15));
		this.add.setBounds(x,y,100,60);
		
		add(this.minus);
		add(this.plus);
		add(this.add);
		
	}

	private void setNum() {
		this.num = new JLabel();
		this.num.setBounds(150, 375, 50, 50);
		this.num.setText("0");
		this.num.setFont(new Font("", Font.BOLD, 30));
		this.num.setVerticalAlignment(JLabel.CENTER);
		this.num.setHorizontalAlignment(JLabel.CENTER);		
		add(this.num);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if(t == this.minus) {
				int num = Integer.parseInt(this.num.getText());
				if(num > 0) {
					num --;
					this.num.setText("" + num);
				}
			}
			else if(t == this.plus) {
				int num = Integer.parseInt(this.num.getText());
				num ++;
				this.num.setText("" + num);
			}
			else if (t == this.add) {
				System.out.println("애드");
				ItemPopUp.chk = true;
			}
		}
	}
}
