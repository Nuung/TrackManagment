package viewer.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.db.DBconnection;
import viewer.ViewFrame;
/*
 * 가장 첫 화면 [ 학생 ] [ 관리자 ]
 * 클릭시 newFrame -> 로그인
 */
public class ViewFirstSet {

	// Default Swing
	ViewFrame viewFrame;
	JFrame newFrame;
	private JPanel p1;
	private JButton btn1, btn2;
	private PlaceholderJTextField idText, passText;
	
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
		
		this.idText = new PlaceholderJTextField("");
		idText.setPlaceholder("Student Num");
		Font f = idText.getFont();
		idText.setFont(new Font(f.getName(), f.getStyle(), 40));
		this.passText = new PlaceholderJTextField("");
		passText.setPlaceholder("Passward");
        passText.setFont(new Font(f.getName(), f.getStyle(), 40));
		JButton loginBtn = new JButton("로그인");
		JButton signupBtn = new JButton("회원가입");
	
		// adding event
		this.btnAction(loginBtn);
		this.btnAction(signupBtn);
		
		newFrame.setSize(512, 512);
		newFrame.setVisible(true);
		newFrame.setLayout(null);
		tempP.setBounds(100, 80, 300, 300);
		tempP.setLayout(new GridLayout(4, 1));
		tempP.add(idText);
		tempP.add(passText);
		tempP.add(loginBtn);
		tempP.add(signupBtn);
		newFrame.add(tempP);
	} // newFrameSet()
	
	// 각 버튼의 액션 이벤트 세팅과 명시
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
		        	DBconnection dbcon = new DBconnection();
		        	// TO DO
		        	
		        	
			    	viewFrame.remove(p1); // delete 'p1' Panel
			    	new ViewSecondSet(viewFrame); // Make Second Layout Setting			   
			    	// New Frame이 아니라, 기존에 있는 Frame Re Setting -> ReLoading
			    	viewFrame.revalidate(); // ReLoading
			    	newFrame.dispose();
		    	} 
		        else if(butSrcTxt == "회원가입") {
		        	viewFrame.remove(p1);
		        	// SignUp button --> 새로운 프레임 띄우기
		        	new ViewThirdSet(viewFrame); // -> 실제 이벤트 액션은 ThirdSet에서 명시
		    		newFrame.dispose();
		    	}
		    	
		    } // actionPerformed()
		}); // addActionListener
	} // btnAction()

}
