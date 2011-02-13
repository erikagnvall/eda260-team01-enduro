package unittest.dataStructure;

import java.util.Iterator;
import java.util.PriorityQueue;

import enduro.racedata.RaceClass;
import enduro.racedata.Time;
import enduro.racedata.RacerData;
import org.junit.*;

import static org.junit.Assert.*;

public class RacerDataTest {

	private RacerData racerData;

	@Before
	public void setUp() {
		racerData = new RacerData();
		racerData.addStartTime(1, new Time(12, 00, 00));
		racerData.addFinishTime(1, new Time(12, 30, 00));
		racerData.addName(new String[]{"1","test1"});
		racerData.addName(new String[]{"2","test2"});
		racerData.addStartTime(2, new Time(12, 00, 00));
		racerData.addFinishTime(2, new Time(12, 20, 00));
		racerData.addFinishTime(2, new Time(13, 00, 00));
		racerData.addClass(new RaceClass("class1"));
		racerData.addClass(new RaceClass("class2"));
	}

	@Test
	public void testGetName() {
		assertEquals("test1", racerData.getName(1));
	}

	@Test
	public void testGetStartTime() {
		PriorityQueue<Time> times = racerData.getStartTime(1);
		assertEquals(1, times.size());
		boolean exists = false;
		for (Time t : times) {
			if (t.equals(new Time(12, 0, 0))) {
				exists = true;
			}
		}
		assertTrue(exists);
	}

	@Test
	public void testGetFinishTime() {
		PriorityQueue<Time> times = racerData.getFinishTime(1);
		assertEquals(1, times.size());
		boolean exists = false;
		for (Time t : times) {
			if (t.equals(new Time(12, 30, 0))) {
				exists = true;
			}
		}
		assertTrue(exists);
	}

	@Test
	public void testWrongRunnerNumber() {
		assertEquals(null, racerData.getFinishTime(-2));
	}

	@Test
	public void testOneRunnerList() {
		assertEquals(new Integer(1), racerData.numberIterator().next());
	}

	@Test
	public void testGetTotalTime() {
		assertEquals(new Time("1.00.00"), racerData.getTotalTime(2));
	}

	@Test
	public void testGetNumberOfLaps() {
		assertEquals(2, racerData.getNumberOfLaps(2));
		racerData.addStartTime(3, new Time(12, 00, 00));
		assertEquals(0, racerData.getNumberOfLaps(3));
	}
	
	@Test
	public void testIterator() {
		Iterator<RaceClass> itr = racerData.iterator();
		itr.hasNext();
		assertEquals(true, new RaceClass("class1").equals(racerData.iterator().next()));
	}
	
	@Test
	public void testAddName() {
		racerData.addName(3, 1, new RaceClass("class1"));
		assertEquals(true, racerData.contestantIsRegistered(3));
	}
	
	@Test
	public void testGetRacerInfo() {
		assertEquals("test1; ", racerData.getRacerInfo(1));
	}
	
	//compareTo() metoden gör inget ännu, returnerar bara 0;
	@Test
	public void testCompareTo() {
		assertEquals(0, racerData.compareTo(new RacerData()));
	}
	
	@Test
	public void testGetClasses() {
		assertEquals(2, racerData.getClasses().size());
		assertEquals("class1", racerData.getClasses().get(0).getName());
	}
	
	@Test
	public void testContainsClass() {
		assertEquals(true, racerData.containsClass(new RaceClass("class1")));
	}
}
