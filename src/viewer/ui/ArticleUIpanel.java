package viewer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class ArticleUIpanel extends JPanel {

	// 레이아웃
	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	//welcomeArticle
	protected JLabel welcomelbl;
	
	// trackArticle
	private JLabel trackBlbl;
	private JLabel trackAlbl;
//	protected JTextArea trackBText;
//	protected JTextArea trackAText;
	protected JTextPane trackBPane;
	protected JTextPane trackAPane;
	protected SimpleAttributeSet set;
	protected Document doc;

	// simulArticle
	protected JTextArea completeText;
	protected JTextArea inCompleteText;
	protected JTextArea topRankText;
	private JPanel addPanel; // 콤보박스 추가 할 패널
	protected JComboBox addCombo; // 추가할 교과
	
	// Side menu - subject value
	String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };
	ArrayList<String> addComboArray = new ArrayList<String>();

	// fidArticle
	protected JTextArea fidIdText;
	private JButton chatBtn;

	// infoArticle
	protected JTextField infoTrackText;
	protected JTextArea infoText;	
	private StudentInfo studentinfo; // 로그인한 학생 정보 덩어리 object
	
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
	static int[] infoprotectSarr = {60,611,622,63,64,65,30,67,68,69,66};
	static int[] datascienceSarr = {51,70,48,71,72,73,74,75,2,17};
	static int[] sweduSarr = {77,8,24,78,79,80,81,40,82,47,83};
	static ArrayList<int[]> totalSubjectNumber = new ArrayList<int[]>();
	
	public ArticleUIpanel(StudentInfo studentinfo) {
		this.studentinfo = studentinfo;
		welcomeArticle();
	}

	public void welcomeArticle() {
		super.setLayout(new BorderLayout());
		welcomelbl = new JLabel("WELCOME");

		super.add(welcomelbl);
		this.studentinfo.gettingStudentInfo();
	} // welcomeArticle()

	public void trackArticle() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		super.setLayout(gbl);

		trackBlbl = new JLabel("트랙 기초 교과");
		trackAlbl = new JLabel("트랙 응용 교과");
		trackBPane = new JTextPane();
		trackAPane = new JTextPane();
		
		set = new SimpleAttributeSet();
		StyleConstants.setBold(set, true);
		
		trackBPane.setCharacterAttributes(set, true);
	
		JScrollPane trackBScroll = new JScrollPane(trackBPane);
		JScrollPane trackAScroll = new JScrollPane(trackAPane);
	     
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

	// Track simulation setting method
	public void simulArticle() {
		
		// Layout and Componets
		super.setLayout(new GridLayout(2, 2));
		completeText = new JTextArea("이수한 트랙");
		inCompleteText = new JTextArea("불이수 트랙");
		topRankText = new JTextArea("트랙 랭킹 순으로");
		addPanel = new JPanel(); // 콤보박스 추가 할 패널


		addComboArray.add("추가 선택할 과목");
		this.addCombo = new JComboBox<String>(addComboArray.toArray(new String[addComboArray.size()]));
		
		addPanel.setLayout(new BorderLayout());
		addPanel.setSize(100, 100);
		addPanel.add(addCombo);
		
		super.add(completeText);
		super.add(inCompleteText);
		super.add(addPanel);
		super.add(topRankText);
		
		// Real Simulation 연산 
		int totalReaching[][] = new int[9] [2]; // 각 트랙의 도달률 
		
		for(String subjectText : this.sideTxt) {
			
			if (subjectText == sideTxt[0]) {
				totalReaching[0] [0]= 0; totalReaching[0] [1]= 0;
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.hciBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.hciBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							String to = Integer.toString(ArticleUIpanel.hciBarr[i]);
							ChangeLecture cl = new ChangeLecture();
							to = cl.numToSubject(ArticleUIpanel.hciBarr[i]);
							totalReaching[0][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								String too = Integer.toString(ArticleUIpanel.hciBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								too = cl.numToSubject(ArticleUIpanel.hciBarr[i]);
								count = 0;
							} // inner if
						} // if - else
					} // inner for
				} // for
				// 선택 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo2 = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.hciSarr.length;i++) {
					int count2 = 0;
					for(int j = 0 ; j < tempStudentinfo2.size(); j++) {
						if(ArticleUIpanel.hciSarr[i] == tempStudentinfo2.get(j).getLectureNum()) {
							// 이수 부분
							String to2 = Integer.toString(ArticleUIpanel.hciSarr[i]);
							ChangeLecture cl2 = new ChangeLecture();
							to2 = cl2.numToSubject(ArticleUIpanel.hciSarr[i]);
							totalReaching[0][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								String too2 = Integer.toString(ArticleUIpanel.hciSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								too2 = cl2.numToSubject(ArticleUIpanel.hciSarr[i]);
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
			if (subjectText == sideTxt[1]) {
				totalReaching[1] [0]= 0; totalReaching[1] [1]= 0;
				ArticleUIpanel artic = new ArticleUIpanel(studentinfo);
				artic.trackArticle();
				
				// 필수 이수 과목 체킹
				Vector<StudentSubject> tempStudentinfo = studentinfo.getStudentSubject();
				for (int i = 0 ; i < ArticleUIpanel.multimediaBarr.length;i++) {
					int count = 0;
					for(int j = 0 ; j < tempStudentinfo.size(); j++) {
						if(ArticleUIpanel.multimediaBarr[i] == tempStudentinfo.get(j).getLectureNum()) {
							// 이수 한 부분
							String to = Integer.toString(ArticleUIpanel.multimediaBarr[i]);
							ChangeLecture cl = new ChangeLecture();
							to = cl.numToSubject(ArticleUIpanel.multimediaBarr[i]);
							totalReaching[1][0]++;
						}else {
							count++;
							if(count == tempStudentinfo.size()) {
								// 미이수 부분
								String too = Integer.toString(ArticleUIpanel.multimediaBarr[i]);
								ChangeLecture cl = new ChangeLecture();
								too = cl.numToSubject(ArticleUIpanel.multimediaBarr[i]);
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
							String to2 = Integer.toString(ArticleUIpanel.multimediaSarr[i]);
							ChangeLecture cl2 = new ChangeLecture();
							to2 = cl2.numToSubject(ArticleUIpanel.multimediaSarr[i]);
							totalReaching[1][1]++;
						}else {
							count2++;
							if(count2 == tempStudentinfo2.size()) {
								// 미이수 부분
								String too2 = Integer.toString(ArticleUIpanel.multimediaSarr[i]);
								ChangeLecture cl2 = new ChangeLecture();
								too2 = cl2.numToSubject(ArticleUIpanel.multimediaSarr[i]);
								count2 = 0;
							} // inner if
						} // if - else
					} // inner for
				}// for
			} // Large if
		}// Large for
	} // simulArticle()

	// 피드백 
	public void fidArticle() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		super.setLayout(gbl);

		fidIdText = new JTextArea("선택한 트랙 이수한 학번");
		chatBtn = new JButton("채팅하기");

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

	public void infoArticle() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		super.setLayout(gbl);

		infoTrackText = new JTextField("선택한 트랙명");
		infoText = new JTextArea("트랙 설명, 장점, 유망직군");

		JScrollPane scroll = new JScrollPane(infoText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
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
	
	public void ConvertRedColor(Document tempDoc, JTextPane tempPane, String tempStr) {
		tempDoc = tempPane.getStyledDocument();
		this.set = new SimpleAttributeSet();
		StyleConstants.setForeground(this.set, Color.red);
		try {
			tempDoc.insertString(tempDoc.getLength(), tempStr, this.set);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //ConvertRedColor()
	
	public void ConvertBlueColor(Document tempDoc, JTextPane tempPane, String tempStr) {
		tempDoc = tempPane.getStyledDocument();
		this.set = new SimpleAttributeSet();
		StyleConstants.setForeground(this.set, Color.blue);
		try {
			tempDoc.insertString(tempDoc.getLength(), tempStr, this.set);
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
