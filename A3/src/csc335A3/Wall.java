package csc335A3;

import java.io.Serializable;

public class Wall implements Serializable{
	private static final long serialVersionUID = 4987435494666781947L;
	
	private int length;
	private int width;
	private int[] coordinates;
	private boolean vertical;
	
	public Wall(int l, int[]coordinates, boolean vertical) {
		this.setVertical(vertical);
		this.setLength(l);
		this.setWidth(1);
		this.setCoordinates(coordinates);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
	
	
}
