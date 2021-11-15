package basic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class ImagePanel extends JPanel {
	
	ImageIcon icon = null;
	
	public ImagePanel(ImageIcon icon) {
		setLayout(null);
		setBounds(0,0,400,500);
		this.icon = icon;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// drawImage(Image, x, y, null/*매핑타입*/,)
		g.drawImage(this.icon.getImage(), 0, 0, null);
		
		repaint();
	}
}

public class Ex_004 extends JFrame{

	JLabel image = null;
	Image im = new ImageIcon("images/속상해.png").getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH/*이미지 랜더링하는 타입*/);
	ImageIcon icon = new ImageIcon(this.im); // 경로
	
	public Ex_004() {
		super("image");
		setLayout(null);
		setBounds(100,100,400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		setImageLabel();
		add(new ImagePanel(this.icon));
		
		setVisible(true);
		revalidate();
	}
	
	private void setImageLabel() {
		// ImageIcon 이미지 처리
		this.image = new JLabel(icon);
		
		this.image.setBounds(0,0,400,500);
		add(this.image);
	}
	
	
	public static void main(String[] args) {
		new Ex_004();
	}

}
