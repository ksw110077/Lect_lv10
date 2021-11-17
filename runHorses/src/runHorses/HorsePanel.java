package runHorses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class HorsePanel extends MyUtil{
	private final int SIZE = 5;
	private final int ENDX = 1350;

	private JButton startAndReset = null;
	private JLabel timer = null;
	private JLabel [] record = new JLabel[this.SIZE];

	private Horse[] horses = new Horse[this.SIZE];
	
	private boolean start = false;
	private int ms = 0;
	private int rank = 0;
	
	public HorsePanel() {
		setLayout(null);
		setBounds(0,0,1500,900);
		setHorse();
		setButton();
		setTimer();
	}


	private void setHorse() {
		// 100 * 100
		// 120 + 130
		int x = 10;
		int y = 120;
		for(int i = 0; i < this.SIZE; i++) {
			this.horses[i] = new Horse(i + 1, x, y, 100,100);
			y += 130;
		}
	}

	private void setTimer() {
		this.timer = new JLabel();
		this.timer.setText("0.000");
		this.timer.setBounds(120,10, 100, 50);
		this.timer.setFont(new Font("NanumGothicBold",Font.PLAIN, 17));
		add(this.timer);
	}

	private void setButton() {
		this.startAndReset = new JButton();
		this.startAndReset.setBounds(10,10,100,50);
		this.startAndReset.setText("START");
		this.startAndReset.addActionListener(this);
		add(this.startAndReset);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// y 시작점 220 증가 130
		// 50 to 1450
		
		int xS = 50;
		int xE = 1450;
		int y = 220;
		
//		if(this.start) {
//			runHorses();
//		}
		
		for(int i =0; i < this.SIZE; i++) {
			g.setColor(Color.black);
			g.drawLine(xS, y, xE, y);
			g.drawImage(this.horses[i].getImage().getImage(), this.horses[i].getX(), this.horses[i].getY(), null);
			y += 130;
		}
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if(target == this.startAndReset) {
				this.start = this.start == false ? true : false;
				updateBtn();
			}
		}
	}


	private void updateBtn() {
		if(this.start) {
			this.startAndReset.setText("RESET");
		}
		else {
			this.startAndReset.setText("START");
			reset();
		}
	}

	private void reset() {
		// reset horse and image
		int x = 10;
		int y = 120;
		for(int i = 0; i < this.horses.length; i++) {
			this.horses[i] = new Horse(i + 1, x, y, 100,100);
			if(this.record[i] != null) {
				this.record[i].setText("");
			}
			y += 130;
		}
		// reset Timer
		this.timer.setText("0.000");
		
		// reset Rank
		this.rank = 0;
		
		// reset ms
		this.ms = 0;
	}


	private void runHorses() {
		Random rn = new Random();
		for(int i = 0; i < this.horses.length; i++) {
			if(this.horses[i].getState() == this.horses[i].READY) {
				this.horses[i].setState(this.horses[i].RUN);
			}
			int r = rn.nextInt(2);
			int chk = 0;
			
			if(r == 1 && this.horses[i].getState() == this.horses[i].RUN) {
				this.horses[i].setX(this.horses[i].getX() + 1);
				if(this.horses[i].getX() == this.ENDX) {
					if(chk == 0) {
						this.rank ++;
						this.horses[i].setState(this.horses[i].GOAL);
						this.horses[i].setRank(this.rank);
						this.horses[i].setRecord(String.format("%3d.%3d", this.ms / 1000, this.ms % 1000));
						rankLabel(i);
						chk ++;
					}
					else {
						i --;
					}
				}
			}
		}
	}
	
	private void rankLabel(int i) {
		if(this.record[i] == null) {
			this.record[i] = new JLabel(this.horses[i].getRank() + "등 : " + this.horses[i].getRecord());
			this.record[i].setBounds(this.horses[i].getX() - 100, this.horses[i].getY(), 100,100);
			add(this.record[i]);
		}
		else{
			this.record[i].setText(this.horses[i].getRank() + "등 : " + this.horses[i].getRecord());
		}
	}


	@Override
	public void run() {
		while(true) {
			if(this.start && this.rank != this.SIZE) {
				this.ms += 1;
				this.timer.setText(String.format("%3d.%3d", this.ms / 1000, this.ms % 1000 ));
				runHorses();
			}
			try {
				Thread.sleep(1); // 1 / 1000 초
			} catch (Exception e) {
			}
		}
	}
}
