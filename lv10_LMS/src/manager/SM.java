package manager;

public class SM {
	private SM() {};
	
	private static SM instance = new SM();
	
	public static SM getInstance() {
		return instance;
	}
}
