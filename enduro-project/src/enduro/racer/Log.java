package enduro.racer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

	private String location;
	private static Log logger = null;
	
	private Log(String location) {
		this.location = location;
	}
	
	public static void log(String data) {
		if(logger == null)
			logger = new Log("log.log");
		logger.writeToLog(data);
	}
	
	public void setLogLocation(String location) {
		logger = new Log(location);
	}
	
	public void reset() {
		File f = new File(location);
		f.delete();
	}
	
	public void writeToLog(String data) {
		try {
			FileWriter writer = new FileWriter(location, true);
			writer.append(data);
			writer.append("\n");
			writer.close();
		} catch (IOException e) {}
	}
	
}
