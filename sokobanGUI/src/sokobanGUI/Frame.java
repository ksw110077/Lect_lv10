package sokobanGUI;

import javax.swing.JFrame;

public class Frame extends JFrame{
	public Frame() {
		
		setTitle("SOKOBAN");
		setLayout(null);
		setBounds(100,100,914,977);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Panel());
		
		setVisible(true);
		revalidate();
	}
}
