package csc335A3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class XTankUI
{
	Hashtable<String, Player> players;
	// The location and direction of the "tank"
	private int x = 300;
	private int y = 500; 

	private Canvas canvas;
	private Display display;
	
	ObjectInputStream in; 
	ObjectOutputStream out;
	
	public XTankUI(ObjectInputStream in, ObjectOutputStream out)
	{
		this.in = in;
		this.out = out;
	}
	
	public void start()
	{
		display = new Display();
		Shell shell = new Shell(display);
		shell.setText("xtank");
		shell.setLayout(new FillLayout());
		shell.setSize(1200,800);
		canvas = new Canvas(shell, SWT.COLOR_BLACK);
		
		//Create the tank coords
		TankMovement tankMove = new TankMovement(x, y, canvas, shell);
		
		//Tank
		canvas.addPaintListener(event -> {
			event.gc.fillRectangle(canvas.getBounds());
			Device device = Display.getCurrent();
			Color red = new Color(device, 255,0,0);
			//event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			event.gc.setBackground(red);
			event.gc.fillRectangle(tankMove.getX(), tankMove.getY(), 40, 90);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(tankMove.getX(), tankMove.getY()+20, 40, 40);
			event.gc.setLineWidth(3);
			event.gc.drawLine(tankMove.getX()+20, tankMove.getY()+20, tankMove.getX()+20, tankMove.getY()-10);
			
		});	

		canvas.addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {
				System.out.println("mouseDown in canvas");
			} 
			public void mouseUp(MouseEvent e) {} 
			public void mouseDoubleClick(MouseEvent e) {} 
		});

		//Move Up, Down, Left, Right
		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				//System.out.println("key " + e.character);
				// update tank location
				Missle missle = new Missle(tankMove.getX(), tankMove.getY());	
				if(e.keyCode == SWT.SPACE) { //Fire 
					if(tankMove.getDirection() == "up") {
						for(;missle.getY() > 0 ;) {
								canvas.addPaintListener(event -> {
									event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));

								});	
								missle.shootUp();		
								//canvas.redraw();

						}
					}
					else if(tankMove.getDirection() == "down") {
						for(;missle.getY() < 750 ;) {
								canvas.addPaintListener(event -> {
									event.gc.fillOval(missle.getX(), missle.getY(), 5, 5);
									//event.gc.drawOval(missle.getX(),missle.getY(),5,5);
							
								});	
								missle.shootDown();		
						//canvas.redraw();
						//display.sleep();
						}
					}
					else if(tankMove.getDirection() == "right") {
						for(;missle.getX() < 1170 ;) {
								canvas.addPaintListener(event -> {
									event.gc.fillOval(missle.getX(), missle.getY(), 5, 5);
									//event.gc.drawOval(missle.getX(),missle.getY(),5,5);
								});	
								missle.shootRight();		
						//canvas.redraw();
						}
					}
					else if(tankMove.getDirection() == "left") {
						for(;missle.getX() > 0 ;) {
								canvas.addPaintListener(event -> {
									event.gc.fillOval(missle.getX(), missle.getY(), 5, 5);
									//event.gc.drawOval(missle.getX(),missle.getY(),5,5);
								});	
								missle.shootLeft();		
						//canvas.redraw();
						}
					}
				}
				else {
					tankMove.action(e);
				}
				try {
					out.writeInt(tankMove.getY());
					out.flush();
					//out.writeInt(x);
				}
				catch(IOException ex) {
					System.out.println("The server did not respond (write KL).");
				}

				canvas.redraw();
			}
			public void keyReleased(KeyEvent e) {} 
		});

		try {
			out.writeInt(tankMove.getY());
			out.flush();
			//out.writeInt(x);
		}
		catch(IOException ex) {
			System.out.println("The server did not respond (initial write).");
		}				
		Runnable runnable = new Runner();
		display.asyncExec(runnable);
		shell.open();
		while (!shell.isDisposed()) 
			if (!display.readAndDispatch())
				display.sleep();

		display.dispose();		
	}
	
	class Runner implements Runnable
	{
		public void run() 
		{
			try {
				if (in.available() > 0)
				{
					System.out.println("here a");
					String test;
					try {
						System.out.println("here b");
						test = (String) in.readObject();
						//players = (Hashtable<String, Player>) in.readObject();
						System.out.println("here c");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y = in.readInt();
					//x = in.readInt();
					//System.out.println("y = " + y);
					canvas.redraw();
				}
			}
			catch(IOException ex) {
				System.out.println("The server did not respond (async).");
			}				
            display.timerExec(150, this);
		}
	};	
}



