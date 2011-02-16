package unittest.misc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enduro.racer.configuration.ConfigParser;


public class ConfigParserTest {
	private ConfigParser cp;
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testSetUpWithoutParameter(){
		ConfigParser.delete();
		assertTrue(ConfigParser.getInstance() != null);
	}
	
	@Test
	public void testFileNotFound(){
		ConfigParser c = ConfigParser.getInstance("asd");
		assertTrue(c.fileNotFound());
	}

}
