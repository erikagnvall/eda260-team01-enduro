package enduro.racer;

import java.util.Comparator;


public class LapRacerSorter implements Comparator<Racer>{

	public int compare(Racer o1, Racer o2) {
		return o1.numLaps - o2.numLaps;
	}
}