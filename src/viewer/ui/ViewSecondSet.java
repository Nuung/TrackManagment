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
import controller.StudentInfo;
import viewer.ChatViewer;
import viewer.ViewFrame;

/*
 * 가장 메인 화면 MainFrame!
 * 상단 메뉴, 네비게이션 메뉴 동적 생성 필요
 */

public class ViewSecondSet {

	// Default Swing
	private ViewFrame viewFrame; // 최상위 프레임과 연결시켜주기 위해 선언
	protected JPanel p1, p2, p3; // 메인 화면에서 상단 바, 좌측 메뉴, 결과를 보여줄 패널
	
	// 상단바 버튼 텍스트 값들에 대한 스트링 배열
	protected String topBar[] = { "트랙", "트랙시뮬레이션", "피드백", "INFO", "LogOut" };
	
	// 측면 메뉴에 있는 버튼들의 텍스트 값들에 대한 스트링 배열
	String[] sideTxt = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육", "사이버국방" };
	private StudentInfo studentinfo;
	
	protected CardLayout cardLayout;
	
	// sideBar, 측면 메뉴
	SidePanel sidePanel;
	
	// 버튼 클릭에 따른 결과 화면을 보여주는 클래스
	ArticleUIpanel articleUIpanel;

	public ViewSecondSet(ViewFrame viewFrame, StudentInfo studentinfo) {
		this.viewFrame = viewFrame;
		this.studentinfo = studentinfo;
		this.panelSetting();
	}

	// setting
	private void panelSetting() {
		this.p1 = new JPanel();
		this.p2 = new JPanel();

		// p2패널 - sideBar
		cardLayout = new CardLayout();
		sidePanel = new SidePanel(this.viewFrame, this.studentinfo);

		this.p2.setLayout(cardLayout); //  측면 메뉴가 상황에 따라 다르게 나타나야 하기 때문에 카드 레이아웃으로 지정
		sidePanel.trackSide(); // 초기에 측면 메뉴에서 트랙의 이름들로 구성된 버튼을 보여준다.
		
		// 카드 레이아웃 추가
		this.p2.add(sidePanel.trackSidePanel, "tP");
		this.p2.add(sidePanel.simulSide(), "sP");
		this.p2.add(sidePanel.fidSide(), "fP");
		this.p2.add(sidePanel.infoSide(), "iP");
		this.p2.add(sidePanel.logOutSide(), "lP");

		// 상단바 레이아웃 지정
		this.p1.setLayout(new GridLayout(1, 5));
		// 상단바 이름 지정
		this.p1.setName("p1");
		// 패널에 버튼 추가
		this.btnBarSetting(this.p1, 5);

		// Main Frame Setting, 결과화면에 대한 객체 생성
		articleUIpanel = new ArticleUIpanel(this.studentinfo);

		// 메인 화면의 레이아웃 구성
		this.viewFrame.setLayout(new BorderLayout());
		this.viewFrame.add(this.p1, BorderLayout.NORTH);
		this.viewFrame.add(this.p2, BorderLayout.WEST);
		this.viewFrame.add(articleUIpanel, BorderLayout.CENTER);
		
		// 초기에 보여지는 화면 세팅
		this.articleUIpanel.welcomeArticle();
	} // panelSetting()

	// 인자로 전달 받은 패널에, 인자로 전달 받은 정수 값 만큼 버튼을 생성 시키는 메서드
	public void btnBarSetting(JPanel tempP, int btnNum) {
		JButton tempBtn[] = new JButton[btnNum]; // 인자로 받아온 정수 값만큼 버튼 동적으로 생성
		for (int i = 0; i < tempBtn.length; i++) { // 버튼 하나하나씩 객체 생성
			tempBtn[i] = new JButton(topBar[i]);
			tempP.add(tempBtn[i]); // 패널에 추가

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

				if (butSrcTxt == topBar[0]) { // 상단 바 메뉴 중 '트랙' 버튼 클릭 시
					System.out.println("TRACK");
					articleUIpanel.resetArticleUI(); // 결과 화면 띄워주는 패널 초기화, 이전 UI와 앞으로 띄워줄 UI가 겹치지 않고 컴포넌트 중복 생성 방지
					articleUIpanel.trackArticle(); // 트랙 이수 정보를 보여줄 화면세팅 해주는 메서드
					cardLayout.show(p2, "tP"); // 해당 화면으로 레이아웃 전환
				} // TRACK tP
				else if (butSrcTxt == topBar[1]) { // 상단 바 메뉴 중 '트랙 시뮬레이션' 버튼 클릭 시 
					System.out.println("TRACK SIMULATION");
					articleUIpanel.simulArticle(); // 시뮬레이션 화면세팅 해주는 메서드
					cardLayout.show(p2, "sP"); // 해당 화면으로 레이아웃 전환
				} // TRACK SIMULATION sP
				else if (butSrcTxt == topBar[2]) { // 상단 바 메뉴 중 '피드백' 버튼 클릭 시
					System.out.println("FEED BACK");
					articleUIpanel.resetArticleUI(); // UI 리셋
					articleUIpanel.fidArticle(); // 피드배 화면세팅 해주는 메서드
					cardLayout.show(p2, "fP"); // 해당 화면으로 레이아웃 전환
				} // FEED BACK fP
				else if (butSrcTxt == topBar[3]) { // 상단 바 메뉴 중 'Information' 버튼 클릭 시
					System.out.println("INFO ");
					articleUIpanel.resetArticleUI(); // UI 리셋
					articleUIpanel.infoArticle(); // 정보 화면세팅 해주는 메서드
					cardLayout.show(p2, "iP"); // 해당 화면으로 레이아웃 전환
				} // INFO iP
				else if (butSrcTxt == topBar[4]) { // 상단 바 메뉴 중 '로그아웃' 버튼 클릭 시
					System.out.println("LOGOUT");
					articleUIpanel.resetArticleUI(); // UI 리셋
					cardLayout.show(p2, "lP"); // 해당 화면으로 레이아웃 전환
				} // LOGOUT lP
			} // actionPerformed()
		});
	} // btnAction()
}
