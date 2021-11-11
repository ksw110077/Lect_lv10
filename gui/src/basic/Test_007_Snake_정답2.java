package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SnakeRect03 {
	private int x, y, w, h;
	private Color c;

	public SnakeRect03(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

class SnakeGame03 extends JPanel implements ActionListener, KeyListener{

	private final int SIZE = 10;
	private SnakeRect03[][] map = new SnakeRect03[this.SIZE] [this.SIZE];
	
	private ArrayList<SnakeRect03> snake = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> yx = new ArrayList<>();
	
	private ArrayList<SnakeRect03> items = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> itemYx = new ArrayList<>();
	
	private final int LEFT = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int STOP = 4;
	private boolean death;
	private int dir;
	
	public SnakeGame03() {
		setLayout(null);
		setBounds(0,0,700,500);
		
		setMap();
		setSnake();
		setItems();
		setFocusable(true);
		addKeyListener(this);
	}
	
	
	private void setItems() {
		Random rn = new Random();
		int r = rn.nextInt(this.SIZE * this.SIZE / 5) + 5;
		
		for(int i =0; i < r; i ++) {
			int rY = rn.nextInt(this.SIZE); // 인덱스
			int rX = rn.nextInt(this.SIZE);
			
			boolean check = false;
			
			for(int j = 0; j < this.yx.size(); j++) {
				if(rY == this.yx.get(j).get(0) && rX == this.yx.get(j).get(1)) {
					check = true;
				}
				if(check) {
					i --;
					break;
				}
			}
			
			if(check) {
				continue;
			}
			for(int j = 0; j < this.itemYx.size(); j++) {
				if(rY == this.itemYx.get(j).get(0) && rX == this.itemYx.get(j).get(1)) {
					check = true;
				}
				if(check) {
					i --;
					break;
				}
			}
			if(!check) {
				SnakeRect03 t = this.map[rY][rX];
				this.items.add(new SnakeRect03(t.getX() + 10, t.getY() + 10, 20, 20, Color.green));
				
				ArrayList<Integer>  pair = new ArrayList<>();
				pair.add(rY);
				pair.add(rX);
				this.itemYx.add(pair);
			}
			System.out.println("items : " + this.items.size());
		}
	}


	private void setSnake() {
		for(int i = 0; i < 4; i ++) {
			SnakeRect03 t = this.map[0][i];
			Color c = Color.blue;
			if(i == 0) {
				c = Color.red;
			}
			this.snake.add(new SnakeRect03(t.getX(), t.getY(), t.getW(), t.getH(), c));
			
			// yx
			ArrayList<Integer> pair = new ArrayList<>();
			pair.add(0); // map의 y 인덱스
			pair.add(i); // map의 x 인덱스
			this.yx.add(pair);
		}
	}


	private void setMap() {
		int x = 500  / 2 - 40 *this.SIZE /2;
		int y = 500  / 2 - 40 *this.SIZE /2;
		
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE; j++) {
				this.map[i][j] = new SnakeRect03(x, y, 40, 40, Color.gray);
				x += 40;
			}
			x = 500  / 2 - 40 *this.SIZE /2;
			y += 40;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// snake
		for(int i =0; i < this.snake.size(); i++) {
			SnakeRect03 r = this.snake.get(i);
			Color c = r.getC();
			if(this.death) {
				c = Color.black;
			}
			g.setColor(c);
			g.fillRect(r.getX(), r.getY(), r.getW(), r.getH());
		}
		
		
		// item
		for(int i =0; i < this.items.size(); i++) {
				SnakeRect03 r = this.items.get(i);
				g.setColor(r.getC());
				g.fillRoundRect(r.getX(), r.getY(), r.getW(), r.getH(), r.getW(), r.getH());
		}
		

		// map
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				SnakeRect03 r = this.map[i][j];
				g.setColor(r.getC());
				g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
			}
		}
		
		requestFocusInWindow();
		repaint();
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_LEFT) {
			this.dir = this.LEFT;
		}
		if(e.getKeyCode() == e.VK_DOWN) {
			this.dir = this.DOWN;
		}
		if(e.getKeyCode() == e.VK_RIGHT) {
			this.dir = this.RIGHT;
		}
		if(e.getKeyCode() == e.VK_UP) {
			this.dir = this.UP;
		}
		move();
	}

	private void move() {
		int yy = this.yx.get(0).get(0);
		int xx = this.yx.get(0).get(1);
		
		// moving
		if(this.dir == this.LEFT) {
			xx --;
		}
		else if(this.dir == this.DOWN) {
			yy ++;
		}
		else if(this.dir == this.RIGHT) {
			xx ++;
		}
		else if(this.dir == this.UP) {
			yy --;
		}
		
		// check
		if(xx < 0 || xx >= this.SIZE || yy < 0 || yy >= this.SIZE) {
			return;
		}
		
		//bodyCheck
		for(int i = 0; i < this.yx.size(); i++) {
			if(yy == this.yx.get(i).get(0) && xx == this.yx.get(i).get(1)) {
				this.death = true;
			}
		}
		
		// item
		// 꼬리 증가, 아이템 삭제
		boolean isGrow = false;
		for(int i = 0; i < this.itemYx.size(); i++) {
			if(yy == this.itemYx.get(i).get(0) && xx == this.itemYx.get(i).get(1)) {
				isGrow = true;
				this.items.remove(i);
				this.itemYx.remove(i);
			}
		}
		
		// move
		if(!this.death) {
			SnakeRect03 tail = this.snake.get(this.snake.size() - 1);
			ArrayList<Integer> tailYx = this.yx.get(this.yx.size() - 1);
			
			// body
			for(int i = this.snake.size() - 1; i > 0; i --) {
				SnakeRect03 temp = this.snake.get(i - 1);
				temp.setC(Color.blue);
				this.snake.set(i, temp);
				
				ArrayList<Integer> pair = this.yx.get(i - 1);
				this.yx.set(i, pair);
			}
			
			// head
			
			SnakeRect03 t = this.map[yy][xx];
			this.snake.set(0, new SnakeRect03(t.getX(), t.getY(), t.getW(), t.getH(), Color.red));
			ArrayList<Integer> pair = new ArrayList<Integer>();
			
			pair.add(yy);
			pair.add(xx);
			this.yx.set(0, pair);
			
			if(isGrow) {
				this.snake.add(tail);
				this.yx.add(tailYx);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class Snake03 extends JFrame{
	public Snake03() {
		super("snake items");
		setLayout(null);
		setBounds(100,100,700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add (new SnakeGame03());
		
		setVisible(true);
		revalidate();
	}
}

public class Test_007_Snake_정답2 {

	public static void main(String[] args) {
		Snake03 game = new Snake03();
	}

}
