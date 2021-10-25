package lv10_Zombie;

public abstract class Unit {
	private String name; // 이름
	private int hp; // 체력
	private int MaxHp; // 최대 체력
	private int att;
	private int def;
	private int floor;
	
	protected Unit() {
	}
	
	public String getName() {
		return name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getMaxHp() {
		return MaxHp;
	}
	public void setMaxHp(int maxHp) {
		MaxHp = maxHp;
	}

	
	protected  Unit(String name,int MaxHp,int att,int def,int floor){
		this.name = name;
		this.hp = MaxHp;
		this.MaxHp = MaxHp;
		this.att = att;
		this.def = def;
		this.floor = floor;
	}
	@Override
	public String toString() {
		String str = "";
		str += "[이름] : " + "[" + this.name + "]" + "\t" + "[체력] : " + "[" + this.hp + " / " + this.MaxHp + "]\n";
		str += "[공격력] : " + "[" + this.att + "]" + "\t" + "[방어력] : " + "[" + this.def + "]";
		return str;
	}
}
