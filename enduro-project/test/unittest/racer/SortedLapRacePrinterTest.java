package unittest.racer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import org.junit.Test;

import enduro.InputHandler.ConfigParser;
import enduro.racedata.Time;
import enduro.racer.Racer;
import enduro.racer.printer.SortedLapRacePrinter;

public class SortedLapRacePrinterTest {

	private Racer racer;
	private SortedLapRacePrinter printer = new SortedLapRacePrinter();
	
	@Test public void assertTestLapsesAreCorrect() {
		assertTrue(ConfigParser.getInstance().getIntConf("laps")==3);
	}
	
	@Test public void testPrintTopPart() {
		StringBuilder out = new StringBuilder();
		out.append("Plac; StartNr; Namn; #Varv; Totaltid");
		
		for(int i = 1; i <= ConfigParser.getInstance().getIntConf("laps"); i++) {
			out.append("; Varv");
			out.append(i);
		}
		
		assertEquals(out.toString(), printer.printTopInformation());
	}
	
	@Test public void testPrintWithoutPlace() {
		racer = new Racer(new String("2; Bengt Bsson; FMCK Bstad; BTM").split("; "));
		racer.addFinishTime(new Time("12.14.00"));
		racer.addFinishTime(new Time("12.41.00"));
		racer.addFinishTime(new Time("13.15.16"));
		racer.addStartTime(new Time("12.00.00"));

		HashMap<String, String> extraInformation = new HashMap<String, String>();
		
		assertEquals("; 2; Bengt Bsson; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16", printer.print(racer, extraInformation));
	}
	
	@Test public void testPrintWithPlace() {
		racer = new Racer(new String("2; Bengt Bsson; FMCK Bstad; BTM").split("; "));
		racer.addFinishTime(new Time("12.14.00"));
		racer.addFinishTime(new Time("12.41.00"));
		racer.addFinishTime(new Time("13.15.16"));
		racer.addStartTime(new Time("12.00.00"));

		HashMap<String, String> extraInformation = new HashMap<String, String>();
		extraInformation.put("position", "1");
		assertEquals("1; 2; Bengt Bsson; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16", printer.print(racer, extraInformation));
	}
	
}