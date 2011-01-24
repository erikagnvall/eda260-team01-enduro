package unittest;

import java.util.ArrayList;

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
		//System.out.println(timeData.getStartTime(1));
		ArrayList<Time> times = timeData.getStartTime(1);
		assertTrue(times.size()==1);
		boolean exists = false;
		for(Time t:times) {
			if(t.equals(new Time(12,0,0))) {
				exists = true;		
			}
		}
		assertTrue(exists);
	}
	
	@Test public void testGetFinishTime() {
		ArrayList<Time> times = timeData.getFinishTime(1);
		assertTrue(times.size()==1);
		boolean exists = false;
		for(Time t:times) {
			if(t.equals(new Time(12,30,0))) {
				exists = true;		
			}
		}
		assertTrue(exists);
	}
	
	@Test public void testWrongRunnerNumber() {
		assertTrue(timeData.getFinishTime(-2) == null);
	}
	
	@Test public void testOneRunnerList() {
		assertEquals(new Integer(1), timeData.getRunnerIterator().next());
	}
}
