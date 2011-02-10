package unittest.sorter;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Test;
import org.junit.runner.RunWith;

import regressiontest.ListTest;
import regressiontest.PVGRunner;

@ListTest(sorter=ListTest.Sorter.marathon)
@RunWith(PVGRunner.class)
public class ClassHandlingTest {

	@Test
	public void testClassRegistration() {
		assertEquals(PVGRunner.lapSorter.getClasses().get(0).getName(), "SENIOR");
		assertEquals(PVGRunner.lapSorter.getClasses().get(1).getName(), "JUNIOR");
	}

	@Test
	public void testClassRegContestant() {
		Iterator itr = PVGRunner.lapSorter.getClasses().get(0).getList().iterator();
		assertEquals(itr.next(), new Integer(1));
		assertEquals(itr.next(), new Integer(2));
		itr = PVGRunner.lapSorter.getClasses().get(1).getList().iterator();
		assertEquals(itr.next(), new Integer(101));
	}
	
}