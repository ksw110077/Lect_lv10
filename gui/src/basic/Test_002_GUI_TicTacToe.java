package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TTTGPanel extends JPanel implements ActionListener {
	private JButton[] map = new JButton[9];
	private int[] mark = new int[9];
	private JButton reset = new JButton();

	private int player = 1;
	private int win;

	public TTTGPanel() {
		setLayout(null);
		setBounds(0, 0, TTTGFrame.SIZE, TTTGFrame.SIZE);
		setReset();
		setMap();
	}

	private void setReset() {
		this.reset.setText("RESET");
		this.reset.setBounds(TTTGFrame.SIZE / 2 - 50, TTTGFrame.SIZE - 100, 100, 40);
		this.reset.setBackground(new Color(201, 216, 182));
		this.reset.addActionListener(this);
		add(this.reset);
	}

	private void resetMap() {
		this.mark = new int[9];
		this.player = 1;
		this.win = 0;
		for (int i = 0; i < this.map.length; i++) {
			this.map[i].setBackground(new Color(214, 210, 196));
		}
	}

	private void setMap() {
		int x = TTTGFrame.SIZE / 2 - ((120 * 3) + (6)) / 2;
		int y = TTTGFrame.SIZE / 2 - ((120 * 3) + (6)) / 2;

		for (int i = 0; i < this.map.length; i++) {
			this.map[i] = new JButton();
			this.map[i].setBounds(x, y, 120, 120);
			this.map[i].setBackground(new Color(214, 210, 196));
			this.map[i].addActionListener(this);
			add(this.map[i]);

			x += 120 + 3;
			if (i % 3 == 2) { // 3 개씩 만들어 질 때 마다
				x = TTTGFrame.SIZE / 2 - ((120 * 3) + (6)) / 2; // x 값 초기화
				y += 120 + 3;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		for (int i = 0; i < this.map.length; i++) {
			if (target == this.map[i] && this.mark[i] == 0 && this.win == 0) {
				if (this.player == 1) {
					target.setBackground(new Color(255, 245, 218));
				} else {
					target.setBackground(new Color(247, 218, 217));
				}
				this.mark[i] = this.player;

				chkWin();

				this.player = this.player == 1 ? 2 : 1;
			}
		}

		if (e.getSource() == this.reset) {
			System.out.println("reset");
			resetMap();
		}
	}

	private void chkWin() {
		this.win = this.win == 0 ? checkGa() : this.win;
		this.win = this.win == 0 ? checkSe() : this.win;
		this.win = this.win == 0 ? checkRB() : this.win;
		this.win = this.win == 0 ? checkLB() : this.win;

		if (this.win != 0) {
			new WinFrame(String.format("플레이어%d 의 승", this.win));
		}
	}

	/*
	 * 0 1 2 3 4 5 6 7 8
	 */

	private int checkLB() { // 2, 4, 6
		int cnt = 0;
		for (int i = 1; i <= 3; i++) {
			if (this.mark[i * 2] == this.player) {
				cnt++;
			}
		}
		if (cnt == 3) {
			return this.player;
		}
		return 0;
	}

	/*
	 * 0 1 2 3 4 5 6 7 8
	 */

	private int checkRB() { // 0, 4, 8
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (this.mark[i * 4] == this.player) {
				cnt++;
			}
		}
		if (cnt == 3) {
			return this.player;
		}
		return 0;
	}

	/*
	 * 0 1 2 3 4 5 6 7 8
	 */

	private int checkSe() {
		for (int i = 0; i < 3; i++) {
			int cnt = 0;
			for (int j = 0; j < 3; j++) {
				if (this.mark[i + j * 3] == this.player) {
					cnt++;
				}
			}
			if (cnt == 3) {
				return this.player;
			}
		}
		return 0;
	}

	/*
	 * 0 1 2 3 4 5 6 7 8
	 */

	private int checkGa() {
		for (int i = 0; i < this.mark.length; i += 3) {
			int cnt = 0;
			for (int j = 0; j < 3; j++) {
				if (this.mark[i + j] == this.player) {
					cnt++;
				}
			}
			if (cnt == 3) {
				return this.player;
			}
		}
		return 0;
	}
}

class WinFrame extends JFrame {
	private JLabel text = new JLabel();

	public WinFrame(String winText) {
		setTitle("GAME CLEAR");
		setLayout(null);
		setBounds(TicFrame.width / 2 - 150 / 2, TicFrame.height / 2 - 150 / 2, 150, 150);
		text.setBounds(0, -20, 150, 150);
		text.setText(winText);
		text.setVerticalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		setVisible(true);
		revalidate();
	}
}

class TTTGFrame extends JFrame {
	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;

	public static final int SIZE = 606;

	public TTTGFrame() {
		setLayout(null);
		setTitle("TIC TAC TOE");
		setBounds(width / 2 - SIZE / 2, height / 2 - SIZE / 2, SIZE, SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jLabelTitle();
		add(new TTTGPanel());
		setVisible(true);
		revalidate();
	}

	private void jLabelTitle() {
		JLabel title = new JLabel();
		title.setFont(title.getFont().deriveFont(30.0f));
		title.setForeground(new Color(93, 84, 74));
		title.setBounds(50, -190, 500, 500);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setText("TIC TAC TOE");
		add(title);
	}
}

public class Test_002_GUI_TicTacToe {

	public static void main(String[] args) {
		TTTGFrame tf = new TTTGFrame();
	}

}
