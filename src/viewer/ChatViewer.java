package viewer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatViewer extends JFrame {

	// Login Panel
	private JPanel loginPanel;
	// Login Button
	protected JButton loginButton;
	

	// Label for Ncikname 
	private JLabel inLabel;
	// Label for output Nickname
	protected JLabel outLabel;
	// Textfield for input Nickname
	protected JTextField idInput;
	
	// Logout Panel
	private JPanel logoutPanel;
	// Logout Button
	protected JButton logoutButton;
	

	JLabel chLabel; // 채널 명을 담아줄 라벨

	// Message input Panel
	private JPanel msgPanel;
	// Message input Textfield / OutPut Area
	protected JTextField msgInput;
	public JTextArea msgOut;
	private JScrollPane jsp; // Scroll Bar for Output
	// Exit button
	private JButton exitButton;
	
	// LayOut for View Changer
	protected Container tab;
	protected CardLayout cardLayout;
	
	// constructer
	public ChatViewer() {
		
		// main Frame Setting
		super(":: MULTI CHAT ::");
		
		// login panel
		loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		idInput = new JTextField(15);
		loginButton = new JButton("LOG IN");
		inLabel = new JLabel("NickName ");
		loginPanel.add(inLabel, BorderLayout.WEST);
		loginPanel.add(idInput, BorderLayout.CENTER);
		loginPanel.add(loginButton, BorderLayout.EAST);
		
		// logout panel
		logoutPanel = new JPanel();
		logoutPanel.setLayout(new BorderLayout());
		outLabel = new JLabel();
		logoutButton = new JButton("LOG OUT");
		logoutPanel.add(outLabel, BorderLayout.CENTER);
		logoutPanel.add(logoutButton, BorderLayout.EAST);
		
		// login or logout Select Panel
		tab = new JPanel();
		cardLayout = new CardLayout();
		tab.setLayout(cardLayout);
		tab.add(loginPanel, "login");
		tab.add(logoutPanel, "logout");
		cardLayout.show(tab, "login");
		
		// Message input Panel
		msgPanel = new JPanel();
		msgPanel.setLayout(new BorderLayout());
		msgInput = new JTextField();
    
		chLabel = new JLabel("채널");
		setExitButton(new JButton("EXIT"));
		msgPanel.add(chLabel, BorderLayout.NORTH);
		msgPanel.add(msgInput, BorderLayout.CENTER);
		msgPanel.add(getExitButton(), BorderLayout.EAST);
		
		// Message Output Panel
		msgOut = new JTextArea("", 10, 30);
		msgOut.setEditable(false); // cant edit the contents
		
		// Scroll Bar for Output
		jsp = new JScrollPane(msgOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		
		// Default Setting for Super (JFrame, adding all Elements)
		this.mainFrameSetting();
	}

	private void mainFrameSetting() {
		// main Frame Setting
		this.setSize(550, 300);
		this.setLayout(new BorderLayout());
		this.add(this.tab, BorderLayout.NORTH); // tab -> cardLayout -> button evetn [ change Show 'login' 'logout' ]
		this.add(this.jsp, BorderLayout.CENTER);
		this.add(this.msgPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void addButtonActionListener(ActionListener listener) {
		// Add action Event
		this.loginButton.addActionListener(listener);
		this.logoutButton.addActionListener(listener);
		this.getExitButton().addActionListener(listener);
		this.msgInput.addActionListener(listener);
	} // addButtonActionListener()

	// ----------------------------------- getter and setter ---------------------------------- //
	
	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JTextField getIdInput() {
		return idInput;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JTextField getMsgInput() {
		return msgInput;
	}

	public JTextArea getMsgOut() {
		return msgOut;
	}

	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public void setInLabel(JLabel inLabel) {
		this.inLabel = inLabel;
	}

	public void setOutLabel(JLabel outLabel) {
		this.outLabel = outLabel;
	}

	public void setIdInput(JTextField idInput) {
		this.idInput = idInput;
	}

	public void setLogoutPanel(JPanel logoutPanel) {
		this.logoutPanel = logoutPanel;
	}

	public void setLogoutButton(JButton logoutButton) {
		this.logoutButton = logoutButton;
	}

	public void setMsgPanel(JPanel msgPanel) {
		this.msgPanel = msgPanel;
	}

	public void setMsgInput(JTextField msgInput) {
		this.msgInput = msgInput;
	}

	public void setMsgOut(JTextArea msgOut) {
		this.msgOut = msgOut;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}

	public void setTab(Container tab) {
		this.tab = tab;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}

	public JLabel getInLabel() {
		return inLabel;
	}

	public JLabel getOutLabel() {
		return outLabel;
	}

	public JPanel getLogoutPanel() {
		return logoutPanel;
	}

	public JPanel getMsgPanel() {
		return msgPanel;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public Container getTab() {
		return tab;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	
	public JLabel getChLabel() {
		return chLabel;
	}

}
