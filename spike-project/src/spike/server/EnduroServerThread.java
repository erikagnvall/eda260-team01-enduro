package spike.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EnduroServerThread extends Thread {
	private Socket socket = null;
	private PrintWriter out;
	private BufferedReader in;

	public EnduroServerThread(Socket socket, int number) throws IOException {
		super("EnduroServerThread " + number);
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));
	}

	public void run() {

		System.out.println("server");
		while (true) {
			String s = null;
			try {
				s = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (s.equals("quit"))
				break;
			out.println(s);
			System.out.println("server:" + s);
		}
		// Close it all up
		System.out.println("hejdå");
		out.close();
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
