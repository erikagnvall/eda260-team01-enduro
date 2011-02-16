package acceptanceTest;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import enduro.MainClass;
import enduro.racer.configuration.ConfigParser;

/**
 * An automatic JUnit testclass. It uses an alternative runner which allows for
 * parameterized tests. It searches for a specific pattern (result [identifying
 * token] .txt in the facit folder and loads all key files in
 * result/[identifying token]/* (non recursive).
 * 
 * currently only a result [identifying token] .txt.result is created in result
 * this is then compared with the relevant acceptance test.
 * 
 * @author alexander, mohamed m.fl.
 * 
 */
@RunWith(value = LabelledParameterized.class)
public class JUnitAcceptanceTest {

	private String file;
	private String testId;
	private static String facitFolder = "acceptanceTest/facit/";
	private static String resultFolder = "acceptanceTest/result/";
	private String testPath;

	private boolean fileReadFail = false;
	private boolean fileWriteFail = false;
	private boolean fileLogWriteFail = false;

	public JUnitAcceptanceTest(String test) {
		this.file = test;
		testId = test.substring(9, test.length());
		testId = testId.substring(0, testId.length() - 4);
		// System.out.println("id: " + testId);
		try {
			System.setOut(new PrintStream(new FileOutputStream(resultFolder
					+ test + ".log"), true));
		} catch (FileNotFoundException e1) {
			fileLogWriteFail = true;
		}
		testPath = resultFolder + testId + "/";

		FileListGenerator gen = new FileListGenerator(new File(testPath));
			
		String configLoc = "config.conf";
		String[] configFileLoc = gen.getFilesThatContains("config");
		if(configFileLoc.length == 1)
			configLoc = testPath + configFileLoc[0];
		
		System.out.println("conf: " + configLoc);
		if(testId.compareTo("29")==0) {
			MainClass.main(new String[]{"-config", configLoc, "-output", "acceptanceTest/result/" + test + ".tmpres", "-debug", "true", "-html", "acceptanceTest/result/" + test + ".result"});
		} else {
			MainClass.main(new String[]{"-config", configLoc, "-output", "acceptanceTest/result/" + test + ".result", "-debug", "true"});

		}
		
	}

	@Parameters
	public static Collection<Object[]> data() {
		File facit = new File("acceptanceTest/facit");
		String[] facitFiles = facit.list(new FileFilter());

		LinkedList<Object[]> tmp = new LinkedList<Object[]>();

		for (String test : facitFiles) {
			if (test.startsWith("resultat_"))
				if(!test.contains("~"))
					tmp.add(new Object[] { test });
		}

		// tmp.add(new Object[]{"resultat_6.txt"});
		return tmp;

		// return Arrays.asList(data);

	}

	/**
	 * fails if there were some errors with reading specified material
	 */
	@Test
	public void testIfReadTestFilesCorrectly() {
		assertTrue(!this.fileReadFail);
	}

	/**
	 * fails if there were some errors with generating any of the result files.
	 */
	@Test
	public void testIfWriteToResultFileSuccess() {
		assertTrue(!this.fileWriteFail);
	}

	/**
	 * fails if there were some errors with generating the log file
	 */
	@Test
	public void testIfWriteToLogSuccess() {
		assertTrue(!this.fileLogWriteFail);
	}

	/**
	 * acceptance test. compares generated output with results.
	 */
	@Test
	public void testAcceptance() {
		System.out.println(file);
		ResultCompare c;
		try {
			c = new ResultCompare(new BufferedInputStream(new FileInputStream(
					facitFolder + file)), new BufferedInputStream(
					new FileInputStream(resultFolder + file + ".result")));
			assertTrue(c.compareLineWise(true));
		} catch (FileNotFoundException e) {
			System.out.println("result file not found");
			assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}
}

class FileFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return !(name.startsWith(".") || name.startsWith("_") || name.contains("~"));
	}
}
