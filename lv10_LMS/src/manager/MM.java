package manager;

public class MM {
	private MM() {};
	private static MM instance = new MM();
	public static MM getInstance() {
		return instance;
	}
}
