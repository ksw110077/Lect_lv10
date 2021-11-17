package sokobanGUI;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Alert extends JFrame implements WindowListener{
	
	private JLabel text = new JLabel();
	private boolean chkClose;
	public Alert() {
		this.chkClose = false;
		setBounds(100 + 250, 100 + 360, 400, 120);
		this.text.setText("You have Completed this level, the next stage will level now.");
		this.text.setBounds(0, 0, 300, 120);
		this.text.setFont(new Font("NanumGothicBold", Font.PLAIN, 10));
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
		addWindowListener(this);
		setVisible(true);
		revalidate();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.chkClose = true;
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean getChkClose() {
		return chkClose;
	}
	
	
	
}
