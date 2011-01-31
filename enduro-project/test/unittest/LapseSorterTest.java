package unittest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import enduro.LapseSorter;

public class LapseSorterTest {
	private LapseSorter sorter;

	@Before
	public void setUp() {
		sorter = new LapseSorter();
	}

	@Test
	public void testReadStartTimeFile() {
		try {
			sorter
					.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	@Test
	public void testReadFinishTimeFile() {
		try {
			sorter
					.readFinishFile("./test/unittest/unit-test-files/fakeFinish.txt");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testImpossibleLap() {

		try {
			sorter
					.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
			sorter
					.readFinishFile("./test/unittest/unit-test-files/fakeManyFinish.txt");
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
			sorter
					.createResultFile("./test/unittest/unit-test-files/fakeImpossibleLap.txt");
		} catch (Exception e) {
			System.err.println(e);
		}

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./test/unittest/unit-test-files/fakeImpossibleLap.txt"));//
			assertEquals(
					"StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Start; Varvning1; Mål",
					in.readLine());
			assertEquals(
					"1; Anders Asson; 2; 00.50.00; 00.40.00; 00.10.00; 12.00.00; 12.40.00; 12.50.00; Omöjlig varvtid?",
					in.readLine());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
