package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

// paintComponent() 메소드를 활용한 사각형 그리기
class Nemo{
	private int x, y,width, height;
	private Color c;
	
	public Nemo(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class MyPanel03 extends JPanel implements MouseListener{
	 
//	private Nemo nemo = new Nemo();
	private Nemo[][] map = new Nemo[3][3];
	
	public MyPanel03() {
		setLayout(null);
		setBounds(0,0,500,400);
		setBackground(Color.orange);
		
//		this.nemo.setX(100);
//		this.nemo.setY(100);
//		this.nemo.setWidth(200);
//		this.nemo.setHeight(200);
//		this.nemo.setC(Color.blue);
		
		setMap();
		
		// 패널에 혹은 특정하는 컴포넌트에 -> 마우스 리스너를 달 수 있다.
		addMouseListener(this); // this : MyPanel
		
	}
	
	private void setMap() {
		int x = 50;
		int y = 50;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.map[i][j] = new Nemo(x, y, 50, 50, Color.red);
				x += 50;
			}
			x = 50;
			y += 50;
		}
		
	}
	// paintComponent() 메소드 오버라이딩(JComponent로);
	
	@Override
	protected void paintComponent(Graphics g) { // JComponent, 페인트 스레드가 돌고 있음
		// 화면상 그래픽을 그려주는 역할
		super.paintComponent(g); // 그래픽을 지움 (갱신)
		
		
//		g.setColor(Color.red);
//		g.drawRect(100, 100, 100, 100); // 좌표 x,좌표 y, 가로 , 세로
//		g.fillRect(100, 100, 100, 100);
		
//		g.setColor(this.nemo.getC());
//		g.drawRect(this.nemo.getX(), this.nemo.getY(), this.nemo.getWidth(), this.nemo.getHeight());

		// draw Map
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Nemo temp = this.map[i][j];
				g.setColor(temp.getC());
				// 네모 그리기
//				g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				// 원 그리기
				g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(),temp.getWidth(), temp.getHeight());
			}
		}
		
		
		// 다시 그리기
		// 갱신된 값에 대해서는 repaint(); 해줘야 함
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("클릭!");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("클-");
		
		int x = e.getX();
		int y = e.getY();
		
		System.out.println(x + " / " + y);
		
		
//		if(x >= this.nemo.getX() && x < this.nemo.getX() + this.nemo.getWidth()
//		&& y >= this.nemo.getY() && y < this.nemo.getY() + this.nemo.getHeight()) {
//			this.nemo.setC(Color.red);
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("릭");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("hello");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("bye");
	}
}

class MyFrame03 extends JFrame{
	public MyFrame03() {
		setLayout(null);
		setTitle("MyFrame");
		setBounds(50,50,500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 종료 조건
		
		add(new MyPanel03());
		
		setVisible(true);
		revalidate(); // 갱신
	}
}

public class Ex_003 {

	public static void main(String[] args) {
		MyFrame03 mf = new MyFrame03();
	}

}
