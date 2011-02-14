package unittest.racer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import enduro.racer.InputHandler;
import enduro.racer.Configuration.ConfigParser;

public class InputHandlerTest {
	
	/**
	 * creating a simple InputHandler and checks if it accepts bogus files.
	 * This shouldn't give any errors as the checks are later.
	 * 
	 * Added for consistency (if anything is changed in the future)
	 */
	@Test public void testBasicInput() {
		try {
			InputHandler handler = new InputHandler();
			handler.addFinishFile("/test1");
			handler.addStartFile("/test2");
			handler.addNameFile("/test3");
		} catch(Exception E) {
			fail();
		}
	}
	
	/**
	 * test in accordance with acceptance test 17_unit (default input file for previous work)
	 */
	@Test public void testRealInput() {
		ConfigParser.getInstance("config.conf");
		InputHandler handler = new InputHandler();
		handler.addNameFile("acceptanceTest/result/17_unit/namnfil.txt");
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt");
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt");
		handler.addStartFile("acceptanceTest/result/17_unit/starttider.txt");
		
		String[] lines = handler.print().split("\n");
		
		assertEquals("SENIOR", lines[0]);
		assertEquals("StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål", lines[1]);
		assertEquals("1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34", lines[2]);
		assertEquals("2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?", lines[3]);
		assertEquals("JUNIOR", lines[4]);
		assertEquals("StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål", lines[5]);
		assertEquals("101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06", lines[6]);
		assertEquals("102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07", lines[7]);
		assertEquals("103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00", lines[8]);
		assertEquals("Icke existerande startnummer", lines[9]);
		assertEquals("StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål", lines[10]);
		assertEquals("210; ; ; ; 0; --:--:--; ; ; ; --:--:--; 13.16.07; ; ; Start?", lines[11]);
	}

}
