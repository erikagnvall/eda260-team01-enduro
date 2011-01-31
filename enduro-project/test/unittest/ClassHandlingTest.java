package unittest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import enduro.MarathonSorter;

public class ClassHandlingTest {
	private MarathonSorter sorter;

	@Before
	public void setUp() {
		sorter = new MarathonSorter();
	}

	@Test
	public void testClassRegistration() {
		try {
			sorter
					.readNameFile("./test/unittest/unit-test-files/fakeClassName.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(sorter.getClasses().get(0).getName(), "Seniorer");
		assertEquals(sorter.getClasses().get(1).getName(), "Juniorer");
	}

	@Test
	public void testClassRegContestant() {
		try {
			sorter
					.readNameFile("./test/unittest/unit-test-files/fakeClassName.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator itr = sorter.getClasses().get(0).getList().iterator();
		assertEquals(itr.next(), new Integer(1));
		assertEquals(itr.next(), new Integer(2));
		itr = sorter.getClasses().get(1).getList().iterator();
		assertEquals(itr.next(), new Integer(100));
	}

	
}