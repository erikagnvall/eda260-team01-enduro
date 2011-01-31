package enduro.racedata;


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
	/**
	 * 
	 * @return name of this class
	 */
	public String getName(){
		return name;
	}
	/**
	 * 
	 * @return set of numbers registered to this clss
	 */
	public TreeSet<Integer> getList(){
		return numbers;
	}
	/**
	 * 
	 * @return iterator over the sets of numbers registered to this class
	 */
	public Iterator<Integer> iterator(){
		return numbers.iterator();
	}

}
