package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Rect {
	private int x, y, w, h, owner;
	private Color c;
	
	
	public Rect(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) { ////
		this.owner = owner;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) { ////
		this.c = c;
	}
	
}

class OmokResult02 extends JFrame{
	
	private JLabel  text = new JLabel();
	
	public OmokResult02(String str) {
		super("Game Clear");
		setLayout(null);
		setBounds(100,100,300,200);
		this.text.setBounds(0,0,300,200);
		text.setText(str);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		setVisible(true);
		revalidate();
	}
}

class Omok2Panel extends JPanel implements MouseListener{
	private final int SIZE = 10;
	private Rect [][] map = new Rect[this.SIZE][this.SIZE];
	
	private int turn = 1;
	private int win;
	
	private Color p1 = Color.black;
	private Color p2 = Color.white;
	
	
	public Omok2Panel() {
		setLayout(null);
		setBounds(0,0,700,700);
		
		setMap();
		
		addMouseListener(this); // this : Panel
	}
	

	private void setMap() {
		
		int x = 700 / 2 - 50 * 10 / 2;
		int y = 700 / 2 - 50 * 10 / 2;
		
		for(int i = 0; i< this.SIZE; i++) {
			for(int j = 0; j< this.SIZE; j++) {
				this.map[i][j] = new Rect(x, y, 50, 50, Color.green);
				x += 50;
			}
			x = 700 / 2 - 50 * 10 / 2;
			y += 50;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// map 그리기 
		for(int i = 0; i< this.SIZE; i++) {
			for(int j = 0; j< this.SIZE; j++) {
				Rect r = this.map[i][j];
				g.drawRect(r.getX() - 25, r.getY() - 25, r.getW(), r.getH());
				if(i == this.SIZE -1) {
					g.drawRect(r.getX() - 25, r.getY() - 25 + 50, r.getW(), r.getH());
				}
				if(j == this.SIZE -1) {
					g.drawRect(r.getX() - 25 + 50, r.getY() - 25, r.getW(), r.getH());
				}
				if(i == this.SIZE -1 && j == this.SIZE -1) {
					g.drawRect(r.getX() - 25 + 50, r.getY() - 25 + 50, r.getW(), r.getH());
				}
				
			}
		}
		
		// stone 그리기
		for(int i = 0; i< this.SIZE; i++) {
			for(int j = 0; j< this.SIZE; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() != 0) {
					g.setColor(Color.black);
					g.drawRoundRect(r.getX()+5, r.getY()+5, r.getW() - 10, r.getH() - 10, r.getW(), r.getH());
					g.setColor(r.getC());
					g.fillRoundRect(r.getX()+5, r.getY()+5, r.getW() - 10, r.getH() - 10, r.getW(), r.getH());
				}
			}
		}
		
		repaint(); // 다시 그리기
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("press");
		int x = e.getX();
		int y = e.getY();
		for(int i = 0; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE; j++) {
				Rect r = this.map[i][j];
				if(x >= r.getX() && x < r.getX() + r.getW() && y >= r.getY() && y < r.getY() + r.getW()) {
					if(r.getOwner() == 0) {
						r.setOwner(this.turn);
						r.setC(this.turn == 1 ? this.p1 : this.p2);
						
						checkWin();
						
						this.turn = this.turn == 1 ? 2 : 1;
					}
				}
			}
		}
	}


	private void checkWin() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		
		if(this.win != 0) {
			// alert result
			System.out.printf("p%d win!\n", this.win);
			new OmokResult02(String.format("p%d win!\n", this.win));
		}
	}


	private int checkReverse() {
		for(int i = 4; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE - 4; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k = 0; k < 5; k++) {
						if(this.map[i-k][j+k].getOwner() == this.turn) {
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
		for(int i = 0; i < this.SIZE - 4; i++) {
			for(int j = 0; j < this.SIZE - 4; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k = 0; k < 5; k++) {
						if(this.map[i+k][j+k].getOwner() == this.turn) {
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
		for(int i = 0; i < this.SIZE - 4; i++) {
			for(int j = 0; j < this.SIZE; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k = 0; k < 5; k++) {
						if(this.map[i + k][j].getOwner() == this.turn) {
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


	private int checkHori() {
		for(int i = 0; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE - 4; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k = 0; k < 5; k++) {
						if(this.map[i][j+k].getOwner() == this.turn) {
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


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class Omok2 extends JFrame{
	private Omok2Panel panel = new Omok2Panel();
	public Omok2() {
		super("Omok Game2");
		setLayout(null);
		setBounds(50,50,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}
	
}

public class Test_005_숙제마우스오목_정답 {

	public static void main(String[] args) {
		Omok2 game = new Omok2();
	}

}
