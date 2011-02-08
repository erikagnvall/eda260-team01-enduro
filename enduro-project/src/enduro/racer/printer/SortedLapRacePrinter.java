package enduro.racer.printer;

import java.util.HashMap;

import enduro.InputHandler.ConfigParser;
import enduro.racedata.Time;
import enduro.racer.Racer;

public class SortedLapRacePrinter implements RacerPrinter {

	public String print(Racer r, HashMap<String, String> extraInformation) {
		//Plac; StartNr; Namn; #Varv; Totaltid; Varv1; Varv2
		StringBuilder out = new StringBuilder();
		
		printPosition(r, out, extraInformation);
		
		printNameAndNumber(r, out);
		
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
		out.append("Plac; StartNr; Namn; #Varv; Totaltid");
		
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

	private void printNameAndNumber(Racer r, StringBuilder out) {
		out.append(r.racerInformation.get(0));
		out.append("; ");
		
		if(r.racerInformation.size() > 1) {
			out.append(r.racerInformation.get(1));
		}
		out.append("; ");
	}

	private void printPosition(Racer r, StringBuilder out, HashMap<String, String> extraInformation) {
		
		String position = extraInformation.get("position");
		
		if(position == null) {
			position = "";
		}
		
		out.append(position);
		out.append("; ");	
	}
	

}
