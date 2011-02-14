package enduro.racer.printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import enduro.racer.Racer;
import enduro.racer.Time;
import enduro.racer.configuration.ConfigParser;

/**
 * prints a stage race with each stage included and so forth.
 */
public class StageRacePrinter implements RacerPrinter {
	private String extraInformation = "";
	private int stages = ConfigParser.getInstance().getIntConf("stages");
	private int extraRunnerInformation;

	/**
	 * returns a string that contains stage race info. 
	 */
	public String print(Racer r, HashMap<String, String> extraInformation) {
		
		System.out.println(r.finishTimes.toString());
		System.out.println(r.startTimes.toString());
		StringBuilder out = new StringBuilder();
		
		TreeMap<Integer, Time> stages = this.getStages(r);
		
		StringBuilder errorTrail = new StringBuilder();
		
		printRunnerInformation(r, out, stages, errorTrail);
		
		printTotalTime(r, out, stages, errorTrail);
		
		printNbrOfStages(r, out, stages, errorTrail);
		
		printStages(r, out, stages, errorTrail);
		
		printStartAndFinish(r, out, stages, errorTrail);
		
		out.append(errorTrail.toString());
		
		return out.toString();
	}

	private void printNbrOfStages(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		out.append(stages.size());
		out.append("; ");
	}

	private void printRunnerInformation(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		int i = 0;
		for (; i < r.racerInformation.size(); i++) {
			out.append(r.racerInformation.get(i));
			out.append("; ");
		}
		for (; i < extraRunnerInformation; i++) {
			out.append("; ");
		}

	}

	private void printStartAndFinish(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		
		for(int stage:stages.keySet()) {
			if(r.startTimes.get(stage)!=null)
				out.append(r.startTimes.get(stage).first());
			out.append("; ");
			if(r.finishTimes.get(stage)!=null)
				out.append(r.finishTimes.get(stage).first());
			out.append("; ");
		}
		out.delete(out.length()-2, out.length());
	}

	public String printTopInformation() {
		StringBuilder out = new StringBuilder();
		out.append(extraInformation);
		out.append("Totaltid; #Etapper");

		for (int i = 1; i <= stages; i++) {
			out.append("; Etapp");
			out.append(i);
		}
		for (int i = 1; i <= stages; i++) {
			out.append("; Start");
			out.append(i);
			out.append("; Mål");
			out.append(i);
		}
		return out.toString();
	}

	public void setHeaderInformation(String[] extraInformation) {
		extraRunnerInformation = extraInformation.length;
		for (int i = 0; i < extraRunnerInformation; i++) {
			this.extraInformation += extraInformation[i] + "; ";
		}
	}

	private void printStages(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		for(int stage:stages.keySet()) {
			if(stages.get(stage) != null)
				out.append(stages.get(stage));
			out.append("; ");
		}
	}

	private void printTotalTime(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		Time totalTime = new Time(0, 0, 0);
		for(int stage:stages.keySet()) {
			if(stages.get(stage) != null)
				totalTime.increment(stages.get(stage));
		}
		out.append(totalTime.toString());
		out.append("; ");
	}

	private TreeMap<Integer, Time> getStages(Racer r) {
		TreeSet<Integer> stagesList = new TreeSet<Integer>();
		stagesList.addAll(r.startTimes.keySet());
		stagesList.addAll(r.finishTimes.keySet());
		
		TreeMap<Integer, Time> res = new TreeMap<Integer, Time>();
		
		for(int stage: stagesList) {
			if(r.startTimes.containsKey(stage) && r.finishTimes.containsKey(stage)) {
				res.put(stage, r.startTimes.get(stage).first().getTotalTime(r.finishTimes.get(stage).last()));
			} else {
				res.put(stage, null);
			}
		}
		return res;
	}
}
