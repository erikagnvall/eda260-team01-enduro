package unittest.misc;

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
		Time t3 = new Time(12, 23, 57);
		Time t4 = new Time(12, 23, 55);
		assertEquals(-1, t1.compareTo(t3));
		assertEquals(1, t1.compareTo(t4));
		assertEquals(0, (new Time(12,23,56)).compareTo(t1));
	}
	
	@Test public void testStringParse() {
		assertEquals(t1, new Time("12.23.56"));
	}
	
	@Test public void testEquals() {
		assertEquals(true, t1.equals(new Time(12, 23, 56)));
		assertEquals(false, t1.equals(t2));
		assertEquals(false, t1.equals(new Integer(1)));
	}
}
