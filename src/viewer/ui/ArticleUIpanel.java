package viewer.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ArticleUIpanel extends JPanel {

	// 레이아웃
	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	// trackArticle
	private JLabel trackBlbl;
	private JLabel trackAlbl;
	protected JTextArea trackBText;
	protected JTextArea trackAText;

	// simulArticle
	protected JTextArea completeText;
	protected JTextArea inCompleteText;
	protected JTextArea topRankText;
	private JPanel addPanel; // 콤보박스 추가 할 패널
	protected JComboBox addCombo; // 추가할 교과
	ArrayList<String> addArray = new ArrayList<String>();

	// fidArticle
	protected JTextArea fidIdText;
	private JButton chatBtn;

	// infoArticle
	protected JTextField infoTrackText;
	protected JTextArea infoText;	
	
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

	public ArticleUIpanel() {
		welcomeArticle();
	}

	public void welcomeArticle() {
		super.setLayout(new BorderLayout());
		super.add(new JLabel("WELCOME"));
	} // welcomeArticle()

	public void trackArticle() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		super.setLayout(gbl);

		trackBlbl = new JLabel("트랙 기초 교과");
		trackAlbl = new JLabel("트랙 응용 교과");
		trackBText = new JTextArea("트랙 기초 교과", 7, 20);
		trackAText = new JTextArea("트랙 응용 교과", 7, 20);

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
		super.add(trackBText, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 3;
		gbc.gridy = 1;
		super.add(trackAText, gbc);

	} // trackArticle()

	public void simulArticle() {

		super.setLayout(new GridLayout(2, 2));

		completeText = new JTextArea("이수한 트랙");
		inCompleteText = new JTextArea("불이수 트랙");
		topRankText = new JTextArea("트랙 랭킹 순으로");
		addPanel = new JPanel(); // 콤보박스 추가 할 패널

		addArray.add("임시로 추가할 교과");
		JComboBox<String> addCombo = new JComboBox<String>(addArray.toArray(new String[addArray.size()]));
		// addCombo = new JComboBox(); // 추가할 교과
		addPanel.setLayout(new BorderLayout());
		addPanel.setSize(100, 100);
		addPanel.add(addCombo);

		super.add(completeText);
		super.add(inCompleteText);
		super.add(addPanel);
		super.add(topRankText);

	} // simulArticle()

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
	
	// 버튼 클릭 시 패널에 있는 UI 초기화를 해주기 위한 메소드
	public void resetArticleUI() {
		super.removeAll();
		super.revalidate();
		super.repaint();
	}
}
