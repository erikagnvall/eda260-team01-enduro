package enduro.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
		// Creates a new socket for incoming connections. Both sockets work on
		// port 44444
		// TODO: Create new threads for each incoming connection (Client)
		try {
			serverSocket = new ServerSocket(44444);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 44444");
			System.exit(-1);
		}
		// Creates a new socket for outgoing connections
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Accept failed: 44444");
			System.exit(-1);
		}
		// Creates new writers and readers for sockets
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		// Ugly hack deluxe, only accepts specific strings in specifik
		// orders.
		if (in.readLine().equals("Nu kommer konden!")) {
			System.out.println("Client: Nu kommer kunden!");
			out.println("Har ni frågor, har ni problem?");
			System.out.println("Server: Har ni frågor, har ni problem?");
		}
		if (in.readLine().equals("Nu ser det ut som skit i repositoryt!")) {
			System.out.println("Client: Nu ser det ut som skit i repositoryt!");
			out.println("Vem körde inte update innan commit?");
			System.out.println("Server: Vem körde inte update innan commit?");
		}
		if (in.readLine().equals("Bendix")) {
			System.out.println("Client: Bendix");
			out.println("=O");
			System.out.println("Server: =O");
		}
		// Close it all up
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}
