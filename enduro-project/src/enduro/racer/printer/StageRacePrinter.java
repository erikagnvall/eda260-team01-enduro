package enduro.racer.printer;

import java.util.HashMap;

import enduro.racer.Racer;
import enduro.racer.Configuration.ConfigParser;

public class StageRacePrinter implements RacerPrinter {
	private String extraInformation = "";
	private int stages = ConfigParser.getInstance().getIntConf("stages");
	private int extraRunnerInformation;
	@Override
	public String print(Racer r, HashMap<String, String> extraInformation) {
		return null;
	}

	@Override
	public String printTopInformation() {
		StringBuilder out = new StringBuilder();
		out.append(extraInformation);

		out.append("StartNr; Namn; Klubb; MC; Totaltid; #Etapper");

		for (int i = 1; i <= stages; i++) {
			out.append("; Etapp");
			out.append(i);
		}
		for (int i = 1; i <= stages; i++) {
			out.append("; Start");
			out.append(i);	
			out.append("; Mål");
			out.append(i);			
		}
		System.out.println(out.toString());
		return out.toString();
	}

	@Override
	public void setHeaderInformation(String[] extraInformation) {
		extraRunnerInformation = extraInformation.length;
		for(int i = 0; i < extraRunnerInformation; i++) {
			this.extraInformation += extraInformation[i] + "; ";
		}
	}
}
