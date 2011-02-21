package enduro.racer.printer;

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
	private String[] specialStages = ConfigParser.getInstance().getStringConf("special").split(",");
	private String[] multiplyers = ConfigParser.getInstance().getStringConf("multiply").split(",");
	

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
		
		if(errorTrail.length() > 0) {
			out.append("; ");
			out.append(errorTrail.toString());
		}
		
		
		return out.toString();
	}
	
	/**
	 * Prints the number of stages (as defined by the racer construct
	 * @param r the racer to be printed
	 * @param out the stringbuilder which will be outputed
	 * @param stages the fully completed stages (e.g. stages with both start and finish times)
	 * @param errorTrail outdata to be printed to the end of the out stream
	 */
	private void printNbrOfStages(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		out.append(stages.size());
		out.append("; ");
	}

	/**
	 * Prints the racer's information
	 * @param r the racer to be printed
	 * @param out the stringbuilder which will be outputed
	 * @param stages the fully completed stages (e.g. stages with both start and finish times)
	 * @param errorTrail outdata to be printed to the end of the out stream
	 */
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

	/**
	 * Prints the start and finish times. Nonexisting start or finish times are replaced by
	 * "Start?" respectively "Slut?"
	 * @param r the racer to be printed
	 * @param out the stringbuilder which will be outputed
	 * @param stages the fully completed stages (e.g. stages with both start and finish times)
	 * @param errorTrail outdata to be printed to the end of the out stream
	 */
	private void printStartAndFinish(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {

		/*
		  || (stage == 1 && r.startTimes.get(1).size()==0)
		  
		 */
		for(int stage=1; stage <= this.stages; stage++) {
			if(r.startTimes.containsKey(stage))
				if(stage == 1 && r.startTimes.get(1).size()==0)
					out.append("Start?");
				else
					out.append(r.startTimes.get(stage).first());
			else
				out.append("Start?");
			out.append("; ");
			

			if(r.finishTimes.containsKey(stage))
				if(stage == 1 && r.finishTimes.get(1).size()==0)
					out.append("Slut?");
				else
					out.append(r.finishTimes.get(stage).first());
			else
				out.append("Slut?");
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

	/**
	 * Prints the "total time" of each stage. Handles wrong number of start/finish times and appends to error stream
	 * @param r the racer to be printed
	 * @param out the stringbuilder which will be outputed
	 * @param stages the fully completed stages (e.g. stages with both start and finish times)
	 * @param errorTrail outdata to be printed to the end of the out stream
	 */
	private void printStages(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		
		StringBuilder startErr = new StringBuilder();
		StringBuilder finishErr = new StringBuilder();
		
		
		
		for(int stage=1; stage <= this.stages; stage++) {
			if(stages.get(stage) != null)
				out.append(stages.get(stage));
			
			if(r.finishTimes.containsKey(stage))
				if(r.finishTimes.get(stage).size() > 1) {
					Iterator<Time> t = r.finishTimes.get(stage).iterator();
					t.next();
					while(t.hasNext())
						finishErr.append("Etapp" + stage + " " + t.next().toString());
				}
			
			if(r.startTimes.containsKey(stage))
				if(r.startTimes.get(stage).size() > 1) {
					Iterator<Time> t = r.startTimes.get(stage).iterator();
					t.next();
					while(t.hasNext())
						startErr.append("Etapp" + stage + " " + t.next().toString());
				}
			
			out.append("; ");
		}
		
		if(startErr.length() > 0)
			errorTrail.append("Flera Startttider " + startErr.toString());
		if(finishErr.length() > 0)
			errorTrail.append("Flera Sluttider " + finishErr.toString());
		
	}

	/**
	 * Prints the total time. if no completed finish times exists (e.g. time is zero); "--.--.--" is printed instead
	 * @param r the racer to be printed
	 * @param out the stringbuilder which will be outputed
	 * @param stages the fully completed stages (e.g. stages with both start and finish times)
	 * @param errorTrail outdata to be printed to the end of the out stream
	 */
	private void printTotalTime(Racer r, StringBuilder out, TreeMap<Integer, Time> stages,
			StringBuilder errorTrail) {
		Time totalTime = new Time(0, 0, 0);
		for(int stage:stages.keySet()) {
			totalTime.increment(stages.get(stage));
		}
		if(totalTime.equals(new Time("00.00.00")))
			out.append("--.--.--");
		else
			out.append(totalTime.toString());
		out.append("; ");
	}

	/**
	 * iterates over all stages and adds the total time to each stage with both existing finish and start times.
	 * @param r the racer to be printed
	 * @param out the stringbuilder which will be outputed
	 * @param stages the fully completed stages (e.g. stages with both start and finish times)
	 * @param errorTrail outdata to be printed to the end of the out stream
	 */
	private TreeMap<Integer, Time> getStages(Racer r) {
		TreeSet<Integer> stagesList = new TreeSet<Integer>();
		stagesList.addAll(r.startTimes.keySet());
		stagesList.addAll(r.finishTimes.keySet());
		
		TreeMap<Integer, Time> res = new TreeMap<Integer, Time>();
		
		for(int stage: stagesList) {
			if(r.startTimes.containsKey(stage) && r.finishTimes.containsKey(stage)) {
				if(stage == 1)
					if(!(r.startTimes.get(1).size() > 0 && r.finishTimes.get(1).size() > 0))
						continue;
				res.put(stage, r.startTimes.get(stage).first().getTotalTime(r.finishTimes.get(stage).first()));
			}
		}
		return res;
	}
}
