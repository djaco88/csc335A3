package csc335A3;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Missle implements Serializable{

	private static final long serialVersionUID = -1827142864999109750L;
	
	private int x;
	private int y;
	
	Missle(int x, int y){
		this.x = x;
		this.y = y;

	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	void setX(int x) {
		this.x = x;
	}
	
	void setY(int y) {
		this.y = y;
	}
	
	void shootUp() {
		y -= 10;
	}
	void shootDown() {
		y += 10;
	}
	void shootRight() {
		x += 10;
	}
	void shootLeft() {
		x -= 10;
	}
}
