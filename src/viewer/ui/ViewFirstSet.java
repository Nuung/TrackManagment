package viewer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.StudentInfo;
import controller.db.DBconnection;
import viewer.ViewFrame;
import viewer.components.PlaceholderJPasswordField;
import viewer.components.PlaceholderJTextField;

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
	protected PlaceholderJTextField idText;
	protected PlaceholderJPasswordField passText;

	// For testing the Length of ID and PASS
	protected boolean signUpIDLenCk = false;
	protected boolean signUpPASSLenCk = false;

	// 생성자
	public ViewFirstSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		try {
			this.btnSetting();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		this.viewFrame.setLayout(null);
		this.viewFrame.add(this.p1);
//		this.p1.setBounds(100, 100, 800, 256);
	}

	// Private Panel Setting
	private void btnSetting() throws IOException {
		this.p1 = new JPanel();
		this.p1.setLayout(new GridLayout(1, 3, 50, 0));
		this.p1.setBackground(Color.WHITE);
		this.p1.setForeground(Color.WHITE);
		// reading the Image file to Main Front
		JLabel ImagIcon = new JLabel(new ImageIcon("res/image_main.png"));

		this.btn1 = new JButton("학생");
		this.btn2 = new JButton("관리자");
		this.p1.add(btn1);
		this.p1.add(ImagIcon);
		this.p1.add(btn2);
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
		idText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (idText.getText().length() >= 7) {
					signUpIDLenCk = true;
					idText.setBackground(new Color(102, 255, 102)); // GREEN
				} else {
					signUpIDLenCk = false;
					idText.setBackground(new Color(204, 102, 102)); // RED
				} // inner if -else
			} // keyPressed()
		}); // idText Key input Action
		
		this.passText = new PlaceholderJPasswordField("");
		passText.setPlaceholder("Passward");
		passText.setFont(new Font(f.getName(), f.getStyle(), 40));
		passText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && signUpPASSLenCk && signUpIDLenCk) {
					DBconnection dbcon = new DBconnection();
					String passString = new String(passText.getPassword(), 0,
					passText.getPassword().length);
					StudentInfo newStudent = dbcon.findUser(Integer.parseInt(idText.getText()), passString);
					if(newStudent != null) { // login
						// 로그인 성공
						newFrame.dispose();
						viewFrame.remove(p1); // delete 'p1' Panel
						new ViewSecondSet(viewFrame, newStudent); // Make Second Layout Setting
						// New Frame이 아니라, 기존에 있는 Frame Re Setting -> ReLoading
						viewFrame.revalidate(); // ReLoading
					} // inner if
				} else {
					if (passText.getPassword().length >= 7) {
						signUpPASSLenCk = true;
						passText.setBackground(new Color(102, 255, 102)); // GREEN
					} else {
						signUpPASSLenCk = false;
						passText.setBackground(new Color(204, 102, 102)); // RED
					} // inner if -else
				} // if - else
			} // keyPressed()
		}); // passText Key input Action
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
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {

				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText();

				if (butSrcTxt == "학생") {
					// test, making new frame
					newFrameSet();
				} // STUDENT
				else if (butSrcTxt == "로그인" && signUpPASSLenCk && signUpIDLenCk) {
					DBconnection dbcon = new DBconnection();
					String passString = new String(passText.getPassword(), 0,
					passText.getPassword().length);
					StudentInfo newStudent = dbcon.findUser(Integer.parseInt(idText.getText()), passString);
					if(newStudent != null) { // login
						// 로그인 성공
						newFrame.dispose();
						viewFrame.remove(p1); // delete 'p1' Panel
						new ViewSecondSet(viewFrame, newStudent); // Make Second Layout Setting
						// New Frame이 아니라, 기존에 있는 Frame Re Setting -> ReLoading
						viewFrame.revalidate(); // ReLoading
					}
					else 
					{ // 로그인 실패
					 JOptionPane.showMessageDialog(viewFrame, "아이디 또는 비밀번호를 확인해 주세요", "Message",
					 JOptionPane.ERROR_MESSAGE); 
					} // if - else
				} // LOGIN
				else if (butSrcTxt == "회원가입") {
					viewFrame.remove(p1);
					// SignUp button --> 새로운 프레임 띄우기
					new ViewThirdSet(viewFrame); // -> 실제 이벤트 액션은 ThirdSet에서 명시
					newFrame.dispose();
				} // SIGNUP

			} // actionPerformed()
		}); // addActionListener
	} // btnAction()

}
