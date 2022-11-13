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
	
    public static void main(String[] args) throws Exception 
    {
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
                	
                	System.out.println("coords = " + x + ", " + y);
                	
                	//add players to hashtable
                	String name = socket.getRemoteSocketAddress().toString();
                	if (!players.containsKey(name)) {
                		players.put(name, new Player(name));
                	}
                	players.get(name).getTank().getMove().setX(coords[0]);
                	players.get(name).getTank().getMove().setY(coords[1]);
                	
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
                    	
                    	//debugging test: try to send string to client
                    	//o.writeObject(players);
                    	o.writeObject(playerList);
                    	o.flush();
                    	o.writeInt(-1);
    					//o.writeInt(ycoord);
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


