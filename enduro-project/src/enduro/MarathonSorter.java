package enduro;

import java.util.Iterator;

import enduro.Sorter.Racer;

/**
 * Class for sorting the results of a marathon according to the formatting rules
 * that apply.
 * 
 * 
 */
public class MarathonSorter extends Sorter {

	@Override
	protected String titleRow(Iterator<Integer> itr) {
		return "StartNr; Namn; TotalTid; StartTider; Måltider";
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
}