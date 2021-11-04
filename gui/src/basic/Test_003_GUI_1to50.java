package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 타이머
// 리스타트
// 버튼 사용

class OTFResultFrame extends JFrame implements ActionListener {
	private JLabel finish = new JLabel();
	private JButton reset = new JButton();

	public OTFResultFrame() {
		setLayout(null);
		setTitle("GAEM WIN");
		setBounds(OneToFiftyFrame.width / 2 - 400 / 2, OneToFiftyFrame.height / 2 - 200 / 2, 400, 200);
		getContentPane().setBackground(Color.white);
		setFinish();
		setReset();
		setVisible(true);
		revalidate();
	}

	private void setFinish() {
		this.finish.setBounds(0, -50, 400, 200);
		String a = ("당신의 기록은 " + OneToFiftyPanel.form.format(OneToFiftyPanel.milli) + " 초 입니다");
		this.finish.setText(a);
		this.finish.setForeground(new Color(68, 102, 170));
		this.finish.setFont(new Font("NanumGothicBold", Font.BOLD, 25));
		this.finish.setVerticalAlignment(JLabel.CENTER);
		this.finish.setHorizontalAlignment(JLabel.CENTER);
		add(this.finish);
	}

	public JButton getReset() {
		return this.reset;
	}

	private void setReset() {
		this.reset.setBounds(150, 100, 100, 50);
		this.reset.setText("RESTART");
		this.reset.setBackground(new Color(68, 102, 170));
		this.reset.setForeground(Color.white);
		this.reset.addActionListener(this);
		this.reset.setFont(new Font("NanumGothicBold", Font.PLAIN, 15));
		this.reset.setVerticalAlignment(JButton.CENTER);
		this.reset.setHorizontalAlignment(JButton.CENTER);
		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if (target == this.reset) {
//				OneToFiftyFrame.c.stop();
				OneToFiftyPanel.ctrlStart = false; // == stop() ????
				OneToFiftyFrame.a.resetAll();
				Runnable b = OneToFiftyFrame.a;
				OneToFiftyFrame.c = new Thread(b);
				OneToFiftyFrame.c.start(); // 이전 스레드 어떻게 되는건가 흠
				dispose();
			}
		}

	}

}

class OneToFiftyPanel extends JPanel implements ActionListener, Runnable {
	private Random rn = new Random();

	private JButton[] map = new JButton[25]; // 맵
	private int[][] numMap = new int[2][25]; // front , back

	public static int cnt = 1;
	private JLabel title = new JLabel("1 to 50");
	private JLabel printCnt = new JLabel("" + cnt);

	public static DecimalFormat form = new DecimalFormat("#.###");
	public static Double milli = 0.0;
	public static JLabel timer = new JLabel();
	
	public static boolean ctrlStart = true;

	public OneToFiftyPanel() {
		setNumMap();
		setLayout(null);
		setBounds(0, 0, OneToFiftyFrame.SIZE, OneToFiftyFrame.SIZE);
		setBackground(Color.white);
		setTitle();
		setTimer();
		setPrintCnt();
		setMapButton();
	}

	public void resetAll() {
		setNumMap(); // 넘버 맵 초기화
		ctrlStart = true; // 스레드 판별값
		cnt = 1;
		milli = 0.0;
		timer.setText("0.000");
		this.printCnt.setText("" + cnt);
		resetButtonNum();
	}

	private void setNumMap() {
		inputNumMap();
		shuffleNumMap();
	}

	private void inputNumMap() {
		for (int i = 1; i <= 50; i++) {
			if (i <= 25) {
				this.numMap[0][i - 1] = i;
			} else {
				this.numMap[1][i - 26] = i;
			}
		}
	}

	private void shuffleNumMap() {
		for (int i = 0; i < 5000; i++) {
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

	private void resetButtonNum() {
		for (int i = 0; i < this.map.length; i++) {
			this.map[i].setText("" + this.numMap[0][i]);
			this.map[i].setBackground(new Color(68, 102, 170));
		}
	}

	private void setTimer() {
		timer.setBounds(130, 100, OneToFiftyFrame.SIZE, 80);
		timer.setBackground(new Color(250, 238, 224));
		timer.setForeground(new Color(68, 102, 170));
		timer.setFont(new Font("NanumGothicBold", Font.PLAIN, 20));
		timer.setHorizontalAlignment(JLabel.LEFT);
		timer.setText("0.000");
		add(timer);
	}

	private void setTitle() {
		this.title.setBounds(0, 0, OneToFiftyFrame.SIZE, 120);
		this.title.setForeground(new Color(68, 102, 170));
		this.title.setFont(new Font("NanumGothicBold", Font.PLAIN, 40));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		add(this.title);
	}

	private void setMapButton() {
		int x = OneToFiftyFrame.SIZE / 2 - ((70 * 5) + (20)) / 2;
		int y = ((70 * 5) + (20)) / 2; // 좌표 설정이 이상함 135 이 가운데

		for (int i = 0; i < this.map.length; i++) {
			// 버튼 생성
			this.map[i] = new JButton();
			this.map[i].setBounds(x, y, 70, 70);
			this.map[i].setBackground(new Color(68, 102, 170));

			this.map[i].addActionListener(this);

			// 폰트 관리
			this.map[i].setText("" + this.numMap[0][i]);
			this.map[i].setForeground(Color.white);
			this.map[i].setFont(new Font("NanumGothicBold", Font.BOLD, 29));

			// 패널에 버튼 추가
			add(this.map[i]);

			// 버튼마다 좌표 바꾸는것
			x += 70 + 5;
			if (i % 5 == 4) {
				x = OneToFiftyFrame.SIZE / 2 - ((70 * 5) + (20)) / 2;
				y += 70 + 5;
			}
		}

	}

	private void setPrintCnt() {
		this.printCnt.setBounds(-150, 100, OneToFiftyFrame.SIZE, 80);
		this.printCnt.setBackground(new Color(250, 238, 224));
		this.printCnt.setForeground(new Color(68, 102, 170));
		this.printCnt.setFont(new Font("NanumGothicBold", Font.PLAIN, 20));
		this.printCnt.setHorizontalAlignment(JLabel.RIGHT);
		add(this.printCnt);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			for (int i = 0; i < this.map.length; i++) {
				if (target == this.map[i] && target.getText().equals("" + cnt)) {
					if (cnt < 26) {
						this.map[i].setText("" + this.numMap[1][i]);
						cnt++;
					} else if (cnt < 51) {
						this.map[i].setText("");
						this.map[i].setBorderPainted(false);
						this.map[i].setBackground(Color.white);
						cnt++;
					}

					if (cnt < 51) {
						this.printCnt.setText("" + cnt);
					}
				}
			}

			if (cnt == 51) {
				new OTFResultFrame();
			}
		}
	}

	@Override
	public void run() {
		
		while (ctrlStart) {
			
			try {
				if(cnt == 1) {
					timer.setText("0.000");
				}
				else if (cnt > 1) {
					milli += 0.001;
					timer.setText("" + form.format(milli));
				}
				Thread.sleep(1);
			} catch (Exception e) {
			}
			if (cnt == 51) { // 50 까지 다 클릭하면 실행 종료
//				OneToFiftyFrame.c.stop();
				ctrlStart = false;
				break;
			}
		}
	}
}

class OneToFiftyFrame extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static OneToFiftyPanel a;
	public static Thread c;
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int SIZE = 620; // 버튼 마다 5씩 띄우기

	public OneToFiftyFrame() {
		setLayout(null);
		setTitle("1 To 50");
		setBounds(width / 2 - SIZE / 2, height / 2 - SIZE / 2, SIZE, SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		a = new OneToFiftyPanel();
		add(a);
		setVisible(true);
		revalidate();
		cRun();
	}

	private void cRun() {
		Runnable b = a;
		c = new Thread(b);
		c.start(); // 스레드 시작
	}
}

public class Test_003_GUI_1to50 {

	public static void main(String[] args) {
		OneToFiftyFrame otff = new OneToFiftyFrame();
	}

}
