package enduro;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileHandler {
	/**
	 * 
	 * @param args
	 *            The first argument is the startFile, and the second argument
	 *            is the finishFile. A new file, ResultFile, is created
	 *            containing the total time for each runner. Else if the second
	 *            argument is -m the third argument will be a file containing a
	 *            list of finish-files.
	 * @throws Exception
	 *             In case of an I/O error.
	 */
	public static void main(String[] args) throws Exception {
		MarathonSorter sorter = new MarathonSorter();
		String startFile = args[0];
		sorter.readStartFile(startFile);
		if (args[1].equals("-m")) {
			BufferedReader in = new BufferedReader(new FileReader(args[2]));
			while (in.ready()) {
				sorter.readFinishFile(in.readLine());
			}
		} else {
			String goalFile = args[1];
			sorter.readFinishFile(goalFile);
		}
		sorter.readNameFile("fakeName.txt");
		sorter.createResultFile("ResultFile.txt");
	}
}
