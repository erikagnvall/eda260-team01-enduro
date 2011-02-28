package enduro.racer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

	private String location;
	private static Log logger = null;
	private static boolean isWritten;
	
	private Log(String location) {
		this.location = location;
	}
	
	public static void log(String data) {
		isWritten = false;
		if(logger == null)
			logger = new Log("log.log");
		logger.writeToLog(data);
	}
	
	public static boolean checkLog(){
		return isWritten;
	}
	
	public static void setLogLocation(String location) {
		logger = new Log(location);
	}
	
	private void removeLog() {
		File f = new File(location);
		f.delete();
	}
	
	public static void reset() {
		if(logger != null)
			logger.removeLog();
	}
	
	public void writeToLog(String data) {
		try {
			isWritten = true;
			FileWriter writer = new FileWriter(location, true);
			writer.append(data);
			writer.append("\n");
			writer.close();
		} catch (IOException e) {}
	}
	
}
