package unittest.misc;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.comparators.RunnerCheckTotalTimeMax;
import enduro.racer.comparators.RunnerLapsComparator;
import enduro.racer.comparators.RunnerNumberComparator;
import enduro.racer.comparators.RunnerTotalTimeComparator;

/**
 * this class tests all subclasses of DecorationCompare as well as combination thereof.
 */
public class ComparatorTest {

	
	RunnerLapsComparator lapse = new RunnerLapsComparator();
	RunnerNumberComparator number = new RunnerNumberComparator();
	RunnerTotalTimeComparator time = new RunnerTotalTimeComparator();
	RunnerCheckTotalTimeMax timeMax = new RunnerCheckTotalTimeMax(null);
	
	Racer runner1; //2 finish times
	Racer runner2; //3 finish times
	Racer runner2copy; // equal to 2 in number and lapses, but not total time
	Racer runner103; // 3 finish times
	Racer runner4; // no finish time
	Racer runner5; // no start time
	Racer runner6; // total time < one hour
	
	
	@Before public void setup() {
		runner1 = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		runner1.addFinishTime(new Time("12.30.00"), 1);
		runner1.addFinishTime(new Time("13.23.34"), 1);
		runner1.addStartTime(new Time("12.00.00"), 1);
		
		runner2 = new Racer(new String("2; Bengt Bsson; FMCK Bstad; BTM").split("; "));
		runner2.addFinishTime(new Time("12.14.00"), 1);
		runner2.addFinishTime(new Time("12.41.00"), 1);
		runner2.addFinishTime(new Time("13.15.16"), 1);
		runner2.addStartTime(new Time("12.00.00"), 1);
		
		runner2copy = new Racer(new String("2; Bengt Bsson; FMCK Bstad; BTM").split("; "));
		runner2copy.addFinishTime(new Time("12.14.00"), 1);
		runner2copy.addFinishTime(new Time("12.41.00"), 1);
		runner2copy.addFinishTime(new Time("13.00.00"), 1);
		runner2copy.addStartTime(new Time("12.00.00"), 1);
		
		runner103 = new Racer(new String("103; Erik Esson; Estad MCK; ETM").split("; "));
		runner103.addFinishTime(new Time("12.44.00"), 1);
		runner103.addFinishTime(new Time("12.24.00"), 1);
		runner103.addFinishTime(new Time("13.16.07"), 1);
		runner103.addStartTime(new Time("12.00.00"), 1);
		runner103.addStartTime(new Time("12.15.00"), 1);
		
		runner4 = new Racer(new String("104; Fredrik Fsson; FMCK Fstad; FTM").split("; "));
		runner4.addStartTime(new Time("12.00.00"), 1);
		
		runner5 = new Racer(new String("105; Göran Gsson; GMCK Gstad; GTM").split("; "));
		runner5.addFinishTime(new Time("13.30.00"), 1);
		
		runner6 = new Racer(new String("106; Håkan Hsson; HGMCK Hstad; HTM").split("; "));
		runner6.addStartTime(new Time("12.00.00"), 1);
		runner6.addFinishTime(new Time("12.30.00"), 1);
		
	}
	
	/*
	 * Tests the RunnerLapse comparator 
	 */
	@Test public void testRunnerLapseComparatorCompareEquals() {
		equals(lapse.compare(runner2, runner2copy));
	}
	
	@Test public void testRunnerLapseComparatorCompareDiff() {
		assertTrue(lapse.compare(runner1, runner2)==-1);
		assertTrue(lapse.compare(runner2, runner1)==1);
	}

	/*
	 * Tests the Starting number comparator 
	 */
	@Test public void testStartingNumberComparatorEquals() {
		equals(number.compare(runner2, runner2copy));
	}

	@Test public void testStartingNumberComparatorDiff() {
		assertTrue(number.compare(runner1, runner2)==-1);
		assertTrue(number.compare(runner2, runner1)==1);
		assertTrue(number.compare(runner1, runner103)==-102);
	}

	/*
	 * Tests the total time number comparator
	 */
	@Test public void testTotalTimeComparatorEquals() {
		equals(time.compare(runner1, runner1));
	}

	@Test public void testTotalTimeComparatorDiff() {
		assertTrue(time.compare(runner1, runner2) > 0);
		assertTrue(time.compare(runner2, runner1) < 0);
	}
	
	@Test
	public void testNoFinishTime() {
		assertTrue(time.compare(runner4, runner4) == 0);
	}
	
	@Test
	public void testOneWithFinishTimeAndOneWithout() {
		assertTrue(time.compare(runner1, runner4) == -1);
	}
	
	@Test
	public void testOneWithoutFinishTimeAndOneWith() {
		assertTrue(time.compare(runner4, runner1) == 1);
	}
	
	@Test
	public void testBothWithoutStartTime() {
		assertTrue(time.compare(runner5, runner5) == 0);
	}

	/*
	 * test time max comparator
	 */
	@Test
	public void testWithSmallTotalTime() {
		assertTrue(timeMax.compare(runner6, runner6) == 0);
	}
	
	@Test
	public void testWithSmallTotalTime2() {
		assertTrue(timeMax.compare(runner2, runner6) == -1);
	}

	/*
	 * test combinations
	 */
	@Test public void testBasicCombination() {
		RunnerLapsComparator lapseplustime = new RunnerLapsComparator(new RunnerTotalTimeComparator());
		
		assertTrue(lapse.compare(runner2, runner2copy)==0);
		assertTrue(lapseplustime.compare(runner2, runner2copy)==time.compare(runner2, runner2copy));
	}
	
	@Test public void testMaximumCombination() {
		RunnerLapsComparator lapseplusnumberplustime = new RunnerLapsComparator(new RunnerNumberComparator(new RunnerTotalTimeComparator()));
		assertTrue(lapse.compare(runner2, runner2copy)==0);
		assertTrue(number.compare(runner2, runner2copy)==0);
		assertTrue(lapseplusnumberplustime.compare(runner2, runner2copy)==time.compare(runner2, runner2copy));
	}

}