package acceptanceTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

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

	String[] facitFiles;
	String facitFolder, resultFolder;
	
	public CompareAcceptanceTests(String facitFolder, String resultFolder) {
		File facit = new File(facitFolder);
		
		facitFiles = facit.list(new FileFilter());
		
		this.facitFolder = facitFolder;
		this.resultFolder = resultFolder;
		
		if(!this.facitFolder.endsWith("/"))
			this.facitFolder +="/";
		if(!this.resultFolder.endsWith("/"))
			this.resultFolder +="/";
	}
	
	public void testAllFacit() {
		for(String test: facitFiles) {
			System.out.println("\n-----------------------");
			System.out.println("NEW ACCEPTANCE TEST FILE");
			System.out.println("file: " + test);
			System.out.println("-----------------------");
			try {
				ResultCompare c = new ResultCompare(new BufferedInputStream(new FileInputStream(facitFolder + test)), new BufferedInputStream(new FileInputStream(resultFolder + test + ".result")));
				c.compareLineWise(true);
			} catch(FileNotFoundException e) {
				System.out.println("result file not found");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		CompareAcceptanceTests tests = new CompareAcceptanceTests("acceptanceTest/facit", "acceptanceTest/result");
		tests.testAllFacit();
	}
	
}

class FileFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return !(name.startsWith(".") ||  name.startsWith("_"));
    }
}