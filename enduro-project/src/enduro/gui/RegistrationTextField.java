package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextField;

public class RegistrationTextField extends JTextField implements ActionListener {
	private RegistrationTextArea registrationTextArea;

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

}
