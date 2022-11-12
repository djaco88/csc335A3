/* Author:
 * Purpose:
 */
package csc335A3;

import org.eclipse.swt.SWT;

public class Tank {
	
	private String userName;
	private TankModel model;
	// move will keep track of orientation and x,y coordinates
	private TankMovement move;
	// hitpoints/armor
	private int hP;
	private int[] coords;
	private String orientation;
	
	// TODO: make weapons class and add a variable here to store it
	
	public Tank(String user, int x, int y, int hP) {
		this.sethP(hP);
		this.userName = user;
		//start with up orientation
		this.orientation = "up";
		int[] temp = {x,y};
		this.setCoords(temp);
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

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	public int gethP() {
		return hP;
	}

	public void sethP(int hP) {
		this.hP = hP;
	}
	
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
