package unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enduro.racedata.PersonData;

public class PersonDataTest {
	private PersonData t1;
  @Before public void setUp(){
	  t1 = new PersonData();
	  t1.addName(1, "test1");
	  t1.addName(2, "test2s");
  }
  @Test public void TestgetName(){
	  assertEquals("test1",t1.getName(1));
  }
}
