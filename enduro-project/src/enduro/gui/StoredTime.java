package enduro.gui;

import javax.swing.JLabel;

public class StoredTime extends JLabel {
	private boolean empty;
	
	/**
	 * Generated
	 */
	private static final long serialVersionUID = -7021838694005442758L;

	public StoredTime(){
		super();
		empty = true;
		empty();
	}
	public void empty(){
		super.setText("              ");
		empty = true;
	}
	public void setText(String s){
		super.setText(s);
		empty = false;
	}
	public boolean isEmpty() {
		return empty;
	}

}
