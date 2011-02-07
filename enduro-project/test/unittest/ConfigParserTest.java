package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import InputHandling.ConfigParser;

public class ConfigParserTest {
	
	@Test
	public void testSuccessfulRead() {
		ConfigParser parser = new ConfigParser("test/unittest/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.getError().length() ==0);
	}
	
	@Test
	public void testFailedRead() {
		ConfigParser parser = new ConfigParser("test/unittest/unit-test-files/config.conf/incorrectConf.conf");
		assertTrue(parser.getError().length() !=0);
	}
	
	@Test public void readString() {
		ConfigParser parser = new ConfigParser("test/unittest/unit-test-files/config.conf/correctConf.conf");
		assertEquals(parser.getStringConf("race"),"lap");
	}
	
	@Test public void readInteger() {
		ConfigParser parser = new ConfigParser("test/unittest/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.getIntConf("laps")==3);
	}
	
	@Test public void toStringWorks() {
		ConfigParser parser = new ConfigParser("test/unittest/unit-test-files/config.conf/correctConf.conf");
		System.out.println(parser);
		assertTrue(parser.toString().length() >0);
	}
	
}
