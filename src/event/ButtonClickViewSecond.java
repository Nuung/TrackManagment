package event;

import java.awt.event.*;
import javax.swing.*;

public class ButtonClickViewSecond implements ActionListener {
	
	private JButton[] jbtn;
	
	
	public ButtonClickViewSecond(JPanel panel) {
		jbtn = new JButton[panel.getComponentCount()];
		
		
		for(int i=0; i<jbtn.length; i++) {
			 jbtn[i] = (JButton)panel.getComponent(i);
		}
		
		System.out.println("in constructor" + jbtn.length);
		
	}
	
	public JButton[] getButton() {
		return jbtn;
	}
	
	public void setButton(JButton[] btn) {
		this.jbtn = btn;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jbtn[0]) {
			System.out.println("clicked");
		}
		
		System.out.println("in method" + jbtn.length);
	}

}
