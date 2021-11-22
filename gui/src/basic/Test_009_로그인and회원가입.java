package basic;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


// 로그인 & 회원 가입
// ㄴ 메인 프레임에 버튼 두 개
// ㄴ 로그인과 회원가입 버튼
// ㄴ 버튼을 누르면 -> 팝업(새로운 프레임) -> 텍스트 입력
// ㄴ 회원가입 정보는 Vector에 저장

//Vector<Vector<String>> users = new Vector<>();
// User : Vector<String>
// ㄴ add(id) : 중복예외처리
// ㄴ add(pw)
// ㄴ add(name)

// 옵션 : 파일 처리

// 프레임 X + 14, Y + 37; // 확실하지 않음


class LogJoinUM {
	private final String FILENAME = "LogJoinData.txt";
	private Vector<Vector<String>> data = new Vector<>();
	private int log = -1;
	
	private static LogJoinUM instance = new LogJoinUM();
	private LogJoinUM() {
		load();
	};
	
	public void load() {
		File file = new File(this.FILENAME);
		
		if(file.exists()) {
			this.data = new Vector<>();

			FileReader fr = null;
			BufferedReader br = null;
			
			try {
				// 아이디/비번/이름
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				String user = br.readLine();
				
				while(user != null) {
					String temp [] = user.split("/");
					
					String id = temp[0];
					String pw = temp[1];
					String name = temp[2];
					
					Vector<String> userData = new Vector<>();
					
					userData.add(id);
					userData.add(pw);
					userData.add(name);
					
					this.data.add(userData);
					
					user = br.readLine();
				}
				
				
			} catch (Exception e) {
			}
		}
	}
	
	public void save () {
		try {
			FileWriter fw = new FileWriter(this.FILENAME);
			
			for(int i = 0; i < this.data.size(); i++) {
				Vector<String> userData = this.data.get(i);
				String user = userData.get(0) + "/" + userData.get(1) + "/" + userData.get(2) + "\n";
				fw.write(user);
			}
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static  LogJoinUM getInstance() {
		return instance;
	}
	
	public Vector<Vector<String>> getData() {
		return data;
	}

	public void setData(Vector<Vector<String>> data) {
		this.data = data;
	}

	public int getLog() {
		return log;
	}

	public void setLog(int log) {
		this.log = log;
	}
}

class SuccessJoin extends MyFrameUtil{
	public SuccessJoin() {
		setTitle("회원가입완료");
		setLayout(null);
		setBounds(300,300,414,137);
		addWindowListener(this);
		
		setLabel();
		setVisible(true);
		revalidate();
	}
	
	private void setLabel() {
		JLabel a = new JLabel();
		a.setText("가입을 축하드립니다.");
		a.setBounds(0,0,400,100);
		a.setHorizontalAlignment(JLabel.CENTER);
		a.setVerticalAlignment(JLabel.CENTER);
		add(a);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}
}

class FailJoin extends MyFrameUtil{
	public FailJoin() {
		setTitle("회원가입 실패");
		setLayout(null);
		setBounds(300,300,414,137);
		addWindowListener(this);
		
		setLabel();
		setVisible(true);
		revalidate();
	}
	
	private void setLabel() {
		JLabel a = new JLabel();
		a.setText("가입 정보를 확인하세요.");
		a.setBounds(0,0,400,100);
		a.setHorizontalAlignment(JLabel.CENTER);
		a.setVerticalAlignment(JLabel.CENTER);
		add(a);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}
}
class FailLogin extends MyFrameUtil{
	public FailLogin() {
		setTitle("로그인 실패");
		setLayout(null);
		setBounds(300,300,414,137);
		addWindowListener(this);
		
		setLabel();
		setVisible(true);
		revalidate();
	}
	
	private void setLabel() {
		JLabel a = new JLabel();
		a.setText("회원 정보를 확인하세요.");
		a.setBounds(0,0,400,100);
		a.setHorizontalAlignment(JLabel.CENTER);
		a.setVerticalAlignment(JLabel.CENTER);
		add(a);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}
}

class JoinPopup extends MyFrameUtil{
	LogJoinUM um = LogJoinUM.getInstance();
	private final int SIZE = 3;
	private JTextField[] jf = new JTextField[this.SIZE];
	private JLabel[] jl = new JLabel[this.SIZE];
	private JButton push;
	
	public JoinPopup() {
		setLayout(null);
		setBounds(300,300,514,337);
		addWindowListener(this);
		setbtn();
		setLabel();
		setTextField();
		setVisible(true);
		revalidate();
	}
	
	private void setbtn() {
		this.push = new JButton();
		this.push.setText("회원가입");
		this.push.setLayout(null);
		this.push.setBounds(350,150 - 25,100,50);
		this.push.addActionListener(this);
		add(this.push);
	}

	private void setLabel() {
		String[] a = {"ID : ","PW : ", "NAME : "};
		int x = 50;
		int y = 30;
		for(int i = 0; i < this.SIZE ; i++) {
			this.jl[i] = new JLabel();
			this.jl[i].setText(a[i]);
			this.jl[i].setBounds(x,y,50,50);
			add(this.jl[i]);
			y += 90;
		}
	}
	
	private void setTextField() {
		int x = 100;
		int y = 30;
		for(int i = 0; i < this.SIZE; i++) {
			this.jf[i] = new JTextField();
			this.jf[i].setBounds(x,y,200,50);
			add(this.jf[i]);
			y += 90;
		}
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if(t == this.push) {
				chkOverlap();
			}
		}
	}


	private void chkOverlap() {
		if(!this.jf[0].getText().equals("") && !this.jf[1].getText().equals("") && !this.jf[2].getText().equals("")) {
			String id = this.jf[0].getText();
			String pw = this.jf[1].getText();
			String name = this.jf[2].getText();
			
			boolean overlap = false;
			
			for(int i = 0 ; i < this.um.getData().size(); i++) {
				Vector<String> user = this.um.getData().get(i);
				String saveId = user.get(0);
				
				if(saveId.equals(id)) {
					overlap = true;
				}
			}
			
			if(!overlap) {
				Vector<String> user = new Vector<>();
				
				user.add(id);
				user.add(pw);
				user.add(name);
				
				this.um.getData().add(user);
				
				
				this.um.save();
				
				dispose();
				
				new SuccessJoin();
			}
			else {
				new FailJoin();
			}
		}
		else if(this.jf[0].getText().equals("") || this.jf[1].getText().equals("") || this.jf[2].getText().equals("")) {
			new FailJoin();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}
}

class loginPopup extends MyFrameUtil{
	LogJoinUM um = LogJoinUM.getInstance();
	private final int SIZE = 2;
	private JTextField[] jf = new JTextField[this.SIZE];
	private JLabel[] jl = new JLabel[this.SIZE];
	private JButton push;
	
	public loginPopup() {
		setTitle("로그인");
		setLayout(null);
		setBounds(300,300,514,337);
		addWindowListener(this);
		setbtn();
		setLabel();
		setTextField();
		setVisible(true);
		revalidate();
	}
	
	private void setbtn() {
		this.push = new JButton();
		this.push.setText("로그인");
		this.push.setLayout(null);
		this.push.setBounds(350,150 - 25,100,50);
		this.push.addActionListener(this);
		add(this.push);
	}

	private void setLabel() {
		String[] a = {"ID : ","PW : "};
		int x = 50;
		int y = 30;
		for(int i = 0; i < this.SIZE ; i++) {
			this.jl[i] = new JLabel();
			this.jl[i].setText(a[i]);
			this.jl[i].setBounds(x,y,50,50);
			add(this.jl[i]);
			y += 90;
		}
	}

	private void setTextField() {
		int x = 100;
		int y = 30;
		for(int i = 0; i < this.SIZE; i++) {
			this.jf[i] = new JTextField();
			this.jf[i].setBounds(x,y,200,50);
			add(this.jf[i]);
			y += 90;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			if(t == this.push) {
				chklogin();
			}
		}
	}

	private void chklogin() {
		if(!this.jf[0].getText().equals("") && !this.jf[1].getText().equals("")) {
			String id = this.jf[0].getText();
			String pw = this.jf[1].getText();
			
			boolean chk = false;
			
			for(int i = 0 ; i < this.um.getData().size(); i++) {
				Vector<String> user = this.um.getData().get(i);
				// id
				// pw
				// name 순 저장
				
				String saveId = user.get(0);
				String savePw = user.get(1);
				
				if(id.equals(saveId) && pw.equals(savePw)) {
					System.out.print(user.get(2) + " ");
					System.out.println("로그인 성공");
					this.um.setLog(i);
					dispose();
					chk = true;
					break;
				}
			}
			if(!chk){
				new FailLogin();
			}
		}
		else if(this.jf[0].getText().equals("") || this.jf[1].getText().equals("")) {
			new FailLogin();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
	}
}

class logJoinPanel extends MyUtil{
	LogJoinUM um = LogJoinUM.getInstance();
	private JoinPopup joinPop; 
	private loginPopup loginPop; 
	
	private JButton login = new JButton();
	private JButton join = new JButton();
	
	private JLabel logId = new JLabel();
	
	private Vector<String> colName = null;
	private JTable jt = null;
	
	// 50 부터
	
	public logJoinPanel() { // 로그인 회원가입 버튼 두개
		setLayout(null);
		setBounds(0,0,500,300);
		
		setLogId();
		setbtn();
	}
	
	private void setLogId() {
		this.logId.setText("");
		this.logId.setBounds(10,10,200,50);
		add(this.logId);
	}

	private void setbtn() {
		this.login.setText("LOGIN");
		this.login.setBounds(40,10,200,30);
		this.login.addActionListener(this);
		add(this.login);
		
		this.join.setText("JOIN");
		this.join.setBounds(260,10,200,30);
		this.join.addActionListener(this);
		add(this.join);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.um.getLog() == -1) {
			this.logId.setText("");
			this.login.setText("LOGIN");
		}
		else {
			String id = this.um.getData().get(this.um.getLog()).get(0);
			this.logId.setText(id + " 로그인 중");
			this.login.setText("LOGOUT");
		}
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton t = (JButton) e.getSource();
			
			if(t == this.login) {
				if(this.um.getLog() == -1) {
					System.out.println("로그인 시도");
					this.loginPop = new loginPopup();
				}
				else {
					System.out.println("로그아웃");
					this.um.setLog(-1);
				}
			}
			else if (t == this.join) {
				System.out.println("회원가입");
				this.joinPop = new JoinPopup();
			}
		}
	}
}


public class Test_009_로그인and회원가입 extends JFrame {
	public Test_009_로그인and회원가입() {
		setLayout(null);
		setBounds(300,300,514,337);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new logJoinPanel());
		
		setVisible(true);
		revalidate();
	}
	
	public static void main(String[] args) {
		new Test_009_로그인and회원가입();
	}

}
