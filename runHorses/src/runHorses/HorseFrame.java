package runHorses;

import javax.swing.JFrame;

public class HorseFrame extends JFrame {
	
	HorsePanel p = new HorsePanel();
	
	public HorseFrame() {
		setLayout(null);
		setBounds(100,100,1500,900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(this.p);
		
		setVisible(true);
		revalidate();
		
		this.p.run();
	}
}
