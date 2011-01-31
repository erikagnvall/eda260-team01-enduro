package enduro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import enduro.racedata.Time;

public class LapseSorter extends Sorter {

	int lapses = 0;
	ArrayList<Time> times;
	private boolean impossibleLapTime = false;

	@Override
	protected String titleRow(Iterator<Integer> itr) {
		StringBuilder out = new StringBuilder();
		out.append("StartNr; Namn; #Varv; TotalTid; ");
		lapses = 0;
		while (itr.hasNext()) {
			PriorityQueue<Time> times = racerData.getFinishTime(itr.next());

			if (times.size() > lapses)
				lapses = times.size();
		}
		for (int i = 1; i <= lapses; i++) {
			out.append("Varv");
			out.append(i);
			out.append("; ");
		}
		out.append("Start; ");
		for (int i = 1; i < lapses; i++) {
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
		int laps = times.size();
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
		if (times.size() < lapses) {
			for (int j = times.size(); j < lapses; j++) {
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
		Time tempTime = startTime;
		for (Time t : times) {
			out.append(t.toString());
			out.append("; ");
			if (tempTime.getTotalTime(t).compareTo(new Time("00.15.00")) < 0) {
				impossibleLapTime = true;
			}
			tempTime = t;
		}
		out.delete(out.length() - 2, out.length());

		if (times.size() < lapses) {
			out.append(";");
		}
		if (impossibleLapTime) {
			out.append("; ");
			out.append("Omöjlig varvtid?");
		}
		return out.toString();
	}

	@Override
	protected ArrayList<Racer> sortRacers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String titleRow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int compareType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
