package acceptanceTest;

import java.io.File;
import java.io.FileReader;

/**
 * naming convention: 
 *  - facit can be named in any way.
 *  - result files must be named after corresponding facit - facit.result
 * 
 *  results and facit must be in explicitly different locations
 *   
 * * @author alexander, mohamed
 *
 */
public class CompareAcceptanceTests {

	String[] facitFiles, resultFiles;
	String facitFolder, resultFolder;
	
	public CompareAcceptanceTests(String facitFolder, String resultFolder) {
		File facit = new File(facitFolder);
		File result = new File(resultFolder);
		
		facitFiles = facit.list();
		resultFiles = result.list();
		
		this.facitFolder = facitFolder;
		this.resultFolder = resultFolder;
		
		if(!this.facitFolder.endsWith("/"))
			this.facitFolder +="/";
		if(!this.resultFolder.endsWith("/"))
			this.resultFolder +="/";
	}
	
	public void testAllFacit() {
		for(String test: facitFiles) {
			//ResultCompare c = new ResultCompare(new FileReader(facitFolder + test));
		}
	}
	
}
