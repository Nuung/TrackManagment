package controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBconnection {

	private DBconfig dbConfigue; // db default setting
	private Connection con;
	private Statement st; // connect에 SQL문장 실행하는 방법을 정해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs; // SQL 결과 받아오는 객체
	
	public ResultSet getRecodeAll() {
		this.connectDB();
		try {
			String SQL = "SELECT * FROM `event`"; // ORDER BY `score` DESC"
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
	
	public void registUser(String name, int studentnum, String passwoard){
		this.connectDB();
		String SQL = "INSERT INTO `user` VALUES(?, ?, ?, ?)";
		try {
			// 3단계 : Statement 생성
			this.pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, "null");
			pstmt.setString(2, name);
			pstmt.setInt(3, studentnum);
			pstmt.setString(4, passwoard);
			
			// 4단계 : SQL 문 쿼리 전송
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} // try - catch
		this.closeDB();
	} // registUser()
	
	// Printing All Elements
	public void printList(){
		this.connectDB();
		this.rs = this.getRecodeAll();
		if(rs == null) { System.out.println("This Table is Empty!"); }
		
		try {
			while(rs.next()) {
				String result_id = rs.getString("id");
				String result_name = rs.getString("name");
				String result_num = rs.getString("student_number");
				String result_pass = rs.getString("password");
				System.out.println(result_id+", "+result_name+", "+result_num+", "+result_pass);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} // try - catch	
		this.closeDB();
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
			this.rs.close();
			this.pstmt.close();
			this.st.close();
			this.con.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try - catch
	} // closeDB()
	
} // DBConnection Class