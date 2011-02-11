package enduro.gui;

import java.awt.*;

import javax.swing.*;

/**
 * Provides a GUI for the Enduro time registration program.
 */
@SuppressWarnings("serial")
public class RegistrationGUI extends JFrame {

	/**
	 * Creates the GUI, magic, do not touch!
	 */
	public RegistrationGUI() {
		super("Registration GUI");
		buildLayout();
		setResizable(false);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Adds all the components to the JFrame.
	 */
	private void buildLayout() {
		Font font = new Font(null, Font.PLAIN, 60);
		setLayout(new BorderLayout());

		RegistrationTextArea registrationTextArea = new RegistrationTextArea(
				font);

		JPanel northPanel = new JPanel();
		StoredTime storedTime = new StoredTime();
		storedTime.setFont(new Font(null, Font.PLAIN, 80));
		
		RegistrationTextField registrationTextField = new RegistrationTextField(
				font, registrationTextArea, storedTime);

		UndoButton regret = new UndoButton("Avbryt", storedTime,
				registrationTextField);
		registrationTextField.setRegretButton(regret);
		regret.setVisible(false);

		registrationTextField.checkForSavedTimeFile();
		
		northPanel.add(regret);
		northPanel.add(registrationTextField);
		northPanel.add(new RegistrationButton("Registrera", font,
				registrationTextField));
		northPanel.add(storedTime);
		add(BorderLayout.NORTH, northPanel);

		add(BorderLayout.CENTER, registrationTextArea);
	}
	
	

	/** Main method. */
	public static void main(String[] args) {
		new RegistrationGUI();
	}
}
