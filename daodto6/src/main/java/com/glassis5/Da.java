package com.glassis5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Da {

	Connection con = null; // 데이터 연결을 위한 connection 객체
	Statement st = null; // SQL문을 실행하기위한 statement 객체

	void connect() {

		try {
			// 고정 1: JDBC 드라이버를 로드 (드라이버 경로는 Db클래스에 정의됨)
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			// 고정 2: 데이터베이스에 연결 (url, id, pw는 Db클래스에 저으이됨)
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			// 고정 3: SQL문을 실행할 수 있는 statement 객체생성
			st = con.createStatement();
		} catch (Exception e) {
			// 예외 발생 시 오류 메시지 출력
			e.printStackTrace();
		}
	}

	// SQL업데이트 작업을 처리하는 메서드
	void update(String sql) {
		try {
			// SQL문 실행(insert, update, delete와 같은 쿼리)
			st.executeUpdate(sql);
		} catch (Exception e) {
			// 예외발생 시 오류메시지 출력
			e.printStackTrace();
		}
	}

	void close() {
		try {
			st.close(); // 고정 4: statement 객체 닫기
			con.close(); // 고정 5: connection 객체 닫기
		} catch (Exception e) {
			// 예외 발생 시 오류 메시지 출력
			e.printStackTrace();
		}
	}

}
