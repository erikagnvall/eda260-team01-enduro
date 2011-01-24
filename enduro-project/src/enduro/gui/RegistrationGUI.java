package enduro.gui;

import java.awt.*;

import javax.swing.*;

/**
 * Provides a GUI for the Enduro time registration program.
 */
public class RegistrationGUI extends JFrame {

	public RegistrationGUI() {
		super("Registration GUI");
		Font font = new Font(null, Font.PLAIN, 120);
		setLayout(new BorderLayout());
		RegistrationTextArea registrationTextArea = new RegistrationTextArea(font);
		add(BorderLayout.CENTER, registrationTextArea);
		JPanel northPanel = new JPanel();
		RegistrationField registrationField = new RegistrationField(font, registrationTextArea);
		registrationField.setFont(font);

		northPanel.add(registrationField);
		northPanel.add(new RegistrationButton("Register", font,
				registrationField));
		add(BorderLayout.NORTH, northPanel);
		setSize(getWorkspaceSize());
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private Dimension getWorkspaceSize() {
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width - insets.left - insets.right;
		int height = screenSize.height - insets.top - insets.bottom;
		return new Dimension(width, height);

	}

	public static void main(String[] args) {
		new RegistrationGUI();
	}
}
