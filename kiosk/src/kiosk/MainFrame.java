package kiosk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame implements ActionListener, Runnable{
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;
	
	private PanelBill p0;
	private PanelCoffee p1;
	private PanelTea p2;
	
	
	private long time = System.currentTimeMillis();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 kk시 mm분 ss초");
	
	private JLabel timer;
	private JButton coffee;
	private JButton tea;
	
	public MainFrame() {
		setTitle("ONE CAFE");
		setLayout(null);
		setBounds(W / 2 - 350, H / 2 - 500,700 + 14,1000 + 37);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setLabel();
		setBtn();
		setP0();
		setP1();
		setP2();
		
		setVisible(true);
		revalidate();
	}
	private void setLabel() {
		this.timer = new JLabel(this.sdf.format(this.time));
		this.timer.setBounds(390,25,300,50);
		this.timer.setFont(new Font("", Font.BOLD, 15));
		add(this.timer);
	}
	private void setBtn() {
		
		this.coffee = new JButton();
		this.coffee.setText("Coffee");
		this.coffee.setBounds(350 - 82,5,80,30);
		this.coffee.setFont(new Font("", Font.BOLD, 13));
		this.coffee.setBackground(new Color(249, 213, 167));
		this.coffee.addActionListener(this);
		
		this.tea = new JButton();
		this.tea.setText("Tea");
		this.tea.setBounds(350 + 2,5,80,30);
		this.tea.setFont(new Font("", Font.BOLD, 15));
		this.tea.setBackground(new Color(249, 213, 167));
		this.tea.addActionListener(this);
		
		add(this.coffee);
		add(this.tea);
	}


	public void setP0() {
		this.p0 = new PanelBill(0, H / 9 * 5, 700, 1000 - H / 9 * 5);
		add(this.p0);
	}
	public void setP1() {
		this.p1 = new PanelCoffee(0, 0, 700, H / 9 * 5);
		add(this.p1);
		this.p1.setVisible(true);
	}
	public void setP2() {
		this.p2 = new PanelTea(0, 0, 700, H / 9 * 5);
		add(this.p2);
		this.p2.setVisible(false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if(t == this.coffee) {
				this.p1.setVisible(true);
				this.p2.setVisible(false);
			}
			else if (t == this.tea) {
				this.p2.setVisible(true);
				this.p1.setVisible(false);
			}
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				this.time = System.currentTimeMillis();
				this.timer.setText(this.sdf.format(this.time));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
