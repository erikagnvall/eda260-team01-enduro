package unittest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import regressiontest.ListTest;
import regressiontest.PVGRunner;

import enduro.MarathonSorter;
import enduro.racedata.RaceClass;

@ListTest(sorter=ListTest.Sorter.marathon)
@RunWith(PVGRunner.class)
public class IllegalStarNbrtest {

	@Test
	public void testNoticeIllegalStart() {

		assertTrue(PVGRunner.testSorter.getClasses().contains(new RaceClass("Icke existerande startnummer")));

	}
	
	@Test
	public void testRightAmountIllegalStart() {

		assertEquals(1, PVGRunner.testSorter.getClasses().get(PVGRunner.testSorter.getClasses().indexOf(new RaceClass("Icke existerande startnummer"))).size());

	}
	
}
