package viewer.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ArticleUI extends JPanel{

	//레이아웃
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	//trackArticle
	private JLabel trackBlbl;
	private JLabel trackAlbl;
	protected JTextArea trackBText;
	protected JTextArea trackAText;
	
	//simulArticle
	//fidArticle
	protected JTextArea fidIdText;
	private JButton chatBtn;
	
	//infoArticle
	protected JTextField infoTrackText;
	protected JTextArea infoText;
	
	public ArticleUI() {
		setSize(668, 512);
		setVisible(true);
		infoArticle();
	}
	
	public void welcomeArticle() {
		
		super.setLayout(new BorderLayout());
		super.add(new JLabel("WELCOME"));
	} //welcomeArticle()
	
	
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

	} //trackArticle()

	public void simulArticle() {
	} //simulArticle()
	
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
		
	} //fidArticle()

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
		
		
	} //infoArticle()
	
	
}
