package chat.model;

public class ChatMessage {
	
	private String id; 				// id
	private String passwd; 	// passwoard
	private String msg;			// message for chatting
	private String type;			// shape of the message (login, logout, sendmsg)
	private String channel;
	
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
