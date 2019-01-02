package viewer.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import viewer.ViewFrame;

public class SidePanel {
	// Jpanle
	protected JPanel trackSidePanel;
	protected JPanel simulSidePanel;
	protected JPanel fidSidePanel;
	protected JPanel infoSidePanel;
	protected JPanel logOutSidePanel;
	JButton tempBtn[];
	protected ViewFrame viewFrame;
	// 이벤트처리
	protected String sideTxt[] = { "HCI&비쥬얼컴퓨팅", "멀티미디어", "사물인터넷", "시스템응용", "인공지능", "가상현실", "정보보호", "데이터사이언스", "SW교육" };

	public SidePanel(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		trackSidePanel = new JPanel();
		simulSidePanel = new JPanel();
		fidSidePanel = new JPanel();
		infoSidePanel = new JPanel();
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
	public void SideBarBtn(JPanel tempPanel) {
		tempBtn = new JButton[9];
		for (int i = 0; i < 9; i++) {
			tempBtn[i] = new JButton(sideTxt[i]);
			this.btnAction(tempBtn[i]);
			tempPanel.add(tempBtn[i]);
		} // inner for
	} // SideBarBtn()
	
	// 각 버튼의 액션 이벤트 세팅과 명시
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				ArticleUIpanel articleUi = new ArticleUIpanel();
				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();
				int tempnumber = 13011038;
				
				
				if (butSrcTxt == sideTxt[0]) {
					ArticleUIpanel artic = new ArticleUIpanel();
					artic.trackArticle();
					
					
					String jdbcDriver = "com.mysql.cj.jdbc.Driver";
					String jdbcUrl = "jdbc:mysql://localhost:3306/java?characterEncoding=UTF-8&serverTimezone=UTC";
					Connection conn;
					PreparedStatement pstmt;
					ResultSet rs;
					

					String sql ="";
					try {
						
						Class.forName(jdbcDriver); 
						conn = DriverManager.getConnection(jdbcUrl, "kingjw", "1066223gks!"); // url, username, password
			         
						sql = "select * from userinfo where student_number = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(2, tempnumber);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							for(int i = 0 ; i < ArticleUIpanel.hciBarr.length ; i++) {
								for(int j = 0 ; j < ArticleUIpanel.hciBarr.length ; j++) {
									if(ArticleUIpanel.hciBarr[i] != rs.getInt("student_number")) {
										String to = Integer.toString(rs.getInt("student_number"));
										artic.trackBText.setText(to);
									}
								}//안쪽for
							}//바깥쪽 for
							
						}// while
					}catch(Exception e) {
							System.out.print("error arraylist");
					}
					
					
					
					
					
					System.out.println(sideTxt[0]);
//					JTextArea firta = new JTextArea()
					//System.out.println(ArticleUIpanel.hciBarr[0]);
					
					//int[] hcib = new int[3];
					
					
					artic.trackAText.setText("bbbbbbb");
					viewFrame.add(artic);
					viewFrame.revalidate();
				}
				else if (butSrcTxt == sideTxt[1]) {
					System.out.println(sideTxt[1]);
					ArticleUIpanel artic = new ArticleUIpanel();
					artic.trackArticle();
					artic.trackBText.setText("11");
					artic.trackAText.setText("22");
					viewFrame.add(artic);
					viewFrame.revalidate();
//					articleUi.
				}
				else if (butSrcTxt == sideTxt[2]) {
					System.out.println(sideTxt[2]);
				}
				else if (butSrcTxt == sideTxt[3]) {
					System.out.println(sideTxt[3]);
				}
				else if (butSrcTxt == sideTxt[4]) {
					System.out.println(sideTxt[4]);
				}
				else if (butSrcTxt == sideTxt[5]) {
					System.out.println(sideTxt[5]);
				}
				else if (butSrcTxt == sideTxt[6]) {
					System.out.println(sideTxt[6]);
				}
				else if (butSrcTxt == sideTxt[7]) {
					System.out.println(sideTxt[7]);
				}
				else if (butSrcTxt == sideTxt[8]) {
					System.out.println(sideTxt[8]);
				} // if -- else
			} // actionPerformed()
		});
	} // btnAction()

	
} // SidePanel class
