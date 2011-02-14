package enduro.racer;

import java.util.ArrayList;
import java.util.TreeSet;


/**
 * A basic class that represents all information about the racer - number, extra information and lapse times.
 * The only presumption the class does based on the string in the constructor is that the first element is the number.
 * 
 * The class implements Comparable<Racer> based on the start number.
 */
public class Racer implements Comparable<Racer> {
	//caution: startNbr is only here as a reference (indexation and so on). it also exists in racerInformation (explicitly important to know!).
	public int startNbr;
	
	public ArrayList<String> racerInformation = new ArrayList<String>();
	
	public TreeSet<Time> startTimes = new TreeSet<Time>();
	public TreeSet<Time> finishTimes = new TreeSet<Time>();
	
	/**
	 * Takes a string of information that is then treated as information about this racer.
	 * @param racerInformation An array of strings, the first one must be the start number.
	 */
	public Racer(String[] racerInformation) {
		startNbr = Integer.parseInt(racerInformation[0]);
		for(int i = 0; i < racerInformation.length; i++) {
			this.racerInformation.add(racerInformation[i]);
		}
	}
	
	/**
	 * Adds a start time described by a Time element
	 * @param startTime the time described as a Time class
	 */
	public void addStartTime(Time startTime) {
		this.startTimes.add(startTime);
	}
	
	/**
	 * Adds a finish time described by a Time element
	 * @param finishTime the time described as a Time class
	 */
	public void addFinishTime(Time finishTime) {
		this.finishTimes.add(finishTime);
	}

	/**
	 * a function to get an integer reference to the first element of the String array in the constructor
	 * @return the runner's start number
	 */
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