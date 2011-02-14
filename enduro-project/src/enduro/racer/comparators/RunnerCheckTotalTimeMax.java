package enduro.racer.comparators;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

public class RunnerCheckTotalTimeMax extends DecorationCompare {

	private Time maxTime;
	
	public RunnerCheckTotalTimeMax(DecorationCompare fallback) {
		super(fallback);
		
		this.maxTime = new Time(ConfigParser.getInstance().getStringConf("mintime"));
	}

	@Override
	protected int compareRacers(Racer arg0, Racer arg1) {
		boolean arg0Check = arg0.startTimes.get(1).first().getTotalTime(arg0.finishTimes.get(1).last()).compareTo(maxTime) > 0;
		boolean arg1Check = arg1.startTimes.get(1).first().getTotalTime(arg1.finishTimes.get(1).last()).compareTo(maxTime) > 0;
		
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
