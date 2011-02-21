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
public class EnduroServer implements Runnable{
	ServerSocket serverSocket;
	public EnduroServer(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + port);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length != 1){
			System.out.println("Arguments must match \"port\"");
		}
		EnduroServer serv = new EnduroServer(Integer.parseInt(args[0]));
		serv.run();
	}
	
	public void quit(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		System.out.println("Server is now running on port " + serverSocket.getLocalPort());
		int counter = 0;
		while (true)
			try {
				new EnduroServerThread(serverSocket.accept(), counter++).start();
			} catch (IOException e) {
				counter--;
				e.printStackTrace();
			}
	}
}
