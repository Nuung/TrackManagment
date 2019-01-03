package viewer;

import java.awt.Color;

/*
 * 
 */

import javax.swing.JFrame;
import javax.swing.UIManager;

import viewer.ui.ViewFirstSet;

public class ViewFrame extends JFrame{

	// Size of Frame
	private final int WIDTH = 1024, HEIGHT = 512;
	
	public ViewFrame() {
		
		// UI (front) Setting
		this.SettingLookAndFeel();
		new ViewFirstSet(this);
		
		// Main Frame Setting
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Track Manager System");
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
	} // ViewFrame()

	private void SettingLookAndFeel() {
		// look and fell setting
		try {
			 //Nimbus
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// Windows
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		} // try - catch
	} // SettingLookAndFeel()
	
}
