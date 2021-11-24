package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PrintBill extends JFrame{
	private OrderManager om = OrderManager.getInstance();
	private Vector<String> colName = null;
	private JTable table;
	private JLabel title;
	private JLabel takeout;
	private JLabel payment;
	private JLabel total;
	private JScrollPane js;
	
	public PrintBill() {
		setLayout(null);
		setBounds(MainFrame.W / 2 - 300, MainFrame.H / 2 - 400, 600 + 14, 800 + 37);
		
		getContentPane().setBackground(Color.white);
		
		setTitle();
		setTable();
		setLabels();
		this.om.removeData();
		panelBillReset();
		
		setVisible(true);
		revalidate();
	}
	
	private void panelBillReset() {
		DefaultTableModel model= new DefaultTableModel(this.om.getData(), this.colName);
		PanelBill.getTable().setModel(model);
		this.om.updateTotal();
		PanelBill.getTotal().setText(this.om.getTotal() + " 원");
		PanelBill.getTable().revalidate();
		PanelBill.getTable().repaint();
	}
	
	public void setTitle() {
		this.title = new JLabel();
		this.title.setBounds(0,10,600,80);
		this.title.setText("영수증");
		this.title.setFont(new Font("", Font.BOLD, 20));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		add(this.title);
	}
	public void setLabels() {
	
		this.total = new JLabel();
		this.total.setBounds(0,700,600,50);
		this.total.setText(String.format("총액 : %d원", this.om.getTotal()));
		this.total.setFont(new Font("", Font.BOLD, 20));
		this.total.setHorizontalAlignment(JLabel.CENTER);
		
		setTake();
		setPay();
		
		add(this.total);
	}
	
	private void setPay() {
		this.payment = new JLabel();
		this.payment.setBounds(0,650,600,50);
		System.out.println(this.om.getTakeOut());
		String str = "";
		if(this.om.getPayment() == 1) {
			str = "Payment : Cash";
		}
		else if (this.om.getPayment() == 2) {
			str = "Payment : Card";
		}
		this.payment.setText(str);
		this.payment.setFont(new Font("", Font.PLAIN, 15));
		this.payment.setHorizontalAlignment(JLabel.CENTER);
		add(this.payment);
		
	}

	private void setTake() {
		this.takeout = new JLabel();
		this.takeout.setBounds(0,600,600,50);
		// 1 in 2 to, ;
		// 1 cash 2 card;
		
		System.out.println(this.om.getTakeOut());
		String str = "";
		if(this.om.getTakeOut() == 1) {
			str = "TakeOut : 매       장";
		}
		else if (this.om.getTakeOut() == 2) {
			str = "TakeOut : TakeOut";
		}
		this.takeout.setText(str);
		this.takeout.setFont(new Font("", Font.PLAIN, 15));
		this.takeout.setHorizontalAlignment(JLabel.CENTER);
		add(this.takeout);
	}

	private void setTable() {
		this.colName = new Vector<>();
		this.colName.add("Name");
		this.colName.add("Price");
		this.colName.add("Number");
		this.colName.add("Total");
		
		// 생성
		this.table = new JTable(this.om.getData(), this.colName);
		this.table.setBounds(50,100,500,500);
		this.js = new JScrollPane(table);
		this.js.setBounds(50,100,500,500);
		this.js.getViewport().setBackground(Color.white);
		add(this.js);
	}
	
}
