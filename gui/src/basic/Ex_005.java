package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

class JoinFrame extends JFrame{
	
	JLabel label = new JLabel("text<br>");
	// html 코드로 변경 줄바꿈 태그는 <br>
	
	
	JLabel idLabel = new JLabel("id : ");
	JLabel pwLabel = new JLabel("pw : ");
	JLabel nameLabel = new JLabel("name : ");
	
	JTextField idField = new JTextField();
	JTextField pwField = new JTextField();
	JTextField nameField = new JTextField();

	
	public JoinFrame() {
		setLayout(null);
		setBounds(200,200,300,400);
		
		setTextField();
		
		
		
		setVisible(true);
		revalidate();
	}

	private void setTextField() {
		this.idLabel.setBounds(30,50,60,50);
		add(this.idLabel);
		this.idField.setBounds(90,50,150,50);
		add(this.idField);
		
		this.pwLabel.setBounds(30,110,60,50);
		add(this.pwLabel);
		this.pwField.setBounds(90,110,150,50);
		add(this.pwField);

		this.nameLabel.setBounds(30,170,60,50);
		add(this.nameLabel);
		this.nameField.setBounds(90,170,150,50);
		add(this.nameField);
	}
}

class ExPanel extends JPanel implements KeyListener, ActionListener{
	
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

	Vector<String> colName = null;
	// User : Vector<String>
	// ㄴ add(id) : 중복예외처리
	// ㄴ add(pw)
	// ㄴ add(name)
	
	// 옵션 : 파일 처리
	
	JButton login = new JButton();
	JButton join = new JButton();
	
	JoinFrame joinFrame = null;
	
	JTable table = null;
	
	public ExPanel() {
		setLayout(null);
		setBounds(0,0,400,500);
//		setTextField();
//		setTextArea();
		
		
		setTable();
		setButton();
		
//		init();
	}

	private void init() {
		Random rn = new Random();
		
		String front [] = {"김","이","박","정","오"};
		String back1 [] = {"성","지","우","아","희"};
		String back2 [] = {"연","무","안","용","이"};
		
		for(int i = 0; i < 100; i ++) {
			Vector<String> user = new Vector<>();
			String name = front[rn.nextInt(front.length)] + back1[rn.nextInt(back1.length)]  + back2[rn.nextInt(back2.length)];
			user.add(i + "");
			user.add(i + "");
			user.add(name);
			this.users.add(user);
		}
	}

	private void setTable() {
		this.colName = new Vector<>();
		this.colName.add("ID");
		this.colName.add("PW");
		this.colName.add("NAME");
		
		
		// 생성
		this.table = new JTable(this.users, this.colName);
		this.table.setBounds(50,50,300,300);
		
		// 옵션
		this.table.setBorder(new LineBorder(Color.red)); // 외곽선 색
		this.table.setGridColor(Color.black); // 내부 선 색
//		add(this.table);
		
		JScrollPane js = new JScrollPane(this.table); // 인자 스크롤링 하고 싶은 데이터 객체
		js.setBounds(50,50,300,300);
		js.setAutoscrolls(true); // default true
		add(js);
		
		// 스크롤에서 값 수정하면 vector 값 변경
	}

	private void setButton() {
		this.join.setBounds(210,380,100,50);
		this.join.setText("Join");
		this.join.addActionListener(this);
		add(this.join);

		this.login.setBounds(100,380,100,50);
		this.login.setText("Login");
		this.login.addActionListener(this);
		add(this.login);
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
		
		Object targer = e.getSource();
		
		if(targer == this.joinFrame.idField || targer == this.joinFrame.pwField || targer == this.joinFrame.nameField) {
			String id = this.joinFrame.idField.getText();
			String pw = this.joinFrame.pwField.getText();
			String name = this.joinFrame.nameField.getText();
			
			if(!id.equals("") && !pw.equals("") && !name.equals("")) {
				// join
				joinUser(id, pw, name);
			}
		}
	}

	private void joinUser(String id, String pw, String name) {
		boolean check = checkUserId(id);
		
		if(!check) {
			// User
			Vector<String> user = new Vector<>();
			user.add(id);
			user.add(pw);
			user.add(name);
			
			this.users.add(user);
			
			System.out.println("회원가입 완료!");
			System.out.println("users.size()" + this.users.size());
			
			this.table.revalidate();
			this.table.repaint();
			
			System.out.println("0인덱스 유저 정보");
			System.out.println(this.users.get(0));
			System.out.println(this.users.get(1));
			System.out.println(this.users.get(2));
			
			this.joinFrame.dispose(); // 프레임에 대한 창 닫기
		}
		else {
			// 단순 팝업창을 띄울때에만 사용(권장 X 메세지만 띄울 수 있고 컴포넌트를 추가하거나 하는 구성이 안됨)
			// type null, 기본값 초기화
			JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
		}
	}

	private boolean checkUserId(String id) {
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).get(0).equals(id)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.join) {
			this.joinFrame = new JoinFrame();
//			this.joinFrame.setFocusable(true);
//			this.joinFrame.addKeyListener(this);
			
			this.joinFrame.idField.setFocusable(true);
			this.joinFrame.idField.addKeyListener(this);
			this.joinFrame.pwField.setFocusable(true);
			this.joinFrame.pwField.addKeyListener(this);
			this.joinFrame.nameField.setFocusable(true);
			this.joinFrame.nameField.addKeyListener(this);
		}
	}
}


public class Ex_005 extends JFrame {
	
	public Ex_005() {
		setLayout(null);
		setBounds(100,100,400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// JFrame 에는 Pane
		// this.getContentPane() -> 교체 this.setContentPane()
		
		
		// 아래 두줄 동일
		this.getContentPane().add(new ExPanel());
		add(new ExPanel());
		
		
		setVisible(true);
		revalidate();
	}
	
	
	public static void main(String[] args) {
		Ex_005 ex = new Ex_005();
	}

}
