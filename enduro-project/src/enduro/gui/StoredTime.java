package enduro.gui;

import javax.swing.JLabel;

public class StoredTime extends JLabel {
	boolean Empty;
	
	/**
	 * Generated
	 */
	private static final long serialVersionUID = -7021838694005442758L;

	public StoredTime(){
		super();
		Empty = true;
		empty();
	}
	public void empty(){
		super.setText("              ");
		Empty = true;
	}
	public void setText(String s){
		super.setText(s);
		Empty = false;
	}
	public boolean isEmpty() {
		return Empty;
	}

}
