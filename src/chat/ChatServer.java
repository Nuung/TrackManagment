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
	
	// ArrayList for managing Thread that connected Clients 
	ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
	

	// Object for Logger
	Logger logger;
	
	// Start the Server
	public void start() {
		logger = Logger.getLogger(this.getClass().getName());
		
		try {
			// make server socket
			ss = new ServerSocket(8888);
			logger.info("MultiChatServer start");
			
			// waiting the connection with infinte loop
			while(true) {
				s = ss.accept();
				ChatThread chat = new ChatThread(s); 
				chatThreads.add(chat); // add list that clients
				chat.start(); // thread start
			} // while
		} catch (Exception e) {
			logger.info("[MultiChatServer]Start() Exception 발생!!!!");
			e.printStackTrace();
		} // try -catch
	} // start()
	
	// 연결된 모든 클라이언트에 메시지 중계

	void msgSendAll(String msg, String channel) {
		for(ChatThread ct : chatThreads) {
			if(ct.m.getChannel().equals(channel))
				ct.outMsg.println(msg);
		} // for
	} // msgSendAll
	
	class ChatThread extends Thread {
		// for recived msg
		String msg;
		ChatMessage m = new ChatMessage();
		public Gson gson = new Gson(); // for JSON parser;
		
		public Socket s;
		// In out Stream
		private BufferedReader inMsg = null;
		private PrintWriter outMsg = null;
		public ChatThread(Socket s) {
			this.s = s;
		}
		
		@Override
		public void run() {
			
			boolean status = true;
			logger.info("Chat Thread Start ... ");
			
			try {
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
		ChatServer server = new ChatServer();
		server.start();
	} // main
	
} // Class MultiChatServer
