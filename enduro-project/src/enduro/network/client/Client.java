package enduro.network.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * A Java client, mostly self-written.
 * @author Rick
 *
 */
public class Client {
	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner scan = new Scanner(System.in);
		Socket clientSocket = null;
		PrintWriter out = null;
		//Creates a socket for connecting to a server
		try {
			clientSocket = new Socket("127.0.0.1", 44444);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		//Outputs strings and then sleeps for 1 second to allow server to process data.
		System.out.println("client");
		while(true){
			String s = scan.nextLine();
			out.println(s);
			if(s.equals("quit")) break;
			System.out.println("client:" + s);
		}
		out.close();
		clientSocket.close();
	}
}
