package grimpan;

import java.awt.Color;

public class GrimRect {
		private int x, y,w, h;
		private Color c;
		
		public GrimRect(int x, int y, int w, int h, Color c) {
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
		public int getH() {
			return h;
		}
		public Color getC() {
			return c;
		}
		public void setC(Color c) {
			this.c = c;
		}
		
}
