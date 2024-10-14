package com.glassis5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Dao {

	// DB연결을 위한 Connection 객체
	public Connection con = null;
	// SQL 명령을 실행하기 위한 Statement 객체
	public Statement st = null;

	// connect() : DB와 연결을 설정하는 메서드
	public void connect() {
		try {
			// 고정 1: JDBC 드라이버 로딩
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			// 고정 2: DB연결설정 (url, id, pw)
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			// 고정 3: Statement 객체생성
			st = con.createStatement();
		} catch (Exception e) {
			// 오류 시 스택 출력
			e.printStackTrace();
		}
	}

	public void update(String sql) { // 주어진 SQL을 실행하는 메서드(DB수정, 삽입, 삭제)
		try {
			// sql = 실행할 SQL 명령
			st.executeUpdate(sql); // SQL명령실행
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() { // DB연결을 닫는 메서드
		try {
			// 고정 4 : Statement 객체 닫기
			st.close();
			// 고정 5 : Connection 객체 닫기
			con.close();
		} catch (Exception e) {
			// 오류 시 스택출력
			e.printStackTrace();
		}
	}

}
