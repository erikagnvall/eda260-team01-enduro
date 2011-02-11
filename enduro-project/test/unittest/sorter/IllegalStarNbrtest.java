package unittest.sorter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import regressiontest.ListTest;
import regressiontest.PVGRunner;

import enduro.racedata.RaceClass;

@ListTest(sorter=ListTest.Sorter.marathon)
@RunWith(PVGRunner.class)
public class IllegalStarNbrtest {

	@Test
	public void testNoticeIllegalStart() {

		assertTrue(PVGRunner.lapSorter.getClasses().contains(new RaceClass("Icke existerande startnummer")));

	}
	
	@Test
	public void testRightAmountIllegalStart() {

		assertEquals(1, PVGRunner.lapSorter.getClasses().get(PVGRunner.lapSorter.getClasses().indexOf(new RaceClass("Icke existerande startnummer"))).size());

	}
	
}
