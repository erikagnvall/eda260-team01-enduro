package enduro.gui;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextArea;

public class RegistrationTextArea extends JTextArea {

	public RegistrationTextArea(Font font) throws HeadlessException {
		super();
		setFont(font);
		setEditable(false);
		setAutoscrolls(false);
	}
	public void update(String result){
		insert(result + '\n', 0);
	}

}
