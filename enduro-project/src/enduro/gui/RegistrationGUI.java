package enduro.gui;

import java.awt.*;

import javax.swing.*;

/**
 * Provides a GUI for the Enduro time registration program.
 */
public class RegistrationGUI extends JFrame {

	public RegistrationGUI() {
		super("Registration GUI");
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, new JTable(80, 2));
		JPanel southPanel = new JPanel();
		JTextField registrationField = new JTextField(5);
		
		southPanel.add(registrationField);
		southPanel.add(new RegistrationButton("Register"));
		add(BorderLayout.SOUTH, southPanel);
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
