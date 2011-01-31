package enduro;

/**
 * Class for sorting the results of a marathon according to the formatting rules that apply.
 * 
 * 
 */
public class MarathonSorter extends Sorter {

	@Override
	protected String titleRow(){
		return "StartNr; Namn; TotalTid; StartTider; Måltider";
	}

	@Override
	protected String finishTime(int i) {
		String finish;
		try {
			finish = finishTime.toString();
			if (racerData.getFinishTime(i).size() > 0) {
				trail.append("; Flera måltider?");
				while (racerData.getFinishTime(i).size() > 0) {
					trail.append(' ');
					trail.append(racerData.getFinishTime(i).poll());
				}
			}
		} catch (NullPointerException e) {
			finish = "Slut?";
		}
		return finish;
	}

	@Override
	protected int[] sortRacers() {
		// TODO Auto-generated method stub
		return null;
	}
}