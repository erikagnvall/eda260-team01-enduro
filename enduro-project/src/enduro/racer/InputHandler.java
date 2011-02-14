package enduro.racer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import enduro.racedata.Time;
import enduro.racer.comparators.runnerLapseComparator;
import enduro.racer.comparators.runnerNumberComparator;
import enduro.racer.printer.LapRacePrinter;
import enduro.racer.printer.RacerPrinter;

/**
 * this parses all files.
 *
 */
public class InputHandler {

	String[] headerInformation;
	private ArrayList<String> nameFileLocations = new ArrayList<String>();
	private ArrayList<String> finishFileLocations = new ArrayList<String>();
	private ArrayList<String> startFileLocations = new ArrayList<String>();
	HashMap<Integer, Racer> racerList;
	ArrayList<RacerSorter> groups;
	
	public void addNameFile(String location) {
		nameFileLocations.add(location);
	}
	
	public void addFinishFile(String location) {
		finishFileLocations.add(location);
	}
	
	public void addStartFile(String location) {
		startFileLocations.add(location);
	}
	
	public String print() {
		String error =  this.preparePrint(new LapRacePrinter(), new runnerNumberComparator());
		StringBuilder out = new StringBuilder();
		
		for(RacerSorter sorter : groups) {
			out.append(sorter.print());
		}
		if(error.length() > 0)
			out.append("\n\n\nerror: \n");
		return out.toString();
	}
	
	/**
	 * reads all files in the arraylists and populates them into the hashmap and groups arrays.
	 * this function is relatively huge and should be broken up into more logical pieces. alas not at this moment.
	 * @return a trail of errors.
	 */
	private String preparePrint(RacerPrinter rp, Comparator<Racer> rc) {
		StringBuilder error = new StringBuilder();
		
		groups = new ArrayList<RacerSorter>();
		RacerSorter currentGroup = null;
		RacerSorter unnamedGroup;
		RacerSorter unregisteredRacers;
		racerList = new HashMap<Integer, Racer>();
		
		//if(nameFileLocations.size() == 0 || finishFileLocations.size() == 0 || startFileLocations.size() == 0)
		//	return "input data files missing. check your input, a name, start and finish file must all be supplanted for this function to work.";
		
		//assumes there is only one name file at this time. customer has not requested anything else
		try {
			String[] names = this.getLines(nameFileLocations.get(0));
			this.headerInformation = names[0].split("; ");
			
			rp.setHeaderInformation(this.headerInformation);
			unnamedGroup = new RacerSorter("ungrouped people", rc, rp, new Time("01.00.00"));
			unregisteredRacers = new RacerSorter("Icke existerande startnummer", rc, rp, new Time("01.00.00"));
			
			for(int i = 1; i < names.length; i++) {
				String[] lineSplit = names[i].split("; ");
				
				if(lineSplit.length == 1) {
					//new group
					if(currentGroup != null)
						this.groups.add(currentGroup);
					
					currentGroup = new RacerSorter(lineSplit[0], rc, rp, new Time("01.00.00"));
				} else {
					Racer temp = new Racer(lineSplit);
					racerList.put(temp.startNbr, temp);
					
					if(currentGroup != null) {
						currentGroup.addRacer(temp);
					} else {
						unnamedGroup.addRacer(temp);
					}
					
				}
			}
		} catch (IOException e) {
			error.append("FATAL ERROR: error reading the name file: " + nameFileLocations.get(0) + "\n");
			return error.toString();
		}
		
		try {
			for(String file: startFileLocations) {
				String[] lineSplit = this.getLines(file);
				
				for(String line: lineSplit) {
					String[] lineInfo = line.split("; ");
					
					if(lineInfo.length == 2) {
						try {
							Racer relevantRacer = this.racerList.get(Integer.parseInt(lineInfo[0]));
							
							if(relevantRacer == null) {
								//racer does not exist
								Racer r = new Racer(new String[]{lineInfo[0]});
								r.addStartTime(new Time(lineInfo[1]));
								unregisteredRacers.addRacer(r);
								racerList.put(Integer.parseInt(lineInfo[0]), r);
							} else {
								//racer exists
								relevantRacer.addStartTime(new Time(lineInfo[1]));
							}
						} catch(Exception E) {
							error.append("integer parse error in start file: " + file + " line reads:: " + line + "\n");
						}
						
						
					} else {
						error.append("error reading in a start file: " + file + " line reads:: " + line + "\n");
					}
				}
			}
		} catch(IOException e) {
			error.append("error reading a start time file"  + "\n");
		}
		
		try {
			for(String file: finishFileLocations) {
				String[] lineSplit = this.getLines(file);
				
				for(String line: lineSplit) {
					String[] lineInfo = line.split("; ");
					
					if(lineInfo.length == 2) {
						try {
							Racer relevantRacer = this.racerList.get(Integer.parseInt(lineInfo[0]));
							
							if(relevantRacer == null) {
								//racer does not exist
								Racer r = new Racer(new String[]{lineInfo[0]});
								r.addFinishTime(new Time(lineInfo[1]));
								unregisteredRacers.addRacer(r);
								racerList.put(Integer.parseInt(lineInfo[0]), r);
							} else {
								//racer exists
								relevantRacer.addFinishTime(new Time(lineInfo[1]));
							}
						} catch(Exception E) {
							error.append("integer parse error in finish file: " + file + " line reads:: " + line + "\n");
						}
						
						
					} else {
						error.append("error reading in a finish file: " + file + " line reads:: " + line + "\n");
					}
				}
			}
		} catch(IOException e) {
			error.append("error reading a finish time file"  + "\n");
		}
		this.groups.add(currentGroup);
		this.groups.add(unnamedGroup);
		this.groups.add(unregisteredRacers);
		return error.toString();
	}
	
	/**
	 * a very basic filereader which reads a file and returns linewise what it reads as an array of Strings.
	 * 
	 * @param fileLocation the relative / absolute path to the file to be read.
	 * @return an array of strings, the linewise content of the file.
	 * @throws IOException
	 */
	private String[] getLines(String fileLocation) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
		
		ArrayList<String> res = new ArrayList<String>();
		
		while(reader.ready())
			res.add(reader.readLine());
		
		return res.toArray(new String[res.size()]);
	}
}
