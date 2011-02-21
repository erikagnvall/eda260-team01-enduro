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
public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(44444);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 44444");
			System.exit(-1);
		}
	
		while (true)
		    new EnduroServerThread(serverSocket.accept(), 1).start();

	}
}
