package enduro.racer.comparators;

import enduro.racer.Racer;


public class runnerNumberComparator extends DecorationCompare {

	public runnerNumberComparator() {
		super(null);
	}
	
	public runnerNumberComparator(DecorationCompare comp) {
		super(comp);
	}
	
	
	public int compareRacers(Racer o1, Racer o2) {
		return o1.startNbr - o2.startNbr;
	}
}