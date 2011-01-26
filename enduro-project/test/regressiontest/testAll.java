package regressiontest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unittest.FileHandlingTest;
import unittest.PersonDataTest;
import unittest.RegistrationTest;
import unittest.MarathonSorterTest;
import unittest.TimeDataTest;
import unittest.TimeTest;
import unittest.WrongInputTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({FileHandlingTest.class,
					PersonDataTest.class,
					RegistrationTest.class,
					MarathonSorterTest.class,
					TimeDataTest.class,
					TimeTest.class,
					WrongInputTest.class})
public class testAll {

	
}
