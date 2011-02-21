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
 * A Java client, mostly self-written.
 * 
 * @author Rick
 * 
 */
public class EnduroClient {
	Scanner scan;
	Socket clientSocket;
	PrintWriter out;

	public EnduroClient(String address, int port, String type) throws UnknownHostException, IOException {
		scan = new Scanner(new FileInputStream("./times.txt"));
			clientSocket = new Socket(address, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		out.println(type);
		System.out.println("Client is handling " + type);
	}

	public void run() throws IOException {
		BufferedReader terminalScan = new BufferedReader(new InputStreamReader(
				System.in));
		while (true) {
			String s = null;
			if (scan.hasNext())
				s = scan.nextLine();
			else {
				scan = null;
				scan = new Scanner(new FileInputStream("./times.txt"));
			}
			String terminal = null;
			if (terminalScan.ready())
				terminal = terminalScan.readLine();
			if (s != null) {
				out.println(s);
				System.out.println("client: " + s);
			}
			if (terminal != null && terminal.equals("quit"))
				break;
		}
		out.close();
		clientSocket.close();
	}

	public static void main(String[] args){
		// Scanner terminalScan = new Scanner(System.in);
		if (args.length != 3) {
			System.out
					.println("Invalid arguments, must match \"IP port Start/Goal\"");
			System.exit(-1);
		}
		try {
			EnduroClient client = new EnduroClient(args[0], Integer.parseInt(args[1]), args[2]);
			client.run();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
