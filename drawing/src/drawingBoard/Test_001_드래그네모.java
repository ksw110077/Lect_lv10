package drawingBoard;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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

//좌표 반전 조심
//
//드래그 마다 네모 새로 그리기

class drawingJPanel01 extends JPanel implements MouseListener, MouseMotionListener{
	
	private drawingNemo01 nemo = null;
	
	public drawingJPanel01() {
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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

class drawingJFrame01 extends JFrame{
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private final int W = this.dm.width;
	private final int H = this.dm.height;
	
	public drawingJFrame01() {
		setTitle("Drawing Board");
		setLayout(null);
		setBounds(this.W / 2 - 500,this.H / 2 - 500,1100,1100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
	}
}

public class Test_001_드래그네모 {

	public static void main(String[] args) {
		drawingJFrame01 d = new drawingJFrame01();

	}

}
