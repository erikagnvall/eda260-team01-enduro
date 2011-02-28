package enduro;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class EnduroDialogs {
	public static void main(String[] args){
		showConfirmDialog();
		showFailDialog();
	}
	public static void showConfirmDialog(){
		EnduroDialogs df = new EnduroDialogs();
		JOptionPane.showMessageDialog(null, "Sortering klar!", "Sortering klar!", 0, df.getDanishFlag());
	}
	public static void showFailDialog(){
		EnduroDialogs df = new EnduroDialogs();
		if(System.getProperty("os.name").contains("Mac"))
			try {
				Runtime.getRuntime().exec("say \"It's a trap\"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		JOptionPane.showMessageDialog(null, "Se debugResult.txt", "Sortering misslyckad!", 0, df.getItsATrap());
	
	}
	public ImageIcon getDanishFlag(){
		return createImageIcon("/Danish-Flag.jpg", "a danish flag");
	}
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	public ImageIcon getItsATrap(){
		return createImageIcon("/Atrapitis.gif", "It's a trap");
	}
	

}
