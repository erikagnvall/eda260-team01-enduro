package enduro.gui;

import javax.swing.JLabel;

public class StoredTime extends JLabel {
	
	/**
	 * Generated
	 */
	private static final long serialVersionUID = -7021838694005442758L;

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
