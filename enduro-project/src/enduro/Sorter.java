package enduro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	public void readStartFile(String fileName) {
		//TODO
		timeData.addStartTime(1, new Time(12, 00, 00));
	}
	
	/**
	 * Reads a file with finish times and populates the data structure.
	 * @param fileName The name of the file.
	 */
	public void readFinishFile(String fileName) {
		//TODO
		timeData.addFinishTime(1, new Time(12, 30, 00));
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
		out.println("1; 00.30.00; 12.00.00; 12.30.00");
		out.close();
		//TODO
	}
	
}
