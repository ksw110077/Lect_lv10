package sokobanGUI;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {
	public final int GROUND = 1;
	public final int WALL = 2;
	public final int PLAYER = 3;
	public final int PTAGER = 4;
	public final int GOAL = 5;
	public final int NOBOX = 6;
	public final int YESBOX = 7;
	private final int W = 30;
	private final int H = 30;
	
	private String fileName;
	
	// setter, getter
	private ImageIcon image;

	private boolean goal = false;
	private int state;
	
	// getter만
	private int x;
	private int y;
	
	
	public Tile(int x, int y, int state, boolean goal) {
		this.x = x;
		this.y = y;
		this.state = state;
		this.goal = goal;
		this.fileName = String.format("images/tile%d.png", this.state);
		this.image = new ImageIcon(new ImageIcon(this.fileName).getImage().getScaledInstance(this.W, this.H,Image.SCALE_SMOOTH));
	}
	
	
	public ImageIcon getImage() {
		return image;
	}


	public void setImage(ImageIcon image) {
		this.image = image;
	}


	public boolean getGoal() {
		return goal;
	}


	public void setGoal(boolean goal) {
		this.goal = goal;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
	// 이미지
	// 타일 별 상태값
	// x, y 좌표
	// 변동 X 벽, 골

	// 1번 땅
	// 2번 벽
	// 3번 플레이어
	// 4번 not Goal 박스
	// 5번 Goal point
	// 6번 Goal 박스
	// 맵 파일 처리 저장 없이 로드만 가능하게
}
