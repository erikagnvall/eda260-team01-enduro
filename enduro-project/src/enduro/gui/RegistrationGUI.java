package enduro.gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;

import enduro.racer.configuration.ConfigParser;

/**
 * Provides a GUI for the Enduro time registration program.
 */
@SuppressWarnings("serial")
public class RegistrationGUI extends JFrame implements WindowListener{

	RegistrationTextField registrationTextField;
	/**
	 * Creates the GUI, magic, do not touch!
	 */
	public RegistrationGUI(String[] args) {
		super("Registration GUI");
		buildLayout(args);
		setResizable(false);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		addWindowListener(this);
	}

	/**
	 * Adds all the components to the JFrame.
	 */
	private void buildLayout(String[] args) {
		Font font = new Font(null, Font.PLAIN, 60);
		setLayout(new BorderLayout());

		RegistrationTextArea registrationTextArea = new RegistrationTextArea(
				font);

		JPanel northPanel = new JPanel();
		StoredTime storedTime = new StoredTime();
		storedTime.setFont(new Font(null, Font.PLAIN, 80));
		
		registrationTextField = new RegistrationTextField(font,
				registrationTextArea, storedTime, args);
		UndoButton undo = new UndoButton("Avbryt", storedTime,
				registrationTextField);
		registrationTextField.setRegretButton(undo);
		undo.setVisible(false);

		registrationTextField.checkForSavedTimeFile();

		northPanel.add(undo);
		northPanel.add(registrationTextField);
		northPanel.add(new RegistrationButton("Registrera", font,
				registrationTextField));
		northPanel.add(storedTime);
		add(BorderLayout.NORTH, northPanel);

		add(BorderLayout.CENTER, registrationTextArea);

	}

	/** Main method. 
	 * @throws IOException */
	public static void main(String[] args) throws IOException {
		String[] arguments = ConfigParser.getInstance().getClientSetup();
		new RegistrationGUI(arguments);	
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		registrationTextField.closeConnection();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

}
