package basic;

import javax.swing.JFrame;

class FrameOne extends JFrame {
	public FrameOne() {
		// 0. 레이아웃 정렬
		setLayout(null);
		// 1. title
		setTitle("와오아왁");
		// 2. 크기
		setBounds(100,200,900,580);
		// 3. 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 4. 보이기
		setVisible(true);
		// 5. 갱신
		revalidate();
	}
	
	public void run() {
		setLayout(null);
		setTitle("가나");
		setBounds(100,400,500,900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
	}
}

public class Ex_연습 {

	public static void main(String[] args) {
		FrameOne m = new FrameOne();
		m.run();
	}

}
