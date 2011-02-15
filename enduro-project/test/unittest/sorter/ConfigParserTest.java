package unittest.sorter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import enduro.racer.configuration.*;


public class ConfigParserTest {
	
	@Test
	public void testSuccessfulRead() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/misc/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.getError().length() ==0 && !parser.fileNotFound());
	}
	
	@Test
	public void testFailedRead() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/misc/unit-test-files/config.conf/incorrectConf.conf");
		assertTrue(parser.getError().length() != 0 && !parser.fileNotFound());
	}
	
	@Test public void readString() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/misc/unit-test-files/config.conf/correctConf.conf");
		assertEquals(parser.getStringConf("race"),"lap");
		assertTrue(!parser.fileNotFound());
	}
	
	@Test public void readInteger() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/misc/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.getIntConf("laps")==3  && !parser.fileNotFound());
	}
	
	@Test public void toStringWorks() {
		ConfigParser parser = ConfigParser.getInstance("test/unittest/misc/unit-test-files/config.conf/correctConf.conf");
		assertTrue(parser.toString().length() > 0  && !parser.fileNotFound());
	}
	
}
