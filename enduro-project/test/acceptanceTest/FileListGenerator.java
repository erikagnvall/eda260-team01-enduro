package acceptanceTest;

import java.io.File;
import java.io.FilenameFilter;
/**
 * A very basic non recursive file selector class. 
 * 
 * Uses a filename filer to select files containing a specific sequence
 * and returns them as a vectorized string. Nothing fancy, move along ;-)
 * @author alexander
 *
 */
public class FileListGenerator {

	File baseDir;
	
	public FileListGenerator(File baseDir) {
		this.baseDir = baseDir;
	}
	
	public String[] getFilesThatContains(String key) {
		DynFileFilter dyn = new DynFileFilter(key);
		return baseDir.list(dyn);
	}
	
}

class DynFileFilter implements FilenameFilter {
	
	String contains;
	
	public DynFileFilter(String cont) {
		contains = cont;
	}
    public boolean accept(File dir, String name) {
    	return name.contains(contains);
    }
}
