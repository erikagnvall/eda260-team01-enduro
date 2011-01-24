package enduro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import enduro.racedata.Time;
import enduro.racedata.TimeData;

/**
 * Program for generating a result file
 * in an Enduro race.
 */
public class Sorter {
	
	private TimeData timeData;
	
	/**
	 * Creates a new Sorter object and
	 * initializes the data structures.
	 */
	public Sorter() {
		timeData = new TimeData();
	}
	
	/**
	 * Reads a file with start times and populates the data structure..
	 * @param fileName The name of the file.
	 */
	public void readStartFile(String fileName) throws Exception{
		try{
			ArrayList<String[]> startingTimes = readFile(fileName);
			for (int i = 0; i < startingTimes.size(); i++) {
				int startNbr = Integer.parseInt(startingTimes.get(i)[0]);
				timeData.addStartTime(startNbr, new Time(startingTimes.get(i)[1]));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * Reads a file with finish times and populates the data structure.
	 * @param fileName The name of the file.
	 */
	public void readFinishFile(String fileName) throws Exception{
		try{
			ArrayList<String[]> finishTimes = readFile(fileName);
			for (int i = 0; i < finishTimes.size(); i++) {
				int startNbr = Integer.parseInt(finishTimes.get(i)[0]);
				timeData.addFinishTime(startNbr, new Time(finishTimes.get(i)[1]));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
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
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while(in.ready()){
				list.add(in.readLine().split("; "));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return list;
	}
	
	/**
	 * Creates a result file based on the information
	 * from readStartFile and readFinishFile.
	 * @param fileName The name of the result file to be created.
	 * @throws IOException In case of an I/O error.
	 */
	public void createResultFile(String fileName) throws IOException {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			out.println("StartNr; Totaltid; Starttid; Måltid");
			Iterator<Integer> itr = timeData.getRunnerIterator();
			while(itr.hasNext()){
				int i = itr.next();
				Time startTime = timeData.getStartTime(i).get(0);
				Time finishTime = timeData.getFinishTime(i).get(0);
				Time totalTime = startTime.getTotalTime(finishTime);
				out.println(i +"; " + totalTime.toString() +"; " + startTime + "; " + finishTime);
			}
			out.close();
	}
}
	

