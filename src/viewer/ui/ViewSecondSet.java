package viewer.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import viewer.ViewFrame;

/*
 * 가장 메인 화면 MainFrame!
 * 상단 메뉴, 네비게이션 메뉴 동적 생성 필요
 */

public class ViewSecondSet {

	// Default Swing
	private ViewFrame viewFrame;
	private ArticleUIpanel articleUi;
	protected JPanel p1, p2, p3;
	protected String topBar[] = { "트랙", "트랙시뮬레이션", "피드백", "INFO", "LogOut" };
	String[] sideTxt = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육", "사이버국방" };

	// sideBar
	protected CardLayout cardLayout;
	SidePanel sidePanel;
	ArticleUIpanel articleUIpanel;

	public ViewSecondSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.panelSetting();
	}

	// setting
	private void panelSetting() {
		this.p1 = new JPanel();
		this.p2 = new JPanel();
		// Main Frame Setting
		this.articleUi = new ArticleUIpanel();

		// p2패널 - sideBar
		cardLayout = new CardLayout();
		sidePanel = new SidePanel(this.viewFrame);

		this.p2.setLayout(cardLayout);
		sidePanel.trackSide();
		this.p2.add(sidePanel.trackSidePanel, "tP");
		this.p2.add(sidePanel.simulSide(), "sP");
		this.p2.add(sidePanel.fidSide(), "fP");
		this.p2.add(sidePanel.infoSide(), "iP");
		this.p2.add(sidePanel.logOutSide(), "lP");

		this.p1.setLayout(new GridLayout(1, 5));
		this.p1.setName("p1");
		this.btnBarSetting(this.p1, 5);

		// Main Frame Setting
		articleUIpanel = new ArticleUIpanel();

		this.viewFrame.setLayout(new BorderLayout());
		this.viewFrame.add(this.p1, BorderLayout.NORTH);
		this.viewFrame.add(this.p2, BorderLayout.WEST);
		this.viewFrame.add(articleUIpanel, BorderLayout.CENTER);

	} // panelSetting()

	public void btnBarSetting(JPanel tempP, int btnNum) {
		JButton tempBtn[] = new JButton[btnNum];
		for (int i = 0; i < tempBtn.length; i++) {
			tempBtn[i] = new JButton(topBar[i]);
			tempP.add(tempBtn[i]);

			// P1 패널 버튼에만 P2 패널 카드 레이아웃 조절 권한
			if (tempP.getName() == "p1") {
				btnAction(tempBtn[i]);
			} // if
		} // for
	} // btnBarSetting()

	// 각 버튼의 액션 이벤트 세팅과 명시
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();

				if (butSrcTxt == topBar[0]) {
					System.out.println("TRACK");
					articleUIpanel.resetArticleUI();
					articleUIpanel.trackArticle();
					cardLayout.show(p2, "tP");
				} // TRACK tP
				else if (butSrcTxt == topBar[1]) {
					System.out.println("TRACK SIMULATION");
					articleUIpanel.resetArticleUI();
					articleUIpanel.simulArticle();
					cardLayout.show(p2, "sP");
				} // TRACK SIMULATION sP
				else if (butSrcTxt == topBar[2]) {
					System.out.println("FEED BACK");
					articleUIpanel.resetArticleUI();
					articleUIpanel.fidArticle();
					cardLayout.show(p2, "fP");
				} // FEED BACK fP
				else if (butSrcTxt == topBar[3]) {
					System.out.println("INFO ");
					articleUIpanel.resetArticleUI();
					articleUIpanel.infoArticle();
					cardLayout.show(p2, "iP");
				} // INFO iP
				else if (butSrcTxt == topBar[4]) {
					System.out.println("LOGOUT");
					articleUIpanel.resetArticleUI();
					cardLayout.show(p2, "lP");
				} // LOGOUT lP
			} // actionPerformed()
		});
	} // btnAction()
}
