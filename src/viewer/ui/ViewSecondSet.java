package viewer.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import event.ButtonClickViewSecond;
import viewer.ViewFrame;

/*
 * 媛��옣 硫붿씤 �솕硫� MainFrame!
 * �긽�떒 硫붾돱, �꽕鍮꾧쾶�씠�뀡 硫붾돱 �룞�쟻 �깮�꽦 �븘�슂
 */
public class ViewSecondSet {

	// Default Swing
	private ViewFrame viewFrame;
	private JPanel p1, p2, p3;
	private ButtonClickViewSecond btnclick;
	private String topBar[] = { "트랙", "트랙시뮬레이션", "피드백", "INFO", "LogOut" };
	String[] sideTxt = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육", "사이버국방" };

	// sideBar
	CardLayout cardLayout;
	SidePanel sidePanel;

	public ViewSecondSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.panelSetting();
	}

	// setting
	private void panelSetting() {
		this.p1 = new JPanel();
		this.p2 = new JPanel();
		this.p3 = new JPanel();

		this.p1.setLayout(new GridLayout(1, 5));
		this.p1.setName("p1");
		this.btnBarSetting(this.p1, 5); // p1 �뙣�꼸�뿉 5媛� 踰꾪듉 異붽�
		this.btnAddListener(this.p1, btnclick);

//		this.p2.setLayout(new GridLayout(10, 1));
//		this.p2.setName("p2");
//		this.btnBarSetting(this.p2, 5); // p2 패널에 5개 버튼 추가
//		this.btnAddListener(this.p2, btnclick);

		// p2패널 - sideBar
		cardLayout = new CardLayout();
		sidePanel = new SidePanel();
		this.p2.setLayout(cardLayout);
		sidePanel.trackSide();
		this.p2.add(sidePanel.trackSidePanel, "tP");
		this.p2.add(sidePanel.simulSide(), "sP");
		this.p2.add(sidePanel.fidSide(), "fP");
		this.p2.add(sidePanel.infoSide(), "iP");
		this.p2.add(sidePanel.logOutSide(), "lP");

		cardLayout.show(p2, "tP");

		// this.p2.setVisible(false); // topBar(�듃�옓, �뵾�뱶諛�, INFO) �겢由��떆
		// setVisible(true)

		this.p3.setLayout(new GridLayout(1, 1));
		JScrollPane jsp = new JScrollPane(new JTextArea("TEST"));
		this.p3.add(jsp); // p3 �뙣�꼸�� article 遺�遺� (MAIN)

		// Main Frame Setting
		ArticleUIpanel au = new ArticleUIpanel();

		this.viewFrame.setLayout(new BorderLayout());
		this.viewFrame.add(this.p1, BorderLayout.NORTH);
		this.viewFrame.add(this.p2, BorderLayout.WEST);
		// this.viewFrame.add(this.p3, BorderLayout.CENTER);
		this.viewFrame.add(au, BorderLayout.CENTER);
	} // panelSetting()

	public void btnBarSetting(JPanel tempP, int btnNum) {
		JButton tempBtn[] = new JButton[btnNum];
		for (int i = 0; i < tempBtn.length; i++) {
			tempBtn[i] = new JButton(topBar[i]);
			tempP.add(tempBtn[i]);
		} // for
	} // btnBarSetting()

	// Inner Btn Action -> 媛꾪렪�븯寃� �슦�꽑 �씡紐낇븿�닔濡� 留뚮뱾�뼱�몺
	private void btnAddListener(JPanel panel, ButtonClickViewSecond btnclickEvent) {
		btnclickEvent = new ButtonClickViewSecond(panel);
		JButton[] btn = btnclickEvent.getButton();
		// adding Event to All Button
		for (int i = 0; i < btn.length; i++) {
			btn[i].addActionListener(btnclickEvent);
		} // for
	} // btn add Event

}
