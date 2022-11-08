package csc335A3;

public class Wall {
	private int length;
	private int width;
	private int[] coordinates;
	
	public Wall(int l, int w, int[]coordinates) {
		this.setLength(l);
		this.setWidth(w);
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
	
}
