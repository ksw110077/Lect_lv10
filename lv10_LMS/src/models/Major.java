package models;

import java.util.ArrayList;

public class Major {
	private String name;
	private int majorCode;
	ArrayList<Sub> sub = new ArrayList<>();
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMajorCode() {
		return this.majorCode;
	}
	public void setMajorCode(int majorCode) {
		this.majorCode = majorCode;
	}
}
