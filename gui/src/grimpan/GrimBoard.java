package grimpan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class GrimBoard extends MyUtil{
	private ArrayList<GrimRect> rects = new ArrayList<>();
	private ArrayList<GrimRect> circles = new ArrayList<>();
	
	private GrimRect rect = null;
	private int startX, startY;
	private boolean shift;
	
	private final int RECTANGLE = 0;
	private final int CIRCLE = 1;
	private final int TRIANGLE = 2;
	
	private String [] btnText = {"□","○","△"};
	private JButton []  btn = new JButton[3];
	private int type;
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0,0,700,700);
		
		setButton();
		
		addMouseListener(this);
		addMouseMotionListener(this);

		setFocusable(true);
		addKeyListener(this);
		
	}
	
	private void setButton() {
		int x = 30;
		int y = 50;
		for(int i = 0; i < this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x, y, 50, 50);
			this.btn[i].setText(this.btnText[i]);
			this.btn[i].addActionListener(this);
			
			add(this.btn[i]);
			
			y += 50 + 3;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// sample triangle
		// g.drawPolygon(int[], int[], int)
		// 1. x 좌표의 배열
		// 2. y 좌표의 배열
		// 3. 좌표 개수 (꼭지점 개수)
		int [] xx = new int[3];
		int [] yy = new int[3];
		xx[0] = 100;
		yy[0] = 100;
		xx[1] = 150;
		yy[1] = 200;
		xx[2] = 50;
		yy[2] = 200;
		g.setColor(Color.GREEN);
		g.drawPolygon(xx, yy, 3);
		
		// this.rect : 임시 객체 - > 타입에 따라 다르게
		if(this.rect != null) {
			g.setColor(this.rect.getC());

			if(this.type == this.RECTANGLE) {
				g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
			}
			else if (this.type == this.CIRCLE) {
				g.drawRoundRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH(), this.rect.getW(), this.rect.getH());
			}
			else if (this.type == this.TRIANGLE) {
				
			}
		}
		
		
		// rects
		for(int i =0; i < this.rects.size(); i++) {
			GrimRect r = this.rects.get(i);
			g.setColor(r.getC());
			g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
		}
		// circles
		
		for(int i =0; i < this.circles.size(); i++) {
			GrimRect r = this.circles.get(i);
			g.setColor(r.getC());
			g.drawRoundRect(r.getX(), r.getY(), r.getW(), r.getH(), r.getW(), r.getH());
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
		
		if(this.type == this.RECTANGLE) {
			this.rects.add(this.rect);
		}
		else if (this.type == this.CIRCLE) {
			this.circles.add(this.rect);
		}
		else if (this.type == this.TRIANGLE) {
		}
		this.rect = null;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btn[this.RECTANGLE]) {
			this.type = this.RECTANGLE;
		}
		else if(e.getSource() == this.btn[this.CIRCLE]) {
			this.type = this.CIRCLE;
		}
		else if(e.getSource() == this.btn[this.TRIANGLE]) {
			this.type = this.TRIANGLE;
		}
	}
}
