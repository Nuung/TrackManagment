package chat.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import chat.model.ChatData;
import chat.model.ChatMessage;
import viewer.ChatViewer;


public class ChatControl implements Runnable {
	
	// Refer object for View Class
	private final ChatViewer v;
	
	// Refer object for Data Class
	private final ChatData chatData;
	
    // 소켓 연결을 위한 변수 선언
    private String ip = "127.0.0.1";
    private Socket socket;
    private BufferedReader inMsg = null;
    private PrintWriter outMsg = null; 

    // 메시지 파싱을 위한 객체 생성
    Gson gson = new Gson();
    ChatMessage m;
    // 상태 플래그
    boolean status;
    // 로거 객체
    private Logger logger;
    // 메시지 수신 스레드
    Thread thread;
    String id; // values of idinput
	
	public ChatControl(ChatData chatData, ChatViewer v) {
		this.logger = Logger.getLogger(this.getClass().getName());
		this.chatData = chatData;
		this.v = v;
	}
	
	private void appMain() {
		this.chatData.addObj(v.msgOut); // Adding UI object that dealing with the change of the data
		v.addButtonActionListener(new ActionListener() {

			// 리스너 자체에 대한 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object obj = e.getSource();
				
				// 각 버튼 이벤트 핸들링
				if(obj == v.getExitButton()) { // 종료 버튼 처리
					System.exit(0);
				} else if(obj == v.getLoginButton()) { 	// Login Button Action
					id = v.getIdInput().getText();
					v.getOutLabel().setText(" 대화명 : " + id);
					v.getCardLayout().show(v.getTab(), "logout"); // Change the tab panel
					connectServer(); // connect to the server
				} else if(obj == v.getLogoutButton()) {	// Logout Button Action
					outMsg.println(gson.toJson(new ChatMessage(id, "", "", "logout")));
					v.getMsgOut().setText(""); // Clear the 대화창
					v.getCardLayout().show(v.getTab(), "login"); // Change the tab panel
					outMsg.close();
					
					// Logout --> 접속 종료와 메세지 스트림 종료
					try {
						inMsg.close();
						socket.close();
					} catch( IOException ex ) {
						ex.printStackTrace();
					} // try - catch
					status = false;
				} else if(obj == v.getMsgInput()) {
					// Sending the Message
					outMsg.println(gson.toJson(new ChatMessage(id, "", v.getMsgInput().getText(), "msg")));
//					chatData.refreshData(v.getMsgInput().getText());
					v.getMsgInput().setText(""); // Clear the 대화창
				} // if - else
			} // actionPerformed()
		}); // addButtonActionListener
	} // appMain
	
	// 서버에 연결하고 액션 처리
	public void connectServer() {
        try {
            // 소켓 생성
            socket = new Socket(ip, 8888);
            logger.log(Level.INFO,"[Client]Server 연결 성공!!");

            // 입출력 스트림 생성
            inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outMsg = new PrintWriter(socket.getOutputStream(),true);
            
            // 서버에 로그인 메시지 전달
            m = new ChatMessage(id,"","","login");
            outMsg.println(gson.toJson(m));
            
            // 메시지 수신을 위한 스레드 생성
            thread = new Thread(this);
            thread.start();
        } catch(Exception e) {
            logger.log(Level.WARNING, "[ChatViewer]connectServer() Exception 발생!!");
            e.printStackTrace();
        } // try - catch
    } // connectServer


     //메시지 수신을 독립적으로 처리하기 위한 스레드 실행
    @Override
	public void run() {

        // 수신 메시지 처리를 위한 변수
        String msg;
        this.status = true;
        
        while(this.status) {
            try{
                msg = inMsg.readLine(); // 메시지 수진 및 파싱 준비
                m = gson.fromJson(msg, ChatMessage.class);

                // MultiChatData 객체를 통해 데이터 갱신
                chatData.refreshData(m.getId() + ">" + m.getMsg() + "\n");
                // 커서를 현재 대화 메시지에 보여줌
                v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
            } catch(IOException e) {
                logger.log(Level.WARNING,"[ChatViewer]메시지 스트림 종료!!");
            } // try - catch
        } // while()
        
        logger.info("[ChatViewer]" + thread.getName()+ " 메시지 수신 스레드 종료됨!!");
    } // run() 

    // 프로그램 시작을 위한 메인 메서드
    public static void main(String[] args) {
        ChatControl app = new ChatControl(new ChatData(),new ChatViewer());
        app.appMain();
    } // main()
} // Class MultiChatController
