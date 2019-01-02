package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.db.DBconnection;

public class StudentInfo {

	// inner class like Structer [ 구조체적 요소, id : 과목 쌍을 가지는 object ]
	protected class StudentSubject {
		private int studentid;
		private int lecture_num;
		public StudentSubject(int studentid, int lecture_num) {
			this.studentid = studentid;
			this.lecture_num = lecture_num;
		}
	}
	
	// member
	private int studentid;
	private String name;
	protected Vector<StudentSubject> studentSubject; // 동시다발적으로 일어날 가능성 
	private DBconnection dbcon;
	
	public StudentInfo() {
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
}
