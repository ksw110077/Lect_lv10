package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// GUI (Graphic User Interface)
// ㄴawt & swing
// 사용자의 os 리소스를 사용하기 때문에 무겁다
// 자바가 제공하는 좀 더 라이트한 라이브러리

// UI Element(요소)를 담는 컨테이너

// 최상위 컨테이너 : JFrame
// 일반 컨테이너 : JPanel
// 컴포넌트 (Element) : JButton, JLabel, JTextField, ...

class MenualPanel extends JPanel{
	public MenualPanel(int x, int y, int width, int height, Color c) {
		setBounds(x,y,width,height);
		setBackground(c);
	}
}

class MyPanel extends JPanel {
	public MyPanel (){
		// setBounds의 좌표값은 Frame 안에서의 좌표
		setBounds(0,0,250,400);
		// setBackground(Color.색상선택)
		// import java..awt.Color;
		setBackground(Color.pink);
	}
}



// 패널 한개 더 생성 하는 방법 (쉽긴 하나 코드가 길어짐)
//class MyPanel2 extends JPanel {
//	public MyPanel2 (){
//		setBounds(250,0,250,400);
//		setBackground(Color.orange);
//	}
//}


class Contents extends JPanel implements ActionListener {
	
	// 버튼 만들기
	// JButton 클래스를 import > 객체 생성
	private JButton bt = new JButton();
	private boolean click = false;
	public Contents() {

		// 버튼이 올라갈 패널 생성
		setLayout(null);
		setBounds(0,0,500,400);
		
		// 버튼 속성 설정
		System.out.println(this.bt);
		this.bt.setBounds(100,100,100,100); // 위치와 크기 
		this.bt.setText("PUSH"); // 버튼의 글씨

		// on mac (윈도우 생략 가능)
		this.bt.setOpaque(true);// 투명도
		this.bt.setBorderPainted(false); // 디폴트 테두리 삭제
		
		this.bt.setBackground(Color.gray); // 버튼 컬러
		this.bt.addActionListener(this);// 버튼에 리스너를 달아줌(반응 가능)
		
		// Panel에 버튼을 달아줌
		add(this.bt);
	}

	// implements ActionListener
	// 버튼이 반응 할 수 있게 해줌
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e.getSource()); // 버튼 정보 출력
		System.out.println("Click");
		
		if(e.getSource() == this.bt) {
			// 내 코드
//			if(!click) {
//				this.bt.setBackground(Color.red);
//				this.click = true;
//			}
//			else {
//				this.bt.setBackground(Color.gray);
//				this.click = false;
//			}
			
			// 선생님 코드
			this.click = this.click ? false : true;
			if(this.click) {
				this.bt.setBackground(Color.red);
			}
			else {
				this.bt.setBackground(Color.gray);
			}
		}
	}
}


// JFrame  (최상위 컨테이너) 만들기
class MyFrame extends JFrame{ // (JSwing import)
	public MyFrame() {
		// JFrame 설정
		
		
		// 0.
		// 기본 레이아웃 구성의 설정 -> 순서대로 나열식
		setLayout(null);
		
		// 1.
		// 타이틀 설정
		// super("title");
		// setTitle("title);
		// ㄴ 위 둘중 택 1
//		super("MyFrame");
		setTitle("MyFrame");
		
		// 2.
		// 크기 설정
		// setBounds(x,y,width,height);
		// setBounds(좌표x,좌표y,넓이,높이);
		setBounds(50,50,500,400);
		
		// 3.
		// 종료 조건
		// 기본은 콘솔을 닫아도 프로그램이 종료 되지 않는다..
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setDefaultCloseOperatio 으로 수동으로 종료 설정
		// Close 버튼을 눌렀을 때 프로그램을 종료
		
		// add 메소드 패널 추가
		// 패널 상위 작성 = 레이어상 위치도 위
//		add(new MyPanel());
//		add(new MyPanel2());
//		add(new MenualPanel(0,0,250,400,Color.blue));
//		add(new MenualPanel(0,0,250,400,Color.blue));
		add(new Contents());
		
		// 4.
		// 보이기
		// setVisible(true)
		setVisible(true);
		
		// 5.
		// 갱신
		revalidate();
		
	}
}

class ExFrame extends JFrame{
	
	public ExFrame() {
		
		// 1. 제목 설정
		// setLayout 뒤에 실행 되면 super 사용 불가??
		// super로 title 설정 후에 setTitle로 title 재설정 가능하지만
		// 반대 순서는 안됨
		super("제목");
		setTitle("제목2");
//		setTitle("제목2");
//		super("제목");
		
		// 0. 순서대로 나열?
		setLayout(null);
		
		// 2. 크기 설정
		setBounds(100,100,1500,300);
		
		// 3. 종료조건
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 닫기 창 눌렀을 때 thread 종료
		
		// 4. 창보이게 설정
		setVisible(true);
		
		// 5. 갱신
		revalidate();
		
	}
}

public class Ex_001_JFrame사용 {

	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
//		ExFrame frameEx = new ExFrame();
	}

}
