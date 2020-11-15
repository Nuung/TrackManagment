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

import chat.controller.ChatControl;
import chat.model.ChatData;
import viewer.ChatViewer;


import controller.StudentInfo;
import controller.StudentInfo.StudentSubject;
import controller.ChangeLecture;
import viewer.ViewFrame;

public class SidePanel {
	// Jpanle
	protected JPanel trackSidePanel; // 상단 바에서 '트랙' 버튼 클릭 시 나타나는 좌측 메뉴 패널
	protected JPanel simulSidePanel; // 상단 바에서 '트랙 시뮬레이션' 버튼 클릭 시 나타나는 좌측 메뉴 패널
	protected JPanel fidSidePanel; // 상단 바에서 '피드백' 버튼 클릭 시 나타나는 좌측 메뉴 패널
	protected JPanel infoSidePanel; // 상단 바에서 'information' 버튼 클릭 시 나타나는 좌측 메뉴 패널
	protected JPanel logOutSidePanel; // 상단 바에서 '로그아웃' 버튼 클릭 시 나타나는 좌측 메뉴 패널
	JButton tempBtn[]; // 각 메뉴 별로 좌측 메뉴에서의 버튼을 생성시켜주기 위해 선언한 JButton 배열

	protected ViewFrame viewFrame; // 최상위 프레임과 연결시켜주기 위해 선언
	
	// 버튼의 텍스트 값들을 스트링 배열로 선언
	protected String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };
	
	// 로그인을 한 유저의 정보(학번 및 로그인한 유저가 이수한 강의들의 번호)를 가져오기 위해 선언한 클래스
	protected StudentInfo studentinfo;
	
	// 버튼 클릭에 따른 결과 화면에 어떤 화면을 띄워줄 것인지를 결정하기 위해 선언한 클래스
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
	// 메뉴별로 그에 해당하는 패널에 각각 버튼을 동적으로 할당
	public void SideBarBtn(JPanel tempPanel) {
		tempBtn = new JButton[9];
		for (int i = 0; i < 9; i++) {
			tempBtn[i] = new JButton(sideTxt[i]);
			if(tempPanel.getName() == "trackSidePanel") { // 상단 바에서 '트랙' 버튼을 클릭 시에 대한 좌측 메뉴의 버튼 생성
				this.trackBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "fidSidePanel") { // 상단 바에서 '피드백' 버튼을 클릭 시에 대한 좌측 메뉴의 버튼 생성
				this.fidBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "infoSidePanel") { // 상단 바에서 'information' 버튼을 클릭 시에 대한 좌측 메뉴의 버튼 생성
				this.infoBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			} else if(tempPanel.getName() == "logOutSidePanel") { // 상단 바에서 '로그아웃' 버튼을 클릭 시에 대한 좌측 메뉴의 버튼 생성
				this.logoutBtnAction(tempBtn[i]);
				tempPanel.add(tempBtn[i]);
			}
		} // for
	} // SideBarBtn()
	
	// Side Bar Button의 액션 이벤트 세팅과 명시
	// trackSidePanel, 트랙 메뉴에 대한 좌측 메뉴 버튼 이벤트 처리
	private void trackBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				
				
				if (butSrcTxt == sideTxt[0]) { // HCI ~ 트랙 이수 여부를 판단하는 로직
					// 결과화면에 도출된 이수 여부를 저장하기 위해 선언한 객체
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);	artic.trackArticle();
					
					// 로그인한 유저의 수업 이수 정보를 가져오기 위한 벡터 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// HCI ~ 트랙에서 필수 이수 수업과 학생이 이수한 수업들을 비교하는 로직
					for (int i = 0 ; i < ArticleUIpanel.hciBarr.length;i++) {
						
						// 이수 여부를 확인해주는 변수
						// 트랙 기초 교과에서 한 수업을 학생이 이수한 수업들과 비교를 진행해서 일치하지 않을 때마다 1씩 더해준다.
						// 일치하는 수업이 있다면 최종적으로 count 변수에 저장된 값이 트랙 기초 교과의 갯수보다 부족하기 때문에 이는 이수한 수업으로 판정
						int count = 0;
						
						// 교과목명의 폰트에 색을 추가해주기 위해 선언
						artic.doc = artic.trackBPane.getStyledDocument();
						
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 트랙 기초 교과의 수업 넘버와 학생이 이수한 수업 넘버를 비교했을 때 값이 같을 경우
							if(ArticleUIpanel.hciBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 교과목명 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.hciBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.hciBarr[i]);
						
								// 이수한 교과목명의 색은 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
								
								// 트랙 기초 교과의 수업 넘버와 학생이 이수한 수업 넘버를 비교했을 때 값이 같을 경우
							}else {
								count++; // 수업이 일치하지 않으므로 count 변수에 저장된 값을 1 증가
								
								// 결과적으로 count의 값이 학생이 이수한 수업의 수와 같은 경우에는 이수한 수업이 아니라는 것을 의미
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 교과목명 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.hciBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.hciBarr[i]);
						
									// 이수하지 않은 교과목명은 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// count 변수 값을 초기화하여 다음 수업 비교를 진행
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					
					// 유저의 이수한 수업 정보를 가져온다.
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// HCI~ 트랙의 응용 교과와 유저의 수업 정보를 비교하는 로직, 필수교과와의 비교 처리와 같은 로직
					for (int i = 0 ; i < ArticleUIpanel.hciSarr.length;i++) {
						int count2 = 0; // 이수 여부를 판단하기 위한 변수
						
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업인 경우
							if(ArticleUIpanel.hciSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 교과목명 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.hciSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.hciSarr[i]);
								
								// 이수한 수업은 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
								
							// 이수한 수업이 아닌 경우
							}else {
								count2++; // 변수 값 ++
								
								
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 교과목명 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.hciSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.hciSarr[i]);
//									
									// 이수하지 않은 수업은 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업 비교를 위해 변수 값 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					// 이수 결과를 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				}//if
				
				
				else if (butSrcTxt == sideTxt[1]) { // 멀티미디어 트랙과 비교를 하는 로직
					
					// 결과 화면을 띄워주기 위해 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);

					artic.trackArticle();
					
					// 유저가 이수한 수업 정보를 담아주기 위해 벡터로 객체 선언

					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
					
					// 멀티미디어 트랙 기초 교과와 유저의 수업 비교
					for (int i = 0 ; i < ArticleUIpanel.multimediaBarr.length;i++) {
						int count = 0; // 수업 이수 여부를 확인하기 위한 변수
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 일치하는 수업이 있으면
							if(ArticleUIpanel.multimediaBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 교과목명 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.multimediaBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.multimediaBarr[i]);
					
								// 이수한 수업은 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
								
							// 일치하지 않으면
							}else {
								count++;
								
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 교과목명 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.multimediaBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.multimediaBarr[i]);
						
									// 이수하지 않은 수업은 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업과의 비교를 위해 변수 값 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					
					// 유저의 수업 정보를 담아오기 위해 벡터 객체 선언
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// 멀티미디어 트랙 응용 교과와 학생이 이수한 수업을 비교하는 로직
					for (int i = 0 ; i < ArticleUIpanel.multimediaSarr.length;i++) {
						int count2 = 0; // 이수 여부를 판단하기 위한 변수
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이 있으면
							if(ArticleUIpanel.multimediaSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.multimediaSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.multimediaSarr[i]);
					
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 이수하지 않았으면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.multimediaSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.multimediaSarr[i]);
					
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									//다음 수업과의 비교를 위해 변수 값 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					// 결과를 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				}
				
				else if (butSrcTxt == sideTxt[2]) { // IOT 트랙 이수 여부를 처리하기 위한 로직
					
					// 결과를 화면에 담아줄 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					

					// 유저의 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// 트랙 기초 교과 비교 로직
					for (int i = 0 ; i < ArticleUIpanel.iotBarr.length;i++) {
						int count = 0; // 이수 여부를 판단하기 위한 로직
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이 있으면
							if(ArticleUIpanel.iotBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.iotBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.iotBarr[i]);
					
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
							}else {
								count++;
								
								// 수업을 이수하지 않으면
								if(count == tempStudentinfo.size()) {
									// 수업 넘버를 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.iotBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.iotBarr[i]);
					
									// 빨간색으로 변환
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					

					
					// 유저의 이수한 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// IOT 트랙 응용 교과와의 비교 로직
					for (int i = 0 ; i < ArticleUIpanel.iotSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이 있다면
							if(ArticleUIpanel.iotSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.iotSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.iotSarr[i]);
				
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 수업을 이수하지 않았으면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.iotSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.iotSarr[i]);
				
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					// 프레임에 결과 화면 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[3]) { // 시스템응용 트랙 이수 여부 판단 로직
					
					// 결과를 담아줄 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					// 유저가 이수한 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// 시스템 응용 트랙 필수 교과 비교 로직
					for (int i = 0 ; i < ArticleUIpanel.systemappBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이 있다면
							if(ArticleUIpanel.systemappBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.systemappBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.systemappBarr[i]);
				
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
							}else {
								count++;
								
								// 이수하지 않았다면
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.systemappBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.systemappBarr[i]);
				
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업과의 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					
					// 유저의 수업 정보를 가져와 벡터에 저장
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// 시스템 응용 트랙 응용 교과와 비교하는 로직
					for (int i = 0 ; i < ArticleUIpanel.systemappSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이 있다면
							if(ArticleUIpanel.systemappSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.systemappSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.systemappSarr[i]);
				
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 이수하지 않았다면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.systemappSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.systemappSarr[i]);
				
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					// 결과 화면을 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[4]) { // 인공지능 트랙 이수 여부 처리
					
					// 결과를 출력하는 화면 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					// 유저의 수업 정보를 벡터에 저장
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// 인공지능 트랙 기초 교과 이수 여부 판단
					for (int i = 0 ; i < ArticleUIpanel.aiBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이 있다면
							if(ArticleUIpanel.aiBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.aiBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.aiBarr[i]);
					
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
							}else {
								count++;
								
								// 이수하지 않았다면
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.aiBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.aiBarr[i]);
					
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					
					// 유저의 수업 정보를 담는 벡터 생성
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// 인공지능 트랙 응용 교과 이수 여부 판단 로직
					for (int i = 0 ; i < ArticleUIpanel.aiSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.aiSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.aiSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.aiSarr[i]);
				
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
								
							}else {
								count2++;
								
								// 이수하지 않았다면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.aiSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.aiSarr[i]);
						
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					// 결과 화면을 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[5]) { // 가상현실 트랙 이수 여부 비교
					
					// 결과를 담아줄 화면 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					// 유저의 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// 가상현실 트랙 기초 교과 이수 여부 비교 로직
					for (int i = 0 ; i < ArticleUIpanel.virtualrealityBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.virtualrealityBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.virtualrealityBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.virtualrealityBarr[i]);
			
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
							}else {
								count++;
								
								// 이수하지 않았다면
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 스트링으로 표시
									String too = Integer.toString(ArticleUIpanel.virtualrealityBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.virtualrealityBarr[i]);
			
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					

					
					// 유저의 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// 가상현실 트랙 응용 교과 이수 여부 판단 로직
					for (int i = 0 ; i < ArticleUIpanel.virtualrealitySarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.virtualrealitySarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.virtualrealitySarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.virtualrealitySarr[i]);
			
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 이수하지 않았다면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.virtualrealitySarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.virtualrealitySarr[i]);
			
									// 빨간색으로 변환
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					
					// 결과 화면을 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[6]) { // 정보보안 트랙 이수 여부 판단
					
					// 결과를 담아줄 화면 객체를 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					// 유저의 수업 정보를 담아줄 객체 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// 정보보안 트랙 기초 교과 이수 여부 판단 로직
					for (int i = 0 ; i < ArticleUIpanel.infoprotectBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.infoprotectBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.infoprotectBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.infoprotectBarr[i]);
		
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
							}else {
								count++;
								
								// 이수하지 않았다면
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 스트링으로 표시
									String too = Integer.toString(ArticleUIpanel.infoprotectBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.infoprotectBarr[i]);
		
									//빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					

					// 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// 정보보안 트랙 응용 교과 이수 여부 판단 로직
					for (int i = 0 ; i < ArticleUIpanel.infoprotectSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.infoprotectSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.infoprotectSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.infoprotectSarr[i]);
			
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 이수하지 않았다면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.infoprotectSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.infoprotectSarr[i]);

			
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}
					
					// 결과 화면을 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[7]) { // 데이터 사이언스 트랙 이수 여부 판단
					
					// 결과를 담을 화면 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					// 유저의 수업 정보를 담을 벡터 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					for (int i = 0 ; i < ArticleUIpanel.datascienceBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.datascienceBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.datascienceBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.datascienceBarr[i]);
			
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
								
							}else {
								count++;
								
								// 이수하지 않았다면
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 통해 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.datascienceBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.datascienceBarr[i]);
				
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					
					
					// 유저의 수업 정보를 담을 벡터 생성
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// 데이터 사이언스 트랙 응용 교과 이수 여부 판단 로직
					for (int i = 0 ; i < ArticleUIpanel.datascienceSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이라면
							if(ArticleUIpanel.datascienceSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.datascienceSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.datascienceSarr[i]);
		
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 이수하지 않았다면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.datascienceSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.datascienceSarr[i]);
									
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업을 위해 변수 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}


					// 결과 화면을 프레임에 추가
					viewFrame.add(artic);
					viewFrame.revalidate();
				
				}
				else if (butSrcTxt == sideTxt[8]) { // SW 교육 트랙 이수 여부 판단
					
					// 결과를 담아줄 화면 객체 생성
					ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
					artic.trackArticle();
					
					// 유저의 수업 정보를 담아줄 벡터 생성
					Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();

					// SW 교육 트랙 기초 교과 이수 여부 판단 로직
					for (int i = 0 ; i < ArticleUIpanel.sweduBarr.length;i++) {
						int count = 0;
						for(int j = 0 ; j < tempStudentinfo.size(); j++) {
							
							// 이수한 수업이라면 
							if(ArticleUIpanel.sweduBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to = Integer.toString(ArticleUIpanel.sweduBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								to = cl.numToSubject(ArticleUIpanel.sweduBarr[i]);
								
								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackBPane, to+"\n");
							}else {
								count++;
								
								// 이수하지 않았다면
								if(count == tempStudentinfo.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too = Integer.toString(ArticleUIpanel.sweduBarr[i]);
									ChangeLecture cl = new ChangeLecture();
									too = cl.numToSubject(ArticleUIpanel.sweduBarr[i]);
									
									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackBPane, too+"\n");
									
									// 다음 수업과의 비교를 위해 변수 초기화
									count = 0;
								} // inner if
							} // if - else
						} // inner for
					} // for
					//왼쪽 text
					

					// 유저의 수업 정보를 담기 위해 베터 생성
					Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();

					// SW 교육 트랙 응용교과 이수 여부를 판단하기 위한 로직
					for (int i = 0 ; i < ArticleUIpanel.sweduSarr.length;i++) {
						int count2 = 0;
						for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
							
							// 이수한 수업이 있다면
							if(ArticleUIpanel.sweduSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
								
								// 수업 넘버를 스트링으로 변환
								String to2 = Integer.toString(ArticleUIpanel.sweduSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								to2 = cl2.numToSubject(ArticleUIpanel.sweduSarr[i]);

								// 파란색으로 표시
								artic.ConvertBlueColor(artic.doc, artic.trackAPane, to2+"\n");
							}else {
								count2++;
								
								// 이수하지 않았다면
								if(count2 == tempStudentinfo2.size()) {
									
									// 수업 넘버를 스트링으로 변환
									String too2 = Integer.toString(ArticleUIpanel.sweduSarr[i]);
									ChangeLecture cl2 = new ChangeLecture();
									too2 = cl2.numToSubject(ArticleUIpanel.sweduSarr[i]);

									// 빨간색으로 표시
									artic.ConvertRedColor(artic.doc, artic.trackAPane, too2+"\n");
									
									// 다음 수업 비교를 위해 변수 초기화
									count2 = 0;
								} // inner if
							} // if - else
						} // inner for
					}					
					
					// 결과 화면을 프레임에 추가
					viewFrame.add(artic);
					
					viewFrame.revalidate();
				
				} // if -- else
				
			} // actionPerformed()
		});
	} // btnAction()

	
	// fidSidePanel
	// 피드백 메뉴에 대한 좌측 메뉴 버튼 이벤트 리스너 추가 메서드
	private void fidBtnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();

				
				//"HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육"
				if (butSrcTxt == sideTxt[0]) {
					// HCI 채널로 멀티채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "HCI");
					chat.appMain();
				} // 왼쪽 첫 번째 버튼
				
				else if (butSrcTxt == sideTxt[1]) {
					// MULTIMEDIA 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "MULTIMEDIA");
					chat.appMain();
				} // 왼쪽 두 번째 버튼
				
				else if (butSrcTxt == sideTxt[2]) {
					// IOT 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "IOT");
					chat.appMain();
				} // 왼쪽 세 번째 버튼
				
				else if (butSrcTxt == sideTxt[3]) {
					// SYSTEMAPPLICATION 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "SYSTEMAPPLICATION");
					chat.appMain();
				} // 왼쪽 네 번째 버튼
				
				else if (butSrcTxt == sideTxt[4]) {
					// AI 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "AI");
					chat.appMain();
				} // 왼쪽 다섯 번째 버튼
				
				else if (butSrcTxt == sideTxt[5]) {
					// VR 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "VR");
					chat.appMain();
				} // 왼쪽 여섯 번째 버튼
				
				else if (butSrcTxt == sideTxt[6]) {
					// SECURITY 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "SECURITY");
					chat.appMain();
				} // 왼쪽 일곱 번째 버튼
				
				else if (butSrcTxt == sideTxt[7]) {
					// DATASCIENCE 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "DATASCIENCE");
					chat.appMain();
				} // 왼쪽 여덟 번째 버튼
				
				else{
					// SWEDU 채널로 멀티 채팅 시작
					ChatControl chat = new ChatControl(new ChatData(), new ChatViewer(), "SWEDU");
					chat.appMain();
				} // 왼쪽 아홉 번째 버튼

			} // actionPerformed()
		}); // addActionListener
	} // fidBtnAction()
	
	// infoSidePanel
	// information 메뉴 클릭 시 좌측 메뉴의 버튼 들의 이벤트 추가 메서드
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
	// 로그아웃 버튼 처리 메서드
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
