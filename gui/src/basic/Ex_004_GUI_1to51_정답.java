package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class AlertResult extends JFrame{
	
	private JLabel text = new JLabel();
	
	public AlertResult(int ms) {
		super("Game Clear");
		setLayout(null);
		setBounds(700 /2 - 300 /2 + 50, 800 /2 - 200 /2 + 50,300,200);
		
		this.text.setText(String.format("성적 : %5d.%3d", ms / 1000, ms % 1000));
		this.text.setBounds(0,0,300,200);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		setVisible(true);
	}
}

class GamePanel extends JPanel implements ActionListener, Runnable{
	
	private JLabel title = new JLabel("1 to 50");
	private JLabel timer = new JLabel("READY");
	private int ms;
	private boolean isRun;
	
	private final int SIZE = 5;
	private JButton[][] map = new JButton[this.SIZE][this.SIZE];
	private int [][] front = new int [this.SIZE][this.SIZE];
	private int [][] back = new int [this.SIZE][this.SIZE];
	
	private JButton reset = new JButton();
	
	private int gameNum = 1;
	
	private Color background = new Color(246,234,190);
	private Color frontC = new Color(200,227,212);
	private Color backC = new Color(135,170,170);
	
	public GamePanel() {
		setLayout(null);
		setBounds(0,0,700,800); // 프레임 위에서 기준
		
		setTitle();
		setTimer();
		
		setData();
		setMap();
		
		setResetButton();
	}
	
	private void setResetButton() {
		this.reset.setText("RESET");
		this.reset.setBounds(700 /2 - 100 / 2, 800 - 120, 100, 50);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	public void setTimer() {
		this.timer.setBounds(20,0,200,50);
		this.timer.setBackground(Color.green); //
		this.timer.setHorizontalAlignment(JLabel.LEFT);
		
		add(this.timer);
	}
	
	public void setTitle() {
		this.title.setBounds(0,0,700,130);
		this.title.setForeground(this.backC);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.CENTER);
		this.title.setFont(new Font("", Font.PLAIN, 30));
		add(this.title);
	}
	
	private void setData() {
		int n = 1;
		for(int i=0; i < this.SIZE; i++) {
			for(int j=0; j < this.SIZE; j++) {
				this.front[i][j] = n;
				this.back[i][j] = n + this.SIZE * this.SIZE;
				n++;
			}
		}
		shuffle();
	}
	
	private void shuffle() {
		Random rn = new Random();
		for(int i =0; i < 1000; i++) {
			// front
			
			int r1 = rn.nextInt(this.SIZE);
			int r2 = rn.nextInt(this.SIZE);
			
			int temp = this.front[0][0];
			this.front[0][0] = this.front[r1][r2];
			this.front[r1][r2] = temp;
			
			// back
			r1 = rn.nextInt(this.SIZE);
			r2 = rn.nextInt(this.SIZE);
			
			temp = this.back[0][0];
			this.back[0][0] = this.back[r1][r2];
			this.back[r1][r2] = temp;
		}

	}
	
	private void setMap() {
		int x = 700 / 2 -100*this.SIZE/2;
		int y = 700 / 2 -100*this.SIZE/2;
		
		for(int i=0; i < this.SIZE; i++) {
			for(int j=0; j < this.SIZE; j++) {
				this.map[i][j] = new JButton();
				// 버튼의 속성 설정
				this.map[i][j].setBounds(x,y,100,100);
				this.map[i][j].setText(this.front[i][j] + "");
				this.map[i][j].setFont(new Font("", Font.PLAIN, 20));
				this.map[i][j].setForeground(Color.white);
				
				//on mac
				this.map[i][j].setOpaque(true);
				this.map[i][j].setBorderPainted(false);
				
				this.map[i][j].setBackground(this.frontC);
				this.map[i][j].addActionListener(this);
				
				add(this.map[i][j]);
				
				x += 100 + 3;
			}
			x = 700 / 2 - 100 * this.SIZE / 2;
			y += 100 + 3;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.reset) {
				System.out.println("reset");
				resetGame();
			}
			else {
				for(int i =0; i < this.SIZE; i++) {
					for(int j =0; j < this.SIZE; j++) {
						if(target == this.map[i][j] && this.front[i][j] == this.gameNum) {
							// start Timer
							if(!this.isRun) {
								this.isRun = true;
							}
							
							// back -> front
							// gameNum
							
							this.front[i][j] = this.back[i][j];
							this.back[i][j] = 0;
							
							if(this.front[i][j] == 0) {
								this.map[i][j].setBackground(this.background);
								this.map[i][j].setText("");
							}
							else {
								this.map[i][j].setBackground(this.backC);
								this.map[i][j].setText(this.front[i][j] + "");
							}
							
							this.gameNum ++;
							
							this.isRun = winCheck();
							if(!this.isRun) {
								new AlertResult(this.ms); // 성적 (ms) 출력
							}
						}
						
					}
				}
			}
		}
	}

	private void resetGame() {
		this.gameNum = 1;
		this.isRun = false;
		this.ms = 0;
		this.timer.setText("READY");

		setData();
		
		for(int i = 0; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE; j++) {
				this.map[i][j].setText(this.front[i][j] + "");
				this.map[i][j].setBackground(this.frontC);
			}
		}
	}
	
	private boolean winCheck() {
//		boolean check = false;
//		for(int i =0; i < this.SIZE; i++) {
//			for(int j =0; j < this.SIZE; j++) {
//				if(this.front[i][j] != 0) {
//					check = true;
//				}
//			}
//		}
//		return check;
		
		if(this.gameNum > this.SIZE * this.SIZE * 2) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public void run() {
		while(true) {
			if(this.isRun) {
				this.ms++;
				this.timer.setText(String.format("%5d.%3d", this.ms / 1000, this.ms % 1000));
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}
}
class Game extends JFrame{
	private GamePanel panel = new GamePanel(); // <- new Runnable 생성
	
	public Game() {
		super("One To Fifty");
		setLayout(null);
		setBounds(50,50,700,800); // PC 디스플레이 위에서 기준
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
		
		this.panel.run(); // 생성된 Runnable의 run() 메소드를 호출, 프레임을 띄운 후 - > 스레드 가동
	}
}

public class Ex_004_GUI_1to51_정답 {

	public static void main(String[] args) {
		Game game = new Game();
	}

}
