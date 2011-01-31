package enduro.gui;

import javax.swing.JLabel;

public class StoredTime extends JLabel {
	
	public StoredTime(){
		super();
		empty();
	}
	public void empty(){
		setText("              ");
	}
	
	public boolean isEmpty() {
		return getText().equals("              ");
	}

}
