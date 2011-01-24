package enduro.gui;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextArea;

public class RegistrationTextArea extends JTextArea {

	/**
	 * Creates a new RegistrationTextArea with the specified font.
	 * 
	 * @param font
	 *            The font to use in this text area.
	 * @throws HeadlessException
	 *             Don't know why
	 */
	public RegistrationTextArea(Font font) throws HeadlessException {
		super();
		setFont(font);
		setEditable(false);
		setAutoscrolls(false);
	}

	/**
	 * Updates the text in the text area.
	 * 
	 * @param result
	 *            The text to insert in the text area.
	 */
	public void update(String result) {
		insert(result + '\n', 0);
	}

}
