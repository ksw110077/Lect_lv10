package drawingBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GrimBoard extends MyUtil{
	// MyUtil -> extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
	// 강제 오버라이딩은 MyUtil에서 담당, 실제 사용시에 클래스에서 필요한 부분만 상속받은 MyUtil에서 불러와 사용
	
	private GrimRect rect = null;
	
	private final int NEMO = 0;
	private final int CIRCLE = 1;
	private final int SEMO = 2;
	private final int LINE = 3;
	
	private int chkForm;
	private int firstX;
	private int firstY;
	private int [] triX = new int [3]; 
	private int [] triY = new int [3]; 
	
	private boolean shift = false;
	
	private ArrayList<GrimRect> rects = new ArrayList<>();
	private ArrayList<GrimRect> circles = new ArrayList<>();
	private ArrayList<GrimRect> triangles = new ArrayList<>();
	private ArrayList<Integer> penX = new ArrayList<>();
	private ArrayList<Integer> penY = new ArrayList<>();
	private ArrayList<int [][]> lines = new ArrayList<>();
	
	private JLabel help = new JLabel("'ESC' Press = RESET");
	private JLabel showShift = new JLabel("'Shift' = false");
	
	private String [] btnText = {"□","○","△", "／"};
	private JButton []  btn = new JButton[4];
	
	
	private Color backC = Color.black;
	private Color [] colors = {Color.black, Color.blue, Color.red, Color.yellow, Color.green};
	private final int BLACK = 0;
	private final int BLUE = 1;
	private final int RED = 2;
	private final int YELLOW = 3;
	private final int GREEN = 4;
	private JButton []  cBtn = new JButton[5];
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0,0,1000,1000);
		setBackground(Color.white);
		
		this.chkForm = 0;
		
		setHelp();
		setShowShift();
		setBtn();
		setColor();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setFocusable(true); // KeyListener 사용시 필요 외우기
		addKeyListener(this);
	}
	


	private void setShowShift() {
		this.showShift.setBounds(800,0,155,30);
		this.showShift.setForeground(Color.red);
		this.showShift.setHorizontalAlignment(JLabel.RIGHT);
		this.showShift.setFont(new Font("NanumGothicBold",Font.PLAIN, 17));
		add(this.showShift);
	}

	public void setHelp() {
		this.help.setBounds(10,0,155,30);
		this.help.setForeground(Color.red);
		this.help.setFont(new Font("NanumGothicBold",Font.PLAIN, 17));
		add(this.help);
	}
	
	private void setBtn() {
		int x = 780;
		int y = 950;
		
		for(int i = 0; i < this.btn.length; i++) {
			this.btn[i] = new JButton();
			this.btn[i].setBounds(x,y, 50, 30);
			this.btn[i].setText(this.btnText[i]);
			this.btn[i].addActionListener(this);
			add(this.btn[i]);
			x += 53;
		}
	}
	
	private void setColor() {
		int x = 510;
		int y = 950;
		
		for(int i = 0; i < this.cBtn.length; i++) {
			this.cBtn[i] = new JButton();
			this.cBtn[i].setBounds(x,y, 50, 30);
			this.cBtn[i].setBackground(this.colors[i]);
			this.cBtn[i].addActionListener(this);
			add(this.cBtn[i]);
			x += 53;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if(target == this.btn[this.NEMO]) {
				this.chkForm = this.NEMO;
			}
			else if (target == this.btn[this.CIRCLE]) {
				this.chkForm = this.CIRCLE;
			}
			else if (target == this.btn[this.SEMO]) {
				this.chkForm = this.SEMO;
			}
			else if (target == this.btn[this.LINE]) {
				this.chkForm = this.LINE;
			}
			else if (target == this.cBtn[this.BLACK]) {
				this.backC = this.colors[this.BLACK];
			}
			else if (target == this.cBtn[this.BLUE]) {
				this.backC = this.colors[this.BLUE];
			}
			else if (target == this.cBtn[this.RED]) {
				this.backC = this.colors[this.RED];
			}
			else if (target == this.cBtn[this.YELLOW]) {
				this.backC = this.colors[this.YELLOW];
			}
			else if (target == this.cBtn[this.GREEN]) {
				this.backC = this.colors[this.GREEN];
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int xx [] = new int [3];
		int yy [] = new int [3];
		if(this.rect != null) {
			g.setColor(this.rect.getC());
			if(this.chkForm == this.NEMO) {
				g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
			}
			else if(this.chkForm == this.CIRCLE) {
				g.drawRoundRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH(), this.rect.getW(), this.rect.getH());
			}
			else if(this.chkForm == this.SEMO) {
				xx = new int[3];
				yy = new int[3];
				xx[0] = this.rect.getX();
				yy[0] = this.rect.getY();
				
				xx[1] = this.rect.getX() - this.rect.getW() / 2;
				yy[1] = this.rect.getY() + this.rect.getH();
				
				xx[2] = this.rect.getX() + this.rect.getW() / 2;
				yy[2] = this.rect.getY() + this.rect.getH();
				
				g.drawPolygon(xx, yy, 3);
			}
			else if (this.chkForm == this.LINE) {
				int [] tX = new int [this.penX.size()];
				int [] tY = new int [this.penY.size()];
				
				for(int i = 0; i < this.penX.size(); i++) {
					tX[i] = this.penX.get(i);
					tY[i] = this.penY.get(i);
				}
				g.drawPolyline(tX, tY, this.penX.size());
			}
		}
		
		// rects
		for(int i = 0; i < this.rects.size(); i++) {
			if(this.rects.get(i) != null) {
				GrimRect a = this.rects.get(i);
				g.setColor(a.getC());
				g.drawRect(a.getX(), a.getY(), a.getW(), a.getH());
			}
		}
		
		
		// circles
		
		for(int i = 0; i < this.circles.size(); i++) {
			if(this.circles.get(i) != null) {
				GrimRect a = this.circles.get(i);
				g.setColor(a.getC());
				g.drawRoundRect(a.getX(), a.getY(), a.getW(), a.getH(), a.getW(), a.getH());
			}
		}
		
		// triangle
		for(int i =0; i < this.triangles.size(); i++) {
			GrimRect r = this.triangles.get(i);
			xx = new int[3];
			yy = new int[3];
			xx[0] = r.getX();
			yy[0] = r.getY();
			
			xx[1] = r.getX() - r.getW() / 2;
			yy[1] = r.getY() + r.getH();
			
			xx[2] = r.getX() + r.getW() / 2;
			yy[2] = r.getY() + r.getH();
			
			g.setColor(r.getC());
			g.drawPolygon(xx, yy, 3);
		}
		
		// line
		for(int i = 0; i < this.lines.size(); i ++) {
			if(this.lines.get(i) != null) {
				int x [] = this.lines.get(i)[0];
				int y [] = this.lines.get(i)[1];
				g.setColor(this.rect.getC());
				g.drawPolyline(x, y, x.length);
			}
		}
		
		requestFocusInWindow(); // 키리스너 필수
		repaint(); // 다시 그리기, 오버라이딩 하자마자 추가
	}
	
	@Override
	public void mousePressed(MouseEvent e) { // 첫 좌표 받기
		this.firstX = e.getX();
		this.firstY = e.getY();
		
		this.penX.add(this.firstX);
		this.penY.add(this.firstY);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// 현재 마우스 좌표
		int x = e.getX();
		int y = e.getY();
		
		this.penX.add(x);
		this.penY.add(y);
		
		// 프레임 or 패널 위에서의 좌표는 무조건 0과 양수 값
		// 기준값 - 이동값 또는 이동값 - 기준값 해도
		// 절대값 처리하면 거리값으로 구해질 수 있음
		
		// 절대값 메소드 Math.abs();
		int w = this.chkForm == this.SEMO ? x - this.firstX : Math.abs(x - this.firstX);
		int h = this.chkForm == this.SEMO ? y - this.firstY : Math.abs(y - this.firstY);
		
		if(this.shift) {
			w = h;
		}
		
		
		int rX = this.firstX;
		int rY = this.firstY;
		
		if (this.chkForm != this.SEMO) {
			if(x < this.firstX) {
				rX = this.firstX - w;
			}
			if(y < this.firstY) {
				rY =this.firstY - h;
			}
		}
		this.rect = new GrimRect(rX, rY, w, h, Color.red);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.rect != null) {
			GrimRect temp = new GrimRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH(), this.rect.getC());
			if(this.chkForm == this.NEMO) {
				this.rects.add(temp);
			}
			else if (this.chkForm == this.CIRCLE) {
				this.circles.add(temp);
			}
			else if (this.chkForm == this.SEMO) {
			}
			else if (this.chkForm == this.LINE) {
				int [] tX = new int [this.penX.size()];
				int [] tY = new int [this.penY.size()];
				
				for(int i = 0; i < this.penX.size(); i++) {
					tX[i] = this.penX.get(i);
					tY[i] = this.penY.get(i);
				}
				
				int [][] t = {tX, tY};
				
				this.lines.add(t);
				
				this.penX = new ArrayList<>();
				this.penY = new ArrayList<>();
			}
			this.rect = null;
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) {
			this.shift = true;
			this.showShift.setForeground(Color.blue);
			this.showShift.setText("'Shift' = true");
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SHIFT) {
			this.shift = false;
			this.showShift.setForeground(Color.red);
			this.showShift.setText("'Shift' = flase");
		}
		if(e.getKeyCode() == e.VK_ESCAPE) {
			this.rects = new ArrayList<>();
			this.circles = new ArrayList<>();
			this.triangles = new ArrayList<>();
			this.lines = new ArrayList<>();
		}
	}
}
