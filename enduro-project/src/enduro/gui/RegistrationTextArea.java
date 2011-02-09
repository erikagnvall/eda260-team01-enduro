package enduro.gui;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextArea;

/**
 * An area displaying the output of the registration GUI.
 */
@SuppressWarnings("serial")
public class RegistrationTextArea extends JTextArea {

	/**
	 * Creates a new RegistrationTextArea with the specified font.
	 * 
	 * @param font
	 *            The font to use in this text area.
	 * @throws HeadlessException
	 *             Don't know why. Maybe when the user gets beheaded? =S
	 */
	public RegistrationTextArea(Font font) throws HeadlessException {
		super();
		setName("TextArea");
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
