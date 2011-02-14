package enduro.racer.comparators;

import enduro.racer.Racer;


public class RunnerNumberComparator extends DecorationCompare {

	public RunnerNumberComparator() {
		super(null);
	}
	
	public RunnerNumberComparator(DecorationCompare comp) {
		super(comp);
	}
	
	
	public int compareRacers(Racer o1, Racer o2) {
		return o1.startNbr - o2.startNbr;
	}
}