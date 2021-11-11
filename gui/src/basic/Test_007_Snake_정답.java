package basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SnakeRect01 {
	private int x, y, w, h;
	private Color c;

	public SnakeRect01(int x, int y, int w, int h, Color c) {
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

class Game001 extends JPanel implements KeyListener, ActionListener {

	private final int SIZE = 10;
	private SnakeRect01[][] map = new SnakeRect01[this.SIZE][this.SIZE];

	private SnakeRect01[] snake = new SnakeRect01[4]; // <- ArrayList<SnakeRect01>
	private int[][] yx = new int[4][2]; // map 인데스 좌표 정보를 기억
										// {{y,x},{y,x},{y,x},{y,x}};

	private ArrayList<SnakeRect01> snake2 = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> yx2 = new ArrayList<>();
	
	private ArrayList<SnakeRect01> items = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> itemYx = new ArrayList<>();
	
	
	private final int LEFT = 0;
	private final int DOWN = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int STOP = 4;
	
	
	private JButton [] btn = new JButton[4];
	private JButton reset = new JButton();
	
	private int dir;
	
	public Game001() {
		setLayout(null);
		setBounds(0, 0, 700, 500);

		setMap();
		setSnake();
		setButton();

		setFocusable(true); // KeyListener 사용할 때 필요
		addKeyListener(this);
	}

	private void setButton() {
		String [] text = {"←","↓","→","↑"};
		
		int x = 700 - 200;
		int y = 500 - 200;
		
		for(int i = 0; i < this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x, y, 50, 50);
			this.btn[i].setText(text[i]);
			this.btn[i].addActionListener(this);
			add(this.btn[i]);
			
			x += 50;
			
			if(i == this.btn.length -1 -1) {
				x -= 100;
				y -= 50;
			}
		}
		
		this.reset = new JButton();
		this.reset.setBounds(700 - 200, y + 110, 150, 50);
		this.reset.setText("RESET");
		this.reset.addActionListener(this);
		add(this.reset);
	}

	private void setSnake() {
		for(int i = 0; i < this.snake.length; i ++) {
			SnakeRect01 temp  = this.map[0][i];
			int x = temp.getX();
			int y = temp.getY();
			int w = temp.getW();
			int h = temp.getH();
			Color c = Color.pink;
			if(i == 0 ) {
				c = Color.red;
			}
			this.snake[i] = new SnakeRect01(x, y, w, h, c);
			
			// 좌표기억
			int [] pair = new int [2]; 
			pair[0] = 0;	// map *배열*의 인덱스 y
			pair[1] = i; 	// map *배열*의 인덱스 x
			this.yx[i] = pair;
		}
	}

	private void setMap() {
		this.dir = this.STOP;
		
		int x = (700 - 200) / 2 - 40 * this.SIZE / 2;
		int y = 500 / 2 - 40 * this.SIZE / 2;
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				this.map[i][j] = new SnakeRect01(x, y, 40, 40, Color.gray);
				x += 40;
			}
			x = (700 - 200) / 2 - 40 * this.SIZE / 2;
			y += 40;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());

		if (e.getKeyCode() == e.VK_ENTER) { // 키 고유값이 elements 상수로 저장되어 있음
			System.out.println("enter");
		}

		if (e.getKeyCode() == e.VK_LEFT) {
			this.dir = this.LEFT;
		}
		else if (e.getKeyCode() == e.VK_DOWN) {
			this.dir = this.DOWN;
		}
		else if (e.getKeyCode() == e.VK_RIGHT) {
			this.dir = this.RIGHT;
		}
		else if (e.getKeyCode() == e.VK_UP) {
			this.dir = this.UP;
		}
		
		// update();
		if (this.dir != this.STOP) {
			update();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = this.STOP;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		// snake
		for(int i = 0; i < this.snake.length; i++) {
			SnakeRect01 r = this.snake[i];
			g.setColor(r.getC());
			g.fillRect(r.getX(), r.getY(), r.getW(), r.getH());
		}
		
		// map
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				SnakeRect01 r = this.map[i][j];
				g.setColor(r.getC());
				g.drawRect(r.getX(), r.getY(), r.getW(), r.getH());
			}
		}

		requestFocusInWindow(); // ← 키 리스너를 위한 포커스를 재 요청
		repaint();
	}

	private void update() {
		int yy = this.yx[0][0];
		int xx = this.yx[0][1];
		
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
		
		if(xx < 0 || xx >= this.SIZE || yy < 0 || yy >= this.SIZE) {
			return;
		}
		
		boolean bodyCheck = false;
		for(int i = 0; i < this.yx.length; i++) {
			if(yy == this.yx[i][0] && xx == this.yx[i][1]) {
				bodyCheck = true;
			}
		}
		
		if(bodyCheck) {
			return;
		}
		
		// move
		
		// body
		for(int i = this.snake.length - 1; i > 0; i--) {
			this.snake[i] = this.snake[i - 1];
			this.snake[i].setC(Color.pink);
			
			// 좌표 갱신
//			this.yx[i] = this.yx[i - 1]; // <<  주소 공유 해서 오류
			int [] pair = new int [2];
			pair[0] = this.yx[i-1][0];
			pair[1] = this.yx[i-1][1];
			this.yx[i] = pair;
		}
		
		// head
		SnakeRect01 t = this.map[yy][xx];
		this.snake[0] = new SnakeRect01(t.getX(), t.getY(), t.getW(), t.getH(), Color.red);
		// 좌표 갱신
		this.yx[0][0] = yy; // <
		this.yx[0][1] = xx;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.reset) {
				resetMap();
			}
			else {
				if(target == this.btn[LEFT]) {
					this.dir = LEFT;
				}
				else if(target == this.btn[DOWN]) {
					this.dir = DOWN;
				}
				else if(target == this.btn[RIGHT]) {
					this.dir = RIGHT;
				}
				else if(target == this.btn[UP]) {
					this.dir = UP;
				}
				// update();
				if(this.dir != this.STOP) {
					update();
				}
			}
		}
	}

	private void resetMap() {
		// TODO Auto-generated method stub
		
	}

}

class Snake01 extends JFrame {
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
