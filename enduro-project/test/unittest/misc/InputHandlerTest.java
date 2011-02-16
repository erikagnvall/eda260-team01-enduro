package unittest.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import enduro.racer.InputHandler;
import enduro.racer.configuration.ConfigParser;

public class InputHandlerTest {

	/**
	 * creating a simple InputHandler and checks if it accepts bogus files. This
	 * shouldn't give any errors as the checks are later.
	 * 
	 * Added for consistency (if anything is changed in the future)
	 */
	@Test
	public void testBasicInput() {
		try {
			InputHandler handler = new InputHandler();
			handler.addFinishFile("/test1", 1);
			handler.addStartFile("/test2", 1);
			handler.addNameFile("/test3", 1);
		} catch (Exception E) {
			fail();
		}
	}

	/**
	 * test in accordance with acceptance test 17_unit (default input file for
	 * previous work)
	 */
	@Test
	public void testRealInput() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile("acceptanceTest/result/17_unit/namnfil.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile("acceptanceTest/result/17_unit/starttider.txt", 1);

		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[3]);
		assertEquals("JUNIOR", lines[4]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[5]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[6]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[7]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[8]);
		assertEquals("Icke existerande startnummer", lines[9]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[10]);
		assertEquals(
				"210; ; ; ; 0; --:--:--; ; ; ; --:--:--; 13.16.07; ; ; Start?",
				lines[11]);
	}

	@Test
	public void testNoSortingConfig() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorting", "");
		InputHandler handler = new InputHandler();
		handler.addNameFile("acceptanceTest/result/17_unit/namnfil.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile("acceptanceTest/result/17_unit/starttider.txt", 1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[3]);
		assertEquals("JUNIOR", lines[4]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[5]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[6]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[7]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[8]);
		assertEquals("Icke existerande startnummer", lines[9]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[10]);
		assertEquals(
				"210; ; ; ; 0; --:--:--; ; ; ; --:--:--; 13.16.07; ; ; Start?",
				lines[11]);
	}

	@Test
	public void testWrongPrinterType() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("race", "asdasd");
		InputHandler handler = new InputHandler();
		handler.addNameFile("acceptanceTest/result/17_unit/namnfil.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile("acceptanceTest/result/17_unit/starttider.txt", 1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[3]);
		assertEquals("JUNIOR", lines[4]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[5]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[6]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[7]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[8]);
		assertEquals("Icke existerande startnummer", lines[9]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[10]);
		assertEquals(
				"210; ; ; ; 0; --:--:--; ; ; ; --:--:--; 13.16.07; ; ; Start?",
				lines[11]);
	}

	@Test
	public void testClasslessRacer() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile("acceptanceTest/result/17_unit/starttider.txt", 1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals("JUNIOR", lines[3]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[4]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[5]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[6]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[7]);
		assertEquals("Ogrupperade förare", lines[8]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[9]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[10]);
		assertEquals("Icke existerande startnummer", lines[11]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[12]);
		assertEquals(
				"210; ; ; ; 0; --:--:--; ; ; ; --:--:--; 13.16.07; ; ; Start?",
				lines[13]);
	}

	@Test
	public void testRacerMissingName() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttider.txt",
				1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals("JUNIOR", lines[3]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[4]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[5]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[6]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[7]);
		assertEquals("Ogrupperade förare", lines[8]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[9]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[10]);
		assertEquals("Icke existerande startnummer", lines[11]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[12]);
		assertEquals(
				"210; ; ; ; 1; 00.16.07; 00.16.07; ; ; 13.00.00; 13.16.07; ; 13.16.07",
				lines[13]);
	}

	@Test
	public void testIntegerParsingErrorInStartFile() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttiderFail.txt",
				1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 0; --:--:--; ; ; ; --:--:--; 12.30.00; 13.00.00; ; Start?",
				lines[2]);
		assertEquals("JUNIOR", lines[3]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[4]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[5]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[6]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[7]);
		assertEquals("Ogrupperade förare", lines[8]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[9]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[10]);
		assertEquals("Icke existerande startnummer", lines[11]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[12]);
		assertEquals(
				"210; ; ; ; 1; 00.16.07; 00.16.07; ; ; 13.00.00; 13.16.07; ; 13.16.07",
				lines[13]);
		assertEquals(
				"error: integer parse error in start file: ./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttiderFail.txt line reads:: 1s; 12.00.00",
				lines[17]);
	}

	@Test
	public void testErrorInStartFile() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider1.txt", 1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttiderFail2.txt",
				1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals("JUNIOR", lines[3]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[4]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[5]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[6]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[7]);
		assertEquals("Ogrupperade förare", lines[8]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[9]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[10]);
		assertEquals("Icke existerande startnummer", lines[11]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[12]);
		assertEquals(
				"210; ; ; ; 1; 00.16.07; 00.16.07; ; ; 13.00.00; 13.16.07; ; 13.16.07",
				lines[13]);
		assertEquals(
				"error: error reading in a start file: ./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttiderFail2.txt line reads:: asd",
				lines[17]);
	}

	@Test
	public void testIntegerParsingErrorInFinishFile() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/maltiderFail.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttider.txt",
				1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 2; 01.23.34; 00.30.00; 00.53.34; ; 12.00.00; 12.30.00; 13.23.34; 13.23.34",
				lines[2]);
		assertEquals("JUNIOR", lines[3]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[4]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[5]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[6]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[7]);
		assertEquals("Ogrupperade förare", lines[8]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[9]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[10]);
		assertEquals("Icke existerande startnummer", lines[11]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[12]);
		assertEquals(
				"210; ; ; ; 1; 00.16.07; 00.16.07; ; ; 13.00.00; 13.16.07; ; 13.16.07",
				lines[13]);
		assertEquals(
				"error: integer parse error in finish file: ./test/unittest/misc/unit-test-files/InputHandlerTestFiles/maltiderFail.txt line reads:: 1s; 13.00.00",
				lines[17]);
	}

	@Test
	public void testErrorInFinishFile() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/maltiderFail2.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttider.txt",
				1);
		String[] lines = handler.print().split("\n");

		assertEquals("SENIOR", lines[0]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[1]);
		assertEquals(
				"1; Anders Asson; FMCK Astad; ATM; 3; 01.23.34; 00.30.00; 00.30.00; 00.23.34; 12.00.00; 12.30.00; 13.00.00; 13.23.34",
				lines[2]);
		assertEquals("JUNIOR", lines[3]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[4]);
		assertEquals(
				"101; Chris Csson; Cstad MCK; CTM; 3; 01.05.06; 00.22.00; 00.20.00; 00.23.06; 12.00.00; 12.22.00; 12.42.00; 13.05.06",
				lines[5]);
		assertEquals(
				"102; David Dsson; Dstad MCK; DTM; 3; 01.12.07; 00.23.00; 00.20.00; 00.29.07; 12.00.00; 12.23.00; 12.43.00; 13.12.07",
				lines[6]);
		assertEquals(
				"103; Erik Esson; Estad MCK; ETM; 3; 01.16.07; 00.24.00; 00.20.00; 00.32.07; 12.00.00; 12.24.00; 12.44.00; 13.16.07; Flera starttider? 12.15.00",
				lines[7]);
		assertEquals("Ogrupperade förare", lines[8]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[9]);
		assertEquals(
				"2; Bengt Bsson; FMCK Bstad; BTM; 3; 01.15.16; 00.14.00; 00.27.00; 00.34.16; 12.00.00; 12.14.00; 12.41.00; 13.15.16; Omöjlig varvtid?",
				lines[10]);
		assertEquals("Icke existerande startnummer", lines[11]);
		assertEquals(
				"StartNr; Namn; Klubb; MC; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål",
				lines[12]);
		assertEquals(
				"210; ; ; ; 1; 00.16.07; 00.16.07; ; ; 13.00.00; 13.16.07; ; 13.16.07",
				lines[13]);
		assertEquals(
				"error: error reading in a finish file: ./test/unittest/misc/unit-test-files/InputHandlerTestFiles/maltiderFail2.txt line reads:: asd",
				lines[17]);
	}

	@Test
	public void testInvalidNameFile() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile("asd", 1);
		handler.addFinishFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/maltiderFail2.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttider.txt",
				1);
		String[] lines = handler.print().split("\n");
		assertEquals("error: FATAL ERROR: error reading the name file: null",
				lines[3]);
	}

	@Test
	public void testInvalidStart() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile(
				"acceptanceTest/result/17_unit/maltider1.txt",
				1);
		handler.addFinishFile("acceptanceTest/result/17_unit/maltider2.txt", 1);
		handler.addStartFile("sad", 1);
		String[] lines = handler.print().split("\n");
		assertEquals("error: error reading a start time file",
				lines[17]);
	}

	@Test
	public void testInvalidFinishFile() {
		ConfigParser.getInstance("config.conf");
		ConfigParser.getInstance().overLoadValue("sorted", "false");
		InputHandler handler = new InputHandler();
		handler.addNameFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/namnfil.txt",
				1);
		handler.addFinishFile(
				"asd",
				1);
		handler.addStartFile(
				"./test/unittest/misc/unit-test-files/InputHandlerTestFiles/starttider.txt",
				1);
		String[] lines = handler.print().split("\n");
		assertEquals("error: error reading a finish time file", lines[17]);
	}
}
