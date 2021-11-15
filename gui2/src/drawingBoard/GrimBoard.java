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
	
	private int chkForm;
	private int firstX;
	private int firstY;
	private int [] triX = new int [3]; 
	private int [] triY = new int [3]; 
	
	private boolean shift = false;
	
	private ArrayList<GrimRect> rects = new ArrayList<>();
	private ArrayList<GrimRect> circles = new ArrayList<>();
	private ArrayList<int []> triangles = new ArrayList<>();
	
	private JLabel help = new JLabel("'ESC' Press = RESET");
	private JLabel showShift = new JLabel("'Shift' = false");
	
	String [] btnText = {"□","○","△"};
	private JButton []  btn = new JButton[3];
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0,0,1000,1000);
		setBackground(Color.white);
		
		this.chkForm = 0;
		
		setHelp();
		setShowShift();
		setBtn();
		
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
		int x = 830;
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
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.rect != null) {
			g.setColor(this.rect.getC());
			if(this.chkForm == this.NEMO) {
				g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
			}
			else if(this.chkForm == this.CIRCLE) {
				g.drawRoundRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH(), this.rect.getW(), this.rect.getH());
			}
			else if(this.chkForm == this.SEMO) {
				g.drawPolygon(this.triX, this.triY, 3);
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
		for(int i = 0; i < this.triangles.size(); i += 2) {
			if(this.triangles.get(i) != null) {
				int x [] = this.triangles.get(i);
				int y [] = this.triangles.get(i + 1);
				g.setColor(Color.blue);
				g.drawPolygon(x, y, 3);
			}
		}
		
		requestFocusInWindow(); // 키리스너 필수
		repaint(); // 다시 그리기, 오버라이딩 하자마자 추가
	}
	
	@Override
	public void mousePressed(MouseEvent e) { // 첫 좌표 받기
		this.firstX = e.getX();
		this.firstY = e.getY();
		this.triX[0] = this.firstX;
		this.triY[0] = this.firstY;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// 현재 마우스 좌표
		int x = e.getX();
		int y = e.getY();
		// 프레임 or 패널 위에서의 좌표는 무조건 0과 양수 값
		// 기준값 - 이동값 또는 이동값 - 기준값 해도
		// 절대값 처리하면 거리값으로 구해질 수 있음
		
		// 절대값 메소드 Math.abs();
		int w = Math.abs(x - this.firstX);
		int h = Math.abs(y - this.firstY);
		if(this.shift) {
			h = w;
		}
		
		this.triX[1] = this.firstX - (w / 2);
		this.triX[2] = this.firstX + (w / 2);
		
		int paintingX = this.firstX;
		int paintingY = this.firstY;
		
		if(x < this.firstX) {
			paintingX = this.firstX - w;
		}
		if(y < this.firstY) {
			paintingY = this.firstY - h;
			this.triY[1] = this.firstY - h;
			this.triY[2] = this.firstY - h;
		}
		else {
			this.triY[1] = this.firstY + h;
			this.triY[2] = this.firstY + h;
		}
		
		
		this.rect = new GrimRect(paintingX, paintingY, w, h, Color.black);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.rect != null) {
			GrimRect temp = new GrimRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH(), Color.blue);
			if(this.chkForm == this.NEMO) {
				this.rects.add(temp);
			}
			else if (this.chkForm == this.CIRCLE) {
				this.circles.add(temp);
			}
			else if (this.chkForm == this.SEMO) {
				int [] tempX = new int [3];
				int [] tempY = new int [3];
				
				for(int i = 0 ; i < this.triX.length; i++) {
					tempX[i] = this.triX[i];
					tempY[i] = this.triY[i];
				}
				this.triangles.add(tempX);
				this.triangles.add(tempY);
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
		}
	}
}
