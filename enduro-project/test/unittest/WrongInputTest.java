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
			assertEquals("1; Anders Asson; --.--.--; 12.30.00; Slut?", in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
