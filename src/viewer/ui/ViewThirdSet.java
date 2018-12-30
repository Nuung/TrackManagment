package viewer.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	}
	private void panelSetting() {
		
		RevisedFrame = new JFrame();
		p1 = new JPanel();
		JTextField nameText = new JTextField();
		JTextField idText = new JTextField();
		JTextField passText = new JTextField();
		JButton uploadBtn = new JButton("첨부파일");
		JButton signupBtn = new JButton("가입하기");
		
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
	
	private void btnAction(JButton inBtn) {
		inBtn.addActionListener ( new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	
				// getting btn text value
				Object source =  ev.getSource();
		        String butSrcTxt = ((AbstractButton) source).getText();
		    	
		        if(butSrcTxt == "첨부파일") {
		    		// test, making new frame
		    		System.out.println("엑셀파일첨부");
		        }
		        else if(butSrcTxt == "가입하기") {
			    	viewFrame.remove(p1); // delete 'p1' Panel
			    	new ViewSecondSet(viewFrame); // Make Second Layout Setting
			    	viewFrame.revalidate(); // ReLoading
			    	RevisedFrame.dispose();
		    	} 
		    	
		    } // btnAction
		});
	} // btnAction()
	


}
