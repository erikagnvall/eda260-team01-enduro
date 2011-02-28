package unittest.misc;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
		assertTrue(lines.length == 12);
			
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

		for(String line: lines) {
			System.out.println("line: " + line);
		}
	}
}
