package unittest.racer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import enduro.racedata.Time;
import enduro.racer.Racer;

public class RacerTest {

	@Test public void testBasicConstruct() {
		try {
			@SuppressWarnings("unused")
			Racer r = new Racer(new String("1; Anders Asson").split("; "));
		} catch(Exception E) {}
	}
	
	@Test public void getValuesTest() {
		Racer r = new Racer(new String("1; Anders Asson").split("; "));
		assertTrue(r.startNbr == 1);
	}
	
	@Test public void getInformation() {
		Racer r = new Racer(new String("1; Anders Asson").split("; "));
		assertTrue(r.racerInformation.get(0).compareTo("1")==0);
		assertTrue(r.racerInformation.get(1).compareTo("Anders Asson")==0);
	}
	
	@Test public void getExtendedInformation() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		assertTrue(r.racerInformation.size() == 4);
		
		assertTrue(r.racerInformation.get(0).compareTo("1")==0);
		assertTrue(r.racerInformation.get(1).compareTo("Anders Asson")==0);
		assertTrue(r.racerInformation.get(2).compareTo("FMCK Astad")==0);
		assertTrue(r.racerInformation.get(3).compareTo("ATM")==0);
	}
	
	@Test public void addTimeInformation() {
		try {
			Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
			r.addFinishTime(new Time("00.00.12"));
			assertTrue(r.startTimes.size()==0);
			assertTrue(r.finishTimes.size()==1);
			r.addStartTime(new Time("00.00.00"));
			assertTrue(r.startTimes.size()==1);
			assertTrue(r.finishTimes.size()==1);
		} catch(Exception E) {}
		
	}
	
	@Test public void getTimeInformation() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		r.addFinishTime(new Time("00.00.12"));
		r.addStartTime(new Time("00.00.00"));
		assertTrue(r.finishTimes.first().compareTo(new Time("00.00.12"))==0);
		assertTrue(r.startTimes.first().compareTo(new Time("00.00.00"))==0);
	}
	
	@Test public void getSortedTimeInformation() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		r.addFinishTime(new Time("00.12.12"));
		r.addFinishTime(new Time("00.00.12"));
		r.addFinishTime(new Time("00.06.12"));
		assertTrue(r.finishTimes.size()==3);
		
		assertTrue(r.finishTimes.first().compareTo(new Time("00.00.12"))==0);
		assertTrue(r.finishTimes.last().compareTo(new Time("00.12.12"))==0);
	}
}
