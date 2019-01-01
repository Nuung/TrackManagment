package viewer.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import event.ButtonClickViewSecond;
import viewer.ViewFrame;

/*
 * 회원가입 Frame
 */
public class ViewThirdSet {
	
	// Default Swing
	ViewFrame viewFrame;
	JFrame RevisedFrame;
	private JPanel p1;
	
	public ViewThirdSet(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		this.panelSetting();
		this.RevisedFrame.transferFocus();
	} // 생성자
	
	private void panelSetting() {
		
		this.RevisedFrame = new JFrame();
		p1 = new JPanel();
		JTextField nameText = new JTextField();
		JTextField idText = new JTextField();
		JTextField passText = new JTextField();
		JButton uploadBtn = new JButton("첨부파일");
		JButton signupBtn = new JButton("가입하기");
		
		// Frame Setting
		RevisedFrame.setSize(512, 512);
		RevisedFrame.setVisible(true);
		RevisedFrame.setLayout(null);
		RevisedFrame.add(p1);
		p1.setBounds(100, 80, 300, 300);
		p1.setLayout(new GridLayout(5, 1));
		p1.add(nameText);
		p1.add(idText);
		p1.add(passText);
		p1.add(uploadBtn);
		p1.add(signupBtn);
		
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
		    		XSSFWorkbook workbook = null;
		    		
		        	// Open 다이얼로그 세팅 -> File 선택자
	    			JFileChooser choosed = new JFileChooser();
	    		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
	    		    choosed.setFileFilter(filter);
	    		    int returnVal = choosed.showOpenDialog(RevisedFrame); 
	    		    
	    		    // Open 다이얼로그 -> 선택자로 가져온 파일, FileInputStream으로 엑셀파일 입력
	    		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    		       System.out.println("You chose to open this file: " + choosed.getSelectedFile().getName());
		    		       try {
		    		    	   File f = choosed.getSelectedFile();
		    		    	   fis = new FileInputStream(f);
		    		    	   workbook = new XSSFWorkbook(fis);
		    		       } catch(Exception error) {
		    		    	   System.out.println(error);
		    		       } // try ~ catch
		    		    } // inner if
		    		
		    		//시트 수 (첫번째에만 존재하므로 0을 준다) -> 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		    		XSSFSheet sheet = workbook.getSheetAt(0);
		    		int rows = sheet.getPhysicalNumberOfRows(); // total 행의 수
		    		int rowindex=0; // 행을 가르키는 index value
		    		int columnindex=0; // 열을 가르키는 index value
		    		
		    		for(rowindex = 1; rowindex < rows; rowindex++){ // 각 행을 모두 읽는다
		    			XSSFRow row = sheet.getRow(rowindex);
		    			
		    		    if(row !=null){ // 행이 비어 있지 않으면
		    		        int cells = row.getPhysicalNumberOfCells(); // 행에 존재하는 total 셀 수
		    		        for(columnindex = 3; columnindex <= 4; columnindex++){ // 셀 수 만큼 '열(컬럼값)'을 읽어온다
		    		            	
		    		            	if(row.getCell(4).getStringCellValue().toString().equals("전선") || row.getCell(4).getStringCellValue().toString().equals("전필")) {
		    		            		
		    		            		XSSFCell cell = row.getCell(columnindex); //셀값을 읽는다
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
				    		            
				    		            	
				    		            if(columnindex == 3) 
					    		           	System.out.print("교과목명 : "+value + " / ");
					    		        else
					    		           	System.out.println("구분 : "+value);
		    		            		
		    		            } // gubun if
				    		        		

		    		        } // inner for
		    		    } // if
		    		} // for
		        } // if 첨부파일
		        else if(butSrcTxt == "가입하기") {
			    	viewFrame.remove(p1); // delete 'p1' Panel
			    	new ViewSecondSet(viewFrame); // Make Second Layout Setting
			    	viewFrame.revalidate(); // ReLoading
			    	RevisedFrame.dispose();
		    	} // if - else
		    	
		    } // actionPerformed()
		}); // addActionListener
	} // btnAction()
	


}
