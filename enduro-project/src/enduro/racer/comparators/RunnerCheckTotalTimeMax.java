package enduro.racer.comparators;

import java.util.TreeSet;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

/**
 * A basic comparison class that handles if racers are not able to fulfill a basic minimum time.
 * The racers are therefore ordered based on whether or not they fulfill this basic premise.
 * Returns 0 if both fulfill/both don't fulfill the time limit or -1 or 1 if one fulfills the minimum time.
 */
public class RunnerCheckTotalTimeMax extends DecorationCompare {

	private Time maxTime;
	
	public RunnerCheckTotalTimeMax(DecorationCompare fallback) {
		super(fallback);
		
		this.maxTime = new Time(ConfigParser.getInstance().getStringConf("mintime"));
	}

	@Override
	protected int compareRacers(Racer arg0, Racer arg1) {
		
		boolean arg0Check;
		boolean arg1Check;
		
		if(arg0.finishTimes.size() > 1 || arg0.startTimes.size() > 1 || arg1.finishTimes.size() > 1 || arg1.startTimes.size() > 1) {
			
			TreeSet<Integer> keys = new TreeSet<Integer>();
			keys.addAll(arg0.finishTimes.keySet());
			keys.addAll(arg0.startTimes.keySet());
			
			Time totalArg0 = new Time("00.00.00");
			for (int key : keys) {
				if(arg0.startTimes.get(key) != null && arg0.finishTimes.get(key) != null)
					totalArg0.increment(arg0.startTimes.get(key).first().getTotalTime(arg0.finishTimes.get(key).first()));
			}
			
			keys = new TreeSet<Integer>();
			keys.addAll(arg1.finishTimes.keySet());
			keys.addAll(arg1.startTimes.keySet());
			
			Time totalArg1 = new Time("00.00.00");
			for (int key : keys) {
				if(arg1.startTimes.get(key) != null && arg1.finishTimes.get(key) != null)
					totalArg1.increment(arg1.startTimes.get(key).first().getTotalTime(arg1.finishTimes.get(key).first()));
			}
			
			arg0Check = totalArg0.compareTo(maxTime) > 0;
			arg1Check = totalArg1.compareTo(maxTime) > 0;
		
		} else {
			arg0Check = arg0.startTimes.get(1).first().getTotalTime(arg0.finishTimes.get(1).last()).compareTo(maxTime) > 0;
			arg1Check = arg1.startTimes.get(1).first().getTotalTime(arg1.finishTimes.get(1).last()).compareTo(maxTime) > 0;
			
		}
		
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
