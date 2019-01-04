package viewer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ChangeLecture;
import controller.StudentInfo;
import controller.db.DBconnection;

import controller.db.DBconnection;


/* 엑셀 파일 처리를 위한 poi 라이브러리 import */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import viewer.ViewFrame;
import viewer.components.PlaceholderJPasswordField;
import viewer.components.PlaceholderJTextField;

/*
 * 회원가입 Frame
 */
public class ViewThirdSet {
	
	// Default Swing
	ViewFrame viewFrame;
	JFrame RevisedFrame; // 회원가입 창을 담아줄 프레임
	private JPanel p1;
	private JLabel fileText;
	
	// Name / student id / passwoard
	protected PlaceholderJTextField nameText, idText; // 이름, 학번을 입력할 텍스트 필드
	protected PlaceholderJPasswordField passText; // 비밀번호를 입력할 텍스트 필드
	
	// For testing the Length of ID and PASS
	protected boolean signUpIDLenCk = false;
	protected boolean signUpPASSLenCk = false;
	
	protected ArrayList<String> subjectList; // 기이수 강의 담아줄 ArrayList
	
	public ViewThirdSet(ViewFrame viewFrame) {
		this.subjectList = new ArrayList<String>();
		this.viewFrame = viewFrame;
		this.panelSetting();
		this.RevisedFrame.transferFocus();
	} // 생성자
	
	private void panelSetting() {
		
		this.RevisedFrame = new JFrame();
		p1 = new JPanel();
		this.nameText = new PlaceholderJTextField("");
		nameText.setPlaceholder("Name");
		this.idText = new PlaceholderJTextField("");
		idText.setPlaceholder("Student Num");
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
		passText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (passText.getPassword().length >= 7) {
					signUpPASSLenCk = true;
					passText.setBackground(new Color(102, 255, 102)); // GREEN
				} else {
					signUpPASSLenCk = false;
					passText.setBackground(new Color(204, 102, 102)); // RED
				} // inner if -else
			} // keyPressed()
		}); // passText Key input Action
		
		Font f = nameText.getFont();
		nameText.setFont(new Font(f.getName(), f.getStyle(), 35));
        idText.setFont(new Font(f.getName(), f.getStyle(), 35));
        passText.setFont(new Font(f.getName(), f.getStyle(), 35));

		JButton uploadBtn = new JButton("첨부파일");
		JButton signupBtn = new JButton("가입하기");
		this.fileText = new JLabel("");
		
		// Frame Setting
		RevisedFrame.setSize(512, 512);
		RevisedFrame.setVisible(true);
		RevisedFrame.setLayout(null);
		RevisedFrame.add(p1);
		p1.setBounds(100, 80, 300, 300);
		p1.setLayout(new GridLayout(6, 1));
		p1.add(nameText);
		p1.add(idText);
		p1.add(passText);
		p1.add(uploadBtn);
		p1.add(signupBtn);
		p1.add(fileText);
		
		this.btnAction(uploadBtn);
		this.btnAction(signupBtn);
	}
	
	// Button Event for Thrid set Frame
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener ( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		    	
				// getting btn text value
				Object source =  ev.getSource();
		        String butSrcTxt = ((AbstractButton) source).getText();
		    	
		        if(butSrcTxt == "첨부파일") {
		    		// Val for Reading Excel Files (XLSX 파일 리딩)
		    		FileInputStream fis;
		    		HSSFWorkbook workbook = null;
		    		
		        	// Open 다이얼로그 세팅 -> File 선택자
	    			JFileChooser choosed = new JFileChooser();
	    		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xls");
	    		    choosed.setFileFilter(filter);
	    		    int returnVal = choosed.showOpenDialog(RevisedFrame); 
	    		    
	    		    // Open 다이얼로그 -> 선택자로 가져온 파일, FileInputStream으로 엑셀파일 입력
	    		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    		       // JLabel Text Setting 
		    		       fileText.setVerticalAlignment(SwingConstants.CENTER);
		    		       fileText.setHorizontalAlignment(SwingConstants.CENTER);
		    		       fileText.setFont(new Font("Serif", Font.BOLD, 20));
		    		       fileText.setText("가져온 파일 : "+choosed.getSelectedFile().getName());
		    		       
		    		       try {
		    		    	   File f = choosed.getSelectedFile();
		    		    	   fis = new FileInputStream(f);
		    		    	   workbook = new HSSFWorkbook(fis);
		    		       } catch(Exception error) {
		    		    	   System.out.println(error);
		    		       } // try ~ catch
		    		    } // inner if
		    		
		    		//시트 수 (첫번째에만 존재하므로 0을 준다) -> 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		    		HSSFSheet sheet = workbook.getSheetAt(0);
		    		int rows = sheet.getPhysicalNumberOfRows(); // total 행의 수
		    		int rowindex=0; // 행을 가르키는 index value
		    		int columnindex=0; // 열을 가르키는 index value
		    		
		    		for(rowindex = 1; rowindex < rows; rowindex++){ // 각 행을 모두 읽는다
		    			HSSFRow row = sheet.getRow(rowindex);
		    			
		    		    if(row !=null){ // 행이 비어 있지 않으면
		    		        int cells = row.getPhysicalNumberOfCells(); // 행에 존재하는 total 셀 수
		    		        
		    		        for(columnindex = 3; columnindex <= 4; columnindex++){ // 셀 수 만큼 '열(컬럼값)'을 읽어온다
		    		            	
		    		            	if(row.getCell(4).getStringCellValue().toString().equals("전선") || row.getCell(4).getStringCellValue().toString().equals("전필")) {
		    		            		
		    		            		HSSFCell cell = row.getCell(columnindex); //셀값을 읽는다
				    		            String value = "";
				    		        		    		            
				    		            if(cell == null){ //셀이 빈값일경우를 위한 널체크
				    		                continue;
				    		            } else{ // 셀 타입별로 모두 다른 형태의 값이 된다 -> 셀 타입별로 모두 읽기
				    		                switch (cell.getCellType()){
				    		                case FORMULA:
				    		                    value = cell.getCellFormula();
				    		                    break;
				    		                case NUMERIC:
				    		                    value = cell.getNumericCellValue()+"";
				    		                    break;
				    		                case STRING:
				    		                    value = cell.getStringCellValue()+"";
				    		                    break;
				    		                case BLANK:
				    		                    value = cell.getBooleanCellValue()+"";
				    		                    break;
				    		                case ERROR:
				    		                    value = cell.getErrorCellValue()+"";
				    		                    break;
				    		                } // switch()
				    		            } // inner if - else

				    		            if(columnindex == 3) {// 수업명 출력 
				    		            	subjectList.add(value);
					    		           	System.out.print("교과목명 : "+value + " / ");
				    		            }
				    		            
					    		        else // 구분 출력(전필/전선)
					    		           	System.out.println("구분 : "+value);
		    		            } // gubun if
		    		        } // inner for
		    		    } // if
		    		} // for
		        } // if 첨부파일
		        else if(butSrcTxt == "가입하기" && signUpIDLenCk && signUpPASSLenCk && fileText.getText() != "") {
		        	// DB setting
		        	DBconnection dbcon = new DBconnection();
	                String passString = new String(passText.getPassword(), 0, passText.getPassword().length);
	                dbcon.registUser(nameText.getText(), Integer.parseInt(idText.getText()), passString);
	                new ChangeLecture(Integer.parseInt(idText.getText()), subjectList);
		        	dbcon.printList();
		        	
		        	// panel setting
			    	viewFrame.remove(p1); // delete 'p1' Panel
			    	StudentInfo newStudent = dbcon.findUser(Integer.parseInt(idText.getText()), passString);
			    	new ViewSecondSet(viewFrame, newStudent); // Make Second Layout Setting
			    	viewFrame.revalidate(); // ReLoading
			    	RevisedFrame.dispose();
		    	} // if - else
		    } // actionPerformed()
		}); // addActionListener
	} // btnAction()
}
