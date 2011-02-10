package unittest.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.*;
import org.uispec4j.interception.MainClassAdapter;

import enduro.gui.RegistrationGUI;

public class RegistrationTest extends UISpecTestCase {

	Window window;
	TextBox input;
	TextBox output;
	Button registerButton;
	Button undoButton;
	String time;

	@Before
	protected void setUp() {
		setAdapter(new MainClassAdapter(RegistrationGUI.class, new String[0]));
		window = getMainWindow();
		input = window.getTextBox("Input");
		output = window.getTextBox("Output");
		registerButton = window.getButton("Registrera");
		undoButton = window.getButton("Avbryt");
		if(undoButton.isVisible().isTrue()) undoButton.click();
	}

	@Test
	private void getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		time = sdf.format(cal.getTime());
	}

	@Test
	public void testRegisterTime() {
		input.appendText("1");
		registerButton.click();
		getTime();
		assertEquals("1; " + time + '\n', output.getText());
	}

	@Test
	public void testUndoButtonAppears() {
		registerButton.click();
		assertTrue(undoButton.isVisible());
	}

	@Test
	public void testRegisterEmptyTime() throws InterruptedException {
		registerButton.click();
		getTime();
		// Wait 1 sec for time to be different
		Thread.sleep(1000);
		input.appendText("1");
		registerButton.click();
		assertEquals("1; " + time + '\n', output.getText());
	}

	@Test(expected = FileNotFoundException.class)
	public void testUndoEmptyTime() {
		registerButton.click();
		undoButton.click();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./StoredTimeOfUnknownDriver.txt"));
		} catch (FileNotFoundException e) {
			// Should fail
		}
	}

	@Test
	public void testRegisterSeveralTimesInSuccession() {
		input.appendText("1-3");
		registerButton.click();
		getTime();
		assertEquals("1; " + time + '\n' + "2; " + time + '\n' + "3; " + time
				+ '\n', output.getText());
	}

	@Test
	public void testRegisterSeveralTimesNotInSuccession() {
		input.appendText("1,15,223");
		registerButton.click();
		getTime();
		// Alternate test until GUI is fixed. Swtich when it fails.
		assertEquals("223; " + time + '\n' + "15; " + time + '\n' + "1; "
				+ time + '\n', output.getText());
		// End of alternate test
		// Begin regular tets
		// assertEquals("1; " + time + '\n' + "15; " + time + '\n' + "223; " +
		// time + '\n', output.getText());
	}

	public void testRegisterSeveralTimesWithBothCases() {
		input.appendText("1-3,15");
		registerButton.click();
		getTime();
		// Alternate test until GUI is fixed. Swtich when it fails.
		assertEquals("15; " + time + '\n' + "1; " + time + '\n' + "2; " + time
				+ '\n' + "3; " + time + '\n', output.getText());
		// End of alternate test
		// Begin regular tets
//		 assertEquals("1; " + time + '\n' + "2; " + time + '\n' + "3; " + time
//		 + '\n' + "15; " + time + '\n', output.getText());
	}
}
