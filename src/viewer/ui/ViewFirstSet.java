package viewer.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import viewer.ViewFrame;

public class ViewFirstSet {

	// Default Swing
	private ViewFrame viewFrame;
	private JPanel p1;
	private JButton btn1, btn2;
	
	// 생성자
	public ViewFirstSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.btnSetting();
		this.viewFrame.setLayout(null);
		this.viewFrame.add(this.p1);
		this.p1.setBounds(100, 100, 800, 256);
		
		// test, making new frame
		this.newFrameSet();
	}
	
	// Private Panel Setting
	private void btnSetting() {
		this.p1 = new JPanel();
		this.p1.setLayout(new GridLayout(1, 2, 100, 0));
		
		this.btn1 = new JButton("학생");
		this.btn2 = new JButton("관리자");
		this.p1.add(btn1); this.p1.add(btn2);
	}
	
	// Private New Frame For Loging and SignUp
	private void newFrameSet() {
		JFrame newFrame = new JFrame();
		JPanel tempP = new JPanel();
		JTextField idText = new JTextField();
		JTextField passText = new JTextField();
		JButton loginBtn = new JButton("로그인");
		JButton signupBtn = new JButton("회원가입");
		
		newFrame.setSize(512, 512);
		newFrame.setVisible(true);
		newFrame.setLayout(null);
		newFrame.add(tempP);
		tempP.setBounds(100, 80, 300, 300);
		tempP.setLayout(new GridLayout(4, 1));
		tempP.add(idText);
		tempP.add(passText);
		tempP.add(loginBtn);
		tempP.add(signupBtn);
	}

}
