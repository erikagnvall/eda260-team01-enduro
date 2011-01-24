package acceptanceTest;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class JUnitAcceptanceTest {
	
	private String file;
	private String facitFolder = "acceptanceTest/facit/";
	private String resultFolder = "acceptanceTest/result/";
	
	public JUnitAcceptanceTest(String num) {
		this.file = num;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		File facit = new File("acceptanceTest/facit");
		String[] facitFiles = facit.list(new FileFilter());
		Object[][] data = new Object[facitFiles.length][1];
		for(int i = 0; i < facitFiles.length; i++) {
			data[i][0] = facitFiles[i];
		}
		
		return Arrays.asList(data);

	}
	
	@Test public void testAcceptance() {
		System.out.println(file);
		ResultCompare c;
		try {
			c = new ResultCompare(new BufferedInputStream(new FileInputStream(facitFolder + file)), new BufferedInputStream(new FileInputStream(resultFolder + file + ".result")));
			assertTrue(c.compareLineWise(true));
		} catch(FileNotFoundException e) {
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
        return !(name.startsWith(".") ||  name.startsWith("_"));
    }
}
