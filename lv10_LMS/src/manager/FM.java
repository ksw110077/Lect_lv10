package manager;

public class FM {
	private FM() {};
	
	private static FM instance = new FM();
	
	public static FM getInstance() {
		return instance;
	}
}
