package unittest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import regressiontest.ListTest;
import regressiontest.PVGRunner;

import enduro.MarathonSorter;

@ListTest(sorter=ListTest.Sorter.marathon)
@RunWith(PVGRunner.class)
public class WrongInputTest {
	
	@Test
	public void testManyStartTime() {

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"result.temp"));//
			in.readLine();
			in.readLine();
			in.readLine();
			in.readLine();
			in.readLine();
			in.readLine();
			in.readLine();
			in.readLine();
			assertEquals(
					"103; Erik Esson; 00.24.00; 12.00.00; 12.24.00; Flera starttider? 12.15.00; Flera måltider? 12.44.00 13.16.07",in.readLine());
			in.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Test
	public void testManyFinishTime() {

		try {
			BufferedReader in = new BufferedReader(new FileReader("result.temp"));
			in.readLine();
			in.readLine();
			assertEquals("1; Anders Asson; 00.30.00; 12.00.00; 12.30.00; Flera måltider? 13.00.00 13.23.34",in.readLine());
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
			BufferedReader in = new BufferedReader(new FileReader("result.temp"));
			in.readLine();
			in.readLine();
			in.readLine();
			assertEquals("2; Bengt Bsson; 00.14.00; 12.00.00; 12.14.00; Flera måltider? 12.41.00 13.15.16; Omöjlig Totaltid?",in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
