package viewer.ui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel {
	protected JPanel trackSidePanel;
	protected JPanel simulSidePanel;
	protected JPanel fidSidePanel;
	protected JPanel infoSidePanel;
	protected JPanel logOutSidePanel;
	JButton tempBtn[];

	String[] sideTxt = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육", "사이버국방" };

	public SidePanel() {
		trackSidePanel = new JPanel();
		simulSidePanel = new JPanel();
		fidSidePanel = new JPanel();
		infoSidePanel = new JPanel();
		logOutSidePanel = new JPanel();
	} // 생성자

	// 상단바 트랙버튼에 대한 사이드 바
	public Component trackSide() { 
		trackSidePanel.setLayout(new GridLayout(11, 1));
		SideBarBtn(trackSidePanel);
		return trackSidePanel;
	} // trackSidePanel()

	public Component simulSide() {
		simulSidePanel.setVisible(false);
		return simulSidePanel;
	} // simulSidePanel()

	public Component fidSide() {
		fidSidePanel.setLayout(new GridLayout(11, 1));
		SideBarBtn(fidSidePanel);
		return fidSidePanel;
	} // simulSidePanel()

	public Component infoSide() {
		infoSidePanel.setLayout(new GridLayout(11, 1));
		SideBarBtn(infoSidePanel);
		return infoSidePanel;
	} // simulSidePanel()

	public Component logOutSide() {
		logOutSidePanel.setVisible(false);
		return logOutSidePanel;
	} // simulSidePanel()

	public void SideBarBtn(JPanel tempPanel) {
		tempBtn = new JButton[11];
		for (int i = 0; i < 11; i++) {
			tempBtn[i] = new JButton("btn" + i);
			tempPanel.add(tempBtn[i]);
		}
	} // SideBarBtn()

} // SidePanel class
