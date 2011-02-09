package unittest.GUI;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.uispec4j.*;
import org.uispec4j.interception.MainClassAdapter;

import enduro.gui.RegistrationGUI;

public class RegistrationTest extends UISpecTestCase {
	
	protected void setUp() {
		setAdapter(new MainClassAdapter(RegistrationGUI.class, new String[0]));
	}
	
	public void testRegisterTime(){
		Window window = getMainWindow();
		TextBox input = window.getTextBox("Input");
		TextBox output = window.getTextBox("Output");
		Button registerButton = window.getButton("Registrera");
		input.appendText("1");
		registerButton.click();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		String time = sdf.format(cal.getTime());
		
		
		assertEquals("1; " + time + '\n', output.getText());
	}
}
