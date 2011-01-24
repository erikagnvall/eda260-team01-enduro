package enduro.gui;

import java.awt.*;

import javax.swing.*;

/**
 * Provides a GUI for the Enduro time registration program.
 */
public class RegistrationGUI extends JFrame {

	/**
	 * Creates the GUI, magic, do not touch!
	 */
	public RegistrationGUI() {
		super("Registration GUI");
		buildLayout();
		setResizable(false);
		pack();
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Adds all the components to the JFrame.
	 */
	private void buildLayout() {
		Font font = new Font(null, Font.PLAIN, 120);
		setLayout(new BorderLayout());

		RegistrationTextArea registrationTextArea = new RegistrationTextArea(
				font);
		add(BorderLayout.CENTER, registrationTextArea);

		JPanel northPanel = new JPanel();
		RegistrationTextField registrationTextField = new RegistrationTextField(
				font, registrationTextArea);
		northPanel.add(registrationTextField);
		northPanel.add(new RegistrationButton("Register", font,
				registrationTextField));
		add(BorderLayout.NORTH, northPanel);
	}

	/** Main method. */
	public static void main(String[] args) {
		new RegistrationGUI();
	}
}
