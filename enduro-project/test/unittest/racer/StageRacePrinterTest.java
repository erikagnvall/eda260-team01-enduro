package unittest.racer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import enduro.racedata.Time;
import enduro.racer.Racer;
import enduro.racer.Configuration.ConfigParser;
import enduro.racer.printer.StageRacePrinter;

public class StageRacePrinterTest {
	private Racer racer;
	private StageRacePrinter printer = new StageRacePrinter();
	@Before public void doBefore() {
		printer.setHeaderInformation(new String[]{"StartNr", "Namn", "Klubb", "MC"});
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
}