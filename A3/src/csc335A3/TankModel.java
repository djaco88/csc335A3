/* Author:
 * Purpose:
 */
package csc335A3;

import java.io.Serializable;

public class TankModel implements Serializable{
	
	private static final long serialVersionUID = -8514351025877623441L;
	
	private String name;
	// rbg values for color
	private int r;
	private int b;
	private int g;
	
	public TankModel(String name, int r, int g, int b) {
		this.name = name;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setRBG(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int[] getRBG() {
		int[] rbg = {this.r,this.b,this.g};
		return rbg;
	}
	
	public String getName() {
		return this.name;
	}

}
