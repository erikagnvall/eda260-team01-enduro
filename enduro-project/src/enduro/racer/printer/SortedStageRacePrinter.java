package enduro.racer.printer;

import java.util.HashMap;
import java.util.TreeSet;

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
		
		//Iterator<Time> itr = r.startTimes.get(1).iterator();
		
		for (int key = 1; key <= this.stages; key++) {
			if(r.finishTimes.get(key)!=null && r.startTimes.get(key)!=null) {
				out.append(r.startTimes.get(key).first().getTotalTime(r.finishTimes.get(key).first()));
			}
			out.append("; ");
		}
	}

	private void printNumStages(Racer r, StringBuilder out) {
		int stages = 0;
		if(r.finishTimes.get(1).size() > 0 && r.startTimes.get(1).size() > 0)
			stages++;
		for(int i = 2; i <= this.stages; i++) {
			if(r.finishTimes.containsKey(i) && r.startTimes.containsKey(i))
				stages++;
		}
		out.append(stages);
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
		
		Time total = new Time("00.00.00");
		for (int key = 1; key <= this.stages; key++) {
			if(r.finishTimes.containsKey(key) && r.startTimes.containsKey(key)) {
				if(r.finishTimes.get(1).size() == 0 | r.startTimes.get(1).size() == 0)
					continue;
				total.increment(r.startTimes.get(key).first().getTotalTime(r.finishTimes.get(key).first()));
			}
			
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
