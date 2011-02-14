package releaseCode;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class fileFormatListing {

	public static void main(String[] args) {
		String projectDir = System.getProperty("user.dir");
		
		try {
			FileWriter writer = new FileWriter("technicalDoc/projektstruktur.txt");
			System.out.println(print(projectDir, projectDir + "/src/", ""));
			writer.append(print(projectDir, projectDir + "/src/", ""));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String print(String startLocation, String fileLocation, String before) {
		File f = new File(fileLocation);
		
		StringBuilder out = new StringBuilder();
		
		String output = fileLocation.substring(startLocation.length());
		out.append(before + output + "\n");
		if(f.isDirectory()) {
			if(!fileLocation.endsWith("/"))
				fileLocation += "/";
			String[] files = f.list(new FileFilter());
			for(String file: files) {
				out.append(print(startLocation, fileLocation + file, before + "\t"));
			}
		}
		
		return out.toString();
	}
	
}

class FileFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return !(name.startsWith(".") || name.startsWith("_") || name.contains("~"));
	}
}