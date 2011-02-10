package regressiontest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unittest.GUI.RegistrationGUITest;
import unittest.dataStructure.RacerDataTest;
import unittest.misc.*;
import unittest.sorter.ClassHandlingTest;
import unittest.sorter.IllegalStarNbrtest;
import unittest.sorter.LapRaceSorterTest;
import unittest.sorter.MarathonSorterTest;
import unittest.sorter.WrongInputTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClassHandlingTest.class,
					FileHandlingTest.class,
					IllegalStarNbrtest.class,
					LapRaceSorterTest.class,
					MarathonSorterTest.class,
					RacerDataTest.class,
					RegistrationTest.class,
					TimeTest.class,
					WrongInputTest.class, RegistrationGUITest.class})
public class testAll {

	
}
