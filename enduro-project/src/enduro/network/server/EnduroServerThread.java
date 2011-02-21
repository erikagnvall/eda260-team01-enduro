package enduro.network.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EnduroServerThread extends Thread {
	private final static int START = 0;
	private final static int GOAL = 1;

	private Socket socket = null;
	private PrintWriter out;
	private BufferedReader in;

	public EnduroServerThread(Socket socket, int number) throws IOException {
		super("EnduroServerThread " + number);
		this.socket = socket;
		// out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public void run() {

		int status = -1;
		String s = null;
		try {
			s = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (status == -1) {
			if (s.equals("Goal"))
				status = GOAL;
			else if (s.equals("Start"))
				status = START;
			else
				System.out.println("Unexpected file format from client");
		}
		System.out.println("Client handles " + s);
		FileWriter writer = null;
		try {
			switch (status) {
			case GOAL: {
				writer = new FileWriter("maltider.txt");
			}
			case START: {
				writer = new FileWriter("starttider.txt");
			}
			}
		} catch (Exception e) {
		}
		out = new PrintWriter(new BufferedWriter(writer), true);

		while (true) {
			try {
				s = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (s != null) {
				if (s.equals("quit"))
					break;
				out.println(s);
				System.out.println("client sends:" + s);
			}
		}
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
