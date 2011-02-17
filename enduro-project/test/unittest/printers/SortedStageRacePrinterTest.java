package unittest.printers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;
import enduro.racer.printer.SortedLapRacePrinter;
import enduro.racer.printer.SortedStageRacePrinter;

public class SortedStageRacePrinterTest {

	private Racer racer;
	private SortedStageRacePrinter printer = new SortedStageRacePrinter();
	@Before
	public void setUp(){
		ConfigParser.getInstance().overLoadValue("stages", "2");
		printer.setHeaderInformation(new String[]{"StartNr", "Namn","Klubb", "MC"});
	}
	
	
	@Test public void assertTestStagesAreCorrect() {
		assertEquals(2,ConfigParser.getInstance().getIntConf("stages"));
	}
	
	@Test public void testPrintTopPart() {
		StringBuilder out = new StringBuilder();
		out.append("Plac; StartNr; Namn; Klubb; MC; Totaltid; #Etapper");
		
		for(int i = 1; i <= ConfigParser.getInstance().getIntConf("stages"); i++) {
			out.append("; Etapp");
			out.append(i);
		}
		assertEquals(out.toString(), printer.printTopInformation());
	}
	
	@Test public void testPrintWithoutPlace() {
		racer = new Racer(new String("2; Bengt Bsson; Bklobb; BTM").split("; "));
		
		racer.addStartTime(new Time("11.01.00"), 1);
		racer.addFinishTime(new Time("12.11.00"), 1);
		
		racer.addStartTime(new Time("12.31.00"), 2);
		racer.addFinishTime(new Time("12.43.02"), 2);

		HashMap<String, String> extraInformation = new HashMap<String, String>();
		
		assertEquals("; 2; Bengt Bsson; Bklobb; BTM; 01.22.02; 2; 01.10.00; 00.12.02", printer.print(racer, extraInformation));
	}
	
	@Test public void testPrintWithPlace() {
		racer = new Racer(new String("2; Bengt Bsson; Bklobb; BTM").split("; "));
		
		racer.addStartTime(new Time("11.01.00"), 1);
		racer.addFinishTime(new Time("12.11.00"), 1);
		
		racer.addStartTime(new Time("12.31.00"), 2);
		racer.addFinishTime(new Time("12.43.02"), 2);

		HashMap<String, String> extraInformation = new HashMap<String, String>();
		extraInformation.put("position", "1");
		assertEquals("1; 2; Bengt Bsson; Bklobb; BTM; 01.22.02; 2; 01.10.00; 00.12.02", printer.print(racer, extraInformation));
	}
	
}
