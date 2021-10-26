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
		this.name = n;		this.id = i;		this.pw = p;
		this.majorCode = mc;		this.subCode = sc; this.userCode = uc;
	}
	
	abstract String printAll(); // 출력
}
