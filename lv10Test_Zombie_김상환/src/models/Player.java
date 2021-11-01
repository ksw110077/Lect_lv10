package models;

public class Player extends Unit implements Att, Drink, Shielder{
	private int waterCnt = 3;
	
	public Player(String n, int mH, int a, int d, int f) {
		super(n,mH,a,d,f);
	}
	
	public int getWaterCnt() {
		return this.waterCnt;
	}
	
	
	@Override
	public void drink() {
		if(this.waterCnt > 0) {
			if(super.getHp() == super.getMaxHp()) {
				System.out.println("회복할 체력이 없습니다.");
			}
			else {
				int tempHp = super.getHp() + 100;
				if(tempHp <= super.getMaxHp()) {
					super.setHp(tempHp);
					System.out.println("체력 100 회복 완료");
				}
				else {
					int temp = super.getMaxHp() - super.getHp();
					super.setHp(super.getMaxHp());
					System.out.println("체력 " + temp + " 회복 완료" );
				}
				System.out.printf("HP : [%d] / [%d]\n[남은 물약][%d 개]\n"
						,super.getHp(), super.getMaxHp(), this.waterCnt);
				this.waterCnt --;
			}
		}
		else {
			System.out.println("물약 없음");
		}
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
		ZombieKing target = (ZombieKing) shielder;
		boolean canAtt = true;
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
				canAtt = false;
			}
		}
		
		return canAtt;
	}

	@Override
	public String printAll() {
		String str = "";
		str += "[이름] : " + "[" + super.getName() + "]" + "\t" + "[체력] : " + "[" + super.getHp() + " / " + super.getMaxHp() + "]\n";
		str += "[공격력] : " + "[" + super.getAtt() + "]" + "\t" + "[방어력] : " + "[" + super.getDef() + "]";
		str += "[물약] : [" + this.waterCnt + "개]";
		return str;
	}
	
}