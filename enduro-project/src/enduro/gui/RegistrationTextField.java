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

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import enduro.Registration;
import enduro.racedata.Time;

@SuppressWarnings("serial")
public class RegistrationTextField extends JTextField implements ActionListener {
	private RegistrationTextArea registrationTextArea;
	private Registration registration;
	private StoredTime storedTime;

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
		String[] text = getText().split("-");
		if (text.length > 1) {
			deleteStoredTimeFile();
			String time = getTime();
			StringBuilder sb = new StringBuilder();
			for (int i = Integer.parseInt(text[0]); i < Integer
			.parseInt(text[1]) + 1; i++) {
				sb.append(i);
				sb.append(';');
				sb.append(' ');
				sb.append(time);
				sb.append('\n');
				saveToFile(i, new Time(time));
			}
			try {
				sb.deleteCharAt(sb.length() - 1);
				registrationTextArea.update(sb.toString());
			} catch (StringIndexOutOfBoundsException e) {

			}
		} else if (!getText().equals("")) {
			deleteStoredTimeFile();
			StringBuilder sb = new StringBuilder();
			sb.append(getText());
			sb.append(';');
			sb.append(' ');
			if (storedTime.isEmpty()) {
				sb.append(getTime());
			} else {
				sb.append(storedTime.getText());
				storedTime.empty();
			}

			try {
				saveToFile(Integer.parseInt(getText()), new Time(getTime()));
				registrationTextArea.update(sb.toString());
			} catch (NumberFormatException e) {

			}
		} else if (getText().equals(("")) && storedTime.isEmpty()) {
			storeTime();
		}
		setText("");
		requestFocus();
	}


	private void storeTime(){
		String time = getTime();
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("storedTimeOfUnknownDriver.txt")));
			out.println(time);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		storedTime.setText(time);
	}

	public void deleteStoredTimeFile() {
		File f = new File("storedTimeOfUnknownDriver.txt");
		f.delete();
	}

	public void checkForSavedTimeFile(){
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("storedTimeOfUnknownDriver.txt"));
			storedTime.setText(in.readLine());
		} catch (FileNotFoundException e1) {

		}catch(IOException e){

		}
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected Document createDefaultModel() {
		return new numberDocument();
	}	
	
	/**
	 * A class that contains a method that is called every time a new character
	 * is entered in the text field. Checks if the character is a digit or "," or "-".s
	 * 
	 *
	 */
	private class numberDocument extends PlainDocument {

		public void insertString(int offs, String str, AttributeSet a) 
		throws BadLocationException {

			if (str == null) {
				return;
			}
			char[] number = str.toCharArray();
			for (int i = 0; i < number.length; i++) {
				if(!Character.isDigit(number[i]) && number[i] != ',' && number[i] != '-')
					return;
	
			}
			
			super.insertString(offs, new String(number), a);
		}
	}

}
