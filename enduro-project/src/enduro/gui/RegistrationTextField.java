package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextField;

import enduro.Registration;
import enduro.racedata.Time;

public class RegistrationTextField extends JTextField implements ActionListener {
	private RegistrationTextArea registrationTextArea;
	private Registration registration;

	/**
	 * Creates a new RegistrationTextField with the specified Font and reference
	 * to the RegistrationTextArea.
	 * 
	 * @param font
	 *            The font to use in this text field.
	 * @param registrationTextArea
	 *            The RegistrationTextArea to add new stuff to.
	 */
	public RegistrationTextField(Font font,
			RegistrationTextArea registrationTextArea) {
		super(5);
		setFont(font);
		this.registrationTextArea = registrationTextArea;
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (!getText().equals("")) {
			StringBuilder sb = new StringBuilder();
			sb.append(getText() + "; ");
			sb.append(getTime());
			saveToFile();
			setText("");
			registrationTextArea.update(sb.toString());
		}
		requestFocus();
	}

	/**
	 * Returns the current time as a String. Probably exists in the back end.
	 * 
	 * @return The current time as a String.
	 */
	private String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		return sdf.format(cal.getTime());
	}

	/**
	 * Saves the current time for the entered number to file.
	 */
	private void saveToFile() {
		try {
			registration = new Registration("times.txt");
			registration.registerTime(Integer.parseInt(getText()), new Time(
					getTime()));
			registration.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
