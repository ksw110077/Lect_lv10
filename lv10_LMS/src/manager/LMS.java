package manager;

public class LMS {
	private LMS() {
	} // 기본 생성자 제한

	private static LMS instance = new LMS();

	public static LMS getinstance() {
		return instance;
	}
	
	public void run() {
		
	}
}
