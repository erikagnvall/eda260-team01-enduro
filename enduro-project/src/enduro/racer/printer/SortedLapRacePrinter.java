package enduro.racer.printer;

import java.util.HashMap;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

/**
 * Prints a sorted list (that is, a list with positions printed out, but no errors).
 * Requires a lap race model for correct input, but no assumptions are made.
 */
public class SortedLapRacePrinter implements RacerPrinter {

	String extraInformation = "";
	int extraRunnerInformation = 0;
	
	public String print(Racer r, HashMap<String, String> extraInformation) {
		//Plac; StartNr; Namn; #Varv; Totaltid; Varv1; Varv2
		StringBuilder out = new StringBuilder();
		
		printPosition(r, out, extraInformation);
		
		printRunnerInformation(r, out);
		
		printNumLapses(r, out);
		
		printTotalTime(r, out);
		
		printLapses(r, out);
		
		out.delete(out.length()-2, out.length());
		
		return out.toString();
	}
	
	private void printTotalTime(Racer r, StringBuilder out) {
		out.append(r.startTimes.get(1).first().getTotalTime(r.finishTimes.get(1).last()));
		out.append("; ");
	}

	public String printTopInformation() {
		StringBuilder out = new StringBuilder();
		out.append("Plac; " + extraInformation + "#Varv; Totaltid");
		
		for(int i = 1; i <= ConfigParser.getInstance().getIntConf("laps"); i++) {
			out.append("; Varv");
			out.append(i);
		}
		
		return out.toString();
	}

	private void printNumLapses(Racer r, StringBuilder out) {
		out.append(r.finishTimes.get(1).size());
		out.append("; ");
	}

	private void printLapses(Racer r, StringBuilder out) {
		Time before = r.startTimes.get(1).first();
		
		for(Time next: r.finishTimes.get(1)) {
			out.append(before.getTotalTime(next));
			out.append("; ");
			before = next;
		}
	}

	/**
	 * this function calculates the runner name and all extra information
	 * that was inserted with the user. Unknown information is padded with ;<whitespace>
	 * this is a "dumb" implementation as it does not map data which data is printed, it just uses the
	 * racer arraylist.
	 * @param r the racer that is printed
	 * @param out the stringbuilder class that will in the end supply the result.
	 * @param errorTrail the stringbuilder class that will summarize the errors collected.
	 */
	private void printRunnerInformation(Racer r, StringBuilder out) {
		int i = 0;
		
		for(; i < r.racerInformation.size(); i++) {
			out.append(r.racerInformation.get(i));
			out.append("; ");
		}
		for(; i < extraRunnerInformation; i++) {
			out.append("; ");
		}
	}

	private void printPosition(Racer r, StringBuilder out, HashMap<String, String> extraInformation) {
		
		String position = extraInformation.get("position");
		
		if(position == null) {
			position = "";
		}
		
		out.append(position);
		out.append("; ");	
	}

	public void setHeaderInformation(String[] extraInformation) {
		for(String str: extraInformation) {
			this.extraInformation += str + "; ";
		}
		extraRunnerInformation = extraInformation.length;
	}
	

}
