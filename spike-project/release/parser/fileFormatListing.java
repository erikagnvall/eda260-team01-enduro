package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Parses javadoc for all classes in src (except this one and files in the overlaying folder) and output it to the technical documentation.
 * It is not javadoc - this code only parses it and presents it in a single file.  ;-)
 */
public class fileFormatListing {

	public static void main(String[] args) {
		String projectDir = System.getProperty("user.dir");
		String basicString = "This is a basic description of most files in the source directory.\n" +
								"As well as describes the structure of the map hierarchy.\n\n\n";
		
		try {
			FileWriter writer = new FileWriter("technicalDoc/projektstruktur.txt");
			System.out.println(print(projectDir, projectDir + "/src/", ""));
			writer.append(basicString);
			writer.append(print(projectDir, projectDir + "/src/", ""));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Crawls through a file structure and handles indentation for printing. Files are sent to the printFileJDoc function.
	 */
	public static String print(String startLocation, String fileLocation, String before) {
		if(fileLocation.contains("releaseCode"))
			return "";
		
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
		} else {
			out.append(printFileJDoc(fileLocation, before + "  "));
		}
		
		return out.toString();
	}
	
	/**
	 * Parses most java doc class information from a file.
	 */
	public static String printFileJDoc(String fileLocation, String before) {
		StringBuilder out = new StringBuilder();
		out.append("\n");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
			
			StringBuilder file = new StringBuilder();
			
			while(reader.ready())
				file.append(reader.readLine() + "\n");
			
			String fileCont = file.toString();
			String[] split = fileCont.split("public class");
			
			if(split.length != 2)
				split = fileCont.split("public interface");
			if(split.length != 2)
				split = fileCont.split("public abstract class");
			
			String[] splitLines = split[0].split("\n");

			
			for(String line: splitLines) {
				if(line.contains(" *") && !line.contains("@") && !line.contains("**") && !line.contains("*/")) {
					String newline = line.substring(2);
					out.append(before);
					out.append(newline);
					out.append("\n");
				}
			}
			out.append("\n");
		} catch (Exception e) {}
		
		return out.toString();
	}
}
/**
 * A simple Filenamefilter that disallows all files that starts with . (hidden) _ (specific marker) or contains ~ (temp file)
 */
class FileFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return !(name.startsWith(".") || name.startsWith("_") || name.contains("~"));
	}
}