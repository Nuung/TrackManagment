package viewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import controller.ChangeLecture;
import controller.StudentInfo;
import controller.StudentInfo.StudentSubject;
import javafx.application.Application;

public class ArticleUIpanel extends JPanel {

	// 레이아웃
	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	//welcomeArticle 에 사용할 컴포넌트
	protected JLabel welcomelbl;
	
	// trackArticle 에 사용할 컴포넌트
	private JLabel trackBlbl;
	private JLabel trackAlbl;
	protected JTextPane trackBPane;
	protected JTextPane trackAPane;
	protected SimpleAttributeSet set;
	protected Document doc;
	protected Font font;

	// simulArticle 에 사용할 컴포넌트
	protected JTextArea completeText;
	protected JTextArea inCompleteText;
	protected JTextArea topRankText;
	private JPanel addPanel; // 콤보박스 추가 할 패널
	protected JComboBox addCombo; // 추가할 교과
	
	// Side menu - subject value
	String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };
	ArrayList<String> addComboArray = new ArrayList<String>();

	// fidArticle 에 사용할 컴포넌트
	protected JTextArea fidIdText;
	private JButton chatBtn;

	// infoArticle 에 사용할 컴포넌트
	protected JTextField infoTrackText;
	protected JTextArea infoText;	
	private StudentInfo studentinfo; // 로그인한 학생 정보 덩어리 object
	
	// Real Simulation 연산 
	// 트랙별 교과목명들을 정수로 치환한 값들을 배열로 선언
	// ~Barr = 트랙 기초 교과를 의미
	// ~Sarr = 트랙 응용 교과를 의미
	static int totalReaching[][] = new int[9] [2]; // 각 트랙의 도달률을 계산하기 위해 선언한 배열
	static int[] hciBarr = {1,2,3};
	static int[] multimediaBarr = {16,1,17};
	static int[] iotBarr = {25,18,26};
	static int[] systemappBarr = {37,38,39};
	static int[] aiBarr = {48,26,40};
	static int[] virtualrealityBarr = {13,1,2};
	static int[] infoprotectBarr = {58,59,25};
	static int[] datascienceBarr = {40,26,50};
	static int[] sweduBarr = {76,3,42};
	static int[] hciSarr = {4,5,6,7,8,9,10,11,1,13,14,15};
	static int[] multimediaSarr = {18,19,29,21,22,4,23,24,12,13};
	static int[] iotSarr = {27,19,28,29,30,31,32,33,34,35,36};
	static int[] systemappSarr = {40,41,42,43,36,44,45,28,46,13,47};
	static int[] aiSarr = {4,21,42,43,36,44,45,28,46,13,47};
	static int[] virtualrealitySarr = {14,12,16,54,55,56,57,5,4,13,15};
	static int[] infoprotectSarr = {60,61,62,63,64,65,30,67,68,69,66};
	static int[] datascienceSarr = {51,70,48,71,72,73,74,75,2,17};
	static int[] sweduSarr = {77,8,24,78,79,80,81,40,82,47,83};
	
	// java fx frame (Application)
	// 시뮬레이션(이수율 차트로 출력)을 위해 선언한 객체
	SimulationFX simulFx;
	
	// 결과 화면을 보여주는 클래스이기 때문에 기본적으로 유저의 정보를 담고 있는 클래스를 매개변수로 받아온다.
	public ArticleUIpanel(StudentInfo studentinfo) {
		this.studentinfo = studentinfo;
	}

	// 로그인 했을 시 가장 처음 나타나는 화면
	public void welcomeArticle() {
		// JPanel을 상속 받았으므로 super 를 이용해서 레이아웃 지정
		super.setLayout(new BorderLayout());
		
		// 라벨 텍스트 및 폰트, 컬러 지정
		welcomelbl = new JLabel(this.studentinfo.getStudentName() + "님 환영합니다!!\n"+this.studentinfo.getStudentId());
		welcomelbl.setFont(new Font("맑은고딕", Font.BOLD, 20));
		welcomelbl.setForeground(Color.DARK_GRAY);
		
		// 라벨을 가운데로 위치 시킨다.
		welcomelbl.setHorizontalAlignment(JLabel.CENTER);
		
		// 라벨을 상위 컴포넌트에 추가
		super.add(welcomelbl);
		
		// 유저의 정보를 가져온다.
		this.studentinfo.gettingStudentInfo();
	} // welcomeArticle()

	// 트랙 메뉴 클릭 시 보여주는 화면을 구성한 메서드
	public void trackArticle() {
		
		// gridBagLayout 객체 생성
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		// 레이아웃 지정
		super.setLayout(gbl);

		// 트랙 기초 교과와 트랙 응용 교과를 나눠서 보여줄 것이기 때문에 라벨로 표시
		trackBlbl = new JLabel("트랙 기초 교과");
		trackAlbl = new JLabel("트랙 응용 교과");
		
		// 교과목명을 출력하기 위한 TextPane
		trackBPane = new JTextPane();
		trackAPane = new JTextPane();
		
		// 교과목명의 텍스트 속성 설정을 위해 선언
		set = new SimpleAttributeSet();
		StyleConstants.setBold(set, true);
		
		// textPane에 적용
		trackBPane.setCharacterAttributes(set, true);
	
		
		// 스크롤 적용
		JScrollPane trackBScroll = new JScrollPane(trackBPane);
		JScrollPane trackAScroll = new JScrollPane(trackAPane);
	
		// JTextPane 두개의 위치 및 할당 공간 설정, 상위 컴포넌트에 추가
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		super.add(trackBlbl, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 0;
		super.add(trackAlbl, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipady = 250;
		gbc.weightx = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		super.add(trackBScroll, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 1;
		super.add(trackAScroll, gbc);

	} // trackArticle()

	// -------------------------------------------------------------------------------------------------------------------------------------- // 
	// Track simulation setting method
	// 시뮬레이션 메뉴 클릭 시 나타나는 결과 화면을 구성한 메서드
	public void simulArticle() {
		
		// Layout and Componets
		// 트랙의 갯수만큼 라벨 생성
		JLabel tempOne[] = new JLabel[sideTxt.length];
		JLabel tempTwo[] = new JLabel[sideTxt.length];
		
		// 패널 3개 생성
		JPanel innerP[] = new JPanel[3];
		for (int i = 0; i < innerP.length; i++) {
			innerP[i] = new JPanel();
		}
		
		// 레이아웃 지정
		super.setLayout(new BorderLayout());
		innerP[0].setLayout(new GridLayout(9, 1));
		innerP[1].setLayout(new GridLayout(9, 1));
		innerP[2].setLayout(new GridLayout(2, 1));
		 
		// adding JButton to Panel3 (that located in right side)
		// 시뮬레이션 화면에서 우측에 두개 버튼 추가
		innerP[2].add(new JButton("새로고침"));
		innerP[2].add(new JButton("추가하기"));
		super.add(innerP[0], BorderLayout.WEST);
		super.add(innerP[1], BorderLayout.CENTER);
		super.add(innerP[2], BorderLayout.EAST); 
		
		// 이수율을 계산하기 위한 로직
		for(String subjectText : this.sideTxt) {
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[0]) { // HCI~ 트랙인 경우
				totalReaching[0] [0]= 0; totalReaching[0] [1]= 0; // 값 초기화   [0][0]은 기초 교과, [0][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 유저의 정보를 가져온다
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				
				// HCI~ 트랙 기초 교과에서 이수한 수업의 갯수 체크
				for (int i = 0 ; i < ArticleUIpanel.hciBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						
						// 이수했으면
						if(ArticleUIpanel.hciBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							
							// 이수했으므로 값 더해준다.
							totalReaching[0][0]++;
						}else {
							count++;
							
							// 이수하지 않았으면
							if(count == tempStudentinfo.size()) {
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// HCI~ 트랙 응용 교과에서 이수한 수업 갯수 체크
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.hciSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						
						// 이수했으면
						if(ArticleUIpanel.hciSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							
							// 이수했으므로 값 더해준다.
							totalReaching[0][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[1]) { // 멀티미디어 트랙인 경우
				totalReaching[1] [0]= 0; totalReaching[1] [1]= 0; // [1][0]은 필수 교과, [1][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.multimediaBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.multimediaBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							
							// 이수했으므로 값 더해준다.
							totalReaching[1][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.multimediaSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.multimediaSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[1][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[2]) { // IOT 트랙인 경우
				totalReaching[2] [0]= 0; totalReaching[2] [1]= 0; // [2][0]은 기초 교과, [2][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.iotBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.iotBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[2][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.iotSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.iotSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[2][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[3]) { // 시스템응용 트랙인 경우
				totalReaching[3] [0]= 0; totalReaching[3] [1]= 0; // [3][0]은 기초 교과, [3][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.systemappBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.systemappBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[3][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.systemappSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.systemappSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[3][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업을 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[4]) { // 인공지능 트랙인 경우
				totalReaching[4] [0]= 0; totalReaching[4] [1]= 0; // [4][0]은 기초 교과, [4][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.aiBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.aiBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[4][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업을 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.aiSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.aiSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[4][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[5]) { // 가상현실 트랙인 경우
				totalReaching[5] [0]= 0; totalReaching[5] [1]= 0; // [5][0]은 기초 교과, [5][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.virtualrealityBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.virtualrealityBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[5][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.virtualrealitySarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.virtualrealitySarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[5][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if			
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[6]) { // 정보보안 트랙인 경우
				totalReaching[6] [0]= 0; totalReaching[6] [1]= 0; // [6][0]은 기초 교과, [6][1]은 응용교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.infoprotectBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.infoprotectBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분

							
							// 이수했으므로 값 더해준다.
							totalReaching[6][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.infoprotectSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.infoprotectSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[6][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if			
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[7]) { // 데이터 사이언스 트랙인 경우
				totalReaching[7] [0]= 0; totalReaching[7] [1]= 0; // [7][0]은 기초 교과, [7][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.datascienceBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.datascienceBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[7][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.datascienceSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.datascienceSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[7][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if			
			// -------------------------------------------------------------------------------------------------------------------------------- //
			if (subjectText == sideTxt[8]) { // SW교육 트랙인 경우
				totalReaching[8] [0]= 0; totalReaching[8] [1]= 0; // [8][0]은 기초 교과, [8][1]은 응용 교과
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.sweduBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.sweduBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[8][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.sweduSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.sweduSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							
							// 이수했으므로 값 더해준다.
							totalReaching[8][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								
								// 다음 수업 비교를 위해 변수 초기화
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if			
		}// Large for
		
		// Sendding to JAVAFX Main thread the Val and Start (launch)
		// 도표 출력을 위한 객체 생성 및 실행
		if(this.simulFx == null) {
			this.simulFx = new SimulationFX();
			simulFx.main(sideTxt);
		}
		else {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			} // try - catch
		} // if - else
		
		// adding Label Components
		// java FX 말고 메인 화면에서도 텍스트로 이수율 출력을 위한 로직
		for (int i = 0; i < sideTxt.length; i++) {
			
			// 트랙 갯수만틈 라벨 생성
			tempOne[i] = new JLabel(sideTxt[i]);
			
			// 선언한 라벨들 추가
			innerP[0].add(tempOne[i]);
			tempTwo[i] = new JLabel("       ");
			String tempS = "";
			double tempPersent[] = simulFx.translaterReaching(); // FX 도표에서 이수율 수치 가져옴

			// 이수율에 비례한 만큼 스트링에 별을 추가해준다.
			for (int j = 0; j < totalReaching[i][0] + totalReaching[i][1]; j++) {
				tempS+=" ★ ★ ★";
			} // for
			
			// 이수율 수치 표시
			tempTwo[i].setText(tempS+"      ("+String.format("%.2f", tempPersent[i])+")  ");
			
			// 패널에 추가
			innerP[1].add(tempTwo[i]);
		} // for		
	} // simulArticle()

	// 피드백 메뉴 눌렀을 때 화면 구성 메서드
	public void fidArticle() {
		
		// 레이아웃 객체 생성
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		// 레이아웃 지정
		super.setLayout(gbl);

		// 화면에 컴포넌트 생성
		fidIdText = new JTextArea("선택한 트랙 이수한 학번");
		chatBtn = new JButton("채팅하기");

		// 컴포넌트 위치, 할당 공강 지정하여 패널에 추가
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		super.add(fidIdText, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.ipadx = 50;
		gbc.gridx = 0;
		gbc.gridy = 1;
		super.add(chatBtn, gbc);

	} // fidArticle()

	// information 정보 눌렀을 때 화면 구성 메서드
	public void infoArticle() {
		
		// 레이아웃 객체 생성
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		// 레이아웃 지정
		super.setLayout(gbl);

		// 컴포넌트 생성
		infoTrackText = new JTextField("선택한 트랙명");
		infoText = new JTextArea("트랙 설명, 장점, 유망직군");

		// 스크롤 추가
		JScrollPane scroll = new JScrollPane(infoText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// 컴포넌트 위치 및 할당 공간 지정하여 패널에 추가
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		gbc.ipadx = 200;
		gbc.ipady = 10;
		gbc.gridx = 0;
		gbc.gridy = 0;
		super.add(infoTrackText, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.ipadx = 800;
		gbc.ipady = 350;
		gbc.gridx = 0;
		gbc.gridy = 1;
		super.add(infoText, gbc);
		infoText.add(scroll);
		
	} // infoArticle()
	
	// 폰트 색을 변경해주기 위한 메서드, 수업의 이수 구분할 때 사용
	public void ConvertRedColor(Document tempDoc, JTextPane tempPane, String tempStr) {
		tempDoc = tempPane.getStyledDocument(); // JTextPane 의 스타일 속성을 가져온다.
		this.set = new SimpleAttributeSet(); // 속성을 지정해주기 위한 객체 생성
		this.font = new Font("Dialog", Font.BOLD, 16); // 폰트 객체 생성
		
		tempPane.setFont(font); // 인자로 받아온 JTextPane에 폰트 추가
		StyleConstants.setForeground(this.set, Color.red); // 폰트색을 빨간색으로 지정

		try {
			tempDoc.insertString(tempDoc.getLength(), tempStr, this.set); // 인자로 받아온 스트링에 지정한 속성을 적용
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //ConvertRedColor()
	
	// 폰트 색을 변경해주기 위한 메서드, 수업의 이수 구분할 때 사용
	public void ConvertBlueColor(Document tempDoc, JTextPane tempPane, String tempStr) {
		tempDoc = tempPane.getStyledDocument(); // JTextPane 의 스타일 속성을 가져온다.
		this.set = new SimpleAttributeSet(); // 속성을 지정해주기 위한 객체 생성
		this.font = new Font("Dejavu Sans", Font.BOLD, 16);// 폰트 객체 생성
		
		tempPane.setFont(font); // 인자로 받아온 JTextPane에 폰트 추가
		StyleConstants.setForeground(this.set, Color.blue); //폰트색을 파란색으로 지정
		
		try {
			tempDoc.insertString(tempDoc.getLength(), tempStr, this.set); // 인자로 받아온 스트링에 지정한 속성을 적용
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //ConvertRedColor()
	
	// 버튼 클릭 시 패널에 있는 UI 초기화를 해주기 위한 메소드
	public void resetArticleUI() {
		super.removeAll();
		super.revalidate();
		super.repaint();
	}
}
