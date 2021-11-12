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

class TestMMLPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	private TestMMLRect rect;
	private boolean isMoving;
	private int mouseY;
	private int mouseX;
	
	
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
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(this.rect.getC());
		g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
		
		repaint();
	}
	
	private void moving(int moveY, int moveX) {
		this.rect.setC(Color.red);
		this.rect.setX(this.rect.getX() + moveX);
		this.rect.setY(this.rect.getY() + moveY);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// 이동 좌표 - 이전 좌표 = 이동한 좌표 값
		System.out.printf("%d : %d\n" , this.mouseY, this.mouseX);
		int moveY = e.getY() - this.mouseY;
		int moveX = e.getX() - this.mouseX;
		this.mouseY = e.getY(); // 이동 좌표
		this.mouseX = e.getX(); // 이동 좌표
		if(this.isMoving) {
			moving(moveY, moveX);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseY = e.getY();
		this.mouseX = e.getX();
		System.out.printf("%d / %d\n" , this.mouseY, this.mouseY);
		if(this.mouseY > this.rect.getY() && this.mouseY < this.rect.getY() + this.rect.getH() 
		&& this.mouseX > this.rect.getX() && this.mouseX < this.rect.getX() + this.rect.getW()) {
			this.isMoving = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.isMoving = false;
		this.rect.setC(Color.white);
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
