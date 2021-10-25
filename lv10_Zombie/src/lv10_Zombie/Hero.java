package lv10_Zombie;

import interfaces.Att;
import interfaces.Drink;
import interfaces.Shielder;
public class Hero extends Unit implements Att, Drink, Shielder{
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
					,super.getHp(), super.getMaxHp(), this.water);
			System.out.println("회복 완료");
			this.water --;
		}
		else {
			System.out.println("물약이 없습니다.");
		}
	}

	@Override
	public boolean attack(Shielder shielder) {
		ZombieKing target = (ZombieKing) shielder;
		boolean cantKill = true;
		int shield = target.getShield();
		int targetHp = target.getHp();
		int dam;
		dam = super.getAtt() - target.getDef();
		System.out.printf("%s의 공격!! : ", super.getName());
		
		if(shield > 0) {
			shield -= dam;
			if(shield == 0) {
				target.setShield(0);
				System.out.printf("%d의 데미지 \n", dam);
				System.out.println("좀비킹의 실드가 부서졌다!!");
			}
			else if (shield < 0) {
				int tempDam = shield * -1;
				target.setShield(0);
				target.setHp(targetHp - tempDam);
				System.out.printf("%d의 데미지 \n", dam);
				System.out.println("좀비킹의 실드가 부서졌다!!");
			}
			else {
				target.setShield(shield);
				System.out.printf("%d의 데미지 \n", dam);
				System.out.println("실드를 부수기엔 힘이 부족했다!");
				System.out.printf("[Shield][%d]\n", target.getShield());
			}
		}
		else {
			target.setHp(targetHp - dam);
			System.out.printf("%d의 데미지 \n", dam);
			
			if(targetHp == 0) {
				System.out.println(target.getName() + "를 사냥했다.");
				cantKill = false;
			}
		}
		
		return cantKill;
	}

	@Override
	public boolean attack(Unit target) {
		boolean cantKill = true;
		int dam;
		
		dam = super.getAtt() - target.getDef();
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

	@Override
	String printAll() {
		String str = "";
		str += "[이름] : " + "[" + super.getName() + "]" + "\t" + "[체력] : " + "[" + super.getHp() + " / " + super.getMaxHp() + "]\n";
		str += "[공격력] : " + "[" + super.getAtt() + "]" + "\t" + "[방어력] : " + "[" + super.getDef() + "]";
		return str;
	}
}
