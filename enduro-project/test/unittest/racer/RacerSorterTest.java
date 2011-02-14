package unittest.racer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import enduro.racedata.Time;
import enduro.racer.Racer;
import enduro.racer.RacerSorter;
import enduro.racer.comparators.runnerNumberComparator;
import enduro.racer.printer.LapRacePrinter;

public class RacerSorterTest {

	private Racer racer1, racer2, racer103;
	private RacerSorter sorter;
	
	@Before public void bef() {
		
		LapRacePrinter printer = new LapRacePrinter();
		printer.setHeaderInformation(new String[]{"startNr", "Namn", "Klubb", "annat"});
		
		racer1 = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		racer1.addFinishTime(new Time("12.30.00"));
		racer1.addFinishTime(new Time("13.23.34"));
		racer1.addFinishTime(new Time("13.00.00"));
		racer1.addStartTime(new Time("12.00.00"));
		
		racer2 = new Racer(new String("2; Bengt Bsson; FMCK Bstad; BTM").split("; "));
		racer2.addFinishTime(new Time("12.14.00"));
		racer2.addFinishTime(new Time("12.41.00"));
		racer2.addFinishTime(new Time("13.15.16"));
		racer2.addStartTime(new Time("12.00.00"));
		
		racer103 = new Racer(new String("103; Erik Esson; Estad MCK; ETM").split("; "));
		racer103.addFinishTime(new Time("12.44.00"));
		racer103.addFinishTime(new Time("12.24.00"));
		racer103.addFinishTime(new Time("13.16.07"));
		racer103.addStartTime(new Time("12.00.00"));
		racer103.addStartTime(new Time("12.15.00"));
		
		sorter = new RacerSorter("", new runnerNumberComparator(), printer, new Time("01.00.00"));
	}
	
	@Test public void testConstructionAddRacer() {
		try {
			sorter.addRacer(racer1);
			sorter.addRacer(racer2);
		} catch(Exception E) {
			fail("construction error");
		}
	}
	
	@Test public void testPrintOneRunner() {
		sorter.addRacer(racer1);
		String[] lines = sorter.print().split("\n");
		assertTrue(lines.length == 3);
	}
	
	@Test public void testPrintInCorrectOrder() {
		sorter.addRacer(racer1);
		sorter.addRacer(racer2);
		sorter.addRacer(racer103);
		String[] lines = sorter.print().split("\n");
		
		assertTrue(lines.length == 5);
		
		assertTrue(lines[2].startsWith("1"));
		assertTrue(lines[3].startsWith("2"));
		assertTrue(lines[4].startsWith("103"));
	}
	
	@Test
	public void testGetRacer() {
		sorter.addRacer(racer1);
		sorter.addRacer(racer2);
		sorter.addRacer(racer103);
		assertEquals(103, sorter.getRacer(103).getStartNbr());
		assertEquals(null, sorter.getRacer(50));
	}
	
}
