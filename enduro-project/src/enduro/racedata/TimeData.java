package enduro.racedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.PriorityQueue;
/**
 * Provides a data structure for start
 * and finish times.
 */
public class TimeData {
	
	private TreeSet<Integer> startNbrs;
	private HashMap<Integer, PriorityQueue<Time>> startTimes;
	private HashMap<Integer, PriorityQueue<Time>> finishTimes;
	
	/**
	 * Creates a new TimeData object and initializes it.
	 */
	public TimeData() {
		startNbrs = new TreeSet<Integer>();
		startTimes = new HashMap<Integer, PriorityQueue<Time>>();
		finishTimes = new HashMap<Integer, PriorityQueue<Time>>();
	}
	
	/**
	 * Adds a new start time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @param time The racer's start time.
	 */
	public void addStartTime(int startNbr, Time time) {
		PriorityQueue<Time> list = startTimes.remove(startNbr);
		if(list==null) {
			startNbrs.add(startNbr);
			list = new PriorityQueue<Time>();
		}
		list.add(time);
		startTimes.put(startNbr, list);
	}
	
	/**
	 * Adds a new finish time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @param time The racer's finish time.
	 */
	public void addFinishTime(int startNbr, Time time) {
		PriorityQueue<Time> list = finishTimes.remove(startNbr);
		if(list==null) {
			startNbrs.add(startNbr);
			list = new PriorityQueue<Time>();
		}
		list.add(time);
		finishTimes.put(startNbr, list);
	}
	
	/**
	 * Returns the start time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @return The racer's start time, or null if none can be found.
	 */
	public PriorityQueue<Time> getStartTime(int startNbr) {
		return startTimes.get(startNbr);
	}
	
	/**
	 * Returns the finish time for the specified racer.
	 * @param startNbr The racer's start number.
	 * @return The racer's finish time, or null if none can be found.
	 */
	public PriorityQueue<Time> getFinishTime(int startNbr) {
		return finishTimes.get(startNbr);
	}
	
	/**
	 * Returns a sorted iterator over the identification number related to the runners
	 * @return
	 */
	public Iterator<Integer> getRunnerIterator() {
		return startNbrs.iterator();
	}
	
}
