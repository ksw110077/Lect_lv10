package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 오목게임 (JButton)
// 10 * 10
// P1, P2


class ButtonPanel extends JPanel{
	public JButton reset = new JButton();
	public ButtonPanel() {
		setLayout(null);
		setBounds(0,0,OmokFrame.SIZE,OmokFrame.SIZE);
		setResetButton();
	}
	
	private void setResetButton() {
		this.reset.setText("RESET");
		this.reset.setFont(new Font("",Font.PLAIN, 50));
		this.reset.setBounds(OmokFrame.SIZE / 2 - 150, OmokFrame.SIZE / 2 - 150, 300, 300);
		add(this.reset);
	}
}

class Result extends JFrame{
	private JLabel text = new JLabel(); 
	
	public Result(String str) {
		super("Game Over");
		setBounds(OmokFrame.W / 2 - 150 , OmokFrame.H / 2 - 100, 300 ,200);
		
		this.text.setText(str);
		this.text.setBounds(0,0,300,200);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		
		setVisible(true);
		revalidate();
	}
}

class OmokPanel extends JPanel implements ActionListener{
	
	private JLabel text = new JLabel("Omok Game"); 
	
	private final int SIZE = 10;
	private JButton[][]  map = new JButton[this.SIZE][this.SIZE];
	private int[][]  mark = new int[this.SIZE][this.SIZE];
	
	private int turn = 1;
	private int win;
	
	private ButtonPanel reset = new ButtonPanel();
	
	public OmokPanel() {
		setLayout(null);
		setBounds(0,0,OmokFrame.SIZE,OmokFrame.SIZE);
		setBackground(Color.white);
		
		// add(추가할 컴포넌트 , 0) 
		// 0 ... 우선 순위 설정
		
		// add 하는 순서대로 우선순위가 top부터 정해짐
		this.reset.reset.addActionListener(this);
		add(this.reset);
		this.reset.setVisible(false);
		
		setTitle();
		setMap();
	}
	

	private void setTitle() {
		this.text.setBounds(0,0,OmokFrame.SIZE,100);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		this.text.setFont(new Font("",Font.BOLD, 30));
		add(this.text);
	}
	
	public void setMap() {
		
		int x = OmokFrame.SIZE / 2 - 50 * 10 / 2;
		int y = OmokFrame.SIZE / 2 - 50 * 10 / 2;
		for(int i = 0; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE; j++) {
				// 버튼 생성
				JButton bt = new JButton(); // 생성
				
				// 버튼  크기 설정
				bt.setBounds(x, y, 50, 50); // 좌표 x, 좌표 y, 버튼의 가로 크기, 버튼의 세로 크기
				
				// 버튼 색 설정
				bt.setBackground(Color.lightGray);

				// on Mac
				bt.setOpaque(true); // 투명도
				bt.setBorderPainted(false); // 외곽선
				
				// 이벤트 리스너 달기
				// JButton : ActionLister <- 인터페이스로 제공
				bt.addActionListener(this); // this : bt <- 이벤트 발생 시, actionPerformed() 메소드가 동작
				
				this.map[i][j] = bt; // 주소 넘기기
				
				// 현재 패널 (this)에 버튼 달기
				add(this.map[i][j]); // add(bt); // 객체 주소이기 때문에 둘 중 하나 아무거나 써도 된다
				
				// 다음 버튼을 위한 좌표 설정
				// 내부 for문에서는 x 좌표만 이동
				
				x += 50 + 1; // 크기 + 여백크기
			}
			x = OmokFrame.SIZE / 2 - 50 * 10 /2; // x 좌표 초기화
			y += 50 + 1; // y 좌표 이동
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) { // e 가 JButton 으로 바뀌면
			JButton target = (JButton) e.getSource(); //type cast
			
			if(target == this.reset.reset) {
				resetGame();
				System.out.println("reset");
			}
			else {
				// 맵 안에 있는 버튼이 맞는지,
				// 어떤 버튼인지
				// turn 에 따라서 - > marking
				for(int i =0; i < this.map.length; i++) {
					for(int j =0; j < this.map.length; j++) {
						if(target == this.map[i][j] && this.mark[i][j] == 0) { // 아직 마킹되지 않은 자리 확인
							if(this.turn == 1) {
								target.setBackground(Color.red);
							}
							else {
								target.setBackground(Color.blue);
							}
							
							this.mark[i][j] = this.turn; // 마킹 하고
							
							checkWin();						// 검사
							
							this.turn = this.turn == 1 ? 2 :1 ;
						}
					}
				}
			}
		}
	}
	
	private void resetGame() {
		this.mark = new int [this.SIZE][this.SIZE];
		this.turn =1;
		this.win = 0;
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE; j++) {
				this.map[i][j].setBackground(Color.lightGray);
			}
		}
		
		// 컴포넌트의 삭제
//		this.remove(this.reset);
//		this.revalidate();
//		this.repaint();
		
		this.reset.setVisible(false);
	}

	private void checkWin() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		
		if(this.win != 0) {
			this.reset.setVisible(true); // 게임이 클리어 되면 -> 리셋버튼의 등장
			new Result(String.format("p%d win!", this.win));
		}
	}
	
	
	private int checkHori() {
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE - 4; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.mark[i][j+k] == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	private int checkVerti() {
		for(int i =0; i < this.SIZE - 4; i++) {
			for(int j =0; j < this.SIZE; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.mark[i+k][j] == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	private int checkDia() {
		for(int i =0; i < this.SIZE - 4; i++) {
			for(int j =0; j < this.SIZE - 4; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.mark[i+k][j+k] == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	private int checkReverse() {
		for(int i = 4; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE - 4 ; j++) {
				if(this.mark[i][j] == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.mark[i-k][j+k] == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}

	
	
}


class OmokFrame extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize(); // 사용자 디스플레이 정보
	public static final int W = dm.width;
	public static final int H = dm.height;
	
	public static final int SIZE = 700;
	
	private OmokPanel panel = new OmokPanel();
	
	public OmokFrame() {
		
		setTitle("Omok"); // == super("Omok");
		setLayout(null);
		setBounds(W / 2 - SIZE / 2, H / 2 - SIZE / 2,SIZE,SIZE); // == setLocation(int x,int y)  +  setSize(int width, int height);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 종료 조건
		
		add(panel);
		
		setVisible(true);
		revalidate(); // 갱신
	}
}


public class Test_004_숙제10x10오목_정답 {

	public static void main(String[] args) {
		OmokFrame game = new OmokFrame();
	}

}
