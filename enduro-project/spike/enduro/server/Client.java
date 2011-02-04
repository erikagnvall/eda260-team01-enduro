package enduro.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * A Java client, mostly self-written.
 * @author Rick
 *
 */
public class Client {
	public static void main(String[] args) throws InterruptedException, IOException {
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
		out.println("Nu kommer konden!");
		Thread.sleep(100);
		out.println("Nu ser det ut som skit i repositoryt!");
		Thread.sleep(100);
		out.println("Bendix");
		//Close it up
		out.close();
		clientSocket.close();
	}
}
