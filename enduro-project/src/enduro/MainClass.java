package enduro;

import java.io.FileWriter;

import enduro.racer.InputHandler;
import enduro.racer.Log;
import enduro.racer.configuration.ConfigParser;

/**
 * this is the main class. it parses a command line of the type -COMMAND KEY
 * where COMMAND may be config, list and output.
 * The config file is which config file which should be read.
 * The input file is the file which the program reads from, which previous consistency
 * (first file: name file, second file: start file and the following lines finish time files)
 * The output is where the output should be written
 */
public class MainClass {
	
	public static boolean debug = false;
	
	/**
	 * Reads the optional information "stage" from a string.
	 * The form is A:B where the int of A is returned.
	 * If this is not available, 1 is returned (for backwards compatibility)
	 * @param str a string
	 * @return the "stage" number.
	 */
	private static int getStage(String str) {
		System.out.print("getting stage info:" + str + " num splits: ");
		String[] temp = str.split(":");
		
		System.out.println(temp.length);
		
		if(temp.length == 1)
			return 1;
		else
			return Integer.parseInt(temp[0]);
	}
	
	/**
	 * obtains the file location from a text on the form A:B or A
	 * and returns the correct file. 
	 * @param preliminaryLocation a string
	 * @return the location of a file
	 */
	private static String getLocation(String preliminaryLocation) {
		String[] temp = preliminaryLocation.split(":");
		
		if(temp.length != 2)
			return preliminaryLocation;
		return temp[1];
	}
	
	public static void main(String[] args) {
		for(String arg: args)
			System.out.println(arg);
		String output = "";
		String debugoutput = "";
		String html = "";
		for(int i = 0; i < args.length; i++) {
			if(i+1 < args.length) {
				if(args[i].compareTo("-config")==0) {
					ConfigParser.getInstance(args[i+1]);
				} else if(args[i].compareTo("-output")==0) {
					output = args[i+1];
				} else if(args[i].compareTo("-debugoutput")==0) {
					debugoutput = args[i+1];
				} else if(args[i].compareTo("-html")==0) {
					html = args[i+1];
				} else if(args[i].equals("-debug")) {
					if(args[i+1].equals("true")) {
						debug = true;
					}
				} else if(args[i].equals("-log")) {
					enduro.racer.Log.setLogLocation(args[i+1]);
				}
			}
		}
		Log.reset();
		
		if(output.length() == 0)
			output = ConfigParser.getInstance().getStringConf("output");
		if(html.length() == 0)
			if(ConfigParser.getInstance().getStringConf("html") != null)
				html = ConfigParser.getInstance().getStringConf("html");
			else
				html = "result.html";
		if(debugoutput.length() == 0)
			debugoutput = ConfigParser.getInstance().getStringConf("debugoutput");
		
		InputHandler handler = new InputHandler();
		
		
		
		if(ConfigParser.getInstance().getNameFileList().length + ConfigParser.getInstance().getFinishFileList().length + ConfigParser.getInstance().getStartFileList().length >= 3) {
			try {
				
				for(String unparsedInfo:ConfigParser.getInstance().getNameFileList())
					handler.addNameFile(getLocation(unparsedInfo), getStage(unparsedInfo));
				for(String unparsedInfo:ConfigParser.getInstance().getFinishFileList())
					handler.addFinishFile(getLocation(unparsedInfo), getStage(unparsedInfo));
				for(String unparsedInfo:ConfigParser.getInstance().getStartFileList())
					handler.addStartFile(getLocation(unparsedInfo), getStage(unparsedInfo));
				System.out.println("\n\n printing output to: " + output);
				try {
					FileWriter writer = new FileWriter(output);
					writer.append(handler.print());
					writer.close();
				} catch(Exception E) {
					System.out.println("large error: " + E.toString());
				}
				
				
				System.out.println("\n\n printing debug output to: " + debugoutput);
				FileWriter debugWriter = new FileWriter(debugoutput);
				debugWriter.append(handler.debugPrint());
				debugWriter.close();
				
				TxtToHtml htmlWriter = new TxtToHtml();
				htmlWriter.makeHtmlFile(output, html);
				if(!debug && !Log.checkLog())
					EnduroDialogs.showConfirmDialog();
				else if(!debug)
					EnduroDialogs.showFailDialog();
			} catch(Exception E) {
				System.out.println("Exception::" + E.toString());
			}
		} else {
			System.out.println("lacking enough files to parse input, needs atleast one name, start and finish file");
		}
		
		
	}
}
