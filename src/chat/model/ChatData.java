package chat.model;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class ChatData {
	
	private JTextArea msgOut;
	
	/* 데이터를 변경할 때 업데이트 할 UI 컴포넌트를 등록하는 메서드 */
	public void addObj(JComponent comp) {
		this.msgOut = (JTextArea) comp;
	}
	
	/* 파라미터로 전달된 메세지 내용으로 UI 데이터를 업데이트, 채팅 메세지 창의 텍스트를 추가하는 작업 */
	public void refreshData(String msg) {
		this.msgOut.append(msg);
	}
	
}
