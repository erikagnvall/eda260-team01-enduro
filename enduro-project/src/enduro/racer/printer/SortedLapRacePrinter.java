package enduro.racer.printer;

import java.util.HashMap;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

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
		out.append(r.startTimes.first().getTotalTime(r.finishTimes.last()));
		out.append("; ");
	}

	public String printTopInformation() {
		StringBuilder out = new StringBuilder();
		out.append("Plac; " + extraInformation + "#Varv; TotalTid");
		
		for(int i = 1; i <= ConfigParser.getInstance().getIntConf("laps"); i++) {
			out.append("; Varv");
			out.append(i);
		}
		
		return out.toString();
	}

	private void printNumLapses(Racer r, StringBuilder out) {
		out.append(r.finishTimes.size());
		out.append("; ");
	}

	private void printLapses(Racer r, StringBuilder out) {
		Time before = r.startTimes.first();
		
		for(Time next: r.finishTimes) {
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
