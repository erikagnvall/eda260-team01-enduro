package enduro.racedata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/** Class representing the classes registered in a race
 * 
 * @author dt09go2
 *
 */
public class RaceClass {
	private TreeSet<Integer> numbers;
	private String name;
	
	public RaceClass(String name){
		this.name = name;
		numbers = new TreeSet<Integer>();
	}
	/** Register a start number to the specific class
	 * 
	 * @param nbr number to be registered
	 */
	public void registerContestant(int nbr){
		numbers.add(nbr);
	}
	public String getName(){
		return name;
	}
	public TreeSet<Integer> getList(){
		return numbers;
	}
	public Iterator<Integer> iterator(){
		return numbers.iterator();
	}

}
