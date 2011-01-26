package regressiontest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unittest.FileHandlingTest;
import unittest.RegistrationTest;
import unittest.MarathonSorterTest;
import unittest.RacerDataTest;
import unittest.TimeTest;
import unittest.WrongInputTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({FileHandlingTest.class,
					RegistrationTest.class,
					MarathonSorterTest.class,
					RacerDataTest.class,
					TimeTest.class,
					WrongInputTest.class})
public class testAll {

	
}
