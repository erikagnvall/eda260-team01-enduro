package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;

import regressiontest.ListTest;
import regressiontest.PVGRunner;

@ListTest(sorter=ListTest.Sorter.marathon)
@RunWith(PVGRunner.class)
public class MarathonSorterTest {

	@Test
	public void testReadWrite() {
		assertTrue(PVGRunner.testSuccess);
	}

	@Test
	public void testReadingFiles() {

		try {

			BufferedReader in = new BufferedReader(new FileReader("result.temp.mar"));
			in.readLine();
			in.readLine();
			assertEquals(in.readLine(), "1; Anders Asson; 00.30.00; 12.00.00; 12.30.00; Flera måltider? 13.00.00 13.23.34");
			assertEquals(in.readLine(), "2; Bengt Bsson; 00.14.00; 12.00.00; 12.14.00; Flera måltider? 12.41.00 13.15.16; Omöjlig Totaltid?");
			in.readLine();
			in.readLine();
			
			
			assertEquals(in.readLine(), "101; Chris Csson; 00.22.00; 12.00.00; 12.22.00; Flera måltider? 12.42.00 13.05.06");
			assertEquals(in.readLine(), "102; David Dsson; 00.23.00; 12.00.00; 12.23.00; Flera måltider? 12.43.00 13.12.07");
			assertEquals(in.readLine(), "103; Erik Esson; 00.24.00; 12.00.00; 12.24.00; Flera starttider? 12.15.00; Flera måltider? 12.44.00 13.16.07");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
