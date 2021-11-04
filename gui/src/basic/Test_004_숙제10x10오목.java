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

class OmokResult extends JFrame{
	private JLabel winnerText = new JLabel(String.format("승자 Player %d", OPanel.winner));
	public OmokResult() {
		setTitle("게임 종료");
		setLayout(null);
		setBounds(Omok.width / 2 - 250,Omok.height / 2 - 90,500,180);
		getContentPane().setBackground(new Color(254, 247, 220));
		setWinnerText();
		setVisible(true);
		revalidate();
	}
	
	public void CloseWindow() {
		dispose();
	}
	
	public void setWinnerText() {
		this.winnerText.setBounds(0,0,500,140);
		this.winnerText.setBackground(Color.white);
		this.winnerText.setForeground(new Color(66, 63, 62));
		this.winnerText.setHorizontalAlignment(JLabel.CENTER);
		this.winnerText.setVerticalAlignment(JLabel.CENTER);
		this.winnerText.setFont(new Font("NanumGothicBold", Font.BOLD, 40));
		add(this.winnerText);
	}
}

class OPanel extends JPanel implements ActionListener{
	public static int winner = -1;

	private final int SIZE = 10;
	private int turn = 1;
	private boolean end = false;
	private OmokResult closer;

	private JButton[][] map = new JButton[this.SIZE][this.SIZE];
	private JButton reset = new JButton();
	
	private JLabel title = new JLabel("오목 게임");
	private JLabel printWinOp = new JLabel("승리 조건 : 6 칸 선점");
	private JLabel Player = new JLabel(String.format("Player : %d",this.turn));
	
	private Color pBC = new Color(254, 247, 220);
	private Color bBC = new Color(206, 229, 208);
	private Color bP1C = new Color(255, 191, 134);
	private Color bP2C = new Color(243, 139, 160);
	

	
	public OPanel() {
			setLayout(null);
			setBounds(0,0,Omok.FSIZE, Omok.FSIZE);
			setBackground(this.pBC);
			setTitle();
			setPlayer();
			setPrintWinOp();
			setMap();
			setResetButton();
	}
	
	private void setTitle() {
		this.title.setBounds(0,0,Omok.FSIZE,100);
		this.title.setForeground(new Color(66, 63, 62));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.CENTER);
		this.title.setFont(new Font("NanumGothicBold", Font.BOLD, 40));
		add(this.title);
	}
	private void setPrintWinOp() {
		this.printWinOp.setBounds(170,0,Omok.FSIZE,130);
		this.printWinOp.setForeground(new Color(66, 63, 62));
		this.printWinOp.setForeground(new Color(61, 86, 178));
		this.printWinOp.setHorizontalAlignment(JLabel.LEFT);
		this.printWinOp.setVerticalAlignment(JLabel.BOTTOM);
		this.printWinOp.setFont(new Font("NanumGothicBold", Font.BOLD, 20));
		add(this.printWinOp);
	}
	private void setPlayer() {
		this.Player.setBounds(0,0,Omok.FSIZE - 150,130);
		this.Player.setForeground(new Color(66, 63, 62));
		this.Player.setHorizontalAlignment(JLabel.RIGHT);
		this.Player.setVerticalAlignment(JLabel.BOTTOM);
		this.Player.setFont(new Font("NanumGothicBold", Font.BOLD, 20));
		add(this.Player);
	}
	
	private void setResetButton() {
		this.reset.setText("RESET");
		this.reset.setBounds(818 /2 - 100 / 2, 818 - 120, 100, 50);
		this.reset.setBackground(new Color(200, 227, 212));
		this.reset.setForeground(Color.white);
		this.reset.setFont(new Font("NanumGothicBold", Font.BOLD, 20));
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void setMap() {
		int x = Omok.FSIZE / 2 - 50 * this.SIZE/2;
		int y = Omok.FSIZE / 2 - 50 * this.SIZE/2;
		
		for(int i=0; i < this.SIZE; i++) {
			for(int j=0; j < this.SIZE; j++) {
				this.map[i][j] = new JButton();
				
				this.map[i][j].setBounds(x,y,50,50);
				this.map[i][j].setText("");
				this.map[i][j].setFont(new Font("NanumGothicBold", Font.PLAIN, 20));
				this.map[i][j].setForeground(Color.white);
				this.map[i][j].setBackground(this.bBC);
				
				this.map[i][j].setOpaque(true);
				this.map[i][j].setBorderPainted(false);
				
				this.map[i][j].addActionListener(this); 
				
				add(this.map[i][j]);
				
				x += 50 + 2;
			}
			x = Omok.FSIZE / 2 - 50 * this.SIZE/2;
			y += 50 + 2;
		}
	}
	
	
	private void chkGa() {
		// 가로 검사 영역 세로 Y : 0 ~ 9 가로 X : 0 ~ 4
		
		for(int i = 0; i < 10; i ++) { // 세로
			for(int j = 0; j < 5; j ++) { // 가로
				int cnt = 0;
				for (int k = 0; k < 6; k ++) { // 6 칸 고정
					if(this.turn == 1) {
						if(this.map[i][j+k].getText().equals("O")) { // 6칸 전부가 같을 때만 this.end = true;
							cnt ++;
						}
					}
					else {
						if(this.map[i][j+k].getText().equals("X")) { // 6칸 전부가 
							cnt ++;
						}
					}
				}
				if(cnt == 6) {
					this.end = true;
				}
			}
		}
	}
	private void chkSe() {
//		 세로 검사 영역 세로 Y : 0 ~ 4 가로 X : 0 ~ 9
			for(int j = 0; j < 10; j ++) { // 가로
				for(int i = 0; i < 4; i ++) { // 세로
				int cnt = 0;
				for (int k = 0; k < 6; k ++) { // 6 칸 고정
					if(this.turn == 1) {
						if(this.map[i+k][j].getText().equals("O")) { // 6칸 전부가 같을 때만 this.end = true;
							cnt ++;
						}
					}
					else {
						if(this.map[i+k][j].getText().equals("X")) { // 6칸 전부가 
							cnt ++;
						}
					}
				}
				if(cnt == 6) {
					this.end = true;
				}
			}
		}
	}
	private void chkLR() {
//		 왼오 검사 영역  세로 Y : 0 ~ 4 가로 X : 0 ~ 4
		for(int i = 0; i < 5; i ++) { // 세로
			for(int j = 0; j < 5; j ++) { // 가로
				int cnt = 0;
				for (int k = 0; k < 6; k ++) { // 6 칸 고정
					if(this.turn == 1) {
						if(this.map[i+k][j+k].getText().equals("O")) { // 6칸 전부가 같을 때만 this.end = true;
							cnt ++;
						}
					}
					else {
						if(this.map[i+k][j+k].getText().equals("X")) { // 6칸 전부가 
							cnt ++;
						}
					}
				}
				if(cnt == 6) {
					this.end = true;
				}
			}
		}
		
	}
	private void chkRL() {
//		 오왼 검사 영역  세로 Y : 0 ~ 4 가로 X : 9 ~ 5
		for(int i = 0; i < 5; i ++) { // 세로
			for(int j = 9; j > 4; j --) { // 가로
				int cnt = 0;
				for (int k = 0; k < 6; k ++) { // 6 칸 고정
					if(this.turn == 1) {
						if(this.map[i+k][j-k].getText().equals("O")) { // 6칸 전부가 같을 때만 this.end = true;
							cnt ++;
						}
					}
					else {
						if(this.map[i+k][j-k].getText().equals("X")) { // 6칸 전부가 
							cnt ++;
						}
					}
				}
				if(cnt == 6) {
					this.end = true;
				}
			}
		}
	}
	
	private void winChk() {
		chkGa();
		chkSe();
		chkLR();
		chkRL();
		
		if(this.end) {
			winner = this.turn;
			this.closer = new OmokResult();
		}
	}
	
	private void resetGame() {
		if(this.closer != null) {
			this.closer.CloseWindow();
		}
		this.turn = 1;
		this.end = false;
		this.Player.setText(String.format("Player : %d",this.turn));
		for(int i = 0; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE; j++) {
				this.map[i][j].setText("");
				this.map[i][j].setBackground(this.bBC);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.reset) {
				resetGame();
			}
			else {
				for(int i =0; i < this.SIZE; i++) {
					for(int j =0; j < this.SIZE; j++) {
						if(target == this.map[i][j] && target.getText().equals("") && !this.end) {
							if(this.turn == 1) {
								this.map[i][j].setText("O");
								this.map[i][j].setBackground(bP1C);
//							this.map[i][j].setForeground(bP1C);
							}
							else {
								this.map[i][j].setText("X");
								this.map[i][j].setBackground(bP2C);
//							this.map[i][j].setForeground(bP2C);
							}
							
							// 턴 바뀌기 전에 winChk
							winChk();
							
							this.turn = this.turn == 1? 2 : 1;
							
							this.Player.setText(String.format("Player : %d",this.turn));
						}
					}
				}
			}
		}
	}
}

class Omok extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int FSIZE = 818;
	
	private OPanel op = new OPanel();
	
	public Omok() {
		setTitle("10 x 10 오목");
		setLayout(null);
		setBounds(width / 2 - FSIZE / 2, height / 2 - FSIZE / 2,FSIZE,FSIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		getContentPane().setBackground();  // 패널 크기 때문에 적용안됨
		// 프레임 배경색 설정할때는 getContentPane() 사용
		
		add(this.op);
		setVisible(true);
		revalidate();
	}
}


public class Test_004_숙제10x10오목 {

	public static void main(String[] args) {
		Omok o = new Omok();
	}

}
