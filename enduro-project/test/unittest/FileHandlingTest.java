package unittest;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import enduro.IOHandler;

public class FileHandlingTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testSingleFinishFile() {
		String[] args = { "fakeStart.txt", "fakeFinish.txt" };
		try {
			IOHandler.main(args);
			BufferedReader in = new BufferedReader(new FileReader("ResultFile.txt"));
			in.readLine();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testMultipleFinishFiles() {
		String[] args = { "fakeStart.txt", "-m", "fakeFinishList.txt" };
		try {
			IOHandler.main(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
