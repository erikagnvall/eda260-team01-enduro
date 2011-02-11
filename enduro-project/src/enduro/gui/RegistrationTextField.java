package enduro.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import enduro.Registration;
import enduro.TxtToHtml;
import enduro.racedata.Time;
/**
 * The text field in which the user enters text.
 *
 */
@SuppressWarnings("serial")
public class RegistrationTextField extends JTextField implements ActionListener {
	private RegistrationTextArea registrationTextArea;
	private Registration registration;
	private StoredTime storedTime;
	private UndoButton undo;

	/**
	 * Creates a new RegistrationTextField with the specified Font and reference
	 * to the RegistrationTextArea.
	 * 
	 * @param font
	 *            The font to use in this text field.
	 * @param registrationTextArea
	 *            The RegistrationTextArea to add new stuff to.
	 */
	public RegistrationTextField(Font font,
			RegistrationTextArea registrationTextArea, StoredTime storedTime) {
		super(5);
		setName("Input");
		setFont(font);
		this.registrationTextArea = registrationTextArea;
		this.storedTime = storedTime;
		addActionListener(this);
	}

	/**
	 * Registers multiple racers if the input stream is written as first - last
	 * where first is the first racer's number and last is the last racer's
	 * number. Otherwise the racer is registered according to the input number.
	 */
	public void actionPerformed(ActionEvent ae) {
		String currentTime = getTime();
		Pattern p = Pattern
				.compile("((\\d+)|(\\d+-\\d+))((,\\d+((,\\d+)|(-\\d+))?))*");
		Matcher m = p.matcher(getText());
		boolean invalid = !m.matches();
		String[] commaSeparated = getText().split(",");
		boolean isCommaSeparated = false;
		// commaseperated handling
		if (commaSeparated.length > 1 && !invalid && !getText().equals("")) {
			isCommaSeparated = true;
			for (String raceNbr : commaSeparated) {
				String[] dashSeparated = raceNbr.split("-");
				if (dashSeparated.length > 1) {
					StringBuilder sb = new StringBuilder();
					makeRow(currentTime, dashSeparated, sb);
					try {
						sb.deleteCharAt(sb.length() - 1);
						registrationTextArea.update(sb.toString());
					} catch (StringIndexOutOfBoundsException e) {

					}
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(raceNbr);
					sb.append(';');
					sb.append(' ');
					if (storedTime.isEmpty()) {
						sb.append(currentTime);
					} else {
						sb.append(getStoredTime().toString());
						undo.setVisible(false);
					}

					try {
						deleteStoredTimeFile();
						if (storedTime.isEmpty()) {
							saveToFile(Integer.parseInt(raceNbr), new Time(
									currentTime));
						} else {
							saveToFile(Integer.parseInt(raceNbr),
									getStoredTime());
						}
						registrationTextArea.update(sb.toString());
					} catch (NumberFormatException e) {

					}
				}
			}
			storedTime.empty();
		}
		// Dashseperated handling
		String[] dashSeparated = getText().split("-");
		if (dashSeparated.length > 1 && !isCommaSeparated && !invalid
				&& !getText().equals("")) {
			StringBuilder sb = new StringBuilder();
			makeRow(currentTime, dashSeparated, sb);
			try {
				sb.deleteCharAt(sb.length() - 1);
				registrationTextArea.update(sb.toString());
			} catch (StringIndexOutOfBoundsException e) {

			}
			storedTime.empty();
		} else if (!getText().equals("") && !invalid) {
			StringBuilder sb = new StringBuilder();
			sb.append(getText());
			sb.append(';');
			sb.append(' ');
			if (storedTime.isEmpty()) {
				sb.append(currentTime);
			} else {
				sb.append(getStoredTime().toString());

				undo.setVisible(false);
			}

			try {
				deleteStoredTimeFile();
				if (storedTime.isEmpty()) {
					saveToFile(Integer.parseInt(getText()), new Time(
							currentTime));
				} else {
					saveToFile(Integer.parseInt(getText()), getStoredTime());
				}
				registrationTextArea.update(sb.toString());
			} catch (NumberFormatException e) {

			}
			storedTime.empty();
		} else if ((getText().equals("") || invalid) && storedTime.isEmpty()) {
			if (storedTime.isEmpty()) {
				storeTime();
				undo.setVisible(true);
			}
		}
		setText("");
		requestFocus();
	}

	/**
	 * Creates a new row in sb.
	 * 
	 * @param currentTime
	 * @param dashSeparated
	 * @param sb
	 */
	private void makeRow(String currentTime, String[] dashSeparated,
			StringBuilder sb) {
		for (int i = Integer.parseInt(dashSeparated[0]); i < Integer
				.parseInt(dashSeparated[1]) + 1; i++) {
			sb.append(i);
			sb.append(';');
			sb.append(' ');
			if (storedTime.isEmpty()) {
				sb.append(currentTime);
			} else {
				sb.append(getStoredTime().toString());
			}
			sb.append('\n');
			if (storedTime.isEmpty()) {
				saveToFile(i, new Time(currentTime));
			} else {
				saveToFile(i, getStoredTime());
			}
		}

	}

	/**
	 * Saves the current time to a text file.
	 */
	private void storeTime() {
		String time = getTime();
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("storedTimeOfUnknownDriver.txt")));
			out.println(time);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		storedTime.setText(time);
	}

	/**
	 * Deletes the file storedTimeOfUnknownDriver.txt.
	 */
	public void deleteStoredTimeFile() {
		File f = new File("storedTimeOfUnknownDriver.txt");
		f.delete();
	}

	/**
	 * Checks if storedTimeOfUnknownDriver.txt file exists.
	 */
	public void checkForSavedTimeFile() {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(
					"storedTimeOfUnknownDriver.txt"));
			storedTime.setText(in.readLine());
			undo.setVisible(true);
		} catch (FileNotFoundException e1) {

		} catch (IOException e) {

		}
	}

	/**
	 * Reads the temporary stored file, containing the last entered
	 * unknown-driver time.
	 * 
	 * @return
	 */
	public Time getStoredTime() {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(
					"storedTimeOfUnknownDriver.txt"));
			return new Time(in.readLine());
		} catch (FileNotFoundException e1) {

		} catch (IOException e) {

		}
		return null;
	}

	/**
	 * Returns the current time as a String. Probably exists in the back end.
	 * 
	 * @return The current time as a String.
	 */
	private String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		return sdf.format(cal.getTime());
	}

	/**
	 * Saves the input time for the entered number to file.
	 * 
	 * @param number
	 *            the racer's number
	 * @param t
	 *            the time for the racer.
	 */

	private void saveToFile(int number, Time t) {
		try {
			registration = new Registration("times.txt");
			registration.registerTime(number, t);
			registration.close();
			TxtToHtml txtHt = new TxtToHtml();
			txtHt.makeHtmlFile("times.txt", "times.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used by JTextField and creates a new numberDocument object.
	 */
	protected Document createDefaultModel() {
		return new numberDocument();
	}

	/**
	 * A class that contains a method that is called every time a new character
	 * is entered in the text field. Checks if the character is a digit or ","
	 * or "-".s
	 */
	private class numberDocument extends PlainDocument {

		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			char[] number = str.toCharArray();
			for (int i = 0; i < number.length; i++) {
				if (!Character.isDigit(number[i]) && number[i] != ','
						&& number[i] != '-')
					return;
			}

			super.insertString(offs, new String(number), a);
		}

	}

	public void setRegretButton(UndoButton regret) {
		this.undo = regret;
	}

}
