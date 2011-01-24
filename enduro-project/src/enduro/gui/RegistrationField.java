package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextField;

public class RegistrationField extends JTextField implements ActionListener {
	private RegistrationTextArea registrationTextArea;

	public RegistrationField(Font font, RegistrationTextArea registrationTextArea) {
		super(5);
		setFont(font);
		this.registrationTextArea = registrationTextArea;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!getText().equals("")) {
			StringBuilder sb = new StringBuilder();
			sb.append(getText() + "; ");
			sb.append(getTime());
			setText("");
			registrationTextArea.update(sb.toString());
		}
		requestFocus();
	}
	private String getTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		return sdf.format(cal.getTime());
		
	}

}
