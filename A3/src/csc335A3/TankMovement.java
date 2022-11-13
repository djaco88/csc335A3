//Quinn Jones

package csc335A3;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class TankMovement implements Serializable{
	private static final long serialVersionUID = -3251434859338361504L;
	
	public int x;
	public int y; 
	public int recWidth;
	public int recHeight;
	public int lineWidth;
	public int lineHeight;
	private int directionRight = 10;
	private int directionLeft = -10;
	private int directionUp = -10;
	private int directionDown = 10;
	public String rotation = "up";
	Canvas canvas;
	Shell shell;
	
	TankMovement(int x, int y){
		this.x = x;
		this.y = y;
		this.recWidth = 40;
		this.recHeight = 90;
		this.lineWidth = 20;
		this.lineHeight = -10;
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
	
	int getRecHeight() {
		return recHeight;
	}
	
	int getRecWidth() {
		return recWidth;
	}
	
	int getLineWidth() {
		return lineWidth;
	}
	
	int getLineHeight() {
		return lineHeight;
	}
	
	String getDirection() {
		return rotation;
	}
	
	void action(KeyEvent e){
		switch(e.keyCode) {
		case SWT.ARROW_DOWN:{
			if(rotation != "down") {
				//flip
				flipDown();
				rotation = "down";
				
			}
			if(y< 670) {
				y += directionDown;
			}
			break;
		}
		case SWT.ARROW_UP:{
			if(rotation != "up") {
				//Flip
				flipUp();
				rotation = "up";
			}
			if(y > 20) {
				y += directionUp;
			}
			break;
		}
		case SWT.ARROW_LEFT:{
			//x += directionLeft;
			if(rotation != "left") {
				//flip
				flipLeft();
				rotation = "left";
			}
			if(x > 5) {
				x += directionLeft;
			}
			break;
		}
		case SWT.ARROW_RIGHT:{
			if(rotation != "right") {
				flipRight();
				//flip
				rotation = "right";
			}
			if(x < 1140) {
				x += directionRight;
			}
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
	
	void flipUp() {
		if(rotation == "down") {
			y-=90;
			x-=40;
		}
		if(rotation == "left") {
			x+=50;
			y-=70;
		}
		if(rotation == "right") {
			y-=25;
			x-=70;
		}
		recHeight = 90;
		recWidth = 40;
		lineWidth = 20;
		lineHeight = -10;
	}
	
	void flipDown() {
		if(rotation == "up") {
			y+=90;
			x+=40;
		}
		if(rotation == "left") {
			x+=65;
			y+=25;
		}
		if(rotation == "right") {
			y+=65;
			x+=65;
		}
		recHeight = -90;
		recWidth = -40;
		lineWidth = -20;
		lineHeight = 10;
	}
	
	void flipLeft() {
		if(rotation == "up") {
			y+=65;
			x-=25;
		}
		if(rotation == "down") {
			x-=25;
			y-=65;
		}
		if(rotation == "right") {
			y+=40;
			x-=90;
		}
		recHeight = -40;
		recWidth = 90;
		lineWidth = 10;
		lineHeight = -20;
	}
	
	void flipRight() {
		if(rotation == "up") {
			y+=65;
			x-=25;
		}
		if(rotation == "down") {
			x-=25;
			y-=65;
		}
		if(rotation == "right") {
			y+=40;
			x-=90;
		}
		recHeight = 40;
		recWidth = -90;
		lineWidth = -10;
		lineHeight = 20;
	}
}
