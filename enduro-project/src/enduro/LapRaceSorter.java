package enduro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import enduro.racedata.Time;

public class LapRaceSorter extends Sorter {

	public int numLaps = 0;
	ArrayList<Time> sortedTimeList = new ArrayList<Time>();
	boolean impossibleLapTime = false;

	// Used in sortedList
	protected int compareType() {
		return Racer.LAP_COMPARE;
	}

	// Everything from here on is needed
	@Override
	protected String titleRow(Iterator<Integer> itr) {
		StringBuilder out = new StringBuilder();
		out.append("StartNr; Namn; #Varv; TotalTid; ");
		numLaps = racerData.getNumberOfLaps(itr.next());
		for (int i = 1; i <= numLaps; i++) {
			out.append("Varv");
			out.append(i);
			out.append("; ");
		}
		out.append("Start; ");
		for (int i = 1; i < numLaps; i++) {
			out.append("Varvning");
			out.append(i);
			out.append("; ");
		}
		out.append("Mål");
		return out.toString();
	}

	@Override
	protected String totalTime(int i) {
		Time start = startTimes.peek();
		// Generate a sorted ArrayList with finish times
		PriorityQueue<Time> timeQueue = new PriorityQueue<Time>();
		for (Time t : racerData.getFinishTime(i)) {
			timeQueue.offer(t);
		}
		sortedTimeList = new ArrayList<Time>();
		while (timeQueue.peek() != null) {
			sortedTimeList.add(timeQueue.poll());
		}

		for (Time t : sortedTimeList) {
			finishTime = t;
		}

		return start.getTotalTime(finishTime) + "; ";
	}

	private String lapTimes(int racerNumber, StringBuilder trail) {
		Time lastTime;
		StringBuilder out = new StringBuilder();
		Iterator<Time> timeItr = sortedTimeList.iterator();
		Time t;
		try {
			lastTime = startTimes.peek();
		} catch (NullPointerException e) {
			lastTime = sortedTimeList.get(0);
			out.append("; ");
			timeItr.next();
		}
		while (timeItr.hasNext()) {
			t = timeItr.next();
			if (lastTime.getTotalTime(t).compareTo(new Time(0, 15, 0)) < 0) {
				trail.append("Omöjlig varvtid?");
			}
			out.append(lastTime.getTotalTime(t));
			lastTime = t;
			out.append("; ");
		}
		if (sortedTimeList.size() < numLaps) {
			for (int j = sortedTimeList.size(); j < numLaps; j++) {
				out.append("; ");
			}
		}
		out.delete(out.length() - 2, out.length());
		return out.toString();
	}

	@Override
	protected String getData(int racerNumber) {
		StringBuilder sb = new StringBuilder();
		StringBuilder trail = new StringBuilder();

		// Num laps
		if (startTimes == null) {
			sb.append(racerData.getNumberOfLaps(racerNumber) - 1);
		} else {
			sb.append(racerData.getNumberOfLaps(racerNumber));
		}
		sb.append("; ");

		// Total time
		if (startTimes == null || finishTimes == null) {
			sb.append("--.--.--; ");
			if (finishTimes != null) {
				// Generate a sorted ArrayList with finish times
				PriorityQueue<Time> timeQueue = new PriorityQueue<Time>();
				for (Time t : finishTimes) {
					timeQueue.offer(t);
				}
				sortedTimeList = new ArrayList<Time>();
				while (timeQueue.peek() != null) {
					sortedTimeList.add(timeQueue.poll());
				}
			}
		} else {
			sb.append(totalTime(racerNumber));
		}

		// Lap times
		if (finishTimes == null) {
			for (int i = 0; i < numLaps; i++) {
				sb.append("; ");
			}
		} else {
			sb.append(lapTimes(racerNumber, trail));
			sb.append("; ");
		}

		// Start time
		if (startTimes == null) {
			sb.append("Start?; ");
		} else if (startTimes.size() > 1) {
			sb.append(startTimes.poll());
			sb.append("; ");
			trail.append("Flera starttider? ");
			while (startTimes.size() != 0) {
				trail.append(startTimes.poll().toString());
				trail.append(' ');
			}
			trail.delete(trail.length() - 1, trail.length());
		} else {
			sb.append(startTimes.poll());
			sb.append("; ");
		}

		// Finish time
		if (finishTimes == null) {
			for (int i = 0; i < numLaps - 2; i++) {
				sb.append("; ");
			}
			trail.append("Slut?");
		} else {
			while (finishTimes.size() != 0) {
				sb.append(finishTimes.poll().toString());
				sb.append("; ");
			}
			sb.delete(sb.length() - 2, sb.length());
		}
		if (trail.length() != 0) {
			sb.append("; ");
			sb.append(trail.toString());
		}
		return sb.toString();
	}
}
