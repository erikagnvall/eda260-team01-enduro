package enduro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import enduro.racedata.RaceClass;
import enduro.racedata.Time;
import enduro.racedata.RacerData;

/**
 * Superclass for all sorters. Merges data from input files and ouputs them in a
 * readable format according to the formatting rules (Subclass) used.
 * 
 * @author Rick
 * 
 */
public abstract class Sorter {

	protected RacerData racerData;
	protected PriorityQueue<Time> startTimes;
	protected PriorityQueue<Time> finishTimes;
	protected Time startTime;
	protected Time finishTime;
	protected StringBuilder trail;
	protected String nameInformation = "";
	protected int extrainformationNum = 0; //defined as: namefile semi colons minus 1 (due to racer number is handled separately

	public Sorter() {
		racerData = new RacerData();
		trail = new StringBuilder();
	}

	/**
	 * Reads a file with start times and populates the data structure.. detects
	 * if any number isn't registered to a contestant and give them a specific
	 * non registered class
	 * 
	 * @param fileName
	 *            The name of the file.
	 */
	public void readStartFile(String fileName) throws Exception {
		ArrayList<String[]> startingTimes = readFile(fileName);
		for (int i = 0; i < startingTimes.size(); i++) {
			int startNbr = Integer.parseInt(startingTimes.get(i)[0]);
			if (!racerData.contestantIsRegistered(startNbr)) {
				RaceClass raceClass = new RaceClass(
						"Icke existerande startnummer");
				if (!racerData.containsClass(raceClass))
					racerData.addClass(raceClass);
				racerData.addName(startNbr, extrainformationNum, raceClass);

			}
			racerData.addStartTime(startNbr, new Time(startingTimes.get(i)[1]));
		}
	}

	/**
	 * Reads a file with finish times and populates the data structure. detects
	 * if any number isn't registered to a contestant and give them a specific
	 * non registered class
	 * 
	 * @param fileName
	 *            The name of the file.
	 */
	public void readFinishFile(String fileName) throws Exception {
		ArrayList<String[]> finishTimes = readFile(fileName);
		for (int i = 0; i < finishTimes.size(); i++) {
			int startNbr = Integer.parseInt(finishTimes.get(i)[0]);
			if (!racerData.contestantIsRegistered(startNbr)) {
				RaceClass raceClass = new RaceClass(
						"Icke existerande startnummer");
				if (!racerData.containsClass(raceClass))
					racerData.addClass(raceClass);
				racerData.addName(startNbr, extrainformationNum, raceClass);

			}
			racerData.addFinishTime(startNbr, new Time(finishTimes.get(i)[1]));
		}
	}

	/**
	 * Reads a file with a start number and a time and returns and ArrayList
	 * containing String arrays containing the start number and the time.
	 * 
	 * @param fileName
	 *            The name of the file.
	 * @return ArrayList<String[]> containing start numbers and time.
	 * @throws Exception
	 *             when something goes wrong.
	 */
	private ArrayList<String[]> readFile(String fileName) throws Exception {
		ArrayList<String[]> list = new ArrayList<String[]>();

		BufferedReader in = new BufferedReader(new FileReader(fileName));
		while (in.ready()) {
			list.add(in.readLine().split("; "));
		}
		return list;
	}

	/**
	 * Reads a file containing names of racers and adds them to the data
	 * structure.
	 * 
	 * @param fileName
	 *            the name of the file to be read.
	 * @throws Exception
	 *             when something goes wrong.
	 */
	public void readNameFile(String fileName) throws Exception {
		ArrayList<String[]> names = readFile(fileName);
		RaceClass currentClass = new RaceClass("");
		
		//TODO will need more handling if/when we should be able to read multiple name files.
		String[] information = names.get(0);
		StringBuilder namePart = new StringBuilder();
		for(int i = 0; i < information.length; i++) {
			namePart.append(information[i] + "; ");
		}
		this.nameInformation = namePart.toString();
		this.extrainformationNum = information.length -1;
		
		for (int i = 1; i < names.size(); i++) {
			try {
				int startNbr = Integer.parseInt(names.get(i)[0]);
				racerData.addName(names.get(i));
				currentClass.registerContestant(startNbr);
			} catch (NumberFormatException e) {
				currentClass = new RaceClass(names.get(i)[0]);
				racerData.addClass(currentClass);
			}

		}
		if (!racerData.containsClass(currentClass)) {
			racerData.addClass(currentClass);
		}
	}

	/**
	 * Method used for testing Returns an ArrayList of registered classes
	 * 
	 * @return
	 */
	public ArrayList<RaceClass> getClasses() {
		return racerData.getClasses();
	}

	/**
	 * Creates a readable results file from the gathered data.
	 * 
	 * @param fileName
	 *            the name of the output file.
	 * @throws IOException
	 *             when something goes wrong.
	 */
	public void createResultFile(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		Iterator<RaceClass> itr = racerData.iterator();
		while (itr.hasNext()) {
			RaceClass currentClass = itr.next();
			if (!currentClass.getName().equals(""))
				out.println(currentClass.getName());
			Iterator<Integer> nbrItr = currentClass.iterator();
			out.println(titleRow(currentClass.iterator()));
			while (nbrItr.hasNext()) {
				int i = nbrItr.next();
				startTimes = racerData.getStartTime(i);
				finishTimes = racerData.getFinishTime(i);
				out.println(linePrint(i));
			}
		}
		out.close();
	}

	/**
	 * Creates a list sorted based on the rules of the current competition.
	 * 
	 * @param fileName
	 *            the name of the output file.
	 * @throws IOException
	 */
	public void createSortedResultsFile(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		Iterator<RaceClass> itr = racerData.iterator();
		while (itr.hasNext()) {
			RaceClass currentClass = itr.next();
			if (!currentClass.getName().equals(""))
				out.println(currentClass.getName());
			// Sorts racers according to abstract method
			ArrayList<Racer> racers = sortRacers();
			ArrayList<Integer> positions = new ArrayList<Integer>();
			for (int i = 0; i < racers.size(); i++) {
				positions.add(racers.get(i).number);
			}
			Iterator<Integer> nbrItr = positions.iterator();
			out.println(titleRow(currentClass.iterator()));
			while (nbrItr.hasNext()) {
				int i = nbrItr.next();
				startTimes = racerData.getStartTime(i);
				finishTimes = racerData.getFinishTime(i);
				out.println(linePrint(i));
			}
		}
		out.close();
	}

	/**
	 * Prints a line with data from a race.
	 * 
	 * @param racerNumber
	 * @return
	 */
	private String linePrint(int racerNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append(racerNumber);
		sb.append("; ");
		sb.append(racerData.getRacerInfo(racerNumber));
		sb.append(getData(racerNumber));
		return sb.toString();
	}

	/**
	 * Gets the data for the specified racer number
	 * 
	 * @param racerNumber
	 */
	protected abstract String getData(int racerNumber);

	/**
	 * Returns the title row formatted according to the current race type.
	 * 
	 * @return the title row.
	 */
	protected abstract String titleRow(Iterator<Integer> itr);

	/**
	 * Calculates and returns the value for the total time column. How this is
	 * calculated differs between race types.
	 * 
	 * @param i
	 *            the racer's number.
	 * @return a <code>String</code> containing the total time on the format
	 *         "hh.mm.ss".
	 */
	protected abstract String totalTime(int i);

	/**
	 * Sorts racers in accordance with the type of race.
	 * 
	 * @return an array of ints containing the racers' numbers in the order that
	 *         they ranked.
	 */
	protected ArrayList<Racer> sortRacers() {
		ArrayList<Racer> racers = new ArrayList<Racer>();
		Iterator<Integer> itr = racerData.numberIterator();
		int i = 0;
		while (itr.hasNext()) {
			i = itr.next();
			racers.add(new Racer(i, racerData.getNumberOfLaps(i), racerData
					.getTotalTime(i)));
		}
		Collections.sort(racers);
		return racers;
	}

	/**
	 * Get the set of rules according to which racers should be compared (Time
	 * or laps)
	 * 
	 * @return See Racer constants.
	 */
	protected abstract int compareType();

	/**
	 * Represents a racers.
	 * 
	 * @author et05aw7
	 * 
	 */
	protected class Racer implements Comparable<Racer> {
		int number;
		int numLaps;
		Time time;
		public static final int LAP_COMPARE = 0;
		public static final int TIME_COMPARE = 1;

		protected Racer(int number, int numLaps, Time time) {
			this.number = number;
			this.numLaps = numLaps;
			this.time = time;
		}

		/**
		 * Compares Racers
		 */
		public int compareTo(Racer o) {
			int type = compareType();
			switch (type) {
			case LAP_COMPARE:
				int out = numLaps - o.numLaps;
				if (out != 0)
					return out;
				return time.compareTo(o.time);
			case TIME_COMPARE:
				return time.compareTo(o.time);
			default:
				return 0;
			}
		}
	}

}