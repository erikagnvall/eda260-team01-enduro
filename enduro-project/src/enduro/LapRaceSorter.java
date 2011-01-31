package enduro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import enduro.racedata.Time;

public class LapRaceSorter extends Sorter {

	int numLaps = 0;
	ArrayList<Time> times;
	boolean impossibleLapTime = false;

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
		for (Time t : times) {
			finishTime = t;
		}
		int laps = 0;
		if (times != null) {
			laps = times.size();
		}
		StringBuilder out = new StringBuilder();
		out.append(laps);
		out.append("; ");

		out.append(startTime.getTotalTime(finishTime));
		out.append("; ");

		Time lastTime = startTime;
		for (Time t : times) {
			out.append(lastTime.getTotalTime(t));
			lastTime = t;
			out.append("; ");
		}
		if (times.size() < numLaps) {
			for (int j = times.size(); j < numLaps; j++) {
				out.append("; ");
			}
		}
		out.delete(out.length() - 2, out.length());
		return out.toString();
	}

	@Override
	protected Time getFinishTime(int i) throws NullPointerException {
		PriorityQueue<Time> timeQueue = new PriorityQueue<Time>();
		for (Time t : racerData.getFinishTime(i)) {
			timeQueue.offer(t);
		}
		times = new ArrayList<Time>();
		while (timeQueue.peek() != null) {
			times.add(timeQueue.poll());
		}
		return racerData.getFinishTime(i).poll();
	}

	@Override
	protected String finishTime(int i) {
		StringBuilder out = new StringBuilder();
		Time tempTime;
		if (startTime == null) {
			tempTime = times.get(0);
			System.out.println("times.get(0) = " + times.get(0).toString());
		} else {
			tempTime = startTime;
		}
		impossibleLapTime = false;
		for (Time t : times) {
			out.append(t.toString());
			out.append("; ");
			if (tempTime.getTotalTime(t).compareTo(new Time("00.15.00")) < 0) {
				impossibleLapTime = true;
			}
			tempTime = t;
		}
		out.delete(out.length() - 2, out.length());

		if (times.size() < numLaps) {
			out.append(";");
		}
		if (impossibleLapTime) {
			out.append("; ");
			out.append("Omöjlig varvtid?");
		}
		return out.toString();
	}

	protected String noTotalTime() {
		return "0; --.--.--";
	}

	protected int compareType() {
		return Racer.LAP_COMPARE;
	}
}
