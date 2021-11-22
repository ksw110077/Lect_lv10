package kiosk;

import java.util.Vector;

public class OrderManager {
	private static OrderManager instance = new OrderManager();
	private OrderManager() {};
	public static OrderManager getInstance() {
		return instance;
	}
	
	// 현재 주문
	private Vector<Vector<String>> data = new Vector<>();
	public Vector<Vector<String>> getData() {
		return data;
	}
	
	
	
}