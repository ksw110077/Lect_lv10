package runHorses;

import javax.swing.JFrame;

public class HorseFrame extends JFrame {
	public HorseFrame() {
		setLayout(null);
		setBounds(100,100,1500,900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new HorsePanel());
		
		setVisible(true);
		revalidate();
	}
}
