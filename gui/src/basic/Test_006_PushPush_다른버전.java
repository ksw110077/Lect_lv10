package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class PushNemo03{
	private int x, y, w, h;
	private Color c;
	public PushNemo03(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = Color.black;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class PushPanel03 extends JPanel implements MouseListener{
	private final int UP = 0;
	private final int LEFT = 1;
	private final int DOWN = 2;
	private final int RIGHT = 3;
	
	private final int SIZE = 800;
	
	private PushNemo03 move;
	private PushNemo03 stop;
	private JButton[] arrow = new JButton[4];
	
	private int dir;
	private boolean isMoving = false;
	
	public PushPanel03() {
		setLayout(null);
		setBounds(0,0,this.SIZE, this.SIZE);
		setBackground(Color.white);
		this.dir = 5;
		setArrow();
		setNemo();
	}
	
	private void setNemo() {
		Random rn = new Random();
		int rX = rn.nextInt(this.SIZE - 100);
		int rY = rn.nextInt(this.SIZE - 100);
		
		this.stop = new PushNemo03(rX, rY, 100, 100);
		
		while(true) {
			rX = rn.nextInt(this.SIZE - 100);
			rY = rn.nextInt(this.SIZE - 100);
			
			boolean check = false;
			// 검증
			if(rX < this.stop.getX() || rX >= this.stop.getX() + this.stop.getW() 
			&&rY < this.stop.getY() || rY >= this.stop.getY() + this.stop.getH()) {
				check = true;
			}
			
			if(check) {
				this.move = new PushNemo03(rX, rY, 100, 100);
				break;
			}
		}
	}
	
	private void setArrow() {
		int x = PushFrame.SIZE - 50 - 50 - 10 - 50;
		int y = PushFrame.SIZE - 50 - 50 - 10 - 50;
		for(int i =0; i < this.arrow.length; i++) {
			this.arrow[i] = new JButton();
			if(i == 0) {
				this.arrow[i].setText("↑");
				this.arrow[i].setBounds(x, y, 50, 50);
				x += - 10 - 50;
				y += 50 + 10;
			}
			else {
				if(i == 1) {
					this.arrow[i].setText("←");
					this.arrow[i].setBounds(x, y, 50, 50);
					x += 50 + 10;
				}
				else if (i == 2) {
					this.arrow[i].setText("↓");
					this.arrow[i].setBounds(x, y, 50, 50);
					x += 50 + 10;
				}
				else if (i == 3) {
					this.arrow[i].setText("→");
					this.arrow[i].setBounds(x, y, 50, 50);
				}
			}
			this.arrow[i].setFont(new Font("NanumGothicBold",Font.PLAIN, 17));
			this.arrow[i].setBackground(new Color(249, 243, 223));
			this.arrow[i].addMouseListener(this);
			add(this.arrow[i]);
		}
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.move != null && this.stop != null) {
			g.setColor(Color.black);
			g.drawRect(this.move.getX(), this.move.getY(), this.move.getW(), this.move.getH());
			
			g.setColor(Color.blue);
			g.drawRect(this.stop.getX(), this.stop.getY(), this.stop.getW(), this.stop.getH());
		}
		
		if(isMoving) {
			update();
		}
		
		repaint();
	}
	
	
	private void update() {
		if(this.dir == this.UP) {
			up();
		}
		else if(this.dir == this.LEFT) {
			left();
		}
		else if(this.dir == this.DOWN) {
			down();
		}
		else if(this.dir == this.RIGHT) {
			right();
		}
	}
	
	private boolean chkOverlapUP() {
		boolean chk = false;
		// stop 아래쪽과  move 윗쪽 닿을때
		// stop.getY() + stop.getH() == move.getY();
		int mX = this.move.getX();
		int mY = this.move.getY();
		int mW = this.move.getW();
		int mH = this.move.getH();
		
		int sX = this.stop.getX();
		int sY = this.stop.getY();
		int sW = this.stop.getW();
		int sH = this.stop.getH();
		
		if(sY + sH == mY && ((mX >= sX && mX < sX + sW) || (mX + mW > sX && mX + mW <= sX + sW))) {
			chk = true;
		}
		return chk;
	}
	
	
	private boolean chkOverlapLeft() {
		boolean chk = false;
		
		// stop 오른쪽과 move 왼쪽 닿을때
		// stop.getX() + stop.getW() == move.getX();
		
		int mX = this.move.getX();
		int mY = this.move.getY();
		int mW = this.move.getW();
		int mH = this.move.getH();
		
		int sX = this.stop.getX();
		int sY = this.stop.getY();
		int sW = this.stop.getW();
		int sH = this.stop.getH();
		
		if(sX + sW == mX && ((mY >= sY && mY < sY + sH) || (mY + mH > sY && mY + mH <= sY + sH))) {
			chk = true;
		}
		return chk;
	}
	
	private boolean chkOverlapDown() {
		boolean chk = false;
		// stop 윗쪽과 move 아래쪽 닿을때
		// stop.getY() == move.getY() + move.getH() ;
		
		int mX = this.move.getX();
		int mY = this.move.getY();
		int mW = this.move.getW();
		int mH = this.move.getH();
		
		int sX = this.stop.getX();
		int sY = this.stop.getY();
		int sW = this.stop.getW();
		int sH = this.stop.getH();
		
		if(sY == mY + mH && ((mX >= sX && mX < sX + sW) || (mX + mW > sX && mX + mW <= sX + sW))) {
			chk = true;
		}
		return chk;
	}
	
	private boolean chkOverlapRight() {
		boolean chk = false;
		// stop 왼쪽과 move 오른쪽 닿을때
		// stop.getX() == move.getX() + move.getW();
		
		int mX = this.move.getX();
		int mY = this.move.getY();
		int mW = this.move.getW();
		int mH = this.move.getH();
		
		int sX = this.stop.getX();
		int sY = this.stop.getY();
		int sW = this.stop.getW();
		int sH = this.stop.getH();
		
		if(sX == mX + mW && ((mY >= sY && mY < sY + sH) || (mY + mH > sY && mY + mH <= sY + sH))) {
			chk = true;
		}
		
		return chk;
	}
	
	private void up() {
		if(chkOverlapUP()) { // 같이 이동
			if(this.stop.getY() > 0) {
				this.move.setY(this.move.getY() - 1);
				this.stop.setY(this.stop.getY() - 1);
			}
		}
		else { // move만 이동
			if(this.move.getY() > 0) {
				this.move.setY(this.move.getY() - 1);
			}
		}
	}
	
	private void left() {
		if(chkOverlapLeft()) { // 같이 이동
			if(this.stop.getX() > 0) {
				this.move.setX(this.move.getX() - 1);
				this.stop.setX(this.stop.getX() - 1);
			}
		}
		else { // move만 이동
			if(this.move.getX() > 0) {
				this.move.setX(this.move.getX() - 1);
			}
		}
	}
	
	private void down() {
		if(chkOverlapDown()) { // 같이 이동
			if(this.stop.getY() + this.stop.getH() < this.SIZE) {
				this.move.setY(this.move.getY() + 1);
				this.stop.setY(this.stop.getY() + 1);
			}
		}
		else { // move만 이동
			if(this.move.getY() + this.move.getH() < this.SIZE) {
				this.move.setY(this.move.getY() + 1);
			}
		}
	}
	
	private void right() {
		if(chkOverlapRight()) { // 같이 이동
			if(this.stop.getX() + this.stop.getH() < this.SIZE) {
				this.move.setX(this.move.getX() + 1);
				this.stop.setX(this.stop.getX() + 1);
			}
		}
		else { // move만 이동
			if(this.move.getX() + this.move.getH() < this.SIZE) {
				this.move.setX(this.move.getX() + 1);
			}
		}
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			this.isMoving = true;
			
			if(target == this.arrow[this.UP]) {
				this.dir = this.UP;
			}
			else if(target == this.arrow[this.LEFT]) {
				this.dir = this.LEFT;
			}
			else if(target == this.arrow[this.DOWN]) {
				this.dir = this.DOWN;
			}
			else if(target == this.arrow[this.RIGHT]) {
				this.dir = this.RIGHT;
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		this.isMoving = false;
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

class PushFrame03 extends JFrame{
	private PushPanel03 panel = new PushPanel03();
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private final int W = dm.width;
	private final int H = dm.height;
	public static final int SIZE = 900;
	
	public PushFrame03() {
		setLayout(null);
		setTitle("Push Push");
		setBounds(W / 2 - this.SIZE / 2, H / 2 - this.SIZE / 2, this.SIZE, this.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
	}
	
}

public class Test_006_PushPush_다른버전 {

	public static void main(String[] args) {
		PushFrame03 game = new PushFrame03();
	}

}
