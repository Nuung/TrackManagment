package viewer.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import viewer.ViewFrame;
import event.ButtonClickViewSecond;

public class ViewSecondSet {
	
	// Default Swing
	private ViewFrame viewFrame;
	private JPanel p1, p2, p3;
	private ButtonClickViewSecond btnclick;
	private String topBar[] = {"트랙", "트랙시물레이션", "피드백", "INFO", "LogOut"};
	String trackName[] = {"HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육", "사이버국방"};
	
	
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
		this.topBarSetting(this.p1, 5);
		this.btnAddListener(this.p1, btnclick);
		
		this.p2.setLayout(new GridLayout(10, 1));
		this.p2.setName("p2");
		this.sideBarSetting(this.p2, 10);
		this.btnAddListener(this.p2, btnclick);
	//	this.p2.setVisible(false); // topBar(트랙, 피드백, INFO) 클릭시 setVisible(true)
		
		this.p3.setLayout(null);
		this.p3.add(new JTextArea());
		
		ArticleUI au = new ArticleUI();
		
		this.viewFrame.setLayout(new BorderLayout());
		this.viewFrame.add(this.p1, BorderLayout.NORTH);
		this.viewFrame.add(this.p2, BorderLayout.WEST);
	//	this.viewFrame.add(this.p3, BorderLayout.CENTER);
		this.viewFrame.add(au, BorderLayout.CENTER);	
	
	} // panelSetting()
	
	public void topBarSetting(JPanel tempP, int btnNum) {

		JButton tempBtn[] = new JButton[btnNum];

		for (int i = 0; i < tempBtn.length; i++) {
			tempBtn[i] = new JButton(topBar[i]);
			tempP.add(tempBtn[i]);
		} // for
	} // topBarSetting()

	public void sideBarSetting(JPanel tempP, int btnNum) {
		JButton tempBtn[] = new JButton[btnNum];

		for (int i = 0; i < tempBtn.length; i++) {
			tempBtn[i] = new JButton(trackName[i]);
			tempP.add(tempBtn[i]);
		} // for
	} // sideBarSetting()
	
	// Inner Btn Action -> 간편하게 우선 익명함수로 만들어둠
	private void btnAddListener(JPanel panel, ButtonClickViewSecond btnclickEvent) {
		
		btnclickEvent = new ButtonClickViewSecond(panel);
		JButton[] btn = btnclickEvent.getButton();
		
		// adding Event to All Button
		for(int i=0; i<btn.length; i++) {
			btn[i].addActionListener(btnclickEvent);
		} // for
		
	} // btn add Event

}
