package enduro.racer.comparators;

import enduro.racer.Racer;

/**
 * a non-naive comparison class that computes and compares the total time between two Racer classes.
 * this class is able to handle if finishtime / starttime doesn't exist in either or both and the comparison reflects the result.
 * 
 */
public class RunnerTotalTimeComparator extends DecorationCompare {

	public RunnerTotalTimeComparator() {
		super(null);
	}
	
	public RunnerTotalTimeComparator(DecorationCompare comp) {
		super(comp);
	}
	
	
	public int compareRacers(Racer arg0, Racer arg1) {
		if(arg0.startTimes.get(1).size() > 0 && arg1.startTimes.get(1).size() > 0) {
			if(arg0.finishTimes.get(1).size() > 0 && arg1.finishTimes.get(1).size() > 0) {
				return arg0.startTimes.get(1).first().getTotalTime(arg0.finishTimes.get(1).last()).compareTo(arg1.startTimes.get(1).first().getTotalTime(arg1.finishTimes.get(1).last()));
			} else if(arg0.finishTimes.get(1).size() == 0 && arg1.finishTimes.get(1).size() == 0) {
				//there is no total time due to both lacking finish times
				return 0;
			} else if(arg0.finishTimes.get(1).size() != 0) {
				//arg0 has a finish time, arg1 doesn't
				return -1;
			} else {
				return 1;
				//arg1 has a finish time, arg0 doesn't
			}
		} else {
			//both lack starting times == no total time.
			return 0;
		}
	}

}
