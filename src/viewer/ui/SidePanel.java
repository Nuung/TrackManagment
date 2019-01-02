package viewer.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.StudentInfo;
import controller.StudentInfo.StudentSubject;
import controller.ChangeLecture;
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
	protected ArticleUIpanel articleUi;
	
	public SidePanel(ViewFrame viewFrame, StudentInfo studentinfo) {
		this.viewFrame = viewFrame;
		this.studentinfo = studentinfo;
		this.articleUi = new ArticleUIpanel(studentinfo);
		
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
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.hciBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.hciBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.hciBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.hciSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.hciSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.hciSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.hciSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.hciSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.hciSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				}//if
				else if (butSrcTxt == sideTxt[1]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.multimediaBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.multimediaBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.multimediaBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.multimediaBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.multimediaBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.multimediaBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.multimediaSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.multimediaSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.multimediaSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.multimediaSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.multimediaSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.multimediaSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[2]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.iotBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.iotBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.iotBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.iotBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.iotBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.iotBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.iotSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.iotSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.iotSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.iotSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.iotSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.iotSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[3]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.systemappBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.systemappBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.systemappBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.systemappBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.systemappBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.systemappBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.systemappSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.systemappSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.systemappSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.systemappSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.systemappSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.systemappSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[4]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.aiBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.aiBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.aiBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.aiBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.aiBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.aiBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.aiSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.aiSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.aiSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.aiSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.aiSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.aiSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[5]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.virtualrealityBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.virtualrealityBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.virtualrealityBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.virtualrealityBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.virtualrealityBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.virtualrealityBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.virtualrealitySarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.virtualrealitySarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.virtualrealitySarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.virtualrealitySarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.virtualrealitySarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.virtualrealitySarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[6]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.infoprotectBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.infoprotectBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.infoprotectBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.infoprotectBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.infoprotectBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.infoprotectBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.infoprotectSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.infoprotectSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.infoprotectSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.infoprotectSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.infoprotectSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.infoprotectSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[7]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.datascienceBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.datascienceBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.datascienceBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.datascienceBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.datascienceBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.datascienceBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.datascienceSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.datascienceSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.datascienceSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.datascienceSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.datascienceSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.datascienceSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}

					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[8]) {
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.sweduBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							if(ArticleUIpanel.sweduBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								String to = Integer.toString(ArticleUIpanel.sweduBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.sweduBarr[i]);
								artic.trackBText.append("이수 "+to+"\n");
							}else {
								count++;
								if(count == tempStudentinfo.size()) {
									String too = Integer.toString(ArticleUIpanel.sweduBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.sweduBarr[i]);
									artic.trackBText.append("미이수 "+too+"\n");
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					//System.out.println(sideTxt[0]);
					//오른쪽 text
					
					

					//ArticleUIpanel artic2 = new ArticleUIpanel(studentinfo);
					//artic2.trackArticle();
					
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.sweduSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							if(ArticleUIpanel.sweduSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								String to2 = Integer.toString(ArticleUIpanel.sweduSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.sweduSarr[i]);
								artic.trackAText.append("이수 "+to2+"\n");
							}else {
								count2++;
								if(count2 == tempStudentinfo2.size()) {
									String too2 = Integer.toString(ArticleUIpanel.sweduSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.sweduSarr[i]);
									artic.trackAText.append("미이수 "+too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}					
					
					//artic2.trackAText.setText("b222bbb");
					viewFrame.add(artic);
					//viewFrame.add(artic2);
					viewFrame.revalidate();
				
				} // if -- else
				
			} // actionPerformed()
		});
	} // btnAction()

	// simulSidePanel
	private void simulBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
			} // actionPerformed()
		}); // addActionListener
	} // simulBtnAction()
	
	// fidSidePanel
	private void fidBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
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
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				//INFO article 버튼 이벤트 구현
				String linkedStr[] = {"HCI 산학프로젝트", "멀티미디어 산학프로젝트", "사물인터넷 산학프로젝트", "시스템응용 산학프로젝트", "인공지능 산학프로젝트", 
						"가상현실 산학프로젝트", "정보보호 산학프로젝트", "데이터사이언스 산학프로젝트", "SW교육 산학프로젝트"};
				String employStr[] = {"HCI 관련 직종", "멀티미디어 관련 직종", "사물인터넷 기술응용 물류, 제조, 에너지, 시큐리티, 텔레매틱스, 헬스케어, 스마트홈", 
						"", "지능형SW, 로봇지능, 무인제어, 무인주행, 전문가시스템, 기계번역, 의료/금융 예측 및 이상탐지", "가상현실 기술응용, 교육, 치료, 문화, 오락, 여가 산업", 
						"정보보호, 네트워크 보안, 어플리케이션 보안, 금융/군사 보안", "데이터사이언스 관련 직종", "SW교육 관련 직종"};
				for(int i = 0; i < 9; i++) {
					if(butSrcTxt == sideTxt[i]) { //사이드 패널 버튼을 눌렀다면	
						articleUi.resetArticleUI(); 	// 버튼 클릭 시 패널에 있는 UI 초기화를 해주기 위한 메소드
						ArticleUIpanel articleUi = new ArticleUIpanel(studentinfo); 
						articleUi.infoArticle();
						articleUi.welcomelbl.setText("");
						//article의 trackTextField에 문자열 넣기
						articleUi.infoTrackText.setText(sideTxt[i]);
						articleUi.infoTrackText.setHorizontalAlignment(JTextField.CENTER);
						
						//JTextArea에 해당 트랙 설명, 관련 직종
						articleUi.infoText.setText("산학연계교육 : " + linkedStr[i] + "\n" + "취업 및 진학 : " + employStr[i] + "\n");
						viewFrame.add(articleUi);
						viewFrame.revalidate();
					}
				} //for
			} // actionPerformed()
		}); // addActionListener
	} // simulBtnAction()
	
	// logOutSidePanel
	private void logoutBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {	
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				if (butSrcTxt == sideTxt[0]) {

				} // 왼쪽 첫번째 버튼
			} // actionPerformed()
		}); // addActionListener
	} // infoBtnAction()	
} // SidePanel class
