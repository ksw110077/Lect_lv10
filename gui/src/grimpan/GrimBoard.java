package grimpan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GrimBoard extends MyUtil{
	
	private GrimRect rect = null;
	private int startX, startY;
	private boolean shift;
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0,0,700,700);
		
		addMouseListener(this);
		addMouseMotionListener(this);

		setFocusable(true);
		addKeyListener(this);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.rect != null) {
			g.setColor(this.rect.getC());
			g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
		}
		
		requestFocusInWindow();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) {
			this.shift = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) {
			this.shift = false;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.startX = e.getX();
		this.startY = e.getY();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.rect.setC(Color.blue);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		// 절대값 구하는 메소드  Math.abs
		int w = Math.abs(x - this.startX);
		int h = Math.abs(y - this.startY);
		
		if(this.shift) {
			w = h;
		}
		
		
		int rX = this.startX;
		int rY = this.startY;
		
		if(x < this.startX) {
			rX = this.startX - w;
		}
		if(y < this.startY) {
			rY =this.startY - h;
		}
		this.rect = new GrimRect(rX, rY, w, h, Color.red);
	}
}
