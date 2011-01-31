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
		String[] args = { "./test/unittest/unit-test-files/fakeStart.txt", "./test/unittest/unit-test-files/fakeFinish.txt" };
		try {
			IOHandler.main(args);
			BufferedReader in = new BufferedReader(new FileReader("./test/unittest/unit-test-files/ResultFile.txt"));
			in.readLine();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testMultipleFinishFiles() {
		String[] args = { "./test/unittest/unit-test-files/fakeStart.txt", "-m", "./test/unittest/unit-test-files/fakeFinish.txt" };
		try {
			IOHandler.main(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
