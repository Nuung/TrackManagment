package viewer.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import viewer.ViewFrame;

public class ViewFirstSet {

	// Default Swing
	ViewFrame viewFrame;
	JFrame newFrame;
	private JPanel p1;
	private JButton btn1, btn2;
	
	// 생성자
	public ViewFirstSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.btnSetting();
		this.viewFrame.setLayout(null);
		this.viewFrame.add(this.p1);
		this.p1.setBounds(100, 100, 800, 256);
	}
	
	// Private Panel Setting
	private void btnSetting() {
		this.p1 = new JPanel();
		this.p1.setLayout(new GridLayout(1, 2, 100, 0));
		
		this.btn1 = new JButton("학생");
		this.btn2 = new JButton("관리자");
		this.p1.add(btn1); this.p1.add(btn2);
		// adding event
		this.btnAction(this.btn1);
	}
	
	// Private New Frame For Loging and SignUp
	void newFrameSet() {
		newFrame = new JFrame();
		JPanel tempP = new JPanel();
		JTextField idText = new JTextField();
		JTextField passText = new JTextField();
		JButton loginBtn = new JButton("로그인");
		JButton signupBtn = new JButton("회원가입");
		
		// adding event
		this.btnAction(loginBtn);
		this.btnAction(signupBtn);
		
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
	} // newFrameSet()
	
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener ( new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	
				// getting btn text value
				Object source =  ev.getSource();
		        String butSrcTxt = ((AbstractButton) source).getText();
		    	
		        if(butSrcTxt == "학생") {
		    		// test, making new frame
		    		newFrameSet();
		        }
		        else if(butSrcTxt == "로그인") {
			    	viewFrame.remove(p1); // delete 'p1' Panel
			    	new ViewSecondSet(viewFrame); // Make Second Layout Setting
			    	viewFrame.revalidate(); // ReLoading
			    	newFrame.dispose();
		    	} 
		        else if(butSrcTxt == "회원가입") {
		        	viewFrame.remove(p1);
		        	new ViewThirdSet(viewFrame);
		        	viewFrame.revalidate();
		    		System.out.print("회원가입");
		    		newFrame.dispose();
		    	}
		    	
		    } // actionPerformed
		});
	} // btnAction()

}
