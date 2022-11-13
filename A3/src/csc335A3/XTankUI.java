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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class XTankUI
{
	private ArrayList<Player> players;
	// The location and direction of the "tank"
	private Player playerOne = new Player("player 1");
	int[] coords = {300,500};
	Tank tank = playerOne.getTank();
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
		players = new ArrayList<>();
		players.add(playerOne);
		display = new Display();
		Shell shell = new Shell(display);
		shell.setText("xtank");
		shell.setLayout(new FillLayout());
		shell.setSize(1200,800);
		canvas = new Canvas(shell, SWT.COLOR_BLACK);
		
		//Create the tank coords
		//TankMovement tankMove = new TankMovement(x, y, canvas, shell);
		
		//Tanks
		canvas.addPaintListener(event -> {
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getTank().getOrientation() == "up" || players.get(i).getTank().getOrientation() == "down") {
					event.gc.fillRectangle(canvas.getBounds());
					Device device = Display.getCurrent();
					int rbg[] = players.get(i).getTank().getModel().getRBG();
					Color c = new Color(device, rbg[0],rbg[1],rbg[2]);
					//event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
					event.gc.setBackground(c);
					event.gc.fillRectangle(players.get(i).getTank().getMove().getX(), players.get(i).getTank().getMove().getY(),
								players.get(i).getTank().getMove().getRecWidth(), players.get(i).getTank().getMove().getRecHeight());
					event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
					event.gc.fillOval(players.get(i).getTank().getMove().getX(),players.get(i).getTank().getMove().getY() + players.get(i).getTank().getMove().getLineWidth(), players.get(i).getTank().getMove().getRecWidth(), players.get(i).getTank().getMove().getRecWidth());
					event.gc.setLineWidth(3);
					event.gc.drawLine(players.get(i).getTank().getMove().getX()+players.get(i).getTank().getMove().getLineWidth(), players.get(i).getTank().getMove().getY()
							  + players.get(i).getTank().getMove().getLineWidth(),
						      players.get(i).getTank().getMove().getX()+players.get(i).getTank().getMove().getLineWidth(), players.get(i).getTank().getMove().getY()
						      + players.get(i).getTank().getMove().getLineHeight());
				}
				else {
					event.gc.fillRectangle(canvas.getBounds());
					Device device = Display.getCurrent();
					int rbg[] = players.get(i).getTank().getModel().getRBG();
					Color c = new Color(device, rbg[0],rbg[1],rbg[2]);
					//event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
					event.gc.setBackground(c);
					event.gc.fillRectangle(players.get(i).getTank().getMove().getX(), players.get(i).getTank().getMove().getY(),
								players.get(i).getTank().getMove().getRecWidth(), players.get(i).getTank().getMove().getRecHeight());
					event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
					event.gc.fillOval(players.get(i).getTank().getMove().getX() +  players.get(i).getTank().getMove().getLineHeight(),  players.get(i).getTank().getMove().getY(), players.get(i).getTank().getMove().getRecHeight(), players.get(i).getTank().getMove().getRecHeight());					event.gc.setLineWidth(3);
					event.gc.drawLine(players.get(i).getTank().getMove().getX() + players.get(i).getTank().getMove().getLineHeight(),
							players.get(i).getTank().getMove().getY() + players.get(i).getTank().getMove().getLineHeight(),
						      players.get(i).getTank().getMove().getX()+ players.get(i).getTank().getMove().getLineWidth(),
						      players.get(i).getTank().getMove().getY() + players.get(i).getTank().getMove().getLineHeight());
				}
			}
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
				Missle missle = new Missle(players.get(0).getTank().getMove().getX(), players.get(0).getTank().getMove().getY());	
				if(e.keyCode == SWT.SPACE) { //Fire 
					if(players.get(0).getTank().getMove().getDirection() == "up") {
						for(;missle.getY() > 0 ;) {
								canvas.addPaintListener(event -> {
									event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));

								});	
								missle.shootUp();		
								//canvas.redraw();

						}
					}
					else if(players.get(0).getTank().getMove().getDirection() == "down") {
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
					else if(players.get(0).getTank().getMove().getDirection() == "right") {
						for(;missle.getX() < 1170 ;) {
								canvas.addPaintListener(event -> {
									event.gc.fillOval(missle.getX(), missle.getY(), 5, 5);
									//event.gc.drawOval(missle.getX(),missle.getY(),5,5);
								});	
								missle.shootRight();		
						//canvas.redraw();
						}
					}
					else if(players.get(0).getTank().getMove().getDirection() == "left") {
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
					players.get(0).getTank().getMove().action(e);
				}
				try {
					int[] temp = {players.get(0).getTank().getMove().getX(),players.get(0).getTank().getMove().getY()};
					out.writeObject(temp);
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
			int[] temp = {players.get(0).getTank().getMove().getX(),players.get(0).getTank().getMove().getY()};
			out.writeObject(temp);
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
				/*Object test = "fail";
				System.out.println("HERE1");
				try {
					test = in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("HERE2");
				System.out.println(test);
				System.out.println("HERE3");*/
				if (in.available() > 0)
				{
					System.out.println("here a");
					int temp = in.readInt();
					ArrayList<Player> playerList;
					try {
						System.out.println("here b");
						Object obj =  in.readObject();
						System.out.println("here c");
						System.out.println(obj.getClass());
						//ArrayList<Player> playerList =(ArrayList<Player>) obj;
						playerList = (ArrayList<Player>) obj;
						
						//This will link all players to this UI
						//players = playerList;
						
						System.out.println("Success! name of first player is: " + playerList.get(0).getName());
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



