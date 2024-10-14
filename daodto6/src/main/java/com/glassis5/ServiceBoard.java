package com.glassis5;

public class ServiceBoard { // Service 클래스 - 비즈니스 로직 담당

	// DaoBoard 객체 생성 (DB와의 상호작용 담당)
	DaoBoard dao;

	// 생성자 - DaoBoard 객체 초기화
	public ServiceBoard() {
		dao = new DaoBoard();
	}

	// 게시글 삭제 메소드
	// 주어진 카테고리와 게시글 번호로 해당 게시글을 삭제함
	public void del(String category, String no) {
		dao.del(category, no);
	}

	// 게시글 작성 메소드
	// 주어진 Dto 객체를 통해 새로운 게시글을 DB에 저장함
	public void write(Dto d) {
		dao.insert(d); // Dao 를 통해 DB에 게시글 삽입
	}

	// 게시글 읽기 메서드
	// 카테고리와 게시글 번호를 이용해 해당 게시글 정보를 가져옴
	public Dto read(String category, String no) {
		// Dao를 통해 게시글 데이터를 조회하고 Dto 객체로 반환
		return dao.selectPost(category, no);
	}

	// 게시글 리스트 조회 메서드
	// 카테고리, 현재페이지, 검색어를 이용해 게시글 리스트를 가져옴
	public BoardListProcessor list(String category, String currentPage, String word) {
		// 현재 페이지 값이 null이면 기본값 1로 설정
		if (currentPage == null) {
			currentPage = "1";
		}

		BoardListProcessor blp = new BoardListProcessor(dao, category, currentPage, word);
		return blp;

	}

	// Dao와 관련 정보를 이용해 게시글 리스트를 처리하는 BoardListProcessor 작성
	public void edit(Dto d, String no) {
		dao.update(d, no); // 게시글 리스트 처리 객체반환
	}
}
