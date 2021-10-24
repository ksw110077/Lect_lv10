package lv10_Zombie;

import interfaces.Att;
import interfaces.Drink;
public class Hero extends Unit implements Att, Drink{
	private int water = 3;
	
	public Hero(String name,int MaxHp,int att,int def,int floor) {
		super(name, MaxHp, att, def, floor);
	}
	
	public int getWater() {
		return water;
	}
	@Override
	public void drink() {
		if(this.water > 0) {
			int tempHp = super.getHp() + 100;
			if(tempHp <= super.getMaxHp()) {
				super.setHp(tempHp);
				System.out.println("체력 100 회복");
			}
			else {
				super.setHp(super.getMaxHp());
				int temp = tempHp - super.getMaxHp();
				System.out.println("체력 " + temp + " 회복" );
			}
			System.out.printf("HP : [%d] / [%d]\n[남은 물약 / %d 개]\n"
					, super.getName(),super.getHp(), super.getMaxHp(), this.water);
			System.out.println("회복 완료");
		}
		else {
			System.out.println("물약이 없습니다.");
		}
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
			System.out.println(target.getName() + "를 사냥했다.");
			cantKill = false;
		}
		return cantKill;
	}
	
}
