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
	ViewFrame viewFrame; // 구성된 UI를 담아줄 프레임 객체
	JFrame newFrame; // 회원가입 창을 띄워주기 위한 프레임
	private JPanel p1; // 시스템의 상단 메뉴를 구성하고 있는 패널
	private JButton btn1, btn2; // btn1 : 학생모드 / btn2 : 관리자 모드 버튼
	protected PlaceholderJTextField idText; // placeholder를 적용시킨 아이디를 입력하기 위한 텍스트 필드
	protected PlaceholderJPasswordField passText; // placeholder를 적용시킨 비밀번호를 입력하기 위한 텍스트 필드

	// For testing the Length of ID and PASS
	// 지정된 길이를 넘지 못하면 로그인&회원가입 불가
	protected boolean signUpIDLenCk = false;
	protected boolean signUpPASSLenCk = false;

	// 생성자
	public ViewFirstSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		try {
			this.btnSetting(); // 버튼 세팅 메서드 호출
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.viewFrame.add(this.p1);
	}

	// 버튼 세팅 메서드, 초기 화면에서 학생모드 버튼과 관리자모드 버튼을 생성 및 배치하는 작업을 하는 메서드
	private void btnSetting() throws IOException {
		this.p1 = new JPanel(); // 패널 인스턴스 생성
		this.p1.setLayout(new GridLayout(1, 3, 50, 0)); // Grid Layout을 적용
		this.p1.setBackground(Color.WHITE); // 뒷배경은 하얀색
		this.p1.setForeground(Color.WHITE); // 앞배경도 하얀색  => 삽입된 이미지와의 이질감을 줄이기 위해 모든 배경색을 통일하였다.
		
		// reading the Image file to Main Front
		// 이미지를 위한 라벨 생성
		JLabel ImagIcon = new JLabel(new ImageIcon("res/image_main.png"));

		this.btn1 = new JButton("학생"); // 학생모드 버튼 객체 생성
		this.btn2 = new JButton("관리자"); // 관리자모드 버튼 객체 생성
		this.p1.add(btn1); // 패널에 학생 버튼 추가
		this.p1.add(ImagIcon); // 패널에 이미지 삽입된 라벨 추가
		this.p1.add(btn2); // 패널에 관리자 버튼 추가
		// adding event
		this.btnAction(this.btn1); // 학생버튼 액션 리스너를 등록하기 위한 메서드 호출
	}

	// Private New Frame For Loging and SignUp, 로그인 및 회원가입 창을 띄워주는 작업을 하는 메서드
	void newFrameSet() {
		newFrame = new JFrame(); // 프레임 객체 생성
		JPanel tempP = new JPanel(); // 패널 객체 생성

		this.idText = new PlaceholderJTextField(""); // 아이디를 입력하기 위한 텍스트 필드 객체 생성
		idText.setPlaceholder("Student Num"); // placeholder 적용
		Font f = idText.getFont(); // 텍스트에 적용된 폰트 값을 가져옴
		idText.setFont(new Font(f.getName(), f.getStyle(), 40)); // 텍스트 필드에 폰트 지정
		idText.addKeyListener(new KeyAdapter() { // 키 리스너 등록
			@Override
			public void keyPressed(KeyEvent e) {
				if (idText.getText().length() >= 7) { // 입력된 텍스트의 길이가 7자 이상이면
					signUpIDLenCk = true; // 입력 가능한 아이디
					idText.setBackground(new Color(102, 255, 102)); // GREEN, 가능하므로 초록색으로 표시
				} else { // 7자 미만이면
					signUpIDLenCk = false; // 입력 불가능한 아이디
					idText.setBackground(new Color(204, 102, 102)); // RED, 불가능하므로 빨간색으로 표시
				} // inner if -else
			} // keyPressed()
		}); // idText Key input Action
		
		this.passText = new PlaceholderJPasswordField(""); // 비밀번호를 입력하기 위한 텍스트 필드 객체 생성
		passText.setPlaceholder("Passward"); // placeholder 지정
		passText.setFont(new Font(f.getName(), f.getStyle(), 40)); // 폰트 지정
		passText.addKeyListener(new KeyAdapter() { // 키 리스너 등록
			@Override
			public void keyPressed(KeyEvent e) {
				// 모든 정보를 입력하고 엔터키 입력을 하고, 정보에 대한 글자 수가 조건을 만족했을 경우
				if (e.getKeyCode() == KeyEvent.VK_ENTER && signUpPASSLenCk && signUpIDLenCk) {
					DBconnection dbcon = new DBconnection(); // DB 연결
					String passString = new String(passText.getPassword(), 0,
					passText.getPassword().length); // 패스워드 스트링 추출
					
					// 입력받은 아이디(학번) 값과 비밀번호 값으로 로그인 시도
					StudentInfo newStudent = dbcon.findUser(Integer.parseInt(idText.getText()), passString);
					
					// 결과 값이 존재하는 경우
					if(newStudent != null) { // login
						// 로그인 성공
						newFrame.dispose(); // 프레임을 닫는다.
						viewFrame.remove(p1); // delete 'p1' Panel
						new ViewSecondSet(viewFrame, newStudent); // Make Second Layout Setting
						// New Frame이 아니라, 기존에 있는 Frame Re Setting -> ReLoading
						viewFrame.revalidate(); // ReLoading
					} // inner if
					else {
						// 로그인 실패
						 JOptionPane.showMessageDialog(viewFrame, "아이디 또는 비밀번호를 확인해 주세요", "Message",
								 JOptionPane.ERROR_MESSAGE); 
					}
				} else {
					// 입력 길이 체크
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
		JButton loginBtn = new JButton("로그인"); // 로그인 버튼 객체 생성
		JButton signupBtn = new JButton("회원가입"); // 회원가입 버튼 객체 생성

		// adding event
		// 버튼에 액션 리스너를 추가해주는 메서드 호출
		this.btnAction(loginBtn);
		this.btnAction(signupBtn);

		newFrame.setSize(512, 512); // 프레임 크기 지정
		newFrame.setVisible(true); // setVisible 값 설정
		newFrame.setLayout(null); // 레이아웃 매니저 해제
		tempP.setBounds(100, 80, 300, 300); // 하위 컴포넌트의 크기 및 위치 지정
		tempP.setLayout(new GridLayout(4, 1)); // 하위 컴포넌트의 레이아웃을 Grid Layout으로 지정
		tempP.add(idText); // 아이디 입력하는 텍스트 필드 추가
		tempP.add(passText); // 비밀번호 입력하는 텍스트 필드 추가
		tempP.add(loginBtn); // 로그인 버튼 추가
		tempP.add(signupBtn); // 회원가입 버튼 추가
		newFrame.add(tempP); // 프레임에 패널 추가
	} // newFrameSet()

	// 각 버튼의 액션 이벤트 세팅과 명시
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {

				// getting btn text value
				Object source = ev.getSource();
				String butSrcTxt = ((AbstractButton) source).getText(); // 버튼의 텍스트 추출

				if (butSrcTxt == "학생") { // 학생인 경우
					// test, making new frame
					newFrameSet(); // 학생 로그인 화면을 띄워준다.
				} // STUDENT
				
				// 로그인 버튼을 클릭하고 아이디와 비밀번호가 7자 이상인 경우
				else if (butSrcTxt == "로그인" && signUpPASSLenCk && signUpIDLenCk) {
					DBconnection dbcon = new DBconnection(); // DB 연결
					String passString = new String(passText.getPassword(), 0,
					passText.getPassword().length); // 텍스트 필드에서 비밀번호 값 추출
					
					// 입력한 값으로 로그인 시도
					StudentInfo newStudent = dbcon.findUser(Integer.parseInt(idText.getText()), passString);
					if(newStudent != null) { // login
						// 로그인 성공
						newFrame.dispose();
						viewFrame.remove(p1); // 해당 패널을 닫고
						new ViewSecondSet(viewFrame, newStudent); // ViewSecondSet을 띄워준다.
						// New Frame이 아니라, 기존에 있는 Frame Re Setting -> ReLoading
						viewFrame.revalidate(); // ReLoading
					}
					else { // 로그인 실패
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
