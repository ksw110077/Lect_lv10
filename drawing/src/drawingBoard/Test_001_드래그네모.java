package drawingBoard;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


class drawingNemo01{
	private int x, y, w, h;
	private Color c;
	
	public drawingNemo01(int x, int y, int w, int h) {
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

class drawingJPanel01 extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	
	private drawingNemo01 nemo = null;
	private drawingNemo01 nemo2 = null;
	private boolean isDrawing = false;
	private int pressY;
	private int pressX;
	
	public drawingJPanel01() {
		setLayout(null);
		setBounds(0,0,1000,1000);
		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.nemo != null) {
			g.setColor(this.nemo.getC());
			g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getW(), this.nemo.getH());
		}
		if(this.nemo2 != null) {
			g.setColor(this.nemo2.getC());
			g.drawRect(this.nemo2.getX(), this.nemo2.getY(), this.nemo2.getW(), this.nemo2.getH());
		}
		repaint();
	}
	
	// 프레스 한 지점 좌표 기준으로 드래그 중에 네모가 계속 새로 그려짐
	// 프레스 좌표 기준

	// 0,0
	//        -
	//     2ㅣ1
	// - --------- +
	//     3ㅣ4
	//       +

	// 1사분면 프레스 좌표 기준 x, y - H 가 기준 / x 크고, y 작을 때 // pressY - y; H = x - pressX;
	// 2사분면 프레스 좌표 기준 x - W, y - H 가 기준 / x,y 둘다  작을때 // 
	// 3사분면 프레스 좌표 기준 x - W, y 가 기준 / x 작고 , y 클때
	// 4사분면 프레스 좌표 기준 x, y 가 기준 / x,y 둘다 작을때

	//드래그 마다 네모 새로 그리기
	
	private void one(int y, int x) {
		int gapW = x - this.pressX;
		int gapH = this.pressY - y;
		this.nemo = new drawingNemo01(this.pressX, this.pressY - gapH, gapW, gapH);
	}
	
	private void two(int y, int x) {
		int gapW = this.pressX - x;
		int gapH = this.pressY - y;
		this.nemo = new drawingNemo01(this.pressX - gapW, this.pressY - gapH, gapW, gapH);
	}
	
	private void three(int y, int x) {
		int gapW = this.pressX - x;
		int gapH = y - this.pressY;
		this.nemo = new drawingNemo01(this.pressX - gapW, this.pressY, gapW, gapH);
	}
	
	private void four(int y, int x) {
		int gapW = x - this.pressX;
		int gapH = y - this.pressY;
		this.nemo = new drawingNemo01(this.pressX, this.pressY, gapW, gapH);
	}
	
	// 0,0
	//        -
	//     2ㅣ1
	// - --------- +
	//     3ㅣ4
	//       +
	private void drawing(int y, int x) {
		if(this.isDrawing) {
			if(x >= this.pressX && y <= this.pressY) { // 1 사분면
				one(y, x);
			}
			else if(x <= this.pressX && y <= this.pressY) { // 2 사분면
				two(y, x);
			}
			else if(x <= this.pressX && y >= this.pressY) { // 3 사분면
				three(y, x);
			}
			else if(x >= this.pressX && y >= this.pressY) { // 4 사분면
				four(y, x);
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// 업데이트 계속
		
		int y = e.getY();
		int x = e.getX();
		
		drawing(y, x);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 기준값 전역변수로 업데이트
		this.nemo = null;
		this.isDrawing = true;
		this.pressY = e.getY();
		this.pressX = e.getX();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// 그림 업데이트 종료
		this.nemo2 = this.nemo;
		this.isDrawing = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class drawingJFrame01 extends JFrame{
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private final int W = this.dm.width;
	private final int H = this.dm.height;
	
	public drawingJFrame01() {
		setTitle("Drawing Board");
		setLayout(null);
		setBounds(this.W / 2 - 500,this.H / 2 - 500,1100,1100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new drawingJPanel01());
		
		setVisible(true);
		revalidate();
	}
}

public class Test_001_드래그네모 {

	public static void main(String[] args) {
		drawingJFrame01 d = new drawingJFrame01();

	}

}
