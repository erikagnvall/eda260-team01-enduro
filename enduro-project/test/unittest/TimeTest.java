package unittest;

import enduro.racedata.Time;
import org.junit.*;

import static org.junit.Assert.*;

public class TimeTest {
	
	private Time t1;
	private Time t2;
	
	@Before public void setUp() {
		t1 = new Time(12, 23, 56);
		t2 = new Time(1, 2, 9);
	}
	
	@Test public void testStringRepresentation1() {
		assertEquals("12.23.56", t1.toString());
	}
	
	@Test public void testStringRepresentation2() {
		assertEquals("01.02.09", t2.toString());
	}
	@Test public void testtotalTime() {
		Time t3 =t2.getTotalTime(t1);
		assertEquals("11.21.47",t3.toString());
		
	}
	@Test public void testComparTo() {
		assertTrue(0 < t1.compareTo(t2));
		assertTrue(0 > t2.compareTo(t1));
		assertTrue(0 == t1.compareTo(t1));
		
	}
	
}
