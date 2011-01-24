package enduro;

public class Main {
	/**
	 * 
	 * @param args The first argument is the startFile, and the second argument is the finishFile. 
	 *             A new file, ResultFile, is created containing the total time for each runner.
	 * @throws Exception In case of an I/O error.
	 */
	public static void main (String [] args) throws Exception {
		Sorter sorter = new Sorter();
		String startFile = "start.txt";
		String goalFile = "finish.txt";
		sorter.readStartFile(startFile);
		sorter.readFinishFile(goalFile);
		sorter.createResultFile("ResultFile.txt");
	}
}
