package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.ZombieKing;
import models.Zombie;
import models.Player;
import models.Shielder;
import models.Unit;

public class Game {
	private Game() {
	}

	private static Game instance = new Game();

	public static Game getinstance() {
		return instance;
	}

	public static Random rn = new Random();
	public static Scanner sc = new Scanner(System.in);
	private Player p;
	private ArrayList<Zombie> zombie = new ArrayList<>();
	private ZombieKing zk;
	
	private void startGame() {
		System.out.println("===Welcome to Zombie Tower.===");
	}
	
	// 이름, MH, 공, 방, 층
	
	private void inputPName() {
		System.out.print("플레이어 이름 : ");
		String name = sc.next();
		this.p = new Player(name, 1, 1, 1, 1);
	}
	
	private void init() {
		String name = "층 좀비";
		for (int i = 1; i < 5; i++) {
			this.zombie.add(new Zombie( i + name, i + (4 * i), i + 3, i, i));
		}
		this.zk = new ZombieKing("좀비왕", 100, 20, 4, 5);
	}
	
	private void printFloor() {
		System.out.println("=====" + this.p.getFloor() + " 층 =====");
	}
	
	private boolean rAppear() {
		boolean appear = false;
		if (rn.nextInt(2) == 0) {
			appear = true;
		}
		return appear;
	}
	
	private int StringToInt() {
		System.out.print("입력 : ");
		String temp = sc.next();
		System.out.println();
		int sel = Integer.parseInt(temp);

		return sel;
	}
	
	private void printNothing() {
		System.out.println("[1] 탑을 오른다.");
		System.out.println("[2] 물약을 마신다.");
		System.out.printf("[3] %d 층을 다시 수색한다.\n", this.p.getFloor());
		System.out.println("[0] 탑을 포기한다");
	}
	
	private boolean selNothing() {
		boolean chk = true;
		int sel = StringToInt();
		if (sel == 1) {
			this.p.setFloor(this.p.getFloor() + 1);
			System.out.println("한 층 올라갔다.\n");
		}
		else if (sel == 2) {
			this.p.drink();
		} else if (sel == 3) {
			System.out.printf("%d 층을 다시 수색했다.\n", this.p.getFloor());
		} else if (sel == 0) {
			System.out.println("당신은 탑에서 도망쳤다.");
			System.out.println("[GAME OVER]\n");
			return false;
		} else {
			printFloor();
			printNothing();
			chk = selNothing();
		}
		return chk;
	}
	
	private void printAppear() {
		System.out.println("야생의 좀비가 나타났다!!!!!");
		System.out.println("====== 플레이어 ======");
		System.out.println(this.p.printAll());
		System.out.println("====== VS ======");
		System.out.println("====== 좀비 ======");
		Unit Zombie;
		if (this.p.getFloor() < 5) {
			Zombie = this.zombie.get(this.p.getFloor() - 1);
			System.out.println(Zombie.printAll());
		} else if (this.p.getFloor() == 5) {
			Zombie = this.zk;
			System.out.println(Zombie.printAll());
		}
		System.out.println();

		System.out.println("[1] 좀비와 싸운다");
		System.out.println("[0] 도망친다");
	}
	private boolean selAppear() {
		boolean chk = true;
		int sel = StringToInt();
		if(sel == 1) {
			boolean isRun = true;
			while(isRun) {
				printFightMenu();
				int chkNum = selFightMenu();
				if(chkNum != -1) {
					if(chkNum == 1) { // 플레이어 사망일 경우 시스템 종료
						chk = false;
					}
					break;
				}
			}
		}
		else if (sel == 0) {
			System.out.println("GAME OVER");
			return false;
		}
		else {
			printFloor();
			printAppear();
			chk = selAppear();
		}
		return chk;
	}
	
	private void printFightMenu() {
		System.out.println("[1.공격]");
		System.out.printf("[2.물약 사용][%d개]\n", this.p.getWaterCnt());
	}
	
	private int selFightMenu() {
		int chk = -1;
		int sel = StringToInt();
		if(sel == 1) {
			chk = fight();
		}
		else if (sel == 2) {
			this.p.drink();
		}
		else {
			printFightMenu();
			chk = selFightMenu();
		}
		return chk;
	}
	
	private void printFightContent() {
		System.out.println();
		System.out.println("======= 플레이어 =======");
		System.out.println(this.p.printAll());
		System.out.println("　　　　　　 VS　　　　　　　");
		System.out.println("======= 좀　　비 =======");
		Unit zombie;
		if(this.p.getFloor() < 5) {
			zombie = this.zombie.get(this.p.getFloor() - 1);
		}
		else {
			zombie = this.zk;
		}
		System.out.println(zombie.printAll());
		System.out.println();
	}
	
	
	private int fight() {
		int chk = -1; // 0 좀비 사망, 1 플레이어 사망, -1 둘 다 생존
		if(this.p.getFloor() < 5) {
			Zombie zombie = this.zombie.get(this.p.getFloor() - 1);
			this.p.attack(zombie);
			printFightContent();
			if(zombie.getHp() <= 0) {
				System.out.println();
				System.out.printf("%s의 승리\n",this.p.getName());
				this.p.setFloor(this.p.getFloor() + 1);
				chk = 0;
			}
			else {
				System.out.println("좀비의 공격");
				zombie.attack((Unit)this.p);
				printFightContent();
				if(this.p.getHp() <= 0) {
					System.out.println("플레이어 사망");
					System.out.println("GAME OVER");
					chk = 1;
				}
			}
		}
		else {
			this.p.attack((Shielder) this.zk);
			printFightContent();
			
			if(this.zk.getHp() <= 0) {
				System.out.println();
				System.out.printf("%s의 승리\n",this.p.getName());
				this.p.setFloor(this.p.getFloor() + 1);
				chk = 0;
			}
			else {
				System.out.println("좀비의 공격");
				this.zk.attack((Unit)this.p);
				printFightContent();
				if(this.p.getHp() <= 0) {
					System.out.println("플레이어 사망");
					System.out.println("GAME OVER");
					chk = 1;
				}
			}
		}
		return chk;
	}
	
	private boolean alive(){
		if(this.p.getHp() <= 0) {
			return false;
		}
		return true;
	}
	
	public void run() {
		startGame();
		inputPName();
		init();
		boolean isRun = true;
		while(isRun) {
			System.out.println();
			if(this.p.getFloor() == 6) {
				System.out.println("플레이어 탑 정상 도착");
				break;
			}
			else {
				printFloor();
				if(this.p.getFloor() < 5) {
					if(rAppear()) {
						printAppear();
						isRun = alive();
						isRun = selAppear();
					}
					else {
						printNothing();
						isRun = selNothing();
					}
				}
				else {
					printAppear();
					isRun = alive();
					isRun = selAppear();
				}
			}
		}
	}
}
