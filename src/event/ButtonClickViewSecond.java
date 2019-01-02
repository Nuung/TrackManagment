package event;

import java.awt.event.*;
import javax.swing.*;

public class ButtonClickViewSecond implements ActionListener {
	
	// member
	private JButton[] jbtn;
	private JPanel panel;
	
	// Constructor
	public ButtonClickViewSecond(JPanel panel) {
		this.panel = panel;
		jbtn = new JButton[panel.getComponentCount()];
		
		for(int i=0; i<jbtn.length; i++) {
			 jbtn[i] = (JButton)panel.getComponent(i);
		} // for
		
		System.out.println("in constructor" + jbtn.length);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(panel.getName() == "p1") // P1 은 상단 상태바
			trackButtonEvent(e);
		else if(panel.getName() == "p2") // P2 왼쪽 네비게이션 바
			menuButtonEvent(e);
		
		// button method testing
		System.out.println("in method" + jbtn.length);
		
	} // actionPerformed()
	
	public void trackButtonEvent(ActionEvent e) {
		
		if(e.getSource() == jbtn[0]) {
			System.out.println("track clicked 1");
		}
		
		else if(e.getSource() == jbtn[1]) {
			System.out.println("track clicked 2");
		}
		
		else if(e.getSource() == jbtn[2]) {
			System.out.println("track clicked 3");
		}
		
		else if(e.getSource() == jbtn[3]) {
			System.out.println("track clicked 4");
		}
		
		else {
			System.out.println("track clicked 5");
		}
		
	} // trackButtonEvent()
	
	public void menuButtonEvent(ActionEvent e) {
		
		if(e.getSource() == jbtn[0]) {
			System.out.println("menu clicked 1");
		}
		
		else if(e.getSource() == jbtn[1]) {
			System.out.println("menu clicked 2");
		}
		
		else if(e.getSource() == jbtn[2]) {
			System.out.println("menu clicked 3");
		}
		
		else if(e.getSource() == jbtn[3]) {
			System.out.println("menu clicked 4");
		}
		
		else {
			System.out.println("menu clicked 5");
		}
	} // menuButtonEvent()
	
	// Getter and Setter
	public JButton[] getButton() {
		return jbtn;
	}
	
	public void setButton(JButton[] btn) {
		this.jbtn = btn;
	}

}
