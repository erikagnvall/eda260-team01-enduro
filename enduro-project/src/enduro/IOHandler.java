package enduro;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Handles I/O.
 * 
 * @author Rick
 * 
 */
public class IOHandler {
	/**
	 * 
	 * @param args
	 *            Syntax is "startFile [finishFile] [-m listOfFinishFiles]". The
	 *            first argument is the startFile, and the second argument is
	 *            the finishFile. A new file, ResultFile, is created containing
	 *            the total time for each runner. Else if the second argument is
	 *            -m the third argument will be a file containing a list of
	 *            finish-files.
	 * @throws Exception
	 *             In case of an I/O error.
	 */
	public static void main(String[] args) throws Exception {
		LapRaceSorter sorter = new LapRaceSorter();

		BufferedReader in = new BufferedReader(new FileReader("list.txt"));
		sorter.readNameFile(in.readLine());
		sorter.readStartFile(in.readLine());
		while (in.ready()) {
			sorter.readFinishFile(in.readLine());
		}
		//sorter.readNameFile("./test/unittest/unit-test-files/fakeName.txt");
		sorter.createResultFile("ResultFile.txt");
		//sorter.createSortedResultsFile("SortedResultFile.txt");
	}
}
