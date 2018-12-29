package viewer;

import java.awt.Color;

/*
 * 
 */

import javax.swing.JFrame;

import viewer.ui.ViewFirstSet;
import viewer.ui.ViewSecondSet;

public class ViewFrame extends JFrame{

	// Size of Frame
	private final int WIDTH = 1024, HEIGHT = 512;
	
	public ViewFrame() {
		// UI (front) Setting
		new ViewFirstSet(this);
		
		// test line
//		new ViewSecondSet(this);
		
		// Main Frame Setting
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Track Manager System");
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);

	} // ViewFrame()

}
