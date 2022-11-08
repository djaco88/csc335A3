/* Author:
 * Purpose:
 */
package csc335A3;

import org.eclipse.swt.SWT;

public class Tank {
	
	private String userName;
	private TankModel model;
	// coords
	private int x;
	private int y;
	// hitpoints/armor
	private int hP;
	private String orientation;
	
	// TODO: make weapons class and add a variable here to store it
	
	public Tank(String user, int x, int y, int hP) {
		this.hP = hP;
		this.userName = user;
		this.x = x;
		this.y = y;
		//start with up orientation
		this.orientation = "up";
	}
	
	//Getters and Setters
	public void setModel(TankModel model) {
		this.model = model;
	}
	public TankModel getModel() {
		return this.model;
	}
	public void setUserName(String user) {
		this.userName = user;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getOrientation() {
		return this.orientation;
	}
	public void setCoors(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int[] getCoors() {
		int[] coors = {this.x,this.y};
		return coors;
	}
	// TODO: implement a function to change all coordinates by a set value
	
	//Tank
	/*  Davids Tank Creating Function
	    canvas.addPaintListener(event -> {
		event.gc.fillRectangle(canvas.getBounds());
		event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		event.gc.fillRectangle(x, y, 50, 100);
		event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		event.gc.fillOval(x, y+25, 50, 50);
		event.gc.setLineWidth(4);
		event.gc.drawLine(x+25, y+25, x+25, y-15);
	*/
		
	
	
}
