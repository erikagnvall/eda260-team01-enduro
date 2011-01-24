package acceptanceTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * a basic comparative class for streams
 * @author alexader, mohamed
 *
 */
public class ResultCompare {

	private InputStream facit, result;
	
	public ResultCompare(InputStream facit, InputStream result){
		this.facit = facit;
		this.result = result;
	}
	
	/**
	 * testing bitwise compare between two InputStreams
	 * @return true if the two streams from the beginning are equal bitwise
	 * @throws IOException
	 */
	public boolean equals() throws IOException {
		facit.reset();
		result.reset();
		
		if(facit.available()!=result.available())
			return false;
		
		while(facit.available() > 0) {
			if(facit.read() != result.read())
				return false;
		}
		return true;
	}
	/**
	 * Compares two streams line by line. If they are not equal they are both printed and marked "ERROR".
	 * @param printOnlyErrors if true only the errors are printed, if false the correct lines are also printed.
	 * @throws IOException
	 */
	public void compareLineWise(boolean printOnlyErrors) throws IOException {
		facit.reset();
		result.reset();
		BufferedReader facitReader = new BufferedReader(new InputStreamReader(facit));
		BufferedReader resultReader = new BufferedReader(new InputStreamReader(result));
		int line = 0;
		
		while(facitReader.ready()) {
			line++;
			String facitString = facitReader.readLine();
			String resultString = resultReader.readLine();
			
			if(facitString.compareTo(resultString)!=0) {
				System.out.println("ERROR line " + line + "\n");
				System.out.println("\t facit:" + facitString + "\n");
				System.out.println("\t result:" + resultString + "\n");
			} else if(!printOnlyErrors)
				System.out.println("line " + line + ":" + facitString + "\n");
		}
	}
	
}
