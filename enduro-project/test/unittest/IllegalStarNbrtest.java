package unittest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import enduro.MarathonSorter;

public class IllegalStarNbrtest {
	private MarathonSorter sorter;

	@Before
	public void setUp() {
		sorter = new MarathonSorter();
	}

	@Test
	public void testManyStartTime() {

		try {
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
			sorter
					.readStartFile("./test/unittest/unit-test-files/fakeExtraStart.txt");
			sorter
					.readFinishFile("./test/unittest/unit-test-files/fakeExtraFinish.txt");
			
			sorter
					.createResultFile("./test/unittest/unit-test-files/fakeStartResult.txt");
		} catch (Exception e) {
			System.err.println(e);
		}

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./test/unittest/unit-test-files/fakeStartResult.txt"));//
			assertEquals("StartNr; Namn; TotalTid; StartTider; Måltider", in
					.readLine());
			assertEquals(
					"1; Anders Asson; 00.30.00; 12.00.00; 12.30.00; Flera starttider? 12.12.00",
					in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
