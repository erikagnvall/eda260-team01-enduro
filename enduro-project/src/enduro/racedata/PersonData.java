package enduro.racedata;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonData {
	private HashMap<Integer, ArrayList<String>> names;

	/**
	 * A class for representing a racer in terms of personal data, such as name.
	 */
	public PersonData() {
		names = new HashMap<Integer, ArrayList<String>>();

	}

	/**
	 * Adds a name to a racer.
	 * 
	 * @param startNr
	 *            the racer's number
	 * @param name
	 *            the racer's name
	 */
	public void addName(int startNr, String name) {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(name);
		names.put(startNr, temp);
	}

	/**
	 * Gets the racer's name.
	 * 
	 * @param startNr
	 *            the racer number.
	 * @return The racer's name.
	 */
	public String getName(int startNr) {
		return names.get(startNr).get(0);

	}

}
