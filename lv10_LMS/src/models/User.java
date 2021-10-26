package models;

import java.util.ArrayList;

public abstract class User {
	private String name;
	private String id;
	private String pw;
	private int majorCode;
	private int subCode;
	private int userCode;
	private ArrayList<Sub> sub = new ArrayList<Sub>();
	
	public User(String n, String i, String p, int mc, int sc, int uc) {
		this.setName(n);		this.setId(i);		this.setPw(p);
		this.setMajorCode(mc);		this.setSubCode(sc); this.setUserCode(uc);
	}
	
	abstract String printAll(); // 출력
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getMajorCode() {
		return this.majorCode;
	}

	public void setMajorCode(int majorCode) {
		this.majorCode = majorCode;
	}

	public int getSubCode() {
		return this.subCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

	public int getUserCode() {
		return this.userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
}
