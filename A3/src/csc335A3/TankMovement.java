package csc335A3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class TankMovement {
	
	public int x;
	public int y; 
	private int directionRight = 10;
	private int directionLeft = -10;
	private int directionUp = -10;
	private int directionDown = 10;
	public String rotation = "up";
	Canvas canvas;
	Shell shell;
	
	TankMovement(int x, int y, Canvas canvas, Shell shell){
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
	
	String getDirection() {
		return rotation;
	}
	
	void action(KeyEvent e){
		switch(e.keyCode) {
		case SWT.ARROW_DOWN:{
			if(rotation != "down") {
				//flip
				rotation = "down";
			}
			y += directionDown;
			break;
		}
		case SWT.ARROW_UP:{
			if(rotation != "up") {
				//Flip
				rotation = "up";
			}
			y += directionUp;
			break;
		}
		case SWT.ARROW_LEFT:{
			x += directionLeft;
			if(rotation != "left") {
				//flip
				rotation = "left";
			}
			x += directionLeft;
			break;
		}
		case SWT.ARROW_RIGHT:{
			if(rotation != "right") {
				//flip
				rotation = "right";
			}
			x += directionRight;
			break;
		}
		case SWT.SPACE: {
			System.out.println("space\n");
		}
		default: {
			break;
		}
		}
	}
	
	
	
}
