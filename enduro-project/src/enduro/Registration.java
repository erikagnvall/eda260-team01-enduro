package enduro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import enduro.racedata.Time;

/**
 * Program for registering start and finish
 * times in an Enduro race.
 */
public class Registration {
	
	private PrintWriter out;
	
	/**
	 * Creates a new Registration object.
	 * @param fileName The name of the file to be written to.
	 * @throws IOException In case of an I/O error.
	 */
	public Registration(String fileName) throws IOException  {
		out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
	}
	
	/**
	 * Writes a race time to a start or finish file.
	 * @param startNbr The racer's start number.
	 * @param time The racer's start or finish time.
	 */
	public void registerTime(int startNbr, Time time){
		 out.println(startNbr + "; " + time);
	}
	
	/**
	 * Closes the output stream for writing. Call
	 * this method when no more times should be written
	 * to the file.
	 */
	public void close() {
		out.close();
	}
	
}
