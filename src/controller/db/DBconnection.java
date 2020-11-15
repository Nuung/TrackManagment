package controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.StudentInfo;

import java.sql.PreparedStatement;

public class DBconnection {

	private DBconfig dbConfigue; // db default setting
	private Connection con;
	private Statement st; // connect에 SQL문장 실행하는 방법을 정해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs; // SQL 결과 받아오는 객체

	public ResultSet getRecodeAll(String tablename) {
		this.connectDB();
		try {
			String SQL = "SELECT * FROM "; // 각 트랙 정보가 있는 테이블에서 값을 전부 가져오기 위한 쿼리문
			SQL += tablename; // 테이블 값이 트랙마다 달라지므로 변수 값을 붙여주는 것으로 처리
			rs = st.executeQuery(SQL); // rs가 SQL 구문의 결과 행 값들을 가지게 된다.
			if(rs != null) { // SQL구문의 결과값이 존재한다면
				return rs;
			} // if
		} catch(Exception e) {
			System.out.println(" DB searching error (in SQL 구문) : " + e.getMessage());
		} // try - catch
		this.closeDB();
		return null;
	} // getRecodeAll()
	
	
	// 회원가입 기능을 위한 메서드
	public void registUser(String name, int studentnum, String password){
		this.connectDB(); // DB 연결
		
		// 입력한 개인정보(이름, 학번, 비밀번호) 값을 user 테이블에 삽입하기 위한 쿼리문
		String SQL = "INSERT INTO user(name, student_number, password) VALUES(?, ?, ?)";
		try {
			// 3단계 : Statement 생성
			this.pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, name); // 첫 번째 ? 에 name 변수에 있는 값 저장
			pstmt.setInt(2, studentnum); // 두 번째 ? 에 studentnum 변수에 있는 값 저장
			pstmt.setString(3, password); // 세 번째 ? 에 password 변수에 있는 값 저장
			
			// 4단계 : SQL 문 쿼리 전송
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} // try - catch
		this.closeDB();
	} // registUser()

	// 회원가입을 할 때 엑셀에서 추출해서 반환된 교과목 번호 값을 학번 값과 함께 저장한다.
	public boolean registUserSubject(int stnum, int lecture_num) {
		this.connectDB(); // DB 연결
		
		// 삽입을 위한 쿼리문
		String SQL = "INSERT INTO userinfo(student_number, lecture_num) VALUES(?, ?)";
		
		// lecture_num 이 0인 경우에는 트랙 교과 과정에 없는 수업이므로 제외한다.
		if(lecture_num != 0) {
			try {
				this.pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, stnum); // 첫 번째 ? 값에 학번 값 지정
				pstmt.setInt(2, lecture_num); // 두 번째 ? 값에 강의 번호 값 지정
				pstmt.executeUpdate(); // 쿼리문 실행
				return true;
			}
			catch(SQLException e) {
				e.printStackTrace();
			} // try - catch
		} // if
		else {
			return false;
		} // else
		this.closeDB();
		return false;
	} // registUserSubject
	
	// 학번과 비밀번호 값을 통해 조회된 결과 값을 추출하여 반환하는 메서드, 로그인 등에 이용된다.
	public StudentInfo findUser(int studentnum, String password) {
		this.connectDB(); // DB 연결
		
		// 결과 값이 존재하는지 조회하기 위한 쿼리문
		String SQL = "SELECT * FROM user WHERE student_number="+studentnum; 
		SQL += " AND password="+password;
		try {
			this.rs = st.executeQuery(SQL); // rs가 SQL 구문의 결과 행 값들을 가지게 된다.
			if(rs != null) { // SQL구문의 결과값이 존재한다면
				rs.next();
				
				// 조회된 결과 값에서 이름 값을 저장한다.
				String result_name = rs.getString("name");
				
				// 조회된 결과 값이 존재한다면 로그인에 성공했다는 것이므로 해당 값을 구조체 성격을 지닌 StudentInfo 인스턴스를 생성하여 저장 및 반환
				StudentInfo newStudent = new StudentInfo(studentnum, result_name);
				return newStudent; 
			} // if
		} catch(SQLException e) {
			e.printStackTrace();
		} // try - catch
		this.closeDB();
		return null; // 실패했을 경우 여기서 null 리턴
	} // findUser()
	
	// 학번 값을 가지고 해당 유저가 이수한 수업(강의 번호)들의 값을 반환하기 위한 메서드
	public ResultSet findUserSubject(int studentnum) {
		this.connectDB(); // DB 연결
		String SQL = "SELECT * FROM userinfo WHERE student_number="+studentnum; 
		try {
			this.rs = st.executeQuery(SQL); // rs가 SQL 구문의 결과 행 값들을 가지게 된다.
			if(rs != null) { // SQL구문의 결과값이 존재한다면
				return this.rs; // 성공시 여기서 true 리턴
			} // if
		} catch(SQLException e) {
			e.printStackTrace();
		} // try - catch
		this.closeDB();
		return null; // 실패했을 경우 여기서 false 리턴
	} // findUser()
	
	// Printing All Elements
	public void printList(){
		this.rs = this.getRecodeAll("user"); // user 테이블에 있는 모든 값 반환
		if(rs == null) { System.out.println("This Table is Empty!"); }
		
		try {
			while(rs.next()) {
				
				// 값을 출력하기 위한 로직
				String result_id = rs.getString("id");
				String result_name = rs.getString("name");
				String result_num = rs.getString("student_number");
				String result_pass = rs.getString("password");
				System.out.println(result_id+", "+result_name+", "+result_num+", "+result_pass);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try - catch	
	} // printList()	
	
	// Connecting to my DB
	private void connectDB() {
		// DB conntecion 관련 유저이름, 비밀번호 보안강화를 위한 계층작업
		this.dbConfigue = new DBconfig();
		
		// DB 커넥션 예외 처리 try - catch
		try {
			// cj 6 이상 버전 -> error meg 안뜨게
			Class.forName(this.dbConfigue.getJdbcDriver()); // library의 Dirver Class참조
			this.con = DriverManager.getConnection(this.dbConfigue.getJdbcUrl(),	dbConfigue.getdbName(), dbConfigue.getdbPass());
			this.st = con.createStatement();
		} catch(Exception e) {
			System.out.println("DB error : " + e.getMessage());
		} // try - catch
	} // connectDB()
	
	// End DB connection
	private void closeDB() {
		try {
			if(this.rs != null)
				this.rs.close();
			if(this.pstmt != null)
				this.pstmt.close();
			if(this.st != null)
				this.st.close();
			if(this.con != null)
				this.con.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try - catch
	} // closeDB()
	
} // DBConnection Class