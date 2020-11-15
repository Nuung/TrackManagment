package chat.model;

public class ChatMessage {
	
	private String id; 	// 채팅에서 대화명
	private String passwd; 	// 비밀번호 값을 저장할 변수
	private String msg;		// 메세지 내용을 저장할 변수
	private String type;	// 메세지 타입을 저장하는 변수, login, msg, logout으로 구성되어 있으며, 이 값을 토대로 어떤 로직을 처리할 것인지 결정
	private String channel; // 채널 값을 저장할 변수, 여기서는 트랙이름으로 채널 값이 설정된다.
	
	public ChatMessage() {
		
	}
	
	public ChatMessage(String id, String passwd, String msg, String type, String channel) {

		this.id = id;
		this.passwd = passwd;
		this.msg = msg;
		this.type = type;
		this.channel = channel;

	}

	// ----------------------------------- getter and setter ---------------------------------- //
	
	public  String getType() {
		return this.type;
	}
	
	public String getId() {
		return id;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getMsg() {
		return msg;
	}
	
	public String getChannel() {
		return channel;
	}

}
