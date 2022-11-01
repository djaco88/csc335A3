/* Author:
 * Purpose:
 */
package csc335A3;

public class Tank {
	
	private String userName;
	private TankModel model;
	//upper Left and Right coordinates
	private int[] uRCoor;
	private int[] uLCoor;
	//lower Left and Right coordinates
	private int[] lRCoor;
	private int[] lLCoor;
	// hitpoints/armor
	private int hP;
	private String orientation;
	
	// TODO: make weapons class and add a variable here to store it
	
	public Tank(String user, int[] uL, int[] uR, int[] lR, int[] lL, int hP) {
		this.hP = hP;
		this.userName = user;
		this.uLCoor = uL;
		this.uRCoor = uR;
		this.lRCoor = lR;
		this.lLCoor = lL;
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
	// may want to take in an int[][] instead of four int[]
	public void setCoors(int[] uL, int[] uR, int[] lR, int[] lL) {
		this.uLCoor = uL;
		this.uRCoor = uR;
		this.lRCoor = lR;
		this.lLCoor = lL;
	}
	public int[][] getCoors(){
		int[][] coors = {this.uRCoor,this.uLCoor,this.lRCoor,this.lLCoor};
		return coors;
	}
	// TODO: implement a function to change all coordinates by a set value

}
