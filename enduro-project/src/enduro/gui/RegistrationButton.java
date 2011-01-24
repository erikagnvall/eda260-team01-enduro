package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JTextField;

public class RegistrationButton extends JButton implements ActionListener {
	private RegistrationField registrationTextField;

	public RegistrationButton(String name, Font font, RegistrationField registrationTextField) {
		super(name);
		addActionListener(this);
		setFont(font);
		this.registrationTextField = registrationTextField;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		registrationTextField.actionPerformed(arg0);
	}

}
