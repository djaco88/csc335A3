package csc335A3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Missle {

	private int x;
	private int y;
	
	Missle(int x, int y, String direction){
		this.x = x;
		this.y = y;
		if(direction == "up") {
			shootUp();
		}
	}
	
	void shootUp() {
		//for(int i = y; )
	}
}
