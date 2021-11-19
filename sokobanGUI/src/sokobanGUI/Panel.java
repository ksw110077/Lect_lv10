package sokobanGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Panel extends Util {
	public final int GROUND = 1;
	public final int WALL = 2;
	public final int PLAYER = 3;
	public final int PTAGER = 4;
	public final int GOAL = 5;
	public final int NOBOX = 6;
	public final int YESBOX = 7;

	private final int W = 30;
	private final int H = 30;

	private final int MAXLV = 3;
	private final int StartX = 10;
	private final int StartY = 40;

	private ArrayList<ArrayList<Tile>> map = new ArrayList<>();

	private boolean end = false;
	private int boxIdx;
	private int lv;
	private int cntGoal;
	private int cntFinish;
	private int pIdxY;
	private int pIdxX;
	private int dir;

	private Alert alert;
	private JLabel textLV = new JLabel();
	private JLabel resetHelp = new JLabel();

	private JComboBox<String> levelsBox;
	private String strLV[] = new String[this.MAXLV];

	private JButton selLV;

	private File file;

	private void printMember() {
		System.out.println("==========================");
		System.out.println("this.end : " + this.end);
		System.out.println("this.cntFinish : " + this.cntFinish);
		System.out.println("this.cntGoal : " + this.cntGoal);
		System.out.println("this.pIdxY : " + this.pIdxY);
		System.out.println("this.pIdxX : " + this.pIdxX);
		System.out.println("this.dir : " + this.dir);
		System.out.println("this.lv : " + this.lv);
		System.out.println("this.boxIdx : " + this.boxIdx);
		System.out.println("this.alert : " + this.alert); // 렙업 때만?
		System.out.println("==========================");
	}

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 900, 950); // 위 10픽셀 레벨 선택창
		setBackground(Color.white);
		setNLV();
		setListLV();
		setBox();
		setBtn();
		setResetText();
		setMap();
		setFocusable(true);
		addKeyListener(this);
	}

	private void setNLV() {
		this.lv = 1;
		this.textLV.setText("Level " + this.lv);
		this.textLV.setBounds(10, 10, 60, 20);
		this.textLV.setHorizontalAlignment(JLabel.CENTER);
		this.textLV.setBackground(new Color(173, 216, 230));
		this.textLV.setFont(new Font("", Font.BOLD, 13));
		this.textLV.setOpaque(true);
		add(this.textLV);
	}

	private void setListLV() {
		for (int i = 0; i < this.MAXLV; i++) {
			this.strLV[i] = "Level " + (i + 1);
		}
	}

	private void setBox() {
		this.levelsBox = new JComboBox<String>(this.strLV);
		this.levelsBox.setBounds(80, 10, 70, 20);
		this.levelsBox.addActionListener(this);
		add(this.levelsBox, 0);
	}

	private void setBtn() {
		this.selLV = new JButton();
		this.selLV.setText("Go to Level");
		this.selLV.setBounds(160, 10, 100, 20);
		this.selLV.addActionListener(this);
		add(this.selLV);
	}

	private void setResetText() {
		this.resetHelp.setText("press 'SPACE' = RESET");
		this.resetHelp.setBounds(270, 10, 150, 20);
		this.resetHelp.setHorizontalAlignment(JLabel.LEFT);
		this.resetHelp.setFont(new Font("", Font.BOLD, 13));
		add(this.resetHelp);
	}

	public void setMap() {
		this.file = new File("mapData/" + this.textLV.getText() + ".txt");
		// 파일 내 주석 처리 안됨
		
		this.cntGoal = 0;
		this.cntFinish = 0;
		this.dir = 0;

		if (this.file.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			try {
				this.map = new ArrayList<>();
				fr = new FileReader(this.file);
				br = new BufferedReader(fr);

				int x = this.StartX;
				int y = this.StartY;
				int state = 0;

				String line = br.readLine();

				while (line != null) {
					ArrayList<Tile> tileLine = new ArrayList<>();

					String[] tile = line.split("/");

					for (int i = 0; i < tile.length; i++) {
						boolean goal = false;
						if (tile[i].equals("" + this.GROUND)) {
							state = this.GROUND;
						} else if (tile[i].equals("" + this.WALL)) {
							state = this.WALL;
						} else if (tile[i].equals("" + this.PLAYER)) {
							state = this.PLAYER;
							this.pIdxY = this.map.size();
							this.pIdxX = i;
						} else if (tile[i].equals("" + this.PTAGER)) {
							state = this.PTAGER;
							goal = true;
							this.cntGoal++;
						} else if (tile[i].equals("" + this.NOBOX)) {
							state = this.NOBOX;
						} else if (tile[i].equals("" + this.GOAL)) {
							state = this.GOAL;
							goal = true;
							this.cntGoal++;
						} else if (tile[i].equals("" + this.YESBOX)) {
							state = this.YESBOX;
							goal = true;
							this.cntFinish++;
							this.cntGoal++;
						}
						Tile tic = new Tile(x, y, state, goal);
						tileLine.add(tic);
						x += 30;
					}
					this.map.add(tileLine);
					line = br.readLine();
					x = this.StartX;
					y += 30;
				}
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < this.map.size(); i++) {
			ArrayList<Tile> line = this.map.get(i);
			for (int j = 0; j < line.size(); j++) {
				Tile m = line.get(j);
				g.drawImage(m.getImage().getImage(), m.getX(), m.getY(), null);
			}
		}
		if (this.alert != null && this.alert.getChkClose() && this.lv < this.MAXLV) {
			levelUp();
		}
		repaint();
	}

	private void levelUp() {
		this.lv++;
		this.textLV.setText("Level " + this.lv);
		reset();
	}

	private void reset() {
		this.dir = 0;
		setMap();
		this.end = false;
		this.alert = null;
		requestFocusInWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox b = (JComboBox) e.getSource();
			b.requestFocus();
			this.boxIdx = b.getSelectedIndex();
		} else if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			b.requestFocus();
			if (b == this.selLV) {
				this.lv = this.boxIdx + 1;
				this.textLV.setText("Level " + this.lv);
				reset();
			}
		}
		requestFocusInWindow();
	}

	private void move() {
		if (!this.end) {
			int xx = this.pIdxX;
			int yy = this.pIdxY;

			if (this.dir == 1) { // up
				yy--;
			} else if (this.dir == 2) { // left
				xx--;
			} else if (this.dir == 3) { // down
				yy++;
			} else if (this.dir == 4) { // right
				xx++;
			}

			Tile yx = this.map.get(yy).get(xx);

			if (yy < 0 || yy >= this.map.size() || xx < 0 || xx >= this.map.get(0).size()
					|| yx.getState() == this.WALL) {
				return;
			} // 이동한 곳이 범위 밖이거나 벽일 때 리턴

			if (yx.getState() == this.NOBOX || yx.getState() == this.YESBOX) {
				int bX = xx;
				int bY = yy;

				if (this.dir == 1) { // up
					bY--;
				} else if (this.dir == 2) { // left
					bX--;
				} else if (this.dir == 3) { // down
					bY++;
				} else if (this.dir == 4) { // right
					bX++;
				}

				Tile bYX = this.map.get(bY).get(bX);

				if (bY < 0 || bY >= this.map.size() || bX < 0 || bX >= this.map.get(0).size()
						|| bYX.getState() == this.WALL || bYX.getState() == this.NOBOX
						|| bYX.getState() == this.YESBOX) {
					return;
				}

				if (bYX.getGoal()) {
					bYX.setState(this.YESBOX);
					String n = "images/tile" + this.YESBOX + ".png";
					bYX.setImage(new ImageIcon(
							new ImageIcon(n).getImage().getScaledInstance(this.W, this.H, Image.SCALE_SMOOTH)));
				} else {
					bYX.setState(this.NOBOX);
					String n = "images/tile" + this.NOBOX + ".png";
					bYX.setImage(new ImageIcon(
							new ImageIcon(n).getImage().getScaledInstance(this.W, this.H, Image.SCALE_SMOOTH)));
				}
			}

			Tile pYX = this.map.get(this.pIdxY).get(this.pIdxX);

			if (pYX.getGoal()) {
				pYX.setState(this.GOAL);
				String n = "images/tile" + this.GOAL + ".png";
				pYX.setImage(new ImageIcon(
						new ImageIcon(n).getImage().getScaledInstance(this.W, this.H, Image.SCALE_SMOOTH)));
			} else {
				pYX.setState(this.GROUND);
				String n = "images/tile" + this.GROUND + ".png";
				pYX.setImage(new ImageIcon(
						new ImageIcon(n).getImage().getScaledInstance(this.W, this.H, Image.SCALE_SMOOTH)));
			}

			this.pIdxX = xx;
			this.pIdxY = yy;

			pYX = this.map.get(this.pIdxY).get(this.pIdxX);

			if (pYX.getGoal()) {
				pYX.setState(this.PTAGER);
				String n = "images/tile" + this.PTAGER + ".png";
				pYX.setImage(new ImageIcon(
						new ImageIcon(n).getImage().getScaledInstance(this.W, this.H, Image.SCALE_SMOOTH)));
			} else {
				pYX.setState(this.PLAYER);
				String n = "images/tile" + this.PLAYER + ".png";
				pYX.setImage(new ImageIcon(
						new ImageIcon(n).getImage().getScaledInstance(this.W, this.H, Image.SCALE_SMOOTH)));
			}
		}
	}

	private void winChk() {
		this.cntFinish = 0;
		for (int i = 0; i < this.map.size(); i++) {
			ArrayList<Tile> line = this.map.get(i);
			for (int j = 0; j < line.size(); j++) {
				Tile t = line.get(j);
				if (t.getState() == this.YESBOX) {
					this.cntFinish++;
				}
			}
		}

		if (this.cntFinish == this.cntGoal) {
			this.end = true;
			this.alert = new Alert();
		}
		printMember();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			reset();
		} else if (e.getKeyCode() == e.VK_UP) {
			this.dir = 1;
		} else if (e.getKeyCode() == e.VK_LEFT) {
			this.dir = 2;
		} else if (e.getKeyCode() == e.VK_DOWN) {
			this.dir = 3;
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			this.dir = 4;
		}
		move();
		winChk();
	}

}
