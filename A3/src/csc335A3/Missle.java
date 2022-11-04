package csc335A3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Missle {

	private int x;
	private int y;
	
	Missle(int x, int y, String direction, Canvas canvas, Shell shell){
		this.x = x;
		this.y = y;
		canvas.addPaintListener(event -> {
			//event.gc.fillRectangle(canvas.getBounds());
			//event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			//event.gc.fillRectangle(x, y, 50, 100);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x, y+25, 50, 50);
			//event.gc.setLineWidth(4);
			//event.gc.drawLine(x+25, y+25, x+25, y-15);
		});	
		if(direction == "up") {
			shootUp();
		}
	}
	
	void shootUp() {
		System.out.println("Shooting\n");
	}
}
