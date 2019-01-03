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
	
	// 뷰 클래스 참조 객체
	private final ChatViewer v;
	
	// 데이터 클래스 참조 객체
	private final ChatData chatData;
	
    // 소켓 연결을 위한 변수 선언
    private String ip = "127.0.0.1"; // 로컬에서 테스트 하기 위해 값 설정
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
    String id; // 대화명 input에 입력한 값을 저장하기 위한 변수
    String channel; // 채널 값을 저장하기 위한 변수
	
	public ChatControl(ChatData chatData, ChatViewer v, String channel) {
		this.logger = Logger.getLogger(this.getClass().getName());
		this.chatData = chatData;
		this.v = v;
		this.channel = channel;
	} // ChatControl 생성자
	
	// 컨트롤러 클래스의 메인 로직 부분, UI에서 발생한 이벤트를 위임받아 처리
	public void appMain() {

		// 데이터 객체에서 테이터 변화를 처리할 UI 객체 추가
		this.chatData.addObj(v.msgOut); // Adding UI object that dealing with the change of the data
		
		// UI 버튼 동작 리스너
		v.addButtonActionListener(new ActionListener() {

			// 리스너 자체에 대한 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object obj = e.getSource();
				
				// 각 버튼 이벤트 핸들링
				if(obj == v.getExitButton()) { // 종료 버튼 처리
					System.exit(0);
				} else if(obj == v.getLoginButton()) { 	// 로그인 버튼 클릭 시
					id = v.getIdInput().getText(); // 대화명 값 저장
					v.getChLabel().setText(channel + " 채널"); // label에 채널명 표시

					v.getOutLabel().setText(" 대화명 : " + id); // label에 대화명 표시
					v.getCardLayout().show(v.getTab(), "logout"); // 로그아웃 panel로 전환
					connectServer(); // 서버 연결 메서드 호출
				} else if(obj == v.getLogoutButton()) {	// 로그아웃 버튼 클릭시
					
					// 로그아웃 메세지 전송, gson 라이브러리를 통해 json 형식으로 변환하여 서버에 전송한다.
					outMsg.println(gson.toJson(new ChatMessage(id, "", "", "logout", channel)));

					v.getMsgOut().setText(""); // 대화창을 초기화시킨다.
					v.getCardLayout().show(v.getTab(), "login"); // 로그인 panel로 전환한다.
					outMsg.close();
					
					// 로그아웃 했으므로 소켓 및 입력 스트림 연결을 닫는다.
					try {
						inMsg.close();
						socket.close();
					} catch( IOException ex ) {
						ex.printStackTrace();
					} // try - catch
					status = false;
				} else if(obj == v.getMsgInput()) { // 메세지를 전달하는 경우
					
					// 메세지 전송, gson 라이브러리를 통해 json 형식으로 변경하여 전송
					outMsg.println(gson.toJson(new ChatMessage(id, "", v.getMsgInput().getText(), "msg", channel)));

//					chatData.refreshData(v.getMsgInput().getText());
					
					// 입력한 메세지를 전송함과 동시에 대화창 초기화
					v.getMsgInput().setText(""); 
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
            m = new ChatMessage(id,"","","login", channel);

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
        
        // 스레드를 실행하여 메세지를 수신하므로 status 값은 true로 변경
        this.status = true;
        
        while(this.status) {
            try{
            	
            	// 메세지 수신
                msg = inMsg.readLine();
                
                // 수신된 메세지 파싱
                m = gson.fromJson(msg, ChatMessage.class);

                // 채팅창에 메세지 표시
                chatData.refreshData(m.getId() + ">" + m.getMsg() + "\n");
               
                // 커서를 현재 대화 메시지에 보여줌
                v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
            } catch(IOException e) {
                logger.log(Level.WARNING,"[ChatViewer]메시지 스트림 종료!!");
            } // try - catch
        } // while()
        
        logger.info("[ChatViewer]" + thread.getName()+ " 메시지 수신 스레드 종료됨!!");
    } // run() 

    

} // Class MultiChatController
