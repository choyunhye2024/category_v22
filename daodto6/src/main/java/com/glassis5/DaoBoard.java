package com.glassis5;

import java.sql.ResultSet;
import java.util.ArrayList;

// dao를 상속받은 daoboard
public class DaoBoard extends Dao {

	// (1/5) 글삭제
	public void del(String category, String no) {
		connect(); // DB연결 = connect of super.connect
		// SQL 쿼리 : 지정된 카테고리와 글 번호로 게시글 삭제
		String sql = String.format("delete from %s where b_no = %s and b_category like '%s'", Board.TABLE_PS_BOARD_FREE,
				no, category);
		super.update(sql); // SQL실행
		super.close(); // DB연결 종료
	}

	// (2/5) 글작성
	public void insert(Dto d) {
		connect(); // DB연결 = connect or super.connect
		String sql = String.format("insert into %s (b_category, b_title, b_id, b_text) values ('%s', '%s', '%s', '%s')",
				Board.TABLE_PS_BOARD_FREE, d.category, d.title, d.id, d.text);
		super.update(sql); // SQL실행
		super.close(); // DB연결종료
	}

	// (3/5) 글읽기
	public Dto selectPost(String category, String no) {
		connect(); // DB연결 = connect or super.connect
		Dto post = null;
		try {
			String sql = String.format(
					"select b_category, b_no, b_title, b_id,b_datetime, b_hit, b_text, b_reply_count, b_reply_ori from %s where b_no = %s and b_category like '%s' ",
					Board.TABLE_PS_BOARD_FREE, no, category);
			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql); // SQL실행 결과를 ResultSet로 받음
			rs.next();// 첫번째 결과로 이동
			// 조화된 데이터를 Dto 객체로 생성
			post = new Dto(category, rs.getString("B_NO"), rs.getString("B_TITLE"), rs.getString("B_ID"),
					rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
					rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI"));
		} catch (Exception e) {
			e.printStackTrace(); // 오류시 스택출력
		}
		super.close(); // DB연결종료
		return post; // 조회된 게시물 반환
	}

	// (4/5) 글 목록 조회
	public ArrayList<Dto> selectList(String category, int startIndex) {
		connect(); // DB연결 = connect or super.connect
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			// SQL 쿼리 : 지정된 카테고리의 글 목록을 페이징하여 조회
			String sql = String.format("select * from where b_category like '%s' limit %d, %d",
					Board.TABLE_PS_BOARD_FREE, category, startIndex, Board.LIST_AMOUNT);
			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql); // SQL실행 결과를 ResultSet로 받음
			// 조회된 각 행을 Dto 객체로 반환하여 리스트에 추가
			while (rs.next()) {
				posts.add(new Dto(rs.getString("B_CATEGORY"), rs.getString("B_NO"), rs.getString("B_TITLE"),
						rs.getString("B_ID"), rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
						rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI")));
			}
		} catch (Exception e) {
			e.printStackTrace(); // 오류시 스택출력
		}
		super.close(); // DB연결 종료
		return posts; // 조회된 리스트 반환
	}

	// (5/5) 글 수정
	public void update(Dto d, String no) {
		connect(); // DB연결 = connect or super.connect
		// SQL 쿼리 : 지정된 글 번호의 제목과 내용을 수정
		String sql = String.format("update %s set b_title = '%s', b_text = '%s' where b_no %s",
				Board.TABLE_PS_BOARD_FREE, d.title, d.text, no);
		super.update(sql); // SQL실행
		super.close(); // DB연결종료
	}

	// 게시글 총 수 구하기
	public int selectPostCount(String category) {
		int count = 0;
		connect(); // DB연결 = connect or super.connect
		try {
			// SQL 쿼리 : 지정된 카테고리의 게시글 총 수를 조회
			String sql = String.format("select count(*) from %s where b_category like '%s'", Board.TABLE_PS_BOARD_FREE,
					category);
			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql); // SQL 실행결과를 ResultSet로 받음
			rs.next();
			count = rs.getInt("count(*)"); // 게시글 총 수 저장
		} catch (Exception e) {
			e.printStackTrace(); // 오류시 스택출력
		}

		super.close(); // DB연결종료
		return count; // 게시글 총 수 반환
	}

	// 검색된 게시물 총 수 구하기
	public int selectSearchPostCount(String category, String word) {
		int count = 0;
		connect(); // DB연결 = connect or super.connect
		try {
			// SQL쿼리 : 지정된 카테고리에서 검색어가 포함된 게시글의 총 수를 조회
			String sql = String.format("select count(*) from %s where b_title like '%%%s%%' and b_category like '%s'",
					Board.TABLE_PS_BOARD_FREE, word, category);
			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql); // SQL실행 결과를 ResultSet로 받음
			rs.next(); // 첫번째 결과로 이동
			count = rs.getInt("count(*)"); // 검색된 게시글 총 수 저장
		} catch (Exception e) {
			e.printStackTrace(); // 오류시 스택출력
		}
		super.close(); // DB연결종료
		return count; // 검색된 게시글 총 수 반환
	}

	// 글 목록 조회 (검색)
	public ArrayList<Dto> selectList(String category, int startIndex, String word) {
		connect(); // DB연결 = connect or super.connect
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			// SQL 쿼리 : 지정된 카테고리와 검색어를 만족하는 게시글 목록을 페이징하여 조회
			String sql = String.format(
					"select b_category, b_no, b_title, b_id, b_datetime, b_hit, b_text, b_reply_count, b_reply_ori"
							+ "from %s where b_title like '%%%s%%' and b_category like '%s' limit %s, %s",
					Board.TABLE_PS_BOARD_FREE, word, category, startIndex, Board.LIST_AMOUNT);
			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql); // SQL실행결과를 ResultSet로 받음
			// 조회된 각 행을 Dto 객체로 반환하여 리스트에 추가
			while (rs.next()) {
				posts.add(new Dto(rs.getString("B_CATEGORY"), rs.getString("B_NO"), rs.getString("B_TITLE"),
						rs.getString("B_ID"), rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
						rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close(); // DB연결종료
		return posts; // 검색된 게시글 리스트 반환
	}

}
