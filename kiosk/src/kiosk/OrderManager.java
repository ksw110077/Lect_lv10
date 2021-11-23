package kiosk;

import java.util.Vector;

public class OrderManager {
	private static OrderManager instance = new OrderManager();
	private OrderManager() {};
	public static OrderManager getInstance() {
		return instance;
	}
	
	// 현재 주문

	// name , price, number, total
	private Vector<Vector<String>> data = new Vector<>(); // 테이블 데이터
	// 같은 주문 수량만 늘리는 가공 필요
	public Vector<Vector<String>> getData() {
		return data;
	}
	
	public void removeData() {
		this.data = new Vector<>();
	}
}