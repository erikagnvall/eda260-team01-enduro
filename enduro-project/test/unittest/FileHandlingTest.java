package unittest;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import enduro.FileHandler;

public class FileHandlingTest {

	@Before
	public void setUp() {
	}

	@Test
	public void testSingleFinishFile() {
		String[] args = { "fakeStart.txt", "fakeFinish.txt" };
		try {
			FileHandler.main(args);
			BufferedReader in = new BufferedReader(new FileReader(
					"ResultFile.txt"));
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
			FileHandler.main(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
