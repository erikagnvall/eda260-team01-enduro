//package unittest.network;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.UnknownHostException;
//import java.util.Scanner;
//
//import org.junit.*;
//
//import enduro.network.client.EnduroClient;
//import enduro.network.server.EnduroServer;
//
//public class NetworkTest {
//	EnduroServer server;
//	EnduroClient startClient;
//	EnduroClient goalClient;
//	Thread t;
//
//	public NetworkTest() throws UnknownHostException, IOException {
//		super();
//		File startFile = new File("./starttider.txt");
//		startFile.delete();
//		File finishFile = new File("./maltider.txt");
//		finishFile.delete();
////		t = new Thread(server);
////		t.start();
//	}
//	@Before
//	public void setUp(){
//		server = new EnduroServer(1337);
//		startClient = new EnduroClient("localhost", 1337, "Start");
//		goalClient = new EnduroClient("localhost", 1337, "Goal");
//		t = new Thread(server);
//		t.start();
//	}
//	
//	@After
//	public void tearDown(){
//		server.quit();
//		server = null;
//		startClient = null;
//		goalClient = null;
////		t.stop();
//		t = null;
//	}
//
//	
//
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testStartClient() throws FileNotFoundException {
//		try {
//			startClient.run();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		t.sleep(2000);
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Scanner times = new Scanner(new FileInputStream("./times.txt"));
//		Scanner startTimes = new Scanner(
//				new FileInputStream("./starttider.txt"));
//		while (times.hasNext() || startTimes.hasNext()) {
//			assertEquals(times.nextLine(), startTimes.nextLine());
//		}
//	}
//
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testGoalClient() throws FileNotFoundException {
//		try {
//			goalClient.run();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		t.stop();
////		try {
////			t.sleep(2000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		try {
////			Thread.sleep(1000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		Scanner times = new Scanner(new FileInputStream("./times.txt"));
//		Scanner finishTimes = new Scanner(new FileInputStream("./maltider.txt"));
//		while (times.hasNext() || finishTimes.hasNext()) {
//			assertEquals(times.nextLine(), finishTimes.nextLine());
//		}
//	}
//
//}
