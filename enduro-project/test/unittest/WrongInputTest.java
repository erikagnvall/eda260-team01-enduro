package unittest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import enduro.Sorter;


public class WrongInputTest {
private Sorter sorter;
	
	@Before public void setUp() {
		sorter = new Sorter();
	}
	
@Test public void testNoFinishTime() {
		
		try {
			sorter.readStartFile("fakeCorrectStart.txt");
			sorter.readFinishFile("fakeWrongFinish.txt");
			sorter.readNameFile("fakeName.txt");
			sorter.createResultFile("fakeFinishResult.txt");
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("fakeFinishResult.txt"));//
			assertEquals("StartNr; Namn; Totaltid; Starttid; Måltid", in.readLine());
			assertEquals("1; Anders Asson; --.--.--; 12.00.00; Slut?", in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}


@Test public void testNoStartTime() {
	
	try {
		sorter.readStartFile("fakeWrongStart.txt");
		sorter.readFinishFile("fakeCorrectFinish.txt");
		sorter.readNameFile("fakeName.txt");
		sorter.createResultFile("fakeStartResult.txt");
	} catch (Exception e) {
		System.err.println(e);
	}
	
	try {
		BufferedReader in = new BufferedReader(new FileReader("fakeStartResult.txt"));//
		assertEquals("StartNr; Namn; Totaltid; Starttid; Måltid", in.readLine());
		assertEquals("1; Anders Asson; --.--.--; Start?; 12.30.00", in.readLine());
		in.close();
	} catch (FileNotFoundException e) {
		System.err.println(e);
	} catch (IOException e) {
		System.err.println(e);
	}
}
@Test public void testManyStartTime() {
	
	try {
		sorter.readStartFile("fakeManyStart.txt");
		sorter.readFinishFile("fakeCorrectFinish.txt");
		sorter.readNameFile("fakeName.txt");
		sorter.createResultFile("fakeStartResult.txt");
	} catch (Exception e) {
		System.err.println(e);
	}
	
	try {
		BufferedReader in = new BufferedReader(new FileReader("fakeStartResult.txt"));//
		assertEquals("StartNr; Namn; Totaltid; Starttid; Måltid", in.readLine());
		assertEquals("1; Anders Asson; 00.30.00; 12.00.00; 12.30.00; Flera starttider? 12.12.00", in.readLine());
		in.close();
	} catch (FileNotFoundException e) {
		System.err.println(e);
	} catch (IOException e) {
		System.err.println(e);
	}
}

@Test public void testManyFinishTime() {
	
	try {
		sorter.readStartFile("fakeCorrectStart.txt");
		sorter.readFinishFile("fakeManyFinish.txt");
		sorter.readNameFile("fakeName.txt");
		sorter.createResultFile("fakeStartResult.txt");
	} catch (Exception e) {
		System.err.println(e);
	}
	
	try {
		BufferedReader in = new BufferedReader(new FileReader("fakeStartResult.txt"));//
		assertEquals("StartNr; Namn; Totaltid; Starttid; Måltid", in.readLine());
		assertEquals("1; Anders Asson; 00.30.00; 12.00.00; 12.30.00; Flera måltider? 12.34.00", in.readLine());
		in.close();
	} catch (FileNotFoundException e) {
		System.err.println(e);
	} catch (IOException e) {
		System.err.println(e);
	}
}
@Test public void testFastTime() {
	
	try {
		sorter.readStartFile("fakeCorrectStart.txt");
		sorter.readFinishFile("fakeFastFinish.txt");
		sorter.readNameFile("fakeName.txt");
		sorter.createResultFile("fakeStartResult.txt");
	} catch (Exception e) {
		System.err.println(e);
	}
	
	try {
		BufferedReader in = new BufferedReader(new FileReader("fakeStartResult.txt"));//
		assertEquals("StartNr; Namn; Totaltid; Starttid; Måltid", in.readLine());
		assertEquals("1; Anders Asson; 00.10.00; 12.00.00; 12.10.00; Omöjlig totaltid?", in.readLine());
		in.close();
	} catch (FileNotFoundException e) {
		System.err.println(e);
	} catch (IOException e) {
		System.err.println(e);
	}
}

}
