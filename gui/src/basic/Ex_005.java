package basic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ExPanel extends JPanel implements KeyListener{
	
	JTextField jf = new JTextField(); // 텍스트를 받는 인톱박스
	// JLabel 사용하듯 setBounds -> add
	// 텍스트 입력 받기 위해 리스너 
	// 텍스트 필드는 줄바꿈이 불가
	
	// 줄바꿈 필요하면 JTextArea 활용
	
	JTextArea ja = new JTextArea();
	
	// 로그인 & 회원 가입
	// ㄴ 메인 프레임에 버튼 두 개
	// ㄴ 로그인과 회원가입 버튼
	// ㄴ 버튼을 누르면 -> 팝업(새로운 프레임) -> 텍스트 입력
	// ㄴ 회원가입 정보는 Vector에 저장
	
	Vector<Vector<String>> users = new Vector<>();
	// User : Vector<String>
	// ㄴ add(id) : 중복예외처리
	// ㄴ add(pw)
	// ㄴ add(name)
	
	// 옵션 : 파일 처리
	
	public ExPanel() {
		setLayout(null);
		setBounds(0,0,400,500);
		setTextField();
		setTextArea();
	}

	private void setTextArea() {
		this.ja.setBounds(100,180,200,200);
		add(this.ja);		
	}

	private void setTextField() {
		this.jf.setBounds(100,100,100,30);
		this.jf.setFocusable(true);
		this.jf.addKeyListener(this);
		add(this.jf);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("press");
		
		if(e.getKeyCode() == e.VK_ENTER) {
			System.out.println(this.jf.getText());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


public class Ex_005 extends JFrame {
	
	public Ex_005() {
		setLayout(null);
		setBounds(100,100,400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new ExPanel());
		
		setVisible(true);
		revalidate();
	}
	
	
	public static void main(String[] args) {
		Ex_005 ex = new Ex_005();
	}

}
