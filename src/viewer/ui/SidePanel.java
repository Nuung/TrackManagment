package viewer.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel {
	protected JPanel trackSidePanel;
	protected JPanel simulSidePanel;
	protected JPanel fidSidePanel;
	protected JPanel infoSidePanel;
	protected JPanel logOutSidePanel;
	JButton tempBtn[];

	// 이벤트처리
	protected String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };

	public SidePanel() {
		trackSidePanel = new JPanel();
		simulSidePanel = new JPanel();
		fidSidePanel = new JPanel();
		infoSidePanel = new JPanel();
		logOutSidePanel = new JPanel();
	} // 생성자

	// 상단바에서 트랙 -> 트랙 JPanel
	public Component trackSide() {
		trackSidePanel.setLayout(new GridLayout(9, 1));
		SideBarBtn(trackSidePanel);
		return trackSidePanel;
	} // trackSidePanel()

	// 상단바에서 트랙시뮬 -> 트랙시뮬 JPanel
	public Component simulSide() {
		simulSidePanel.setVisible(false);
		return simulSidePanel;
	} // simulSidePanel()

	// 상단바에서 피드백 -> 피드백 JPanel
	public Component fidSide() {
		fidSidePanel.setLayout(new GridLayout(9, 1));
		SideBarBtn(fidSidePanel);
		return fidSidePanel;
	} // simulSidePanel()

	// 상단바에서 정보 -> 정보 JPanel
	public Component infoSide() {
		infoSidePanel.setLayout(new GridLayout(9, 1));
		SideBarBtn(infoSidePanel);
		return infoSidePanel;
	} // simulSidePanel()

	// Log out
	public Component logOutSide() {
		logOutSidePanel.setVisible(false);
		return logOutSidePanel;
	} // simulSidePanel()

	// adding the JButton to Panel
	public void SideBarBtn(JPanel tempPanel) {
		tempBtn = new JButton[9];
		for (int i = 0; i < 9; i++) {
			tempBtn[i] = new JButton(sideTxt[i]);
			this.btnAction(tempBtn[i]);
			tempPanel.add(tempBtn[i]);
		} // inner for
	} // SideBarBtn()
	
	// 각 버튼의 액션 이벤트 세팅과 명시
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				if (butSrcTxt == sideTxt[0]) {
					System.out.println(sideTxt[0]);					
				}
				else if (butSrcTxt == sideTxt[1]) {
					System.out.println(sideTxt[1]);
				}
				else if (butSrcTxt == sideTxt[2]) {
					System.out.println(sideTxt[2]);
				}
				else if (butSrcTxt == sideTxt[3]) {
					System.out.println(sideTxt[3]);
				}
				else if (butSrcTxt == sideTxt[4]) {
					System.out.println(sideTxt[4]);
				}
				else if (butSrcTxt == sideTxt[5]) {
					System.out.println(sideTxt[5]);
				}
				else if (butSrcTxt == sideTxt[6]) {
					System.out.println(sideTxt[6]);
				}
				else if (butSrcTxt == sideTxt[7]) {
					System.out.println(sideTxt[7]);
				}
				else if (butSrcTxt == sideTxt[8]) {
					System.out.println(sideTxt[8]);
				} // if -- else
			} // actionPerformed()
		});
	} // btnAction()

	
} // SidePanel class
