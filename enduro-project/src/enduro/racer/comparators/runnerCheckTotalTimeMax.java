package enduro.racer.comparators;

import enduro.racedata.Time;
import enduro.racer.Racer;

public class runnerCheckTotalTimeMax extends DecorationCompare {

	private Time maxTime;
	
	public runnerCheckTotalTimeMax(DecorationCompare fallback, Time maxTime) {
		super(fallback);
		
		this.maxTime = maxTime;
	}

	@Override
	protected int compareRacers(Racer arg0, Racer arg1) {
		boolean arg0Check = arg0.startTimes.first().getTotalTime(arg0.finishTimes.last()).compareTo(maxTime) > 0;
		boolean arg1Check = arg1.startTimes.first().getTotalTime(arg1.finishTimes.last()).compareTo(maxTime) > 0;
		
		if(arg0Check)
			if(arg1Check)
				return 0;
			else
				return -1;
		else
			if(arg1Check)
				return 1;
			else
				return 0;
				
	}

}
