package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class MouseOmokResult extends JFrame{
	private JLabel text = new JLabel(); 
	
	public MouseOmokResult(String str) {
		super("Game Over");
		setBounds(MouseOmokFrame.W / 2 - 150 , MouseOmokFrame.H / 2 - 100, 200 ,120);
		
		this.text.setText(str);
		this.text.setBounds(0,0,200,120);
		this.text.setFont(new Font("NanumGothicBold",Font.BOLD, 20));
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		
		setVisible(true);
		revalidate();
	}
}


class OmokPan{
	private int x, y,width, height;
	private Color c;
	private int turn = 0;
	
	public OmokPan(int x, int y, int w, int h, Color c) {
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

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}

class MouseOmokPanel extends JPanel implements ActionListener, MouseListener{

	
	private JLabel text = new JLabel("마우스 오목 게임");
	private JLabel viewTurn = new JLabel();
	
	private final int SIZE = 10;

	private OmokPan[][] map = new OmokPan[this.SIZE][this.SIZE];
	private OmokPan[][] dol = new OmokPan[this.SIZE][this.SIZE];
	private OmokPan[][] visibleMap = new OmokPan[this.SIZE - 1][this.SIZE - 1];
	
	private int turn = 1;
	private int win;
	
	private JButton reset = new JButton();
	private JButton viewGuide = new JButton();
	
	public MouseOmokPanel() {
		setLayout(null);
		setBounds(0,0,MouseOmokFrame.SIZE,MouseOmokFrame.SIZE);
		setBackground(new Color(247, 208, 138));
		addMouseListener(this);
		setTitle();
		setViewTurn();
		setResetButton();
		setGuideButton();
		setVisibleMap();
		setVisibleDol();
		setMap();
	}
	

	private void setTitle() {
		this.text.setBounds(30,0,MouseOmokFrame.SIZE,470);
		this.text.setHorizontalAlignment(JLabel.LEFT);
		this.text.setForeground(new Color(157, 92, 13));
		this.text.setFont(new Font("NanumGothicBold",Font.BOLD, 30));
		add(this.text);
	}
	
	public void setViewTurn() {
		this.viewTurn.setText(String.format("Player %d ☞",this.turn));
		this.viewTurn.setBounds(30,0,MouseOmokFrame.SIZE,580);
		this.viewTurn.setHorizontalAlignment(JLabel.LEFT);
		this.viewTurn.setForeground(Color.black);
		this.viewTurn.setFont(new Font("NanumGothicBold",Font.PLAIN, 20));
		add(this.viewTurn);
	}
	
	private void setResetButton() {
		this.reset.setText("RESET");
		this.reset.setFont(new Font("NanumGothicBold",Font.PLAIN, 20));
		this.reset.setBounds(80, 350, 100, 50);
		this.reset.addActionListener(this);
		add(this.reset);
	}
	
	private void setGuideButton() {
		this.viewGuide.setText("GUIDE");
		this.viewGuide.setFont(new Font("NanumGothicBold",Font.PLAIN, 20));
		this.viewGuide.setBounds(80, 430, 100, 50);
		this.viewGuide.addActionListener(this);
		add(this.viewGuide);
	}
	
	private void setMap() {
		int x = MouseOmokFrame.SIZE / 2 - 50 * 10 / 2 + 125;
		int y = MouseOmokFrame.SIZE / 2 - 50 * 10 / 2 + 25;
		
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				this.map[i][j] = new OmokPan(x, y, 50, 50, new Color(33,255,255));
				x += 50;
			}
			x = MouseOmokFrame.SIZE / 2 - 50 * 10 / 2 + 125;
			y += 50;
		}
	}
	
	private void setVisibleMap() {
		int x = MouseOmokFrame.SIZE / 2 - 50 * 9 / 2 + 125;
		int y = MouseOmokFrame.SIZE / 2 - 50 * 9 / 2 + 25;
		
		for (int i = 0; i < this.SIZE - 1; i++) {
			for (int j = 0; j < this.SIZE - 1; j++) {
				this.visibleMap[i][j] = new OmokPan(x, y, 50, 50, Color.black);
				x += 50;
			}
			x = MouseOmokFrame.SIZE / 2 - 50 * 9 / 2 + 125;
			y += 50;
		}
	}
	
	public void setVisibleDol() {
		int x = MouseOmokFrame.SIZE / 2 - 50 * 10 / 2 + 125;
		int y = MouseOmokFrame.SIZE / 2 - 50 * 10 / 2 + 25;
		
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				this.dol[i][j] = new OmokPan(x, y, 50, 50, Color.white);
				x += 50;
			}
			x = MouseOmokFrame.SIZE / 2 - 50 * 10 / 2 + 125;
			y += 50;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		RunViewGuide(g);
		RunVisibleMap(g);
		RunVisibleDol(g);
		repaint();
	}
	
	private void RunViewGuide(Graphics g) {
		for(int i = 0; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE; j ++) {
				OmokPan temp = this.map[i][j];
				if(temp.getTurn() == 1) {
					g.setColor(temp.getC());
					g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				}
			}
		}
	}
	
	private void RunVisibleMap(Graphics g) {
		for(int i = 0; i < this.SIZE - 1; i ++) {
			for(int j = 0; j < this.SIZE - 1; j ++) {
				OmokPan temp = this.visibleMap[i][j];
				g.setColor(temp.getC());
				g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			}
		}
	}
	
	private void RunVisibleDol(Graphics g) {
		for(int i = 0; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE; j ++) {
				OmokPan temp = this.dol[i][j];
				if (temp.getTurn() == 1 ) {
					temp.setC(Color.black);
					g.setColor(temp.getC());
					g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getWidth(), temp.getHeight());
				}
				else if(temp.getTurn() == 2) {
					temp.setC(Color.white);
					g.setColor(temp.getC());
					g.fillRoundRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight(), temp.getWidth(), temp.getHeight());
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) { // e 가 JButton 으로 바뀌면
			JButton target = (JButton) e.getSource(); //type cast
			
			if(target == this.reset) {
				resetGame();
				System.out.println("reset");
			}
			else if(target == this.viewGuide){
				viewGuide();
				System.out.println("viewGuide");
			}
		}
	}
	
	private void viewGuide() {
		for(int i = 0; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE; j ++) {
				OmokPan temp = this.map[i][j];
				int tempTurn = temp.getTurn();
				tempTurn = tempTurn == 0 ? 1 : 0;
				temp.setTurn(tempTurn);
			}
		}
	}
	
	private void resetGame() {
		this.turn = 1;
		this.win = 0;
		this.viewTurn.setForeground(Color.black);
		this.viewTurn.setText(String.format("Player %d ☞",this.turn));
		resetDol();
		resetMap();
	}
	
	private void resetMap() {
		for(int i = 0; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE; j ++) {
				OmokPan temp = this.map[i][j];
				temp.setTurn(0);
			}
		}
	}
	
	private void resetDol() {
		for(int i = 0; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE; j ++) {
				OmokPan temp = this.dol[i][j];
				temp.setTurn(0);
			}
		}
	}

	private void putDol(int x, int y) {
		notEndDol(x,y);
		endDol(x,y);
	}
	
	private void runPutDol() {
		winChk();
		this.turn = this.turn == 1 ? 2 : 1;
		if(this.turn == 1) {
			this.viewTurn.setForeground(Color.black);
		}
		else {
			this.viewTurn.setForeground(Color.white);
		}
		this.viewTurn.setText(String.format("Player %d ☞",this.turn));
	}
	
	private void notEndDol(int x, int y) {
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE; j++) {
				if (i < this.SIZE -1 && j < this.SIZE - 1) { // 8
					if(x >= this.dol[i][j].getX() && y >= this.dol[i][j].getY() &&
							x < this.dol[i][j + 1].getX() && y < this.dol[i + 1][j].getY() &&
							this.dol[i][j].getTurn() == 0 && this.win == 0) {
						this.dol[i][j].setTurn(this.turn);
						runPutDol();
					}
				}
			}
		}
	}
	
	private void endDol(int x, int y) {
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE; j++) {
				if(i == this.SIZE -1 || j == this.SIZE -1) {
					if(x >= this.dol[i][j].getX() && y >= this.dol[i][j].getY() &&
							x < this.dol[i][j].getX() + 50 && y < this.dol[i][j].getY() + 50&&
							this.dol[i][j].getTurn() == 0  && this.win == 0) {
						this.dol[i][j].setTurn(this.turn);
						runPutDol();
					}
				}
			}
		}
	}
	
	private boolean chkDolCnt() {
		boolean fullDol = false;
		int cnt = 0;
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE; j++) {
				if(this.dol[i][j].getTurn() != 0) {
					cnt ++;
				}
			}
		}
		
		if(cnt == this.SIZE * this.SIZE) {
			fullDol = true;
		}
		
		return fullDol;
	}
	
	
	private void winChk() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		
		if(this.win == 0 && chkDolCnt()) {
			new MouseOmokResult(String.format("Draw!", this.win));
		}
		else if(this.win != 0) {
			this.reset.setVisible(true);
			new MouseOmokResult(String.format("Player%d win!", this.win));
		}
	}
	
	private int checkHori() {
		for(int i =0; i < this.SIZE; i++) {
			for(int j =0; j < this.SIZE - 4; j++) {
				if(this.dol[i][j].getTurn() == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.dol[i][j+k].getTurn() == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	private int checkVerti() {
		for(int i =0; i < this.SIZE - 4; i++) {
			for(int j =0; j < this.SIZE; j++) {
				if(this.dol[i][j].getTurn() == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.dol[i+k][j].getTurn() == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	private int checkDia() {
		for(int i =0; i < this.SIZE - 4; i++) {
			for(int j =0; j < this.SIZE - 4; j++) {
				if(this.dol[i][j].getTurn() == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.dol[i+k][j+k].getTurn() == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	private int checkReverse() {
		for(int i = 4; i < this.SIZE; i ++) {
			for(int j = 0; j < this.SIZE - 4 ; j++) {
				if(this.dol[i][j].getTurn() == this.turn) {
					int cnt = 0;
					for(int k =0; k < 5; k++) {
						if(this.dol[i-k][j+k].getTurn() == this.turn) {
							cnt ++;
						}
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) { // 클릭 떼는 순간
		int x = e.getX();
		int y = e.getY();
		System.out.printf("x : %d / y: %d\n" ,x, y);
		putDol(x, y);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}

class MouseOmokFrame extends JFrame{
	private static Dimension dm = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static final int W = dm.width;
	public static final int H = dm.height;
	public static final int SIZE = 900;
	
	private MouseOmokPanel panel = new MouseOmokPanel();
	
	public MouseOmokFrame() {
		setTitle("Mouse Omok Game");
		setLayout(null);
		setBounds(W / 2 - SIZE /2, H / 2 - SIZE /2, SIZE, SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.panel);
		
		setVisible(true);
		revalidate();
	}
}

public class Test_005_숙제마우스오목 {

	public static void main(String[] args) {
		MouseOmokFrame game = new MouseOmokFrame();
	}

}
