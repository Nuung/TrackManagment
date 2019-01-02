package viewer.ui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import event.ButtonClickViewSecond;

public class SidePanel {
	protected JPanel trackSidePanel;
	protected JPanel simulSidePanel;
	protected JPanel fidSidePanel;
	protected JPanel infoSidePanel;
	protected JPanel logOutSidePanel;

	JButton tempBtn[];

	// 이벤트처리
	private ButtonClickViewSecond btnclick;

	String[] sideTxt = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육", "사이버국방" };

	public SidePanel() {
		trackSidePanel = new JPanel();
		simulSidePanel = new JPanel();
		fidSidePanel = new JPanel();
		infoSidePanel = new JPanel();
		logOutSidePanel = new JPanel();
	} // 생성자

	public Component trackSide() {
		trackSidePanel.setLayout(new GridLayout(11, 1));
		SideBarBtn(trackSidePanel);
		btnListener(trackSidePanel, btnclick);

		return trackSidePanel;
	} // trackSidePanel()

	public Component simulSide() {
		simulSidePanel.setVisible(false);
		btnListener(simulSidePanel, btnclick);
		return simulSidePanel;
	} // simulSidePanel()

	public Component fidSide() {
		fidSidePanel.setLayout(new GridLayout(11, 1));
		SideBarBtn(fidSidePanel);
		btnListener(fidSidePanel, btnclick);

		return fidSidePanel;
	} // simulSidePanel()

	public Component infoSide() {
		infoSidePanel.setLayout(new GridLayout(11, 1));
		SideBarBtn(infoSidePanel);
		btnListener(infoSidePanel, btnclick);

		return infoSidePanel;
	} // simulSidePanel()

	public Component logOutSide() {
		logOutSidePanel.setVisible(false);
		btnListener(infoSidePanel, btnclick);
		return logOutSidePanel;
	} // simulSidePanel()

	public void SideBarBtn(JPanel tempPanel) {
		tempBtn = new JButton[11];
		for (int i = 0; i < 11; i++) {
			tempBtn[i] = new JButton("btn" + i);
			tempPanel.add(tempBtn[i]);
		}
	} // SideBarBtn()

	private void btnListener(JPanel panel, ButtonClickViewSecond btnclickEvent) {
		btnclickEvent = new ButtonClickViewSecond(panel);
		JButton[] btn = btnclickEvent.getButton();
		// adding Event to All Button
		for (int i = 0; i < btn.length; i++) {
			btn[i].addActionListener(btnclickEvent);
			System.out.println(btn[i].toString());
		} // for
	} // btn add Event

} // SidePanel class
