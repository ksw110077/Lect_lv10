package kiosk;

import java.util.Vector;

public class OrderManager {
	private static OrderManager instance = new OrderManager();
	private OrderManager() {};
	public static OrderManager getInstance() {
		return instance;
	}
	
	// name , price, number, total
	private Vector<Vector<String>> data = new Vector<>(); // 테이블 데이터
	private Vector<Vector<String>> printData = new Vector<>();
	private int total = 0;
	private int takeOut = -1; // 1 in 2 to, ;
	private int payment = -1; // 1 cash 2 card;
	
	public Vector<Vector<String>> getData() {
		return data;
	}
	
	
	public void updateTotal() {
		this.total = 0;
		for(int i = 0; i < this.data.size(); i++) {
			this.total += Integer.parseInt(this.data.get(i).get(3));
		}
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void removeData() {
		this.data = new Vector<>();
		this.total = 0;
		this.takeOut = -1; // 1 in 2 to, ;
		this.payment = -1;
	}
	
	public int getTakeOut() {
		return takeOut;
	}
	public void setTakeOut(int takeOut) {
		this.takeOut = takeOut;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
}