package csc335A3;
//Quinn Jones

public class Boarders {
	private int length;
	private int width;
	private int depth;

	Boarders(int l, int w, int d){
		this.length = l;
		this.width = w;
		this.depth = d;
	}
	
	int getl() {return length;}
	
	int getw() {return width;}
	
	int getd() { return depth;}
	
	int [] top() {
		int[] val= {10,10, width - 35, 10};
		return val;
	}
	
	int [] bottom() {
		int[] val = {10, length-55,width - 35, length-55 };
		return val;
	}
	int [] right() {
		int[] val = {width-35, 10,width - 35, length-35 };
		return val;
	}	
	int [] left() {
		int[] val = {10, 10,width - 35, 10 };
		return val;
	}
}
