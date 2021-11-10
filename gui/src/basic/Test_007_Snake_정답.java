package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnakeRect01{
	private int x, y, w, h;
	private Color c;
	public SnakeRect01(int x, int y, int w, int h, Color c) {
		
	}
}


class Game001 extends JPanel implements KeyListener{
	public Game001() {
		setLayout(null);
		setBounds(0,0,700,500);
		
		setFocusable(true); // KeyListener 사용할 때 필요
		addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		
		if(e.getKeyCode() == e.VK_ENTER) { // 키 고유값이 elements 상수로 저장되어 있음
			System.out.println("enter");
		}
		
		if(e.getKeyCode() == e.VK_LEFT) {}
		if(e.getKeyCode() == e.VK_DOWN) {}
		if(e.getKeyCode() == e.VK_RIGHT) {}
		if(e.getKeyCode() == e.VK_UP) {}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		requestFocusInWindow(); // ← 키 리스너를 위한 포커스를 재 요청
		repaint();
	}
	
}

class Snake01 extends JFrame{
	public Snake01() {
		super("snake");
		setLayout(null);
		setBounds(100, 100, 700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Game001());
		
		setVisible(true);
		revalidate();
	}
}

public class Test_007_Snake_정답 {

	public static void main(String[] args) {
		Snake01 game = new Snake01();
	}

}
