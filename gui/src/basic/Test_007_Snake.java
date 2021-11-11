package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SnakeResultFrame extends JFrame {
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private final int W = this.dm.width;
	private final int H = this.dm.height;
	private final int SIZE = 500;
	private JLabel title = new JLabel();

	public SnakeResultFrame() {
		setLayout(null);
		setBounds(this.W / 2 - 250, this.H / 2 - 125, this.SIZE, this.SIZE - 250);
		setBackground(Color.white);
		setTitle();
		setVisible(true);
		revalidate();
	}

	private void setTitle() {
		this.title.setBounds(0, 0, this.SIZE, this.SIZE - 250);
		this.title.setText("GAME OVER");
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.CENTER);
		this.title.setForeground(new Color(207, 183, 132));
		this.title.setFont(new Font("NanumGothicBold", Font.BOLD, 50));
		add(this.title);
	}
}

class SnakeRect {
	private int x, y, w, h;
	private Color c;

	public SnakeRect(int x, int y, int w, int h) {
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

class SnakePanel extends JPanel implements KeyListener, Runnable, ActionListener {
	private Random rn = new Random();

	private final int UP = 0;
	private final int LEFT = 1;
	private final int DOWN = 2;
	private final int RIGHT = 3;

	private final int SIZE = 800;

	private final int MAPSIZE = 10;
	private final int FIRSTSNAKESIZE = 4;
	private final int ITEMCNT = 10;
	private final int WALL00 = 150;

	private JLabel title = new JLabel();
	private JButton reset = new JButton();
	private JButton start = new JButton();

	private SnakeRect[][] map = new SnakeRect[this.MAPSIZE][this.MAPSIZE];
	private ArrayList<SnakeRect> snake;
	private ArrayList<SnakeRect> item;

	private Color snakeHC = new Color(110, 203, 99);
	private Color snakeBC = new Color(177, 230, 147);

	private int dir;
	private int befodir;
	private boolean gameEnd = true;
	private boolean startAble = true;
	private boolean first = true;

	private ArrayList<Integer[]> itemYX;

	public SnakePanel() {
		setLayout(null);
		setBounds(0, 0, this.SIZE, this.SIZE);
		setBackground(Color.white);

		setTitle();
		setStart();
		setReset();
		setMap();
		setSnake();
		setItem();

		setFocusable(true);
		addKeyListener(this);
	}

	private void setTitle() {
		this.title.setBounds(240, 30, 500, 100);
		this.title.setText("SNAKE GAME");
		this.title.setForeground(new Color(207, 183, 132));
		this.title.setHorizontalAlignment(JLabel.LEFT);
		this.title.setFont(new Font("NanumGothicBold", Font.BOLD, 50));
		add(this.title);
	}

	private void setStart() {
		this.start.setText("START");
		this.start.setBounds(this.SIZE - 260, this.SIZE - 100, 100, 60);
		this.start.setForeground(Color.white);
		this.start.setBackground(Color.black);
		this.start.setFont(new Font("NanumGothicBold", Font.BOLD, 20));
		this.start.addActionListener(this);
		add(this.start);
	}

	private void setReset() {
		this.reset.setText("RESET");
		this.reset.setBounds(this.SIZE - 150, this.SIZE - 100, 100, 60);
		this.reset.setForeground(Color.white);
		this.reset.setBackground(Color.black);
		this.reset.setFont(new Font("NanumGothicBold", Font.BOLD, 20));
		this.reset.addActionListener(this);
		add(this.reset);
	}

	public void setMap() {
		this.dir = this.LEFT;
		int x = WALL00;
		int y = WALL00;
		for (int i = 0; i < this.MAPSIZE; i++) {
			for (int j = 0; j < this.MAPSIZE; j++) {
				this.map[i][j] = new SnakeRect(x, y, 50, 50);
				x += 50;
			}
			x = WALL00;
			y += 50;
		}
	}

	private void setSnake() {
		// 4칸
		this.snake = new ArrayList<>();
		int x = WALL00 + 50 + this.rn.nextInt(this.MAPSIZE - 4) * 50;
		int y = WALL00 + this.rn.nextInt(this.MAPSIZE) * 50;
		for (int i = 0; i < this.FIRSTSNAKESIZE; i++) {
			this.snake.add(new SnakeRect(x, y, 50, 50));
			if (i == 0) {
				this.snake.get(i).setC(this.snakeHC);
			} else {
				this.snake.get(i).setC(this.snakeBC);
			}
			x += 50;
		}
	}

	private void setItem() {
		// 10 개
		this.item = new ArrayList<>();
		this.itemYX = new ArrayList<>();
		for (int i = 0; i < this.ITEMCNT; i++) {
			while (true) {
				int x = WALL00 + this.rn.nextInt(this.MAPSIZE) * 50 + 13;
				int y = WALL00 + this.rn.nextInt(this.MAPSIZE) * 50 + 13;

				// 스네이크랑 겹치지 않게
				boolean check = false;
				SnakeRect temp = this.snake.get(0);

				if (i == 0) {
					if ((x <= temp.getX() - temp.getW() || x >= temp.getX() + temp.getW() * this.snake.size()
							|| y <= temp.getY() - temp.getH() || y >= temp.getY() + temp.getH() * this.snake.size())) {
						this.item.add(new SnakeRect(x, y, 26, 26));
						this.item.get(this.item.size() - 1).setC(new Color(255, 224, 93));
						Integer[] tempInt = new Integer[2];
						tempInt[0] = y - 13;
						tempInt[1] = x - 13;
						this.itemYX.add(tempInt);
						check = true;
					}
				} else {
					boolean inChk = true;
					for (int j = 0; j < this.item.size(); j++) {
						SnakeRect temp2 = this.item.get(j);
						if ((x <= temp.getX() - temp.getW() || x >= temp.getX() + temp.getW() * this.snake.size()
								|| y <= temp.getY() - temp.getH() || y >= temp.getY() + temp.getH() * this.snake.size())
								&& (x != temp2.getX() || y != temp2.getY())) {
						} else {
							inChk = false;
						}
					}

					if (inChk) {
						this.item.add(new SnakeRect(x, y, 26, 26));
						this.item.get(this.item.size() - 1).setC(new Color(255, 224, 93));
						Integer[] tempInt = new Integer[2];
						tempInt[0] = y - 13;
						tempInt[1] = x - 13;
						this.itemYX.add(tempInt);
						check = true;
					}

				}

				if (check) {
					break;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void gameReset() {
		this.gameEnd = true;
		this.startAble = true;
		this.dir = this.LEFT;
		setSnake();
		setItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if (target == this.start && this.startAble) {
				this.gameEnd = false;
			} else if (target == this.reset) {
				gameReset();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		SnakeRect temp = this.snake.get(0);
		
		if (e.getKeyCode() == e.VK_UP) {
			System.out.println("UP");
			this.befodir = this.dir;
			this.dir = this.UP;
			if(backChkUp(temp)) {
				this.dir = this.befodir;
			}
		} else if (e.getKeyCode() == e.VK_LEFT) {
			System.out.println("LEFT");
			this.befodir = this.dir;
			this.dir = this.LEFT;
			if(backChkLeft(temp)) {
				this.dir = this.befodir;
			}
		} else if (e.getKeyCode() == e.VK_DOWN) {
			System.out.println("DOWN");
			this.befodir = this.dir;
			this.dir = this.DOWN;
			if(backChkDown(temp)) {
				this.dir = this.befodir;
			}
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			System.out.println("RIGHT");
			this.befodir = this.dir;
			this.dir = this.RIGHT;
			if(backChkRight(temp)) {
				this.dir = this.befodir;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	private void drawMap(Graphics g) {
		for (int i = 0; i < this.MAPSIZE; i++) {
			for (int j = 0; j < this.MAPSIZE; j++) {
				if (this.map[i][j] != null) {
					g.setColor(new Color(250, 238, 224));
					g.fillRect(this.map[i][j].getX(), this.map[i][j].getY(), this.map[i][j].getW(),
							this.map[i][j].getH());
					g.setColor(Color.white);
					g.drawRect(this.map[i][j].getX(), this.map[i][j].getY(), this.map[i][j].getW(),
							this.map[i][j].getH());
				}
			}
		}
	}

	private void drawSnake(Graphics g) {
		for (int i = 0; i < this.snake.size(); i++) {
			SnakeRect temp = this.snake.get(i);
			g.setColor(temp.getC());
			g.fillRect(temp.getX(), temp.getY(), temp.getW(), temp.getH());
			g.setColor(Color.white);
			g.drawRect(temp.getX(), temp.getY(), temp.getW(), temp.getH());
		}
	}

	private void drawItem(Graphics g) {
		for (int i = 0; i < this.item.size(); i++) {
			SnakeRect temp = this.item.get(i);
			if (temp != null) {
				g.setColor(temp.getC());
				g.fillRoundRect(temp.getX(), temp.getY(), temp.getW(), temp.getH(), temp.getW(), temp.getH());
				g.setColor(new Color(221, 190, 190));
				g.drawRoundRect(temp.getX(), temp.getY(), temp.getW(), temp.getH(), temp.getW(), temp.getH());
			}
		}
	}

	private boolean backChkUp(SnakeRect temp) {
		boolean chk = false;
		
		int yy = temp.getY() - temp.getH();
		int xx = temp.getX();
		
		for(int i = 1; i < this.snake.size(); i++) {
			SnakeRect t = this.snake.get(i);
			if(yy == t.getY() && xx == t.getX()) {
				chk = true;
				System.out.println("백은 안돼");
			}
		}
		
		return chk;
	}
	private boolean backChkLeft(SnakeRect temp) {
		boolean chk = false;
		
		int yy = temp.getY();
		int xx = temp.getX() - temp.getW();
		
		for(int i = 1; i < this.snake.size(); i++) {
			SnakeRect t = this.snake.get(i);
			if(yy == t.getY() && xx == t.getX()) {
				chk = true;
				System.out.println("백은 안돼");
			}
		}
		
		return chk;
	}
	private boolean backChkDown(SnakeRect temp) {
		boolean chk = false;
		
		int yy = temp.getY() + temp.getH();
		int xx = temp.getX();
		
		for(int i = 1; i < this.snake.size(); i++) {
			SnakeRect t = this.snake.get(i);
			if(yy == t.getY() && xx == t.getX()) {
				chk = true;
				System.out.println("백은 안돼");
			}
		}
		
		return chk;
	}
	private boolean backChkRight(SnakeRect temp) {
		boolean chk = false;
		
		int yy = temp.getY();
		int xx = temp.getX() + temp.getW();
		
		for(int i = 1; i < this.snake.size(); i++) {
			SnakeRect t = this.snake.get(i);
			if(yy == t.getY() && xx == t.getX()) {
				chk = true;
				System.out.println("백은 안돼");
			}
		}
		
		return chk;
	}

	private boolean wallChkUp(SnakeRect temp) {
		boolean wallChk = false;
		if (temp.getY() == this.WALL00) { // 이동하기 전 벽 좌표이면
			wallChk = true;
		}
		return wallChk;
	}

	private boolean wallChkLeft(SnakeRect temp) {
		boolean wallChk = false;
		if (temp.getX() == this.WALL00) { // 이동하기 전 머리의 좌표가 벽이면
			wallChk = true;
		}
		return wallChk;
	}

	private boolean wallChkDown(SnakeRect temp) {
		boolean wallChk = false;
		if (temp.getY() + temp.getH() == this.WALL00 + 50 * 10) { // 이동하기 전 벽 좌표이면
			wallChk = true;
		}
		return wallChk;
	}

	private boolean wallChkRight(SnakeRect temp) {
		boolean wallChk = false;
		if (temp.getX() + temp.getW() == this.WALL00 + 50 * 10) { // 이동하기 전 머리의 좌표가 벽이면
			wallChk = true;
		}
		return wallChk;
	}

	private boolean itemChk(int mHeadY, int mHeadX) {
		boolean itemChk = false;
		for (int i = 0; i < this.itemYX.size(); i++) {
			if (this.itemYX.get(i)[0] == mHeadY && this.itemYX.get(i)[1] == mHeadX) {
				itemChk = true;
			}
		}
		return itemChk;
	}

	private boolean bodyChk(int mHeadY, int mHeadX) {
		boolean bodyChk = false;
		if (this.snake.size() >= 5) {
			for (int i = 3; i < this.snake.size() - 1; i++) {
				if (this.snake.get(i).getX() == mHeadX && this.snake.get(i).getY() == mHeadY) {
					bodyChk = true;
				}
			}
		}
		return bodyChk;
	}

	private void wallM(ArrayList<SnakeRect> tempSnake) {
		this.snake.removeAll(this.snake);
		for (int i = 0; i < tempSnake.size() - 1; i++) {
			if (i == 0) {
				tempSnake.get(i).setC(snakeBC);
			}
			this.snake.add(tempSnake.get(i));
		}
		this.gameEnd = true;
		this.startAble = false;
		new SnakeResultFrame();
	}

	private void itemM(int mHeadY, int mHeadX, ArrayList<SnakeRect> tempSnake) {
		int itemIdx = -1;
		for (int i = 0; i < this.itemYX.size(); i++) {
			if (this.itemYX.get(i)[0] == mHeadY && this.itemYX.get(i)[1] == mHeadX) {
				itemIdx = i;
			}
		}
		this.item.remove(itemIdx);
		this.itemYX.remove(itemIdx);
		ArrayList<SnakeRect> temp = new ArrayList<>();
		SnakeRect hS = new SnakeRect(mHeadX, mHeadY, 50, 50);
		hS.setC(this.snakeHC);
		temp.add(hS);
		for (int i = 0; i < tempSnake.size(); i++) {
			if (i == 0) {
				tempSnake.get(i).setC(this.snakeBC);
			}
			temp.add(tempSnake.get(i));
		}
		this.snake = temp;
	}

	private void bodyM(ArrayList<SnakeRect> tempSnake) {
		this.snake.removeAll(this.snake);
		for (int i = 0; i < tempSnake.size() - 1; i++) {
			if (i == 0) {
				tempSnake.get(i).setC(snakeBC);
			}
			this.snake.add(tempSnake.get(i));
		}
		this.gameEnd = true;
		this.startAble = false;
		new SnakeResultFrame();
	}

	private void notingM(int mHeadY, int mHeadX, ArrayList<SnakeRect> tempSnake) {
		this.snake.removeAll(this.snake);
		SnakeRect head = new SnakeRect(mHeadX, mHeadY, 50, 50);
		head.setC(this.snakeHC);
		this.snake.add(head);
		for (int i = 0; i < tempSnake.size() - 1; i++) {
			if (i == 0) {
				tempSnake.get(i).setC(snakeBC);
			}
			this.snake.add(tempSnake.get(i));
		}
	}

	private void autoMoving() {
		if (!this.gameEnd) {
			System.out.println("오토중");
			System.out.printf("뱀 사이즈 : %d\n", this.snake.size());
			SnakeRect temp = this.snake.get(0); // 머리만 확인 나머지는 앞의 좌표 물려받음
			ArrayList<SnakeRect> tempSnake = new ArrayList<>(); // 이전 좌표 저장
			for (int i = 0; i < this.snake.size(); i++) {
				tempSnake.add(this.snake.get(i));
			}
			if (this.dir == this.UP) {
				int mHeadX = temp.getX();
				int mHeadY = temp.getY() - temp.getH();

				boolean wallChk = wallChkUp(temp);
				boolean itemChk = itemChk(mHeadY, mHeadX);
				boolean bodyChk = bodyChk(mHeadY, mHeadX);
				
				if (wallChk) {
					wallM(tempSnake);
				} else if (itemChk) {
					itemM(mHeadY, mHeadX, tempSnake);
				} else if (bodyChk) {
					bodyM(tempSnake);
				} else {
					notingM(mHeadY, mHeadX, tempSnake);
				}
			} else if (this.dir == this.LEFT) {
				// 스네이크 머리만 이동 나머지는 앞의 좌표값 물려받음
				// 아이템일 경우 머리가 늘어나는 것과 동일 ( 머리 추가 나머지 좌표 유지)
				// 벽 또는 몸일 경우 머리, 꼬리 삭제 후 게임 종료
				int mHeadX = temp.getX() - temp.getW();
				int mHeadY = temp.getY();

				
				
				boolean wallChk = wallChkLeft(temp);
				boolean itemChk = itemChk(mHeadY, mHeadX);
				boolean bodyChk = bodyChk(mHeadY, mHeadX);
				
				if (itemChk) {
					itemM(mHeadY, mHeadX, tempSnake);
				} else if (wallChk) {
					wallM(tempSnake);
				} else if (bodyChk) {
					bodyM(tempSnake);
				} else {
					notingM(mHeadY, mHeadX, tempSnake);
				}
			} else if (this.dir == this.DOWN) {
				int mHeadX = temp.getX();
				int mHeadY = temp.getY() + temp.getH();

				boolean wallChk = wallChkDown(temp);
				boolean itemChk = itemChk(mHeadY, mHeadX);
				boolean bodyChk = bodyChk(mHeadY, mHeadX);

				if (wallChk) {
					wallM(tempSnake);
				} else if (itemChk) {
					itemM(mHeadY, mHeadX, tempSnake);
				} else if (bodyChk) {
					bodyM(tempSnake);
				} else {
					notingM(mHeadY, mHeadX, tempSnake);
				}
			} else if (this.dir == this.RIGHT) {
				int mHeadX = temp.getX() + temp.getW();
				int mHeadY = temp.getY();
				
				boolean wallChk = wallChkRight(temp);
				boolean itemChk = itemChk(mHeadY, mHeadX);
				boolean bodyChk = bodyChk(mHeadY, mHeadX);
				
				if (itemChk) {
					itemM(mHeadY, mHeadX, tempSnake);
				} else if (wallChk) {
					wallM(tempSnake);
				} else if (bodyChk) {
					bodyM(tempSnake);
				} else {
					notingM(mHeadY, mHeadX, tempSnake);
				}
			}
			
			try {
				Thread.sleep(800);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint 는 컨테이너 와 반대로 추가 되는 순서대로 적층구조
		drawMap(g); // 얘가 왜 아래 있지??
		drawSnake(g);
		drawItem(g);
		requestFocusInWindow();
		repaint();
	}

	@Override
	public void run() {
		while (true) {
			if (this.snake.size() != 0) {
				autoMoving();
			}
		}
	}
}

class Snake extends JFrame {
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private final int W = this.dm.width;
	private final int H = this.dm.height;
	private final int SIZE = 900;
	private SnakePanel panel = new SnakePanel();

	public Snake() {
		setLayout(null);
		setBounds(this.W / 2 - 415, this.H / 2 - 415, this.SIZE, this.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(this.panel);

		setVisible(true);
		revalidate();

		this.panel.run();
	}
}

public class Test_007_Snake {

	public static void main(String[] args) {
		Snake game = new Snake();
		// 초 당 자동으로 한칸 이동
		// 자신의 몸에 부딪히면 사망
		// 벽에 부딪히면 사망
		// 방향키는 뱀의 머리 조종
		// 뱀 머리만 색이 다름
		// 아이템은 맵 전역에 뱀을 제외하고 랜덤 등장
		// 아이템 습득시 몸통 한 칸 길어짐
	}

}
