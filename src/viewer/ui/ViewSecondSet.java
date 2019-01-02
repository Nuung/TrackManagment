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
	private String topBar[] = { "�듃�옓", "�듃�옓�떆臾쇰젅�씠�뀡", "�뵾�뱶諛�", "INFO", "LogOut" };
	String trackName[] = { "HCI&鍮꾩�ъ뼹而댄벂�똿", "硫��떚誘몃뵒�뼱", "�궗臾쇱씤�꽣�꽬", "�떆�뒪�뀥�쓳�슜", "�씤怨듭��뒫", "媛��긽�쁽�떎", "�젙蹂대낫�샇",
			"�뜲�씠�꽣�궗�씠�뼵�뒪", "SW援먯쑁", "�궗�씠踰꾧뎅諛�" };

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
