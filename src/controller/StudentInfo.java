package controller;

import java.util.Vector;

public class StudentInfo {

	// inner class like Structer [ 구조체적 요소, id : 과목 쌍을 가지는 object ]
	protected class StudentSubject {
		private int studentid;
		private String subject;
		public StudentSubject(int studentid, String subject) {
			this.studentid = studentid;
			this.subject = subject;
		}
	}
	
	// member
	private int studentid;
	private String name;
	private String passward;
	protected Vector<StudentSubject> studentSubject; // 동시다발적으로 일어날 가능성 
	
	public StudentInfo() {
		
	}
	
}
