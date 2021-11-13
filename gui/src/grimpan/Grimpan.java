package grimpan;

import javax.swing.JFrame;

public class Grimpan extends JFrame{
	public Grimpan() {
		setLayout(null);
		setBounds(100,100,800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new GrimBoard());
		setVisible(true);
		revalidate();
	}
}
