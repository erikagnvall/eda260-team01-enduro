package unittest;

import enduro.racedata.Time;
import enduro.racedata.TimeData;
import org.junit.*;
import static org.junit.Assert.*;

public class TimeDataTest {
	
	private TimeData timeData;
	
	@Before public void setUp() {
		timeData = new TimeData();
		timeData.addStartTime(1, new Time(12, 00, 00));
		timeData.addFinishTime(1, new Time(12, 30, 00));
	}
	
	@Test public void testGetStartTime() {
		assertEquals("12.00.00", timeData.getStartTime(1).toString());
	}
	
	@Test public void testGetFinishTime() {
		assertEquals("12.30.00", timeData.getFinishTime(1).toString());
	}

}
