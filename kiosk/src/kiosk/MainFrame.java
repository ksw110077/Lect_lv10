package kiosk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame implements ActionListener{
	
	// 화면 
	
	// P1 or P2 메뉴(default는 P1)
	
	// 항상 고정
	// P0. 테이블 <- 업데이트 처리 고민
	// 현재 수량 + 총액
	// P0.주문하기 / 처음으로 버튼
	
	// 메뉴 선택시 수량 선택 팝업
	// "+" "cnt(라벨)? 테이블? 수정가능이니까 테이블?" "-" 버튼  
	// 선택완료 버튼
	
	// 커피, 차 파일 처리
	// 주소, 이름, 가격
	
	
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public final int W = this.dm.width;
	public final int H = this.dm.height;
	
	private PanelBill p0;
	private PanelCoffee p1;
	private PanelTea p2;
	
	private JButton coffee;
	private JButton tea;
	
	public MainFrame() {
		setTitle("ONE CAFE");
		setLayout(null);
		setBounds(this.W / 2 - 350, this.H / 2 - 500,700 + 14,1000 + 37);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBtn();
		setP0();
		setP1();
		setP2();
		
		setVisible(true);
		revalidate();
	}
	
	
	private void setBtn() {
//		LineBorder b = new LineBorder(new Color(249, 213, 167),1);
		
		this.coffee = new JButton();
		this.coffee.setText("Coffee");
		this.coffee.setBounds(350 - 82,5,80,30);
//		this.coffee.setBorder(b);
		
		this.coffee.setFont(new Font("", Font.BOLD, 13));
		this.coffee.setBackground(new Color(249, 213, 167));
//		this.coffee.setBackground(Color.white);
		this.coffee.addActionListener(this);
		
		this.tea = new JButton();
		this.tea.setText("Tea");
		this.tea.setBounds(350 + 2,5,80,30);
//		this.tea.setBorder(b);
		this.tea.setFont(new Font("", Font.BOLD, 15));
		this.tea.setBackground(new Color(249, 213, 167));
//		this.tea.setBackground(Color.white);
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
}
