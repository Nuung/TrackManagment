package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.Gson;

import chat.model.ChatMessage;

public class ChatServer {

	// Server Socket and Client's connection Socket
	private ServerSocket ss = null;
	private Socket s = null;
	
	// 연결된 클라이언트 스레드를 관리하는 ArrayList 
	ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
	

	// Object for Logger
	Logger logger;
	
	// 서버의 메인 실행 메서드, 클라이어늩 연결 및 스레드 생성 처리
	public void start() {
		logger = Logger.getLogger(this.getClass().getName());
		
		try {
			// 서버 소켓 생성
			ss = new ServerSocket(8888);
			logger.info("MultiChatServer start");
			
			// 무한루프 돌리면서 클라이언트 연결 대기
			while(true) {
				s = ss.accept();
				
				// 연결된 클라이언트에 대해 스레드 클래스 생성
				ChatThread chat = new ChatThread(s); 
				
				// ArrayList에 생성된 클래스 추가
				chatThreads.add(chat);
				
				// 스레드 시작
				chat.start();
			} // while
		} catch (Exception e) {
			logger.info("[MultiChatServer]Start() Exception 발생!!!!");
			e.printStackTrace();
		} // try -catch
	} // start()
	
	
	// 연결된 모든 클라이언트에 메시지 중계

	void msgSendAll(String msg, String channel) {
		for(ChatThread ct : chatThreads) {
			// 인자로 받아온 채널 값을 통해 해당 채널로 메세지 전송
			if(ct.m.getChannel().equals(channel))
				ct.outMsg.println(msg);
		} // for
	} // msgSendAll
	
	
	/* 각 클라이언트와 연결을 유지하며, 메세지 송수신을 담당하는 스레드 클래스 */
	class ChatThread extends Thread {
		
		String msg; // 수신 메세지 및 파싱 메세지 처리를 위한 변수
		ChatMessage m = new ChatMessage(); // 메세지 객체
		
		// json 파서를 위해 gson 라이브러리를 사용
		public Gson gson = new Gson();
		
		// 서버 연결을 위한 소켓
		public Socket s;
		
		// 입출력 스트림
		private BufferedReader inMsg = null;
		private PrintWriter outMsg = null;
		
		
		public ChatThread(Socket s) {
			this.s = s;
		} // ChatThread 생성자
		
		// 스레드 동작 메소드, 생성된 각 스레드에서 따로 동작
		@Override
		public void run() {
			
			// 스레드 상태를 나타내기 위한 변수
			boolean status = true;
			logger.info("Chat Thread Start ... ");
			
			try {
				
				// 입출력 스트림 초기화, 안해주면 NullPointer 에러 발생
				this.inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
				this.outMsg = new PrintWriter(s.getOutputStream(), true);
				
				// 상태정보가 true이면 루프를 돌면서 사용자에게서 수신된 메시지 처리
				while(status) {
					try {
						msg = inMsg.readLine(); // 수신된 메시지를 저장 하고
					} catch (IOException e) {
						e.printStackTrace();
					} // try - catch
					
					m = gson.fromJson(msg, ChatMessage.class); // 저장된 메시지를 Json -> 에서 -> Message object로 맵핑
			
					if(m.getType().equals("logout")) { //수신한 메시지가 Logout 일때
						chatThreads.remove(this);
						msgSendAll(gson.toJson(new ChatMessage(m.getId(), "", "님이 종료했습니다.", "server", m.getChannel())), m.getChannel());

						// 해당 클라이언트 스레드 종료로 status를 false로 설정
						status = false;
					} // if
					else if(m.getType().equals("login")) { //수신한 메시지가 LogIn 일때
						msgSendAll(gson.toJson(new ChatMessage(m.getId(), "", "님이 로그인했습니다.", "server", m.getChannel())), m.getChannel());
					}
					else { // 그 밖의 메시지 일때 -> 바로 샌딩
						msgSendAll(msg, m.getChannel());
					} // if - else
				} // while
			}catch (IOException e1) {
					e1.printStackTrace();
			} // try - catch
			
			// while loop 벗어나면 클라이언트 연결이 종료 --> 스레드 인터럽트
			this.interrupt();
			logger.info(this.getName() + " 종료됨!!");
		} // run()
	} // Inner Class ChatThread
	
	public static void main(String[] args) {
		
		// 메인 메서드에서 서버를 실행시키기 위해 ChatServer 인스턴스 생성
		ChatServer server = new ChatServer();
		
		// 서버 실행
		server.start();
	} // main
	
} // Class MultiChatServer
