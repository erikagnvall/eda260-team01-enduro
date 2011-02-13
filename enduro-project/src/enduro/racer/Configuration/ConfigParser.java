package enduro.racer.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * a basic config parser. It reads by default from config.conf in the CLASSPATH
 * (but can be configured otherwise)
 * 
 * It reads value pairs of the type  A:B where B becomes the value and A the key.
 * default values for race, sorting, input, laps and minimumTime (case sensative) exists,
 * but older key value pairs gets deleted if new appears.
 * Comments ( // )and empty lines are ignored.
 * 
 *  A parse error results in continued read. Errors can be polled by getError().
 */
public class ConfigParser {
	private HashMap<String, String> tmp = new HashMap<String, String>();
	private StringBuilder errors = new StringBuilder();

	private static ConfigParser parser = null;
	
	private ConfigParser(){
		this.handleInput("config.conf");
	}
	
	private ConfigParser(String fileLocation){
		this.handleInput(fileLocation);
	}
	
	/**
	 * if the configparser singleton does not exists generates another one, otherwise return previously defined singleton.
	 * @return the singleton configparser
	 */
	public static ConfigParser getInstance() {
		if(parser == null)
			parser = new ConfigParser();
		return parser;
	}

	/**
	 * unconditionally creates a new singleton instance
	 * 
	 * @param fileLocation where the config file is
	 * @return
	 */
	public static ConfigParser getInstance(String fileLocation) {
		parser = new ConfigParser(fileLocation);
		return parser;
	}
	
	private void handleInput(String fileLocation) {
		tmp.put("race", "marathon");
		tmp.put("sorting", "name");
		tmp.put("input", "file");
		tmp.put("laps", "3");
		tmp.put("minimumTime", "900");
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileLocation));
			int line = 1;
			while(in.ready()){
				String temp = in.readLine();
				if(!temp.startsWith("//") && temp.length() > 0){
					try {
						String[] res = temp.split(":");
						tmp.remove(res[0]);
						tmp.put(res[0], res[1]);
					} catch(Exception E) {
						errors.append("line " + line + ":: parse error::" + temp + "\n");
					}
				}
				line++;
			}
		} catch (Exception e) {}
	}
	public int getIntConf(String key) {
		return Integer.parseInt(tmp.get(key));
	}
	
	public String getStringConf(String key) {
		return tmp.get(key);
	}
	
	public String toString() {
		return tmp.toString();
	}
	
	/**
	 * a string containing all errors generated while parsing.
	 * @return
	 */
	public String getError() {
		return this.errors.toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(new ConfigParser());
	}
}
