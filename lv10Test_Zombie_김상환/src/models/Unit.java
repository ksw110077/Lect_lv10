package models;

public abstract class Unit {
	private String name;
	private int hp;
	private int MaxHp;
	private int att;
	private int def;
	private int floor;
	
	private Unit() {
	}
	
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtt() {
		return this.att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getDef() {
		return this.def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getFloor() {
		return this.floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getMaxHp() {
		return this.MaxHp;
	}
	public void setMaxHp(int maxHp) {
		this.MaxHp = maxHp;
	}
	
	public Unit(String name,int MaxHp,int att,int def,int floor){
		this.name = name;
		this.hp = MaxHp;
		this.MaxHp = MaxHp;
		this.att = att;
		this.def = def;
		this.floor = floor;
	}
	
	public abstract String printAll();
}
