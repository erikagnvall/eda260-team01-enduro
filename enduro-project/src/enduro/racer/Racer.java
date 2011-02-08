package enduro.racer;

import java.util.ArrayList;
import java.util.TreeSet;

import enduro.racedata.Time;

public class Racer implements Comparable<Racer> {
	//caution: startNbr is only here as a reference (indexation and so on). it also exists in racerInformation (explicitly important to know!).
	public int startNbr;
	
	public ArrayList<String> racerInformation = new ArrayList<String>();
	
	public TreeSet<Time> startTimes = new TreeSet<Time>();
	public TreeSet<Time> finishTimes = new TreeSet<Time>();
	
	public Racer(String[] racerInformation) {
		startNbr = Integer.parseInt(racerInformation[0]);
		for(int i = 0; i < racerInformation.length; i++) {
			this.racerInformation.add(racerInformation[i]);
		}
	}
	
	public void addStartTime(Time startTime) {
		this.startTimes.add(startTime);
	}
	
	public void addFinishTime(Time finishTime) {
		this.finishTimes.add(finishTime);
	}

	public int getStartNbr() {
		return startNbr;
	}

	public int compareTo(Racer arg0) {
		return startNbr - arg0.startNbr;
	}
	
	public boolean equals(Racer arg0) {
		return compareTo(arg0)==0;
	}
	
}