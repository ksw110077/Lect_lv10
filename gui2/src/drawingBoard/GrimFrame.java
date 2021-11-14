package drawingBoard;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GrimFrame extends JFrame{
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private final int W = this.dm.width;
	private final int H = this.dm.height;
	
	public GrimFrame() {
		setLayout(null);
		setBounds(this.W / 2 - 1000 / 2,this.H / 2 - 1000 / 2,1014, 1037);
		// 프레임과 패널 크기 동일하게 맞추려면
		// 프레임 X + 14, Y + 37; // 확실하지 않음
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new GrimBoard());
		
		setVisible(true);
		revalidate();
	}
}
