package enduro.racer.comparators;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

public class RunnerStageComparator extends DecorationCompare {

	int numStages;
	
	public RunnerStageComparator(DecorationCompare fallback) {
		super(fallback);
		
		this.numStages = Integer.parseInt(ConfigParser.getInstance().getStringConf("numstages"));
		}

	@Override
	protected int compareRacers(Racer arg0, Racer arg1) {
			int racer0Stages = arg0.finishTimes.size();
			int racer1Stages = arg1.finishTimes.size();
			if(racer0Stages < numStages){
				if(racer1Stages > numStages)
					return -1;
				else
					return 0;
			} else {
				if(racer0Stages > numStages)
					return 1;
				else
					return 0;
			}
		}

}
