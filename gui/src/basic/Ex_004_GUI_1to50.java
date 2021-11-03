package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


// 타이머
// 리스타트
// 버튼 사용
// 버튼 위에 라벨 올라가는지 확인
class OneToFiftyPanel extends JPanel implements ActionListener{
	private Random rn = new Random();
	private int cnt = 1;
	private final int FINISH = 50;
	private JButton[] map = new JButton[25]; // 맵
	private int [][] numMap = new int [2][25]; // front , back
	private JLabel title = new JLabel("1 to 50");

	public OneToFiftyPanel() {
		setNumMap();
		setLayout(null);
		setBounds(0,0,OneToFiftyFrame.SIZE,OneToFiftyFrame.SIZE);
		setTitle();
		setMap();
	}
	
	private void setNumMap() {
		inputNumMap();
		shuffleNumMap();
	}
	
	private void inputNumMap() {
		for(int i = 1; i <= 50; i++) {
			if(i <= 25) {
				this.numMap[0][i - 1] = i;
			}
			else {
				this.numMap[1][i - 26] = i;
			}
		}
	}
	
	private void shuffleNumMap() {
		for(int i = 0; i < 5000; i++) {
			int temp1 = this.numMap[0][0];
			int temp2 = this.numMap[1][0];
			
			int rN1 = rn.nextInt(25);
			int rN2 = rn.nextInt(25);
			
			this.numMap[0][0] = this.numMap[0][rN1];
			this.numMap[0][rN1] = temp1;

			this.numMap[1][0] = this.numMap[1][rN2];
			this.numMap[1][rN2] = temp2;
		}
	}
	
	private void setMap() {
		int x = OneToFiftyFrame.SIZE / 2 - ((70*5)+(20)) / 2;
		int y = ((70*5)+(20)) / 2; // 좌표 설정이 이상함 135 이 가운데
		
		for(int i =0; i < this.map.length; i++) {
			this.map[i] = new JButton();
			this.map[i].setBounds(x,y,70,70);
			this.map[i].setText("" + this.numMap[0][i]);
			this.map[i].setForeground(Color.white);
			this.map[i].setFont(new Font("NanumGothicBold", Font.BOLD, 29));
			this.map[i].setBackground(new Color(68, 102, 170));
			add(this.map[i]);
			
			x += 70 + 5;
			if(i % 5 == 4) {
				x = OneToFiftyFrame.SIZE / 2 - ((70*5)+(20)) / 2;
				y += 70 + 5;
			}
		}
		
	}
	
	private void setTitle() {
		this.title.setBounds(0,0,OneToFiftyFrame.SIZE,120);
		this.title.setForeground(new Color(68, 102, 170));
		this.title.setFont(new Font("NanumGothicBold", Font.PLAIN, 40));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		add(this.title);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}

class OneToFiftyFrame extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int  SIZE = 620; // 버튼 마다 5씩 띄우기
	
	public OneToFiftyFrame() {
		setLayout(null);
		setTitle("1 To 50");
		setBounds(width / 2 - SIZE / 2, height / 2 - SIZE / 2, SIZE, SIZE);
		setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new OneToFiftyPanel());
		
		setVisible(true);
		revalidate();
	}
}

public class Ex_004_GUI_1to50 {

	public static void main(String[] args) {
		OneToFiftyFrame otff = new OneToFiftyFrame();
	}

}
