package enduro.racedata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Provides a data structure for start
 * and finish times.
 */
public class TimeData {
	
	private Set<Integer> startNbrs;
	private Map<Integer, Time> startTimes;
	private Map<Integer, Time> finishTimes;
	
	/**
	 * Creates a new TimeData object and initializes it.
	 */
	public TimeData() {
		startNbrs = new TreeSet<Integer>();
		startTimes = new HashMap<Integer, Time>();
		finishTimes = new HashMap<Integer, Time>();
	}
	
	/**
	 * Adds a new start time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @param time The racer's start time.
	 */
	public void addStartTime(int startNbr, Time time) {
		//TODO
	}
	
	/**
	 * Adds a new finish time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @param time The racer's finish time.
	 */
	public void addFinishTime(int startNbr, Time time) {
		//TODO
	}
	
	/**
	 * Returns the start time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @return The racer's start time, or null if none can be found.
	 */
	public Time getStartTime(int startNbr) {
		//TODO
		return new Time(12, 00, 00);
	}
	
	/**
	 * Returns the finish time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @return The racer's finish time, or null if none can be found.
	 */
	public Time getFinishTime(int startNbr) {
		//TODO
		return new Time(12, 30, 00);
	}
	
}
