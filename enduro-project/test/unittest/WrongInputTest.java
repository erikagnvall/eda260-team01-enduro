package unittest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import enduro.MarathonSorter;

public class WrongInputTest {
	private MarathonSorter sorter;

	@Before
	public void setUp() {
		sorter = new MarathonSorter();
	}

	/*
	 * @Test public void testNoFinishTime() {
	 * 
	 * try {
	 * sorter.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
	 * sorter
	 * .readFinishFile("./test/unittest/unit-test-files/fakeWrongFinish.txt");
	 * sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
	 * sorter
	 * .createResultFile("./test/unittest/unit-test-files/fakeFinishResult.txt"
	 * ); } catch (Exception e) { System.err.println(e); }
	 * 
	 * try { BufferedReader in = new BufferedReader(new FileReader(
	 * "./test/unittest/unit-test-files/fakeFinishResult.txt"));
	 * assertEquals("StartNr; Namn; TotalTid; StartTider; Måltider",
	 * in.readLine());
	 * assertEquals("1; Anders Asson; --.--.--; 12.00.00; Slut?",
	 * in.readLine()); in.close(); } catch (FileNotFoundException e) {
	 * System.err.println(e); } catch (IOException e) { System.err.println(e); }
	 * }
	 * 
	 * @Test public void testNoStartTime() {
	 * 
	 * try {
	 * sorter.readStartFile("./test/unittest/unit-test-files/fakeWrongStart.txt"
	 * );
	 * sorter.readFinishFile("./test/unittest/unit-test-files/fakeFinish.txt");
	 * sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
	 * sorter
	 * .createResultFile("./test/unittest/unit-test-files/fakeStartResult.txt");
	 * } catch (Exception e) { System.err.println(e); }
	 * 
	 * try { BufferedReader in = new BufferedReader(new FileReader(
	 * "./test/unittest/unit-test-files/fakeStartResult.txt"));//
	 * assertEquals("StartNr; Namn; TotalTid; StartTider; Måltider",
	 * in.readLine());
	 * assertEquals("1; Anders Asson; --.--.--; Start?; 12.30.00",
	 * in.readLine()); in.close(); } catch (FileNotFoundException e) {
	 * System.err.println(e); } catch (IOException e) { System.err.println(e); }
	 * }
	 */
	@Test
	public void testManyStartTime() {

		try {
			sorter
					.readStartFile("./test/unittest/unit-test-files/fakeManyStart.txt");
			sorter
					.readFinishFile("./test/unittest/unit-test-files/fakeFinish.txt");
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
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

	@Test
	public void testManyFinishTime() {

		try {
			sorter
					.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
			sorter
					.readFinishFile("./test/unittest/unit-test-files/fakeManyFinish.txt");
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
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
					"1; Anders Asson; 00.30.00; 12.00.00; 12.30.00; Flera måltider? 12.34.00",
					in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	@Test
	public void testFastTime() {

		try {
			sorter
					.readStartFile("./test/unittest/unit-test-files/fakeStart.txt");
			sorter
					.readFinishFile("./test/unittest/unit-test-files/fakeFastFinish.txt");
			sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
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
					"1; Anders Asson; 00.10.00; 12.00.00; 12.10.00; Omöjlig Totaltid?",
					in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
