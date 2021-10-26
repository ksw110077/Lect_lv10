package manager;

import java.util.Scanner;

public class LMS {
	public Scanner sc = new Scanner(System.in);

	private LMS() {
	} // 기본 생성자 제한

	private static LMS instance = new LMS();

	public static LMS getinstance() {
		return instance;
	}
	
	private int inputSystem() {
		System.out.print("입력 : ");
		String temp = sc.next();
		int sel = Integer.parseInt(temp);
		return sel;
	}
	
	private void printLMS() {
		System.out.println("======= LMS =======");
	}
	
	private void printLMSMenu() {
		System.out.println("[1.][학생등록]\n[2.][학생탈퇴]\n"
				+ "[3.][수강신청]\n[4.][성적관리]\n[0.][시스템종료]");
	}
	
	private boolean selLMSMenu() {
		int sel = inputSystem();
		if(sel == 1) {
			UM.getinstance().printUMMenu();
		}
		else if (sel == 2) {
			
		}
		else if (sel == 3) {
			
		}
		else if (sel == 4) {
			
		}
		else if (sel == 0) {
			System.out.println("시스템을 종료합니다.");
			return false;
		}
		return true;
	}
	
	public void run() {
		boolean isRun = true;
		while(isRun) {
			printLMS();
			printLMSMenu();
			isRun = selLMSMenu();
		}
	}
}
