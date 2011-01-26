package enduro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import enduro.racedata.Time;

/**
 * Program for generating a result file in an Enduro race.
 */
public class MarathonSorter extends Sorter {

	/**
	 * Creates a new Sorter object and initializes the data structures.
	 */
	public MarathonSorter() {
		super();
	}

	/**
	 * Creates a result file based on the information from readStartFile and
	 * readFinishFile.
	 * 
	 * @param fileName
	 *            The name of the result file to be created.
	 * @throws IOException
	 *             In case of an I/O error.
	 */
	public void createResultFile(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		out.println("StartNr; Namn; TotalTid; StartTider; Måltider");
		Iterator<Integer> itr = timeData.getRunnerIterator();
		while (itr.hasNext()) {
			int i = itr.next();
			String name = personData.getName(i);
			String start;
			String finish;
			String total;
			StringBuilder trail = new StringBuilder();
			Time startTime = null;
			Time finishTime = null;
			try {
				startTime = timeData.getStartTime(i).poll();
				start = startTime.toString();
				if (timeData.getStartTime(i).size() > 0) {
					trail.append("; Flera starttider?");
					while (timeData.getStartTime(i).size() > 0) {
						trail.append(' ');
						trail.append(timeData.getStartTime(i).poll());
					}
				}
			} catch (NullPointerException e) {
				start = "Start?";
			}
			try {
				finishTime = timeData.getFinishTime(i).poll();
				finish = finishTime.toString();
				if (timeData.getFinishTime(i).size() > 0) {
					trail.append("; Flera måltider?");
					while (timeData.getFinishTime(i).size() > 0) {
						trail.append(' ');
						trail.append(timeData.getFinishTime(i).poll());
					}
				}
			} catch (NullPointerException e) {
				finish = "Slut?";
			}
			try {
				Time totalTime = startTime.getTotalTime(finishTime);
				total = totalTime.toString();
				Time fastTime = new Time(0, 15, 0);
				if (fastTime.compareTo(totalTime) > 0)
					trail.append("; Omöjlig Totaltid?");
			} catch (NullPointerException e) {
				total = "--.--.--";
			}

			out.println(i + "; " + name + "; " + total + "; " + start + "; "
					+ finish + trail.toString());
		}
		out.close();
	}
}