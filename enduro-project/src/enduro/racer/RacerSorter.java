package enduro.racer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import enduro.racedata.Time;
import enduro.racer.printer.RacerPrinter;

public class RacerSorter {
	
	private TreeSet<Racer> racers;
	private RacerPrinter printer;

	private Time minTotalTime;
	
	/**
	 * creates a new sorter according to a set number of parameters.
	 * 
	 * @param comp the comparator according to which the runners will be sorted
	 * @param printer the output printer
	 * @param minTotalTime PLACEHOLDER INFORMATION. WILL BE CHANGED FOR SOMETHING BETTER
	 */
	public RacerSorter(Comparator<Racer> comp, RacerPrinter printer, Time minTotalTime) {
		racers = new TreeSet<Racer>(comp);
		this.printer = printer;
		this.minTotalTime = minTotalTime;
	}
	
	/**
	 * Adds a new racer sorted according to the inserted comparator.
	 * @param r the racer
	 */
	public void addRacer(Racer r) {
		racers.add(r);
	}
	
	/**
	 * Returns the Racer connected with the runner id.
	 * If the racer does not exist null is returned instead.
	 * 
	 * This runs in linear time (comparable used is not guaranteed to be logical, so better iterate over all)
	 * 
	 * WARNING. this function removes the racer from the list as it is presumed you are adding values to it, which may or may not change to comparator values for this class.
	 * TreeSet; the underlying structure requires removal and readding under those circumstances.
	 * @param runnerNum the runner id
	 * @return the racer if available, otherwise null.
	 */
	public Racer getRacer(int runnerNum) {
		Iterator<Racer> list = racers.iterator();
		while(list.hasNext()) {
			Racer r = list.next();
			if (r.startNbr == runnerNum) {
				list.remove();
				return r;
			}
		}
		return null;
	}
	
	/**
	 * prints the overlaying text line as well as all related runners.
	 * @return all relevant information.
	 */
	public String print() {
		StringBuilder out = new StringBuilder();
		int position = 1;
		HashMap<String, String> extraInformation = new HashMap<String, String>();
		
		out.append(this.printer.printTopInformation());
		out.append("\n");
		
		for(Racer racer: racers) {
			//clearing hashmap to save the need for constructing a new one for each racer (==yawn)
			extraInformation.clear();
			
			/*
			 * from here all possible extra information for printing is added, independent if a printer needs it or not. it does not get printed unless polled.
			 */
			try {
				//tests if the total time running is larger than the minimum time, if that is the case the position attribute is added
				if(racer.startTimes.first().getTotalTime(racer.finishTimes.last()).compareTo(minTotalTime) < 0) {
					extraInformation.put("position", position + "");
				}
			} catch(Exception E) {}

			//lets the printer print all the whole line. if it doesn't need the (possible nonexistent) position: who cares? =)
			out.append(printer.print(racer, extraInformation));
			out.append("\n");
			
			//position increases constantly.
			position++;
		}
		return out.toString();
	}
}
