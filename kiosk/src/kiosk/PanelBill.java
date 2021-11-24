package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelBill extends MyPanelUtil {
	private OrderManager om = OrderManager.getInstance();
	private Vector<String> colName = null;
	
	private OrderFrame of = null;
	
	private JLabel totalText;

	private static JTable table;
	private static JLabel total;
	
	
	private JScrollPane js;
	private JButton main;
	private JButton order;
	
	public static boolean re = false;
	
	public PanelBill(int x, int y, int w, int h) {
		setLayout(null);
		setBounds(x, y, w, h);
		setBackground(Color.white);
		setLabel();
		setbtn();
		setTable();
	}

	private void setLabel() {
		this.totalText = new JLabel();
		this.totalText.setText("총 금액 :");
		this.totalText.setHorizontalAlignment(JLabel.RIGHT);
		this.totalText.setBounds(430, 250,50,30);
		add(this.totalText);
		total = new JLabel();
		total.setText("0 원");
		total.setHorizontalAlignment(JLabel.RIGHT);
		total.setBounds(550, 250,100,30);
		add(total);
	}

	private void setbtn() {
		this.order = new JButton();
		this.order.setText("주문하기");
		this.main = new JButton();
		this.main.setText("주문초기화");
		
		this.order.setBounds(this.getWidth() / 2 - 205,this.getHeight() - 50,200,30);
		this.main.setBounds(this.getWidth() / 2 + 5,this.getHeight() - 50,200,30);
		
		this.order.addActionListener(this);
		this.main.addActionListener(this);
		
		add(this.order);
		add(this.main);
	}

	private void setTable() {
		this.colName = new Vector<>();
		this.colName.add("Name");
		this.colName.add("Price");
		this.colName.add("Number");
		this.colName.add("Total");
		
		// 생성
		table = new JTable(this.om.getData(), this.colName);
		table.setBounds(50,10,600,230);
		this.js = new JScrollPane(table);
		this.js.setBounds(50,10,600,230);
		this.js.getViewport().setBackground(Color.white);
		add(this.js);
	}
	
	public static JTable getTable() {
		return table;
	}
	public static JLabel getTotal() {
		return total;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if (t == this.order) {
				if(this.om.getData().size() > 0) {
					System.out.println("주문하기");
					this.of = new OrderFrame();
				}
			}
			else if(t == this.main) {
				System.out.println("주문 초기화");
				this.om.removeData();
				
				// 테이블 갱신은 아예 모델로 덮어씌워야 한다
				// 다른거 갱신 안됨
				reset();
				
			}
		}
	}

	private void reset() {
		DefaultTableModel model= new DefaultTableModel(this.om.getData(), this.colName);
		table.setModel(model);
		this.om.updateTotal();
		PanelBill.getTotal().setText(this.om.getTotal() + " 원");
	}
	
}
