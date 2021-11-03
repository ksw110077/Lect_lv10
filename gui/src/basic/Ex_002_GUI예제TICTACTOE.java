package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class TTTPanels extends JPanel implements ActionListener{
	private boolean click = false;
	private JButton bt = new JButton();
	private int player = -1;
	
	public int getPlayer() {
		return this.player;
	}
	
	public TTTPanels(int x, int y,int num, Color c){
		setLayout(null);
		setBounds(0,0,902,902);
		this.bt.setText("" + num);
		this.bt.setBounds(y,x,300,300);
		this.bt.addActionListener(this);
		this.bt.setBackground(c);
		add(this.bt);
	}
	
	
	private int  winPlayerChk(int pNum) {
		int chkPlayer = -1;
		for(int i = 0; i < 3; i ++) {
			int chk = 0;
			for(int j = 0; j <7; j +=3) {
				if(TTTFrame.map.get(i + j).getPlayer() == pNum) {
					chk ++;
				}
			}
			if(chk == 3) {
				chkPlayer = pNum;
			}
		}
		
		for(int i =0; i< 7; i += 3) {
			int chk = 0;
			for(int j = 0; j < 3; j ++) {
				if(TTTFrame.map.get(i + j).getPlayer() == pNum) {
					chk ++;
				}
			}
			if(chk == 3) {
				chkPlayer = pNum;
			}
		}
		
		if(TTTFrame.map.get(0).getPlayer() == pNum &&
				TTTFrame.map.get(4).getPlayer() == pNum &&
						TTTFrame.map.get(8).getPlayer() == pNum) {
			chkPlayer = pNum;
		}
		if(TTTFrame.map.get(2).getPlayer() == pNum &&
				TTTFrame.map.get(4).getPlayer() == pNum &&
						TTTFrame.map.get(6).getPlayer() == pNum) {
			chkPlayer = pNum;
		}
		return chkPlayer;
	}
	
	private void chkWin() {
		if(!TTTGame.player) { // p1
			// 0 1 2
			// 3 4 5
			// 6 7 8
			int win = winPlayerChk(1);
			if(win != -1) {
				TTTGame.win = win;
			}
		}
		else {
			int win = winPlayerChk(2);
			if(win != -1) {
				TTTGame.win = win;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bt && !this.click && TTTGame.win == -1) {
			if(!TTTGame.player) { // P1
				this.bt.setBackground(Color.pink);
				this.player = 1;
			}
			else { // P2
				this.bt.setBackground(Color.blue);
				this.player = 2;
			}
			chkWin();
			if(TTTGame.win != -1) {
				System.out.println("Player" + TTTGame.win + " 승리");
				return;
			}
			this.click = true;
			TTTGame.player = TTTGame.player ? false : true;
		}
	}
	
}

class TTTFrame extends JFrame{
	public static ArrayList<TTTPanels> map = new ArrayList<>();
	public TTTFrame() { // 프레임 생성
		setLayout(null);
		setTitle("TIC TAC TOE");
		setBounds(500,100,902,902);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		int num = 1;
		for(int i =0; i < 900; i += 301) {
			for(int j =0; j < 900; j += 301) {
				TTTPanels a = new TTTPanels(i,j,num,Color.gray);
				num++;
				add(a);
				this.map.add(a);
			}
		}
	}
}


class TTTGame {
	public static boolean player = false;
	public static int win = -1;
	public void run(){
		TTTFrame game = new TTTFrame();
	}
}

public class Ex_002_GUI예제TICTACTOE {
	public static void main(String[] args) {
		TTTGame g = new TTTGame();
		g.run();
	}
}
