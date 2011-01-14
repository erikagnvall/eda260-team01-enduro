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
	
}
