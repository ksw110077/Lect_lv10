package lv10_Zombie;

import interfaces.Att;

public class ZombieKing extends Unit implements Att{
	public ZombieKing(String name,int MaxHp,int att,int def,int floor) {
		super(name, MaxHp, att, def,floor);
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
}
