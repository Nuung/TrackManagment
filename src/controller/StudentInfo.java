package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.db.DBconnection;

public class StudentInfo {

	// inner class like Structer [ 구조체적 요소, id : 과목 쌍을 가지는 object ]
	public class StudentSubject {
		private int studentid;
		private int lecture_num;
		public StudentSubject(int studentid, int lecture_num) {
			this.studentid = studentid;
			this.lecture_num = lecture_num;
		}
		
		public void printingStudent() {
			System.out.println(this.studentid+", "+this.lecture_num);
		}
		public int getLectureNum() {
			return this.lecture_num;
		}
	}
	
	// member
	private int studentid;
	private String name;
	protected Vector<StudentSubject> studentSubject; // 동시다발적으로 일어날 가능성 
	private DBconnection dbcon;
	
	public StudentInfo(int studentid, String name) {
		// Member Set
		this.studentid = studentid;
		this.name = name;
		// DB SET
		this.dbcon = new DBconnection();
		this.studentSubject = new Vector<StudentSubject>();
		this.gettingStuentValue();
	}
	
	private void gettingStuentValue() {
		ResultSet rs = dbcon.findUserSubject(this.studentid);
		try {
			while(rs.next()) {
				int lecture_num = rs.getInt("lecture_num");
				this.studentSubject.add(new StudentSubject(this.studentid, lecture_num));
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try - catch
	} // gettingStuentValue()
	
	// Getter
	public Vector<StudentSubject> getStudentSubject(){
		return this.studentSubject;
	} // getStudent
	
	public void gettingStudentInfo() { // StudentSubject object ( 학번, 이수 수업 번호 짝 구조체 ) 그 짝 모두 출력하기
		for (int i = 0; i < this.studentSubject.size(); i++) {
			this.studentSubject.get(i).printingStudent();
		} // for
	} // gettingStudentInfo
	
	public int getStudentId() {
		return this.studentid;
	}
	
}
