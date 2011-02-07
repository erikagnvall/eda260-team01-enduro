package enduro;

import java.util.Iterator;

import enduro.Sorter.Racer;
import enduro.racedata.Time;

/**
 * Class for sorting the results of a marathon according to the formatting rules
 * that apply.
 * 
 * 
 */
public class MarathonSorter extends Sorter {

	@Override
	protected String titleRow(Iterator<Integer> itr) {
		return this.nameInformation + "TotalTid; StartTider; Måltider";
	}

	@Override
	protected String finishTime(int i) {
		String finish;
		try {
			finish = finishTime.toString();
			if (racerData.getFinishTime(i).size() > 0) {
				trail.append("; Flera måltider?");
				while (racerData.getFinishTime(i).size() > 0) {
					trail.append(' ');
					trail.append(racerData.getFinishTime(i).poll());
				}
			}
		} catch (NullPointerException e) {
			finish = "Slut?";
		}
		return finish;
	}

	@Override
	protected int compareType() {
		return Racer.TIME_COMPARE;
	}

	@Override
	protected String noTotalTime() {
		return "--.--.--";
	}

	@Override
	protected String totalTime(int i) {

		String total;
		try {
			Time totalTime = startTime.getTotalTime(finishTime);
			total = totalTime.toString();
			Time fastTime = new Time(0, 15, 0);
			if (fastTime.compareTo(totalTime) > 0)
				trail.append("; Omöjlig Totaltid?");
		} catch (NullPointerException e) {
			total = "--.--.--";
		}
		return total;
	}
}