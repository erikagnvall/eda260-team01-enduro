package regressiontest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unittest.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClassHandlingTest.class,
					FileHandlingTest.class,
					IllegalStarNbrtest.class,
					LapRaceSorterTest.class,
					LapseSorterTest.class,
					MarathonSorterTest.class,
					RacerDataTest.class,
					RegistrationTest.class,
					TimeTest.class,
					WrongInputTest.class})
public class testAll {

	
}
