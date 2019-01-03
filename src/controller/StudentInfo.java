package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.db.DBconnection;

public class StudentInfo {

	// inner class like Structer [ 구조체적 요소, id : 과목 쌍을 가지는 object ]
	public class StudentSubject {
		private int studentid; // 학번
		private int lecture_num; // 정수로 변환된 강의 번호
		
		// StudentSubject 클래스 생성자
		public StudentSubject(int studentid, int lecture_num) {
			this.studentid = studentid;
			this.lecture_num = lecture_num;
		}
		
		// 학번/교과목명 리스트를 출력하는 메서드
		public void printingStudent() {
			System.out.println(this.studentid+", "+this.lecture_num);
		}
		
		// 교과목명에 접근을 하기 위한 getter 메서드
		public int getLectureNum() {
			return this.lecture_num;
		}
	}
	
	// member
	private int studentid; // 학번
	private String name;
	protected Vector<StudentSubject> studentSubject; // 동시다발적으로 일어날 가능성 
	private DBconnection dbcon;
	
	public StudentInfo(int studentid, String name) {
		// Member Set
		this.studentid = studentid; // 학번
		this.name = name;  // 이름
		// DB SET
		this.dbcon = new DBconnection(); // 데이터베이스 연결
		this.studentSubject = new Vector<StudentSubject>(); // 학번/교과목명 세트를 저장할 벡터
		this.gettingStuentValue(); // gettingStudentValue 메서드 호출
	} // StudentInfo 클래스 생성자
	
	// 로그인한 유저의 학번/교과목명 세트를 데이터베이스에서 가져오기 위한 메서드
	private void gettingStuentValue() {
		ResultSet rs = dbcon.findUserSubject(this.studentid); // 질의 수행의 결과를 ResultSet에 저장
		try {
			while(rs.next()) {
				// 강의 번호를 추출
				int lecture_num = rs.getInt("lecture_num"); 
				
				// 학번은 로그인을 하면 저장이 되므로 this.studentid 변수 이용, 나온 결과 값을 하나씩 studentSubject 벡터에 추가
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
	
	// 학번 값에 접근하기 위한 getter 메서드
	public int getStudentId() {
		return this.studentid;
	}
	
	// 유저의 이름 값에 접근하기 위한 getter 메서드
	public String getStudentName() {
		return this.name;
	}
	
}
