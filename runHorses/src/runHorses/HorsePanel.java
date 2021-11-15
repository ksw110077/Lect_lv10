package runHorses;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class HorsePanel extends MyUtil{
	
	private JButton startAndReset = null;
	private JLabel timer = null;
	private boolean start = false;
	
	public HorsePanel() {
		setLayout(null);
		setBounds(0,0,1500,900);
		setButton();
		setTimer();
	}
	
	
	private void setTimer() {
		this.timer = new JLabel();
		this.timer.setText("0,000");
		this.timer.setBounds(120,10, 100, 50);
		this.timer.setFont(new Font("NanumGothicBold",Font.PLAIN, 17));
		add(this.timer);
	}

	private void setButton() {
		this.startAndReset = new JButton();
		this.startAndReset.setBounds(10,10,100,50);
		this.startAndReset.setText("START");
		add(this.startAndReset);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton targer = 
		}
	}
}
