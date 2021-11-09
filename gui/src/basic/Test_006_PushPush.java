package basic;

import java.awt.Color;

import javax.swing.JFrame;

class PushNemo{
	private int x, y, w, h, owner;
	private Color c;
	public PushNemo() {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class PushFrame extends JFrame{
	public PushFrame() {
	}
	
	
}

public class Test_006_PushPush {

	public static void main(String[] args) {
	}

}
