package viewer.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import viewer.ViewFrame;
import event.ButtonClickViewSecond;

public class ViewSecondSet {
	
	// Default Swing
	private ViewFrame viewFrame;
	private JPanel p1, p2, p3;
	
	private ButtonClickViewSecond btnclick;
	
	public ViewSecondSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.panelSetting();
	}
	
	// setting
	private void panelSetting() {
		this.p1 = new JPanel();
		this.p2 = new JPanel();
		this.p3 = new JPanel();
		
		this.p1.setLayout(new GridLayout(1, 5));
		this.p1.setName("p1");
		this.btnSetting(this.p1, 5);
		this.btnAddListener(this.p1, btnclick);
		
		this.p2.setLayout(new GridLayout(5, 1));
		this.p2.setName("p2");
		this.btnSetting(this.p2, 5);
		this.btnAddListener(this.p2, btnclick);
		
		this.p3.setLayout(null);
		this.p3.add(new JTextArea());
		
		this.viewFrame.setLayout(new BorderLayout());
		this.viewFrame.add(this.p1, BorderLayout.NORTH);
		this.viewFrame.add(this.p2, BorderLayout.WEST);
		this.viewFrame.add(this.p3, BorderLayout.CENTER);
	} // panelSetting()
	
	private void btnSetting(JPanel tempP, int btnNum) {
		
		JButton tempBtn[] = new JButton[btnNum];
		
		for (int i = 0; i < tempBtn.length; i++) {
			tempBtn[i] = new JButton(i + "번 버튼");
			tempP.add(tempBtn[i]);
		} // for
		
	} // btnSetting()
	
	private void btnAddListener(JPanel panel, ButtonClickViewSecond btnclickEvent) {
		
		btnclickEvent = new ButtonClickViewSecond(panel);
		JButton[] btn = btnclickEvent.getButton();
		for(int i=0; i<btn.length; i++) {
			btn[i].addActionListener(btnclickEvent);
		}
	} // btn add Event

}
