package unittest;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import enduro.MarathonSorter;

public class MarathonSorterTest {
	private MarathonSorter sorter;

	@Before
	public void setUp() {
		sorter = new MarathonSorter();
	}

	@Test
	public void testReadStartTimeFile() {
		try {
			sorter.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	@Test
	public void testReadFinishTimeFile() {
		try {
			sorter.readFinishFile("./test/unittest/unit-test-files/fakeFinish.txt");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testReadingFiles() {
		try {
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
			sorter.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
			sorter.readFinishFile("./test/unittest/unit-test-files/fakeFinish.txt");
			sorter.createResultFile("./test/unittest/unit-test-files/fakesortertestresult.txt");
			ArrayList<String[]> list = new ArrayList<String[]>();
			BufferedReader in = new BufferedReader(new FileReader(
					"./test/unittest/unit-test-files/fakesortertestresult.txt"));
			while (in.ready()) {
				list.add(in.readLine().split("; "));
			}
			assertEquals("1; 12.00.00", list.get(1)[0] + "; " + list.get(1)[3]);
			assertEquals("1; 12.30.00", list.get(1)[0] + "; " + list.get(1)[4]);
			assertEquals("1; Anders Asson", list.get(1)[0] + "; "
					+ list.get(1)[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateResultFile() {
		try {
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
			sorter.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
			sorter.readFinishFile("./test/unittest/unit-test-files/fakeFinish.txt");
			sorter.createResultFile("./test/unittest/unit-test-files/fakesortertestresult.txt");
		} catch (Exception e) {
			System.err.println(e);
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader("./test/unittest/unit-test-files/fakesortertestresult.txt"));
			assertEquals("StartNr; Namn; TotalTid; StartTider; Måltider",in.readLine());
			assertEquals("1; Anders Asson; 00.30.00; 12.00.00; 12.30.00",in.readLine());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateSortedResultFile() {
		try {
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
			sorter.readStartFile("./test/unittest/unit-test-files/fakeStart2.txt");
			sorter.readFinishFile("./test/unittest/unit-test-files/fakeFinish2.txt");
			sorter.createSortedResultsFile("./test/unittest/unit-test-files/fakeSorterTestResult2.txt");
		} catch (Exception e) {
			System.err.println(e);
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader("./test/unittest/unit-test-files/fakeSorterTestResult2.txt"));
			assertEquals("1; Anders Asson; 00.30.00; 12.00.00; 12.30.00",in.readLine());
			assertEquals("3; Citron Csson; 00.32.00; 12.00.00; 12.32.00",in.readLine());
			assertEquals("2; Bosse Bsson; 00.33.00; 12.00.00; 12.33.00",in.readLine());
			assertEquals("4; Erik Esson; 00.33.00; 12.00.00; 12.33.00",in.readLine());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}