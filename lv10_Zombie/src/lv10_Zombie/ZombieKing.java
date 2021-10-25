package lv10_Zombie;

import interfaces.Att;
import interfaces.Shielder;

public class ZombieKing extends Unit implements Shielder, Att{
	private int Shield;
	public ZombieKing(String name,int MaxHp,int att,int def,int floor) {
		super(name, MaxHp, att, def,floor);
		this.setShield(100);
	}
	
	public int getShield() {
		return Shield;
	}

	public void setShield(int shield) {
		Shield = shield;
	}

	@Override
	public boolean attack(Shielder shielder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean attack(Unit target) {
		boolean cantKill = true;
		int dam = super.getAtt() - target.getDef();
		System.out.printf("%s의 공격!! : ", super.getName());
		int targetHp = target.getHp();
		if(dam > 0) {
			targetHp = target.getHp() - dam;
		}
		else {
			dam = 0;
		}
		target.setHp(targetHp);
		System.out.printf("%d의 데미지 \n", dam);
		
		if(targetHp == 0) {
			cantKill = false;
		}
		return cantKill;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "[이름] : " + "[" + super.getName() + "]" + "\t" + "[체력] : " + "[" + super.getHp() + " / " + super.getMaxHp() + "]\n";
		str += "[실드] : ["+ this.Shield +"]\n";
		str += "[공격력] : " + "[" + super.getAtt() + "]" + "\t" + "[방어력] : " + "[" + super.getDef() + "]";
		return str;
	}

}
