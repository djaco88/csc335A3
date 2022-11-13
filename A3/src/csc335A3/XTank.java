package csc335A3;
//David
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class XTank 
{
	public static void main(String[] args) throws Exception 
    {
        try (var socket = new Socket("127.0.0.1", 59896)) 
        {
        	InputStream in = socket.getInputStream();
        	OutputStream out = socket.getOutputStream();
        	ObjectOutputStream obOut = new ObjectOutputStream(out);
        	obOut.flush();
        	ObjectInputStream obIn = new ObjectInputStream(in);
            var ui = new XTankUI(obIn, obOut);
            ui.start();
        }
    }
}



