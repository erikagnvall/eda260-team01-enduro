package enduro.racer.comparators;

import enduro.racer.Racer;

/**
 * a naive comparison class that compares the size of finishtime, presumed to be how many lapses completed.
 * Ignores error in output and all other unrelated stuff.
 */
public class runnerLapseComparator extends DecorationCompare {

	public runnerLapseComparator() {
		super(null);
	}
	
	public runnerLapseComparator(DecorationCompare comp) {
		super(comp);
	}
	
	protected int compareRacers(Racer arg0, Racer arg1) {
		return arg0.finishTimes.size() - arg1.finishTimes.size();
	}

}
