package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class RegretButton extends JButton implements ActionListener {
	private StoredTime storedTime;
	private RegistrationTextField registrationTextField;

	public RegretButton(String text, StoredTime storedTime, RegistrationTextField registrationTextField) {
		super(text);
		this.storedTime = storedTime;
		this.registrationTextField = registrationTextField;
		addActionListener(this);
		setFont(new Font(null, Font.PLAIN, 40));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		storedTime.empty();
		registrationTextField.requestFocus();
	}

}
