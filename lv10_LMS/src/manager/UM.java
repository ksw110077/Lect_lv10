package manager;

public class UM {
	private UM() {
	}
	private static UM instance = new UM();
	public static UM getinstance() {
		return instance;
	}
	
}
