package enduro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import enduro.racedata.PersonData;
import enduro.racedata.Time;
import enduro.racedata.TimeData;

public class Sorter {

	protected TimeData timeData;
	protected PersonData personData;

	public Sorter() {
		timeData = new TimeData();
		personData = new PersonData();
	}

	/**
	 * Reads a file with start times and populates the data structure..
	 * 
	 * @param fileName
	 *            The name of the file.
	 */
	public void readStartFile(String fileName) throws Exception {
		ArrayList<String[]> startingTimes = readFile(fileName);
		for (int i = 0; i < startingTimes.size(); i++) {
			int startNbr = Integer.parseInt(startingTimes.get(i)[0]);
			timeData.addStartTime(startNbr, new Time(startingTimes.get(i)[1]));
		}
	}

	/**
	 * Reads a file with finish times and populates the data structure.
	 * 
	 * @param fileName
	 *            The name of the file.
	 */
	public void readFinishFile(String fileName) throws Exception {
		ArrayList<String[]> finishTimes = readFile(fileName);
		for (int i = 0; i < finishTimes.size(); i++) {
			int startNbr = Integer.parseInt(finishTimes.get(i)[0]);
			timeData.addFinishTime(startNbr, new Time(finishTimes.get(i)[1]));
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
	 */
	private ArrayList<String[]> readFile(String fileName) throws Exception {
		ArrayList<String[]> list = new ArrayList<String[]>();
	
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		while (in.ready()) {
			list.add(in.readLine().split("; "));
		}
		return list;
	}

	public void readNameFile(String fileName) throws Exception {
		ArrayList<String[]> names = readFile(fileName);
		for (int i = 0; i < names.size(); i++) {
			int startNbr = Integer.parseInt(names.get(i)[0]);
			personData.addName(startNbr, names.get(i)[1]);
		}
	}

}