package drawingBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

public class GrimBoard extends MyUtil{
	// MyUtil -> extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
	// 강제 오버라이딩은 MyUtil에서 담당, 실제 사용시에 클래스에서 필요한 부분만 상속받은 MyUtil에서 불러와 사용
	
	private GrimRect rect = null;
	
	private int firstX;
	private int firstY;
	
	private boolean shift = false;
	
	private ArrayList<GrimRect> done = new ArrayList<>();
	
	private JLabel help = new JLabel("'ESC' Press = RESET");
	
	public GrimBoard() {
		setLayout(null);
		setBounds(0,0,1000,1000);
		setBackground(Color.white);
		
		setHelp();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		addKeyListener(this);
		setFocusable(true); // KeyListener 사용시 필요 외우기
	}
	
	public void setHelp() {
		this.help.setBounds(10,0,155,30);
		this.help.setForeground(Color.red);
		this.help.setFont(new Font("NanumGothicBold",Font.PLAIN, 17));
		add(this.help);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.rect != null) {
			g.setColor(this.rect.getC());
			g.drawRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH());
		}
		
		for(int i = 0; i < this.done.size(); i++) {
			if(this.done.get(i) != null) {
				GrimRect a = this.done.get(i);
				g.setColor(a.getC());
				g.drawRect(a.getX(), a.getY(), a.getW(), a.getH());
			}
		}
		
		repaint(); // 다시 그리기, 오버라이딩 하자마자 추가
	}
	
	@Override
	public void mousePressed(MouseEvent e) { // 첫 좌표 받기
		this.firstX = e.getX();
		this.firstY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		// 프레임 or 패널 위에서의 좌표는 무조건 0과 양수 값
		// 기준값 - 이동값 또는 이동값 - 기준값 해도
		// 절대값 처리하면 거리값으로 구해질 수 있음
		
		// 절대값 메소드 Math.abs();
		int w = Math.abs(x - this.firstX);
		int h = Math.abs(y - this.firstY);
		
		if(this.shift) {
			if(w > h) {
				h = w;
			}
			else if(h > w) {
				w = h;
			}
			else {
				h = w;
			}
		}
		
		int paintingX = this.firstX;
		int paintingY = this.firstY;
		
		if(x < this.firstX) {
			paintingX = this.firstX - w;
		}
		if(y < this.firstY) {
			paintingY = this.firstY - h;
		}
		
		this.rect = new GrimRect(paintingX, paintingY, w, h, Color.black);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.rect != null) {
			GrimRect temp = new GrimRect(this.rect.getX(), this.rect.getY(), this.rect.getW(), this.rect.getH(), Color.blue);
			this.done.add(temp);
			this.rect = null;
		}
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
		if(e.getKeyCode() == e.VK_ESCAPE) {
			this.done = new ArrayList<>();
		}
	}
}
