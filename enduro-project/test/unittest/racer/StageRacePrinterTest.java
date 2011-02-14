package unittest.racer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;
import enduro.racer.printer.StageRacePrinter;

public class StageRacePrinterTest {
	private Racer racer;
	private StageRacePrinter printer = new StageRacePrinter();

	@Before
	public void doBefore() {
		printer.setHeaderInformation(new String[] { "StartNr", "Namn", "Klubb",
				"MC" });
	}

	@Test
	public void assertTestStageAreCorrect() {
		assertTrue(ConfigParser.getInstance().getIntConf("stages") == 3);
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
		racer.addStartTime(new Time("12.00.01"), 1);
		racer.addFinishTime(new Time("13.00.00"), 1);
		racer.addStartTime(new Time("12.00.02"), 1);
		racer.addFinishTime(new Time("13.23.34"), 1);
		
		
                                        
		assertEquals("1; Anders Asson; FMCK Astad; ATM; 02.53.31; 3; 00.30.00; 00.59.59; 01.23.32; 12.00.00; 12.30.00; 12.00.01; 13.00.00; 12.00.02; 13.23.34",printer.print(racer, null));
	}

}
