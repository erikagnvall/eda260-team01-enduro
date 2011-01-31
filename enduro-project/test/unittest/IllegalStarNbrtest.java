package unittest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import enduro.MarathonSorter;
import enduro.racedata.RaceClass;

public class IllegalStarNbrtest {
	private MarathonSorter sorter;

	@Before
	public void setUp() {
		sorter = new MarathonSorter();
	}

	@Test
	public void testNoticeIllegalStart() {

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
		assertTrue(sorter.getClasses().contains(new RaceClass("Icke existerande startnummer")));

	}
	@Test
	public void testRightAmountIllegalStart() {

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
		ArrayList<RaceClass> temp = sorter.getClasses();
		assertEquals(3, temp.get(temp.indexOf(new RaceClass("Icke existerande startnummer"))).size());

	}
}
