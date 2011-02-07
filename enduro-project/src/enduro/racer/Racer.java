package enduro.racer;

import java.util.ArrayList;
import java.util.Comparator;

import enduro.racedata.Time;

public abstract class Racer {
	protected int startNbr;
	protected int numLaps;
	protected Time startTime;
	protected ArrayList<Time> lapTimes;

	public Racer(int startNbr, int numLaps, Time startTime, ArrayList<Time> lapTimes) {
		this.startNbr = startNbr;
		this.numLaps = numLaps;
		this.startTime = startTime;
		this.lapTimes = lapTimes;
	}

	public int getStartNbr() {
		return startNbr;
	}
	
}