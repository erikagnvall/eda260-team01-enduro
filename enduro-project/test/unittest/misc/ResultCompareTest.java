package unittest.misc;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import acceptanceTest.ResultCompare;

public class ResultCompareTest {
	ResultCompare rcFail;
	ResultCompare rcSuccess;
	@Before
	public void setUp() throws FileNotFoundException, Exception{
		rcFail = new ResultCompare(new BufferedInputStream(new FileInputStream(
					"./test/unittest/misc/unit-test-files/ResultCompareTestFiles/facit.txt")), new BufferedInputStream(new FileInputStream(
					"./test/unittest/misc/unit-test-files/ResultCompareTestFiles/result.txt")));
		rcSuccess = new ResultCompare(new BufferedInputStream(new FileInputStream(
		"./test/unittest/misc/unit-test-files/ResultCompareTestFiles/facit2.txt")), new BufferedInputStream(new FileInputStream(
		"./test/unittest/misc/unit-test-files/ResultCompareTestFiles/result2.txt")));

	}
	
	@Test
	public void testFailure() throws IOException{
		assertFalse(rcFail.compareLineWise(true));
	}
	
	@Test
	public void testSuccessWithoutPrintOnlyErrors() throws IOException{
		assertTrue(rcSuccess.compareLineWise(false));
	}
}
