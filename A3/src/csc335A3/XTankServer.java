package csc335A3;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * When a client connects, a new thread is started to handle it.
 */
public class XTankServer 
{
	static ArrayList<ObjectOutputStream> sq;
	static ArrayList<Socket> sockets;
	static Hashtable<String, Player> players;
	public static int test;
	private static int[][] startingPoints;
	
    public static void main(String[] args) throws Exception 
    {
    	int[][] points = {{300,500}, {500,500}, {700,500}, {100,400}, {300,400}, {500,400}, {700,400}};
    	startingPoints = points;
    	System.out.println("-----------");
		System.out.println(InetAddress.getLocalHost());
    	System.out.println("-----------");
		sq = new ArrayList<>();
		sockets = new ArrayList<>();
		players = new Hashtable<String, Player>();
		
        try (var listener = new ServerSocket(59896)) 
        {
            System.out.println("The XTank server is running...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) 
            {
                pool.execute(new XTankManager(listener.accept()));
            }
        }
    }

    private static class XTankManager implements Runnable 
    {
        private Socket socket;

        XTankManager(Socket socket) { this.socket = socket; }

        @Override
        public void run() 
        {
            System.out.println("Connected: " + socket);
            try 
            {
            	InputStream in = socket.getInputStream();
            	OutputStream out = socket.getOutputStream();
            	ObjectInputStream obIn = new ObjectInputStream(in);
            	ObjectOutputStream obOut = new ObjectOutputStream(out);
            	
                sq.add(obOut);
                sockets.add(socket);
                int[] coords;
                //int xcoord;
                while (true)
                {
                	coords = (int[]) obIn.readObject();
                	int x = coords[0];
                	int y = coords[1];
                	int z = coords[2];
                	String ori ="up";
                	if (coords[2] == 2) {
                		ori = "right";
                	} else if (coords[2] == 3) {
                		ori = "down";
                	} else if (coords[2] == 4) {
                		ori = "left";
                	}
                	
                	System.out.println("coords = " + x + ", " + y + " "+ ori);
                	
                	//add players to hashtable
                	String name = socket.getRemoteSocketAddress().toString();
                	if (!players.containsKey(name)) {
                		players.put(name, new Player(name));
                		int index = (players.size()-1)%10;
                		int[] xY = startingPoints[index];
                		players.get(name).getTank().getMove().setX(xY[0]);
                		players.get(name).getTank().getMove().setY(xY[1]);
                		obOut.writeObject(name);
                		obOut.flush();
                		obOut.writeObject(players.get(name));
                		obOut.flush();
                	} else {
                		players.get(name).getTank().getMove().setX(coords[0]);
                		players.get(name).getTank().getMove().setY(coords[1]);
                		players.get(name).getTank().setOrientation(ori);
                	}
                	
                	//debug code
                	System.out.println("Name in player class: "+players.get(name).getName());
                	
                	
                	ArrayList<Player> playerList = new ArrayList<Player>();
                	for (String i: players.keySet()) {
                		playerList.add(players.get(i));
                	}
                	
                	for (ObjectOutputStream o: sq)
                	{
                    	System.out.println("o = " + o);
                    	
                    	o.writeInt(-2);
                    	o.flush();
                    	o.reset();
                    	o.writeObject(playerList);
                    	o.flush();
                    	o.writeInt(-1);
                        o.flush();
                	}
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Error:" + socket);
            } 
            finally 
            {
                try { 
                	String name = socket.getRemoteSocketAddress().toString();
                	players.remove(name);
                    System.out.println("Closed: " + socket);
                    int index = sockets.indexOf(socket);
                    sockets.remove(index);
                    sq.remove(index);
                    socket.close(); 
                    } 
                catch (IOException e) {}
                
            }
        }
    }
    
}


