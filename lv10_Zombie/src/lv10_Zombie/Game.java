package lv10_Zombie;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	// Singleton
	private Game() {} // 기본 생성자 제한
	private static Game instance = new Game();
	public static Game getinstance() {
		return instance;
	}
	public static Random rn= new Random();
	public static Scanner sc = new Scanner(System.in);
	private Hero h;
	private ArrayList<Zombie> monster = new ArrayList<>(); 
	private ZombieKing zk;
	private void init() {
		for(int i = 1; i < 5; i++) {
			String name = ranName();
			if(!overlapNameChk(name)) {
				this.monster.add(new Zombie(name, i + ( 4 * i), i + 3, i, i));
			}
			else {
				i--;
			}
		}
		this.zk = new ZombieKing("좀비왕",100,20,4,5);
	}
	
	private String ranName() {
		String temp [] = {"빨간","노란", "검정","파란"};
		String temp2 [] = {"좀쌘", "보통", "정예","약한"};
		String name = "";
		int rN = rn.nextInt(4);
		name += temp[rN] + " ";
		rN = rn.nextInt(4);
		name += temp2[rN] + " ";
		name += " 좀비";
		return name;
	}
	
	private boolean overlapNameChk(String name) {
		boolean chk = false;
		for(Unit unit : this.monster) {
			if(unit.getName().equals(name)) {
				chk = true;
			}
		}
		return chk;
	}
	
	private void startGame() {
		System.out.println("===Welcome to Zombie World.===");
	}
	
	private void inputPName() {
		System.out.print("좀비 사냥꾼의 이름은 ?: ");
		String name = sc.next();
		this.h = new Hero(name, 200, 50, 1, 1);
	}
	
	private boolean ranAppear() {
		boolean appear = false;
		if(rn.nextInt(2) == 0) {
			appear = true;
		}
		return appear;
	}
	
	private void printFloor() {
		System.out.println("=====" + this.h.getFloor() + " 층 =====" );
	}
	
	private void printNothing() {
		System.out.println("[1] 탑을 오른다.");
		System.out.println("[2] 물약을 마신다.");
		System.out.printf("[3] %d 층을 다시 수색한다.\n", this.h.getFloor());
		System.out.println("[0] 탑 등반을 포기한다");
	}

	private boolean chkHp(Unit unit) {
		boolean fullHp = true;
		
		if(unit.getHp() < unit.getMaxHp()) {
			fullHp = false;
		}
		
		return fullHp;
	}
	
	
	private boolean selNothing() {
		boolean chk = true;
		System.out.print("입력 : ");
		String temp = sc.next();
		System.out.println();
		int sel = Integer.parseInt(temp);
		if(sel == 1) {
			this.h.setFloor(this.h.getFloor() + 1);
			System.out.println("탑 한 층을 통과했다.\n");
		}
		else if (sel == 2) {
			if(!chkHp(this.h)) {
				this.h.drink();
			}
			else {
				System.out.println("체력이 충분하다\n");
			}
		}
		else if (sel == 3) {
			System.out.printf("%d 층을 다시 수색했다.\n", this.h.getFloor());
		}
		else if (sel == 0) {
			System.out.println("당신은 탑에서 도망쳤다.");
			System.out.println("[GAME OVER]\n");
			chk = false;
		}
		else {
			printNothing();
			chk = selNothing();
		}
		return chk;
	}
	
	private void printAppear() {
		System.out.println("야생의 좀비가 나타났다!!!!!");
		System.out.println("====== 용사 ======");
		System.out.println(this.h.toString());
		System.out.println("====== VS ======");
		System.out.println("====== 좀비 ======");
		Unit Zombie;
		if(this.h.getFloor() < 5) {
			Zombie = this.monster.get(this.h.getFloor() - 1);
			System.out.println(Zombie.toString());
		}
		else if(this.h.getFloor() == 5){
			Zombie = this.zk;
			System.out.println(Zombie.toString());
		}
		System.out.println();
		
		System.out.println("[1] 좀비와 싸운다!");
		System.out.println("[0] 도망친다");
	}
	
	private boolean selAppear() {
		boolean chk = true;
		System.out.print("입력 : ");
		String temp = sc.next();
		System.out.println();
		int sel = Integer.parseInt(temp);
		if(sel == 1) {
			boolean isRun = true;
			while(isRun) {
				printFightMenu();
				// -1 계속 / 0 승리(좀비 사망) / 1 유저 사망 
				isRun = selFightMenu();
			}
			
			chk = false;
		}
		else if (sel == 0) {
			System.out.println("당신은 도망치다" +
		"\n매복해 있던 좀비에게 죽었다");
			System.out.println("[GAME OVER]");
			chk = false;
		}
		else {
			printAppear();
			chk = selAppear();
		}
		return chk;
	}
	
	private void printFightMenu() {
		System.out.println("[1]. 공격");
		System.out.printf("[2]. 물약 [%d개]\n", this.h.getWater());
	}
	
	private boolean selFightMenu() {
		boolean chk = true;
		System.out.print("입력 : ");
		System.out.println();
		String temp = sc.next();
		int sel = Integer.parseInt(temp);
		
		if(sel == 1) {
			chk = fight();
		}
		else if (sel == 2) {
			if(this.h.getHp() < this.h.getMaxHp()) {
				this.h.drink();
			}
			else {
				System.out.println("체력이 충분하다");
			}
		}
		return chk;
	}
	
	private void printFightState() {
		System.out.println();
		System.out.println("====== 용사 ======");
		System.out.println(this.h.toString());
		System.out.println("====== VS ======");
		System.out.println("====== 좀비 ======");
		Unit Zombie;
		if(this.h.getFloor() != 5) {
			Zombie = this.monster.get(this.h.getFloor() - 1);
		}
		else {
			Zombie = this.zk;
		}
		System.out.println(Zombie.toString());
		System.out.println();
	}
	
	private void winBonusAtt() {
		int rN = rn.nextInt(2);
		if(rN == 0) {
			System.out.println("놀라운 기적!!!");
			System.out.println("좀비에게 승리해 공격력이 1 증가했다.");
			this.h.setAtt(this.h.getAtt() + 1);
		}
	}
	private void winBonusDef() {
		int rN = rn.nextInt(2);
		if(rN == 0) {
		System.out.println("놀라운 기적!!!");
		System.out.println("좀비에게 승리해 방어력이 1 증가했다.");
		this.h.setDef(this.h.getDef() + 1);
		}
	}
	private void winBonusHeal() {
		if(this.h.getHp() < this.h.getMaxHp()) {
			int temp = this.h.getMaxHp() - this.h.getHp();
			if(temp > 5) {
				this.h.setHp(this.h.getHp() + 5);
				System.out.println("좀비에게 승리해 체력이 5 회복됐다.");
			}
			else if(temp <= 5 ) {
				this.h.setHp(this.h.getHp() + temp);
				System.out.println("좀비에게 승리해 체력이 " + temp + " 회복됐다.");
			}
		}
		else {
			System.out.println("체력이 충분하다");
		}
	}
	
	private boolean fight() {
		boolean chk = true;
		if(this.h.getFloor() != 5) {
			Zombie zombie = this.monster.get(this.h.getFloor()-1);
			this.h.attack(zombie);
			printFightState();
			try {
				Thread.sleep(1200);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(zombie.getHp() <= 0) {
				System.out.printf("\n%s의 승리\n",this.h.getName());
				this.h.setFloor(this.h.getFloor() + 1);
				winBonusAtt();
				winBonusDef();
				winBonusHeal();
				chk = false;
			}
			else {
				System.out.println("\n좀비의 반격!!!");
				zombie.attack(this.h);
				printFightState();
				if(this.h.getHp() <= 0) {
					System.out.println("플레이어는 좀비에게 죽었다.");
					System.out.println("[GAME OVER]");
					chk = false;
				}
			}
		}
		else {
			ZombieKing zombie = this.zk;
			this.h.attack(zombie);
			printFightState();
			try {
				Thread.sleep(1200);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(zombie.getHp() <= 0) {
				System.out.printf("\n%s의 승리\n",this.h.getName());
				this.h.setFloor(this.h.getFloor() + 1);
				winBonusAtt();
				winBonusDef();
				winBonusHeal();
				chk = false;
			}
			else {
				System.out.println("\n좀비의 반격!!!");
				zombie.attack(this.h);
				printFightState();
				if(this.h.getHp() <= 0) {
					System.out.println("플레이어는 좀비에게 죽었다.");
					System.out.println("GAME OVER");
					chk = false;
				}
			}
		}
		
		return chk;
	}
	
	private boolean chkAlive () {
		boolean end = true;
		if(this.h.getHp() <= 0) {
			end = false;
		}
		return end;
	}
	
	public void run() {
		startGame();
		inputPName();
		init();
		boolean isRun = true;
		while(isRun) {
			System.out.println();
			printFloor();
			if(this.h.getFloor() < 5) {
				if(ranAppear()) {
					printAppear();
					isRun = selAppear();
				}
				else {
					printNothing();
					isRun = selNothing();
				}
			}
			else if(this.h.getFloor() == 5){
				printAppear();
				isRun = selAppear();
			}
			else if(this.h.getFloor() == 6) {
				System.out.println("용사는 탑을 정복했다");
				System.out.println("YOU WIN!!!");
				break;
			}
			isRun = chkAlive();
		}
	}
}
