import game.GameManager;

public class Main {
	
	public static void main(String[] args) {
		GameManager gameManager = game.GameManager.getInstance();
		gameManager.init();
		boolean isRun = true;
		while(isRun) {
			
			if(isRun == false) {
				break;
			}
		}
		System.out.println("게임 종료");
	}
}
