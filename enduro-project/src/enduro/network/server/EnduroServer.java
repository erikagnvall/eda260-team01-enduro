package enduro.network.server;

import java.io.*;
import java.net.*;

/**
 * Server example, blatently stolen from
 * http://download.oracle.com/javase/tutorial
 * /networking/sockets/clientServer.html although somewhat modified.
 * 
 * @author Rick
 * 
 */
public class EnduroServer {
	ServerSocket serverSocket;
	public EnduroServer(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not listen on port: " + port);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length != 1){
			System.out.println("Arguments must match \"port\"");
		}
		EnduroServer serv = new EnduroServer(Integer.parseInt(args[0]));
		try {
			serv.run();
		} catch (IOException e) {
			System.exit(-1);
		}
	}
	
	
	
	public void run() throws IOException{
		System.out.println("Server is now running on port " + serverSocket.getLocalPort());		
		while (true)
		    new EnduroServerThread(serverSocket.accept(), 1).start();
	}
}
