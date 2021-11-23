package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kiosk.ItemPopUp;

public class ItemPopPanel extends MyPanelUtil {
	private OrderManager om = OrderManager.getInstance();
	private Item item = null;
	private JPanel panel = null;
	private JLabel im = null;
	private JLabel num = null;
	private JButton plus = null;
	private JButton minus = null;
	private JButton add = null;
	private String url;
	private boolean chk;
	
	
	public ItemPopPanel(Item item) {
		setLayout(null);
		setBounds(0, 0, 350, 500);
		setBackground(Color.white);
		this.item = item;
		setUrl();
		setBtn();
		setNum();
	}
	
	private void setUrl(){
		String url = this.item.getUrl();
		String []  temp = url.split(".png");
		this.url = temp[0] + "_sub.png";
		ImageIcon imi= new ImageIcon(this.url);
		this.im = new JLabel(imi);
		 // 250 * 300
		this.im.setBounds(50,35,250,300);
		add(this.im);
	}
	
	private void setBtn() {
		this.plus = new JButton();
		this.minus = new JButton();
		this.add = new JButton();
		
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
		
		this.add.setBounds(x,y,100,60);
		this.add.setBackground(new Color(221, 221, 221));
		this.add.setText("추가하기");
		this.add.setFont(new Font("", Font.BOLD, 15));
		this.add.addActionListener(this);
		
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
				int idx = -1;
				String name = this.item.getName();
				String price = "" + this.item.getPrice();
				int num = Integer.parseInt(this.num.getText());
				int total = num * Integer.parseInt(price);
				
				for(int i = 0; i < this.om.getData().size(); i++) {
					String oName = this.om.getData().get(i).get(0);
					if(oName.equals(name)) {
						idx = i;
					}
				}
				
				if(idx != -1) {
					num = Integer.parseInt(this.num.getText()) + Integer.parseInt(this.om.getData().get(idx).get(2));
					total = num * Integer.parseInt(price);
					this.om.getData().get(idx).set(2, "" + num);
					this.om.getData().get(idx).set(3, "" + total);
				}
				else {
					Vector<String>	 temp = new Vector<>();
					temp.add(name);
					temp.add(price);
					temp.add("" + num);
					temp.add("" + total);
					this.om.getData().add(temp);
				}
				PanelBill.getTable().revalidate();
				PanelBill.getTable().repaint();
				System.out.println("닫기");
			}
		}
	}
}
