package csc335A3;
//Quinn Jones, Jake Davis
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
	private String clientName;
	private Player curPlayer;
	//int[] coords = {300,500};
	//Tank tank = playerOne.getTank();
	//private int x = 300;
	//private int y = 500; 

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
		//players.add(playerOne);
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
			
			System.out.println("Cur "+curPlayer.getTank().getMove().getX()+", "+curPlayer.getTank().getMove().getY());
			
			for(int i = 0; i < players.size(); i++) {
				//debug
				System.out.println("Player: "+i+" at "+players.get(i).getTank().getMove().getX()+", "+players.get(i).getTank().getMove().getY()+ " "+players.get(i).getTank().getOrientation());
				
				if(players.get(i).getTank().getOrientation().equals("up") || players.get(i).getTank().getOrientation().equals("down")) {
					
					System.out.println("HERRRREEEE");
					
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
				else if(players.get(i).getTank().getOrientation().equals("left") || players.get(i).getTank().getOrientation().equals("right")) {
					event.gc.fillRectangle(canvas.getBounds());
					Device device = Display.getCurrent();
					int rbg[] = players.get(i).getTank().getModel().getRBG();
					Color c = new Color(device, rbg[0],rbg[1],rbg[2]);
					//event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
					event.gc.setBackground(c);
					event.gc.fillRectangle(players.get(i).getTank().getMove().getX(), players.get(i).getTank().getMove().getY(),
								players.get(i).getTank().getMove().getRecWidth(), players.get(i).getTank().getMove().getRecHeight());
					//event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
					//event.gc.fillOval(players.get(i).getTank().getMove().getX() -  players.get(i).getTank().getMove().getLineHeight(),  players.get(i).getTank().getMove().getY(), players.get(i).getTank().getMove().getRecHeight(), players.get(i).getTank().getMove().getRecHeight());	
					//event.gc.setLineWidth(3);

					/*event.gc.drawLine(players.get(i).getTank().getMove().getX() + players.get(i).getTank().getMove().getLineHeight(),
							players.get(i).getTank().getMove().getY() + players.get(i).getTank().getMove().getLineHeight(),
						      players.get(i).getTank().getMove().getX()+ players.get(i).getTank().getMove().getLineWidth(),
						      players.get(i).getTank().getMove().getY() + players.get(i).getTank().getMove().getLineHeight());
				*/
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
				Missle missle = new Missle(curPlayer.getTank().getMove().getX(), curPlayer.getTank().getMove().getY());	
				if(e.keyCode == SWT.SPACE) { //Fire 
					if(curPlayer.getTank().getMove().getDirection().equals("up")) {
						for(;missle.getY() > 0 ;) {
								canvas.addPaintListener(event -> {
									event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));

								});	
								missle.shootUp();		
								//canvas.redraw();

						}
					}
					else if(curPlayer.getTank().getMove().getDirection().equals("down")) {
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
					else if(curPlayer.getTank().getMove().getDirection().equals("right")) {
						for(;missle.getX() < 1170 ;) {
								canvas.addPaintListener(event -> {
									event.gc.fillOval(missle.getX(), missle.getY(), 5, 5);
									//event.gc.drawOval(missle.getX(),missle.getY(),5,5);
								});	
								missle.shootRight();		
						//canvas.redraw();
						}
					}
					else if(curPlayer.getTank().getMove().getDirection().equals("left")) {
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
					curPlayer.getTank().getMove().action(e);
				}
				try {
					int[] temp = {curPlayer.getTank().getMove().getX(),curPlayer.getTank().getMove().getY()};
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

			int[] temp = {-1,-1};
			out.writeObject(temp);
			out.flush();
			try {
				clientName = (String) in.readObject();
				curPlayer = (Player) in.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("Success!"+clientName);
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
					int temp = in.readInt();
					ArrayList<Player> playerList;
					try {
						Object obj =  in.readObject();
						System.out.println(obj.getClass());
						//ArrayList<Player> playerList =(ArrayList<Player>) obj;
						playerList = (ArrayList<Player>) obj;
						
						//This will link all players to this UI
						players = playerList;
						
						System.out.println("Success! name of first player from server is: " + playerList.get(0).getName());
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int y = in.readInt();
					for(Player i: players) {
						if (i.getName() == clientName) {
							curPlayer = i;
						}
					}
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



