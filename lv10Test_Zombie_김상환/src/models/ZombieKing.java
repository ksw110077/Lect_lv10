package models;

public class ZombieKing extends Unit implements Att, Shielder{

	private int shield;
	
	public ZombieKing(String name, int MaxHp, int att, int def, int floor) {
		super(name, MaxHp, att, def, floor);
		this.setShield(100);
	}

	@Override
	public boolean attack(Unit target) {
		boolean canAtt = true;
		int dam = super.getAtt() - target.getDef();
		System.out.printf("%s의 공격\n", super.getName());
		int targetHp = target.getHp();
		if(dam > 0) {
			targetHp = target.getHp() - dam;
		}
		else {
			dam = 0;
		}
		target.setHp(targetHp);
		System.out.printf("%d의 Demage\n", dam);
		
		if(targetHp == 0) {
			canAtt = false;
		}
		return canAtt;
	}

	@Override
	public boolean attack(Shielder shielder) {
		return false;
	}

	@Override
	public String printAll() {
		String str = "";
		str += "[이름] : " + "[" + super.getName() + "]" + "\t" + "[체력] : " + "[" + super.getHp() + " / " + super.getMaxHp() + "]\n";
		str += "[실드] : ["+ this.shield +"]\n";
		str += "[공격력] : " + "[" + super.getAtt() + "]" + "\t" + "[방어력] : " + "[" + super.getDef() + "]";
		return str;
	}

	public int getShield() {
		return this.shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}
	
}