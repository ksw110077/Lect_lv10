package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import stage.Stage;


public class GameManager {
	private GameManager(){};
	private static GameManager instance = new GameManager();
	public static GameManager getInstance() {
		return instance;
	}
	public static Scanner sc = new Scanner(System.in);
	public static Random rn = new Random();
	public static String nextStage = "";
	public static String curStage = "";
	private Map<String , Stage> stageList = new HashMap<String , Stage>();
	
	public void init() {
		stageList.put("TITLE", new stage.StageTitle());
		stageList.put("BATTLE", new stage.StageBattle());
		stageList.put("LOBBY", new stage.StageLobby());
		nextStage = "TITLE";
	}
	
	public boolean changeStage() {
		System.out.println("curStage : " + curStage);
		System.out.println("nextStage : " + nextStage);
		
		if(curStage.equals(nextStage)) return true;
		
		curStage = nextStage;
		Stage stage = stageList.get(curStage);
		stage.init();
		
		boolean run = true;
		while(true) {
			run = stage.update();
			if(run == false) break;
		}
		
		if(nextStage.equals("")) return false;
		else return true;
	}
}
