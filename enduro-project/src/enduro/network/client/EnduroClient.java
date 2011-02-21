package enduro.network.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * A simple client for sending information linewise from a file to a server
 * 
 */
public class EnduroClient {
	Scanner scan;
	Socket clientSocket;
	PrintWriter out;

	/**
	 * Sets up the client
	 * 
	 * @param address
	 *            String containing IP address of server
	 * @param port
	 *            int port number to connect too
	 * @param type
	 *            String stating if the client is sending Start or Goal data
	 */
	public EnduroClient(String address, int port, String type) {
		try {
			scan = new Scanner(new FileInputStream("./times.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clientSocket = new Socket(address, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.println(type);
		System.out.println("Client is handling " + type);
	}

	/**
	 * Registers a single line to the server
	 * 
	 * @param line
	 */
	public void registerLine(String line) {
		if (line != null) {
			out.println(line);
			out.flush();
			System.out.println("Client: " + line);
		}
	}

	/**
	 * Terminates the connection
	 */
	public void shutDown() {
		out.close();
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs the client until quit command is given
	 * 
	 * @throws IOException
	 */
	public void run() throws IOException {
		BufferedReader terminalScan = new BufferedReader(new InputStreamReader(
				System.in));
		while (true) {
			String s = null;
			if (scan.hasNext())
				s = scan.nextLine();
			else {
				// scan = null;
				// scan = new Scanner(new FileInputStream("./times.txt"));
				break;
			}
			registerLine(s);
			String terminal = null;
			if (terminalScan.ready())
				terminal = terminalScan.readLine();
			if (terminal != null && terminal.equals("quit"))
				break;
		}
		shutDown();
	}

	/**
	 * Main method to start the client
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner terminalScan = new Scanner(System.in);
		if (args.length != 3) {
			System.out
					.println("Invalid arguments, must match \"IP port Start/Goal\"");
			System.exit(-1);
		}
		EnduroClient client = new EnduroClient(args[0], Integer
				.parseInt(args[1]), args[2]);
		try {
			client.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
