package stage;

public class StageLobby implements Stage{

	@Override
	public boolean update() {
		System.out.println("=====[LOBBY]=====");
		System.out.println("[1.전투] [2.종료]");
		int sel = game.GameManager.sc.nextInt();
    
		if(sel == 1) {
			game.GameManager.nextStage = "BATTLE";				
		}
		else if(sel == 2) {
			game.GameManager.nextStage = "";
		}
		else {
			game.GameManager.nextStage = "";					
		}
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
