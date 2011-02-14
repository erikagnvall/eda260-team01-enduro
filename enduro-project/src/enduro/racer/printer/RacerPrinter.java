package enduro.racer.printer;

import java.util.HashMap;

import enduro.racer.Racer;

/**
 * a printer interface to print all relevant information for a race,
 * both line by line for each racer as well as printing the overlaying "header" information.
 */
public interface RacerPrinter {
	
	public void setHeaderInformation(String[] extraInformation);
	
	/**
	 * prints the racer's text line with all information and errors.
	 * 
	 * @param r the runner
	 * @param extraInformation a non-null hashmap with value pairs that may/may not exist, specific for each printer, but sent for all.
	 * @return a line for the runner without linebreak
	 */
	public String print(Racer r, HashMap<String, String> extraInformation);
	
	/**
	 * prints the uppermost information bar.
	 * @return a line with explanations for each information element for the runners.
	 */
	public String printTopInformation();
}
