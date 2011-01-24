package enduro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import enduro.racedata.PersonData;
import enduro.racedata.Time;
import enduro.racedata.TimeData;

/**
 * Program for generating a result file
 * in an Enduro race.
 */
public class Sorter {
	
	private TimeData timeData;
	private PersonData personData;
	
	/**
	 * Creates a new Sorter object and
	 * initializes the data structures.
	 */
	public Sorter() {
		timeData = new TimeData();
		personData = new PersonData();
	}
	
	/**
	 * Reads a file with start times and populates the data structure..
	 * @param fileName The name of the file.
	 */
	public void readStartFile(String fileName) throws Exception{
		ArrayList<String[]> startingTimes = readFile(fileName);
		for (int i = 0; i < startingTimes.size(); i++) {
			int startNbr = Integer.parseInt(startingTimes.get(i)[0]);
			timeData.addStartTime(startNbr, new Time(startingTimes.get(i)[1]));
		}
	}
	
	/**
	 * Reads a file with finish times and populates the data structure.
	 * @param fileName The name of the file.
	 */
	public void readFinishFile(String fileName) throws Exception{
		ArrayList<String[]> finishTimes = readFile(fileName);
		for (int i = 0; i < finishTimes.size(); i++) {
			int startNbr = Integer.parseInt(finishTimes.get(i)[0]);
			timeData.addFinishTime(startNbr, new Time(finishTimes.get(i)[1]));
		}
	}
	/**
	 * Reads a file with a start number and a time and returns and ArrayList containing
	 * String arrays containing the start number and the time.
	 * @param fileName The name of the file.
	 * @return ArrayList<String[]> containing start numbers and time.
	 * @throws Exception
	 */
	private ArrayList<String[]> readFile(String fileName) throws Exception {
		ArrayList<String[]> list = new ArrayList<String[]>();

		BufferedReader in = new BufferedReader(new FileReader(fileName));
		while(in.ready()){
			list.add(in.readLine().split("; "));
		}
		return list;
	}
	
	public void readNameFile(String fileName) throws Exception{
		ArrayList<String[]> names = readFile(fileName);
		for (int i = 0; i < names.size(); i++) {
			int startNbr = Integer.parseInt(names.get(i)[0]);
			personData.addName(startNbr, names.get(i)[1]);
		}
	}
	
	/**
	 * Creates a result file based on the information
	 * from readStartFile and readFinishFile.
	 * @param fileName The name of the result file to be created.
	 * @throws IOException In case of an I/O error.
	 */
	public void createResultFile(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		out.println("StartNr; Namn; TotalTid; StartTider; Måltider");
		Iterator<Integer> itr = timeData.getRunnerIterator();
		while (itr.hasNext()) {
			int i = itr.next();
			String name = personData.getName(i);
			String start = "Start?";
			String finish = "Slut?";
			String total = "--.--.--";
			StringBuilder trail = new StringBuilder();
			Time startTime = null;
			Time finishTime = null;
			try {
				startTime = timeData.getStartTime(i).poll();
				start = startTime.toString();
				if(timeData.getStartTime(i).size() > 0){
					trail.append("; Flera starttider?");
					while(timeData.getStartTime(i).size() > 0) {
						trail.append(' ');
						trail.append(timeData.getStartTime(i).poll());
					}
				}
			} catch (NullPointerException e) {
			}

			try {
				finishTime = timeData.getFinishTime(i).poll();
				finish = finishTime.toString();
				if(timeData.getFinishTime(i).size() > 0){
					trail.append("; Flera måltider?");
					while(timeData.getFinishTime(i).size() > 0){
						trail.append(' ');
						trail.append(timeData.getFinishTime(i).poll());
					}
				}
			} catch (NullPointerException e) {
			}

			try {
				Time totalTime = startTime.getTotalTime(finishTime);
				total = totalTime.toString();
				Time fastTime = new Time(0, 15, 0);
				if(fastTime.compareTo(totalTime) > 0) trail.append("; Omöjlig Totaltid?");
			} catch (NullPointerException e) {}
			

				
				out.println(i +"; "+ name +"; " + total +"; " + start + "; " + finish + trail.toString());

			}
			
		out.close();
	}
	
}
