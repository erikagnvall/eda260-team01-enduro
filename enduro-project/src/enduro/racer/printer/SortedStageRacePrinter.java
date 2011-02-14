package enduro.racer.printer;

import java.util.HashMap;
import java.util.Iterator;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

/**
 * Prints a sorted (that is, no errors and position) of a racer.
 */
public class SortedStageRacePrinter implements RacerPrinter {

	String extraInformation = "";
	private int stages = ConfigParser.getInstance().getIntConf("stages");
	int extraRunnerInformation = 0;

	public String print(Racer r, HashMap<String, String> extraInformation) {
		// Plac; StartNr; Namn; #Varv; Totaltid; Varv1; Varv2
		StringBuilder out = new StringBuilder();

		printPosition(r, out, extraInformation);

		printRunnerInformation(r, out);

		printTotalTime(r, out);
		
		printNumStages(r, out);

		printStages(r, out);

		out.delete(out.length() - 2, out.length());

		return out.toString();
	}

	private void printRunnerInformation(Racer r, StringBuilder out) {
		int i = 0;

		for (; i < r.racerInformation.size(); i++) {
			out.append(r.racerInformation.get(i));
			out.append("; ");
		}
		for (; i < extraRunnerInformation; i++) {
			out.append("; ");
		}
	}

	private void printStages(Racer r, StringBuilder out) {
		Iterator<Time> itr = r.startTimes.get(1).iterator();
		Time before;
		for (Time next : r.finishTimes.get(1)) {
			before = itr.next();
			out.append(before.getTotalTime(next));
			out.append("; ");
		}
	}

	private void printNumStages(Racer r, StringBuilder out) {
		out.append(r.finishTimes.get(1).size());
		out.append("; ");
	}

	private void printPosition(Racer r, StringBuilder out,
			HashMap<String, String> extraInformation) {
		String position = extraInformation.get("position");

		if (position == null) {
			position = "";
		}

		out.append(position);
		out.append("; ");
	}

	private void printTotalTime(Racer r, StringBuilder out) {
		Iterator<Time> itr = r.startTimes.get(1).iterator();
		Time total = new Time("00.00.00");
		Time before;
		for (Time next : r.finishTimes.get(1)) {
			before = itr.next();
			total.increment(before.getTotalTime(next));
		}
		out.append(total.toString());
		out.append("; ");
	}

	public String printTopInformation() {
		StringBuilder out = new StringBuilder();
		out.append("Plac; ");
		out.append(extraInformation);
		out.append("Totaltid; #Etapper");

		for (int i = 1; i <= stages; i++) {
			out.append("; Etapp");
			out.append(i);
		}
		System.out.println(out.toString());
		return out.toString();
	}

	public void setHeaderInformation(String[] extraInformation) {
		for (String str : extraInformation) {
			this.extraInformation += str + "; ";
		}
		extraRunnerInformation = extraInformation.length;
	}

}
