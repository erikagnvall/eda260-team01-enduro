package enduro.racer.comparators;

import enduro.racer.Racer;

/**
 * a naive comparison class that compares the size of finishtime, presumed to be how many lapses completed.
 * Ignores error in output and all other unrelated stuff.
 */
public class RunnerLapsComparator extends DecorationCompare {

	public RunnerLapsComparator() {
		super(null);
	}
	
	public RunnerLapsComparator(DecorationCompare comp) {
		super(comp);
	}
	
	protected int compareRacers(Racer arg0, Racer arg1) {
		return arg0.finishTimes.get(1).size() - arg1.finishTimes.get(1).size();
	}

}
