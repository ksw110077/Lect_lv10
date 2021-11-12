package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//MouseMotionListener를 활용해서 - >
//사각형 객체를 클릭 -> 드래그 하는 동안
//사각형이 마우스를 따라서 -> 무빙

class TestMMLRect {
	private int x, y,w, h;
	private Color c;
	
	public TestMMLRect(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
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
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	
}

class TestMMLPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	private TestMMLRect rect;
	private int [] rectYx = new int [2];
	private int [] dragYx = new int [2];
	private boolean isMoving;
	
	public TestMMLPanel() {
		setLayout(null);
		setBounds(0,0,500,500);
		setBackground(Color.orange);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setRect();
	}

	private void setRect() {
		this.rect = new TestMMLRect(225, 225, 50, 50, Color.white);
		this.rectYx[0] = this.rect.getY();
		this.rectYx[1] = this.rect.getX();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(this.rect.getC());
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
		
		repaint();
	}
	
	private void moving(int moveY, int moveX) {
		if(this.isMoving) {
			this.rectYx[0] = this.rectYx[0] + moveY;
			this.rectYx[1] = this.rectYx[1] + moveX;
			
			this.rect = new TestMMLRect(this.rectYx[1], this.rectYx[0], this.rect.getW(), this.rect.getH(), this.rect.getC());
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		int y = e.getY();
		int x = e.getX();
		// 이동 좌표 - 이전 좌표 = 이동한 좌표 값
		
		int befoY = this.dragYx[0];
		int befoX = this.dragYx[1];
		this.dragYx[0] = y;
		this.dragYx[1] = x;
		
		int moveY = y - befoY;
		int moveX = x - befoX;
		
		moving(moveY, moveX);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int y = e.getY();
		int x = e.getX();
		if(y > this.rectYx[0] && y < this.rectYx[0] + this.rect.getH() 
		&& x > this.rectYx[1] && x < this.rectYx[1] + this.rect.getW()) {
			this.dragYx[0] = y;
			this.dragYx[1] = x;
			this.isMoving = true;
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

class TestMML extends JFrame{
	public TestMML() {
		setTitle("Mouse Motion Listener");
		setLayout(null);
		setBounds(100,100,600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new TestMMLPanel());
		
		setVisible(true);
		revalidate();
	}
}

public class Test_008_사각형드래그 {

	public static void main(String[] args) {
		TestMML m = new TestMML();
	}

}
