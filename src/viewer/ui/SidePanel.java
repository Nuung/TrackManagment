package viewer.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.StudentInfo;
import controller.StudentInfo.StudentSubject;
import controller.db.DBconnection;
import viewer.ViewFrame;

public class SidePanel {
	// Jpanle
	protected JPanel trackSidePanel;
	protected JPanel simulSidePanel;
	protected JPanel fidSidePanel;
	protected JPanel infoSidePanel;
	protected JPanel logOutSidePanel;
	JButton tempBtn[];

	protected ViewFrame viewFrame;
	// 이벤트처리
	protected String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };
	protected StudentInfo studentinfo;
	
	public SidePanel(ViewFrame viewFrame, StudentInfo studentinfo) {
		this.viewFrame = viewFrame;
		this.studentinfo = studentinfo;
		trackSidePanel = new JPanel();
		trackSidePanel.setName("trackSidePanel");
		simulSidePanel = new JPanel();
		simulSidePanel.setName("simulSidePanel");
		fidSidePanel = new JPanel();
		fidSidePanel.setName("fidSidePanel");
		infoSidePanel = new JPanel();
		infoSidePanel.setName("infoSidePanel");
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
			if(tempPanel.getName() == "trackSidePanel") {
				this.trackBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "simulSidePanel") {
				this.simulBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "fidSidePanel") {
				this.fidBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "infoSidePanel") {
				this.infoBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "logOutSidePanel") {
				this.logoutBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			}
		} // for
	} // SideBarBtn()
	
	// Side Bar Button의 액션 이벤트 세팅과 명시
	// trackSidePanel
	private void trackBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				ArticleUIpanel articleUi = new ArticleUIpanel(studentinfo);
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				if (butSrcTxt == sideTxt[0]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.hciBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.hciBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.hciBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.hciBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					
					
//					for(int i = 0 ; i < ArticleUIpanel.hciBarr.length ; i++) {
//						for(int j = 0 ; j < ArticleUIpanel.hciBarr.length ; j++) {
//							if(ArticleUIpanel.hciBarr[i] != rs.getInt("student_number")) {
//								String to = Integer.toString(rs.getInt("student_number"));
//								artic.trackBText.setText(to);
//							}
//						}//안쪽for
//					}//바깥쪽 for
					
					
					System.out.println(sideTxt[0]);
//					JTextArea firta = new JTextArea()
					//System.out.println(ArticleUIpanel.hciBarr[0]);
					
					//int[] hcib = new int[3];
					
					
					artic.trackAText.setText("bbbbbbb");
					viewFrame.add(artic);
					viewFrame.revalidate();
				}
				else if (butSrcTxt == sideTxt[1]) {
					System.out.println(sideTxt[1]);
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					artic.trackBText.setText("11");
					artic.trackAText.setText("22");
					viewFrame.add(artic);
					viewFrame.revalidate();
//					articleUi.
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

	// simulSidePanel
	private void simulBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				ArticleUIpanel articleUi = new ArticleUIpanel(studentinfo);
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				if (butSrcTxt == sideTxt[0]) {
					
				} // 왼쪽 첫번째 버튼
			} // actionPerformed()
		}); // addActionListener
	} // simulBtnAction()
	
	// fidSidePanel
	private void fidBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				ArticleUIpanel articleUi = new ArticleUIpanel(studentinfo);
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				if (butSrcTxt == sideTxt[0]) {
					
				} // 왼쪽 첫번째 버튼
			} // actionPerformed()
		}); // addActionListener
	} // fidBtnAction()
	
	// infoSidePanel
	private void infoBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				ArticleUIpanel articleUi = new ArticleUIpanel(studentinfo);
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				if (butSrcTxt == sideTxt[0]) {
					
				} // 왼쪽 첫번째 버튼
			} // actionPerformed()
		}); // addActionListener
	} // simulBtnAction()
	
	// logOutSidePanel
	private void logoutBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				ArticleUIpanel articleUi = new ArticleUIpanel(studentinfo);
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				if (butSrcTxt == sideTxt[0]) {
					
				} // 왼쪽 첫번째 버튼
			} // actionPerformed()
		}); // addActionListener
	} // infoBtnAction()
	
} // SidePanel class
