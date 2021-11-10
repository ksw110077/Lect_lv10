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


class PushNemo02{
	private int x, y, w, h;
	private Color c;
	public PushNemo02(int x, int y, int w, int h) {
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

class PushPanel02 extends JPanel implements ActionListener, MouseListener{
	
	private final int LEFT = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	
	private final int SIZE = 700;
	
	private JButton [] btn = new JButton[4];
	
	private PushNemo02 nemo1 = null;
	private PushNemo02 nemo2 = null;
	
	private int dir; // 0Left 1Down 2Right 3Up
	private boolean isMoving;
	
	public PushPanel02() {
		setLayout(null);
		setBounds(0,0,this.SIZE, this.SIZE);
		setBackground(Color.white);
		this.dir = 5;
		setBtn();
		setNemo();
	}

	private void setNemo() {
		Random rn = new Random();
		int rX = rn.nextInt(this.SIZE - 100);
		int rY = rn.nextInt(this.SIZE - 100);
		
		this.nemo1 = new PushNemo02(rX, rY, 100, 100);
		
		// nemo2는 nemo1과 겹쳐지지 않도록 중복 확인
		
		while(true) {
			rX = rn.nextInt(this.SIZE - 100 - 100);
			rY = rn.nextInt(this.SIZE - 100 - 100);
			
			// 검증
			
			if(rX < this.nemo1.getX() || rX > this.nemo1.getX() + this.nemo1.getW() 
			|| rY < this.nemo1.getY() || rY > this.nemo1.getY() + this.nemo1.getH()) {
				break;
			}
		}
		this.nemo2 = new PushNemo02(rX, rY, 100, 100);
	}
	
	private void setBtn() {
		String []  text = {"←","↓","→","↑"};
		int x = 500;
		int y = 550;
		for(int i =0; i < 4; i++) {
			JButton bt = new JButton();
			bt.setBounds(x,y,50,50);
			bt.setText(text[i]);
//			bt.addActionListener(this); // this : bt
			bt.addMouseListener(this);
			add(bt);
			
			this.btn[i] = bt;

			x += 50;
			if(i == this.btn.length -2) {
				x -= 100;
				y -= 50;
			}
			
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		// draw Rect
		if(this.nemo1 != null && this.nemo2 != null) {
			g.setColor(Color.black);
			g.drawRect(this.nemo1.getX(), this.nemo1.getY(), this.nemo1.getW(), this.nemo1.getH());

			g.setColor(Color.blue);
			g.drawRect(this.nemo2.getX(), this.nemo2.getY(), this.nemo2.getW(), this.nemo2.getH());
		}
		
		if(isMoving) {
			update();
		}
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("press");
		this.isMoving = true;
		
		// check Button
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.btn[this.LEFT]) {
				this.dir = this.LEFT;
			}
			else if(target == this.btn[this.DOWN]) {
				this.dir = this.DOWN;
			}
			else if(target == this.btn[this.RIGHT]) {
				this.dir = this.RIGHT;
			}
			else if(target == this.btn[this.UP]) {
				this.dir = this.UP;
			}
		}
		
	}

	private void update() {
		if(this.dir == this.LEFT) {
			if(this.nemo1.getX() > 0 ) {
				this.nemo1.setX(this.nemo1.getX() -1);
			}
		}
		if(this.dir == this.DOWN) {
			if(this.nemo1.getY() < this.SIZE - this.nemo1.getH()) {
				this.nemo1.setY(this.nemo1.getY() +1);
			}
		}
		if(this.dir == this.RIGHT) {
			if(this.nemo1.getX() < this.SIZE - this.nemo1.getW() ) {
				this.nemo1.setX(this.nemo1.getX() +1);
			}
		}
		if(this.dir == this.UP) {
			if(this.nemo1.getY() > 0) {
				this.nemo1.setY(this.nemo1.getY() -1);
			}
		}
		checkSecond();
//		try {
//			Thread.sleep(5);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}

	private void checkSecond() {
		if (this.dir == this.LEFT) {
			if (this.nemo2.getX() + this.nemo2.getW() == this.nemo1.getX()
					&& this.nemo2.getY() > this.nemo1.getY() - this.nemo1.getH()
					&& this.nemo2.getY() < this.nemo1.getY() + this.nemo1.getH()) {
				this.nemo2.setX(this.nemo2.getX() - 1);
			}
		} else if (this.dir == this.DOWN) {
			if (this.nemo2.getY() == this.nemo1.getY() + this.nemo1.getH()
					&& this.nemo2.getX() > this.nemo1.getX() - this.nemo1.getW()
					&& this.nemo2.getX() < this.nemo1.getX() + this.nemo1.getW()) {
				this.nemo2.setY(this.nemo2.getY() + 1);
			}
		} else if (this.dir == this.RIGHT) {
			if (this.nemo2.getX() == this.nemo1.getX() + this.nemo1.getW()
					&& this.nemo2.getY() > this.nemo1.getY() - this.nemo1.getH()
					&& this.nemo2.getY() < this.nemo1.getY() + this.nemo1.getH()) {
				this.nemo2.setX(this.nemo2.getX() + 1);
			}
		} else if (this.dir == this.UP) {
			if (this.nemo2.getY() + this.nemo2.getH() == this.nemo1.getY()
					&& this.nemo2.getX() > this.nemo1.getX() - this.nemo1.getW()
					&& this.nemo2.getX() < this.nemo1.getX() + this.nemo1.getW()) {
				this.nemo2.setY(this.nemo2.getY() - 1);
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

class PushFrame02 extends JFrame{
	public PushFrame02() {
		setLayout(null);
		setTitle("Push Push");
		setBounds(50,50,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		add(new PushPanel02());
		
		
		setVisible(true);
		revalidate();
	}
	
}

public class Test_006_PushPush_정답 {

	public static void main(String[] args) {
		PushFrame02 game = new PushFrame02();
	}

}
