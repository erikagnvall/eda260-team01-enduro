package unittest.printers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;
import enduro.racer.printer.StageRacePrinter;

public class StageRacePrinterTest {
	private Racer racer;
	private StageRacePrinter printer;

	@Before
	public void doBefore() {
		ConfigParser.getInstance().overLoadValue("stages", "2");
		printer = new StageRacePrinter();
		printer.setHeaderInformation(new String[] { "StartNr", "Namn", "Klubb",
				"MC" });
		
	}

	@Test
	public void assertTestStageAreCorrect() {
		// int i = (ConfigParser.getInstance().getIntConf("stages"));
		assertTrue(ConfigParser.getInstance().getIntConf("stages") == 2);
	}

	@Test
	public void testPrintTopPart() {
		StringBuilder out = new StringBuilder();
		out.append("StartNr; Namn; Klubb; MC; Totaltid; #Etapper");

		for (int i = 1; i <= ConfigParser.getInstance().getIntConf("stages"); i++) {
			out.append("; Etapp");
			out.append(i);
		}
		for (int i = 1; i <= ConfigParser.getInstance().getIntConf("stages"); i++) {
			out.append("; Start");
			out.append(i);
			out.append("; Mål");
			out.append(i);
		}

		assertEquals(out.toString(), printer.printTopInformation());
	}

	@Test
	public void testPrint() {
		racer = new Racer(new String("1; Anders Asson; FMCK Astad; ATM")
				.split("; "));
		racer.addStartTime(new Time("12.00.00"), 1);
		racer.addFinishTime(new Time("12.30.00"), 1);
		racer.addStartTime(new Time("12.00.00"), 2);
		racer.addFinishTime(new Time("13.00.00"), 2);
		
		
                                        
		assertEquals("1; Anders Asson; FMCK Astad; ATM; 01.30.00; 2; 00.30.00; 01.00.00; 12.00.00; 12.30.00; 12.00.00; 13.00.00",printer.print(racer, null));
	}

}
