package unittest.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import enduro.InputHandler.ConfigParser;


public class ConfigParserTest {
	
	@Test
	public void testSuccessfulRead() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.getError().length() ==0);
	}
	
	@Test
	public void testFailedRead() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/unit-test-files/config.conf/incorrectConf.conf");
		assertTrue(parser.getError().length() !=0);
	}
	
	@Test public void readString() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/unit-test-files/config.conf/correctConf.conf");
		assertEquals(parser.getStringConf("race"),"lap");
	}
	
	@Test public void readInteger() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.getIntConf("laps")==3);
	}
	
	@Test public void toStringWorks() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/unit-test-files/config.conf/correctConf.conf");
		System.out.println(parser);
		assertTrue(parser.toString().length() >0);
	}
	
}
