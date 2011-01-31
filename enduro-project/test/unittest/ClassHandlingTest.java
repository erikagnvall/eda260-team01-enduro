package unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enduro.MarathonSorter;


public class ClassHandlingTest {
	private MarathonSorter sorter;
	
	@Before
	public void setUp() {
		sorter = new MarathonSorter();
	}
	@Test
	public void testClassRegistration(){
		try{
			sorter.readNameFile("./test/unittest/unit-test-files/fakeClassName.txt");
		}catch(Exception e){
			e.printStackTrace();
		}
		assertEquals(sorter.getClasses().get(0).getName(), "Seniorer");
		assertEquals(sorter.getClasses().get(1).getName(), "Juniorer");
	}

}
