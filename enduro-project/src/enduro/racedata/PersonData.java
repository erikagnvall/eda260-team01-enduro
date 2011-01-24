package enduro.racedata;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonData {
	private HashMap<Integer,ArrayList<String>> names;
	
	public PersonData(){
		names = new HashMap<Integer,ArrayList<String>>();
		
	}
	public void addName(int startNr, String name){
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(name);
		names.put(startNr,temp);
	}
	public String getName(int startNr){
		return names.get(startNr).get(0);
		
	}

}
