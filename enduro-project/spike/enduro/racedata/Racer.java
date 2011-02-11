//TODO: This class is a stub, might be useful but currently replaced by RacerData (Not so object oriented though :/). 

package enduro.racedata;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Racer {
	private HashMap<String, String> racerInfo;
	private int startNbr;
	private PriorityQueue<Time> startTimes;
	private PriorityQueue<Time> finishTimes;

	public Racer() {
		racerInfo = new HashMap<String, String>();
		startTimes = new PriorityQueue<Time>();
		finishTimes = new PriorityQueue<Time>();
	}

	/**
	 * Adds info to the racer, such as name, team, MC brand etc.
	 * 
	 * @param dataKey
	 *            the type of value added with capital letter (E.g. Name, Team
	 *            etc.)
	 * @param data
	 *            the data to add (E.g. Anders Asson)
	 */
	public void addRacerInfo(String dataKey, String data) {
		this.racerInfo.put(dataKey, data);
	}

	/**
	 * Gets info about the racer.
	 * 
	 * @param dataKey
	 *            the key for the value to be retrieved, with a capital letter
	 *            (E.g. Name, Team etc.)
	 * @return
	 */
	public String getRacerInfo(String dataKey) {
		return this.racerInfo.get(dataKey);
	}

	/**
	 * Adds a new start time for the specified racer.
	 * 
	 * @param startNbr
	 *            The racer's start number.
	 * @param time
	 *            The racer's start time.
	 */
	// public void addStartTime(int startNbr, Time time) {
	// PriorityQueue<Time> list = startTimes.remove(startNbr);
	// if (list == null) {
	// startNbrs.add(startNbr);
	// list = new PriorityQueue<Time>();
	// }
	// list.add(time);
	// startTimes.put(startNbr, list);
	// }
	//
	// /**
	// * Adds a new finish time for the specified racer.
	// *
	// * @param startNbr
	// * The racer's start number.
	// * @param time
	// * The racer's finish time.
	// */
	// public void addFinishTime(int startNbr, Time time) {
	// PriorityQueue<Time> list = finishTimes.remove(startNbr);
	// if (list == null) {
	// startNbrs.add(startNbr);
	// list = new PriorityQueue<Time>();
	// }
	// list.add(time);
	// finishTimes.put(startNbr, list);
	// }
	//
	// /**
	// * Returns the start time for the specified racer.
	// *
	// * @param startNbr
	// * The racer's start number.
	// * @return The racer's start time, or null if none can be found.
	// */
	// public PriorityQueue<Time> getStartTime(int startNbr) {
	// return startTimes.get(startNbr);
	// }
	//
	// /**
	// * Returns the finish time for the specified racer.
	// *
	// * @param startNbr
	// * The racer's start number.
	// * @return The racer's finish time, or null if none can be found.
	// */
	// public PriorityQueue<Time> getFinishTime(int startNbr) {
	// return finishTimes.get(startNbr);
	// }
	//
	// /**
	// * Returns a sorted iterator over the identification number related to the
	// * runners
	// *
	// * @return
	// */
	// public Iterator<Integer> getRunnerIterator() {
	// return startNbrs.iterator();
	// }
}
