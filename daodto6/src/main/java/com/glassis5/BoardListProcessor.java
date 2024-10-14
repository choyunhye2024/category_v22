package com.glassis5;

import java.util.ArrayList;

public class BoardListProcessor {

	private DaoBoard dao;
	public String category;
	public ArrayList<Dto> posts;
	public int totalPage = 0; // 전체페이지수(페이징)
	public int currentPage = 0; // 현재 페이지번호
	public String word; // 검색어(있으면)

	// (1/9) 블럭 총 갯수 구하기
	int totalBlock = 0;

	// (2/9) 현재블럭번호 구하기
	int currentBlockNo = 0;

	// (3/9) 블럭페이지 시작번호 구하기
	int blockStartNo = 0;

	// (4/9) 블럭페이지 끝번호 구하기
	int blockEndNo = 0;

	// (5/9) (이전/다음) 관련 초기화 처리
	// 현재 블럭에서 (이전/다음) 을 눌렀을 때 현재 페이지 값을 변경할 때 넣을 값을 일단 초기화하기
	public int prevPage = 0;
	int nextPage = 0;
	// (6/9) (이전/다음) 관련 계산 등 처리
	boolean hasPrev = true; // 이전 블럭 가기 가능 여부 저장값 초기화
	boolean hasNext = true; // 이전 블럭가기 가능 여부 저장값 초기화

	public BoardListProcessor(DaoBoard dao, String category, String currentPage, String word) {
		this.dao = dao; // DAO 객체를 생성자로 전달받아 저장(게시판 데이터 접근용)
		this.category = category; // 카테고리 저장 (게시판의 카테고리)
		this.currentPage = Integer.parseInt(currentPage); // 현재 페이지 번호를 문장려에서 정수로 변환하여 저장
		this.word = word; // 검색어 저장(검색어가 없는 경우 null로 저장)

		// 검색어가 없는경우 (일반 게시글 리스트를 가져옴)
		if (word == null) {
			// 게시글 리스트를 보여주기 위해 전체 페이지 수를 계산
			this.totalPage = getPageCount();
			// 현재 페이지와 전체 페이지 수에 맞는 게시글 리스트를 가져오는 메서드 호출
			getList();

		} else {

			// 검색어가 있는 경우 (검색 결과리스트를 가져옴)
			// 검색어에 맞는 전체 페이지 수를 계산
			this.totalPage = getPageCount(word);
			// 검색어에 맞는 현재 페이지의 게시글 리스트를 가져오는 메서드 호출
			getList(word);

		}

		// (1/9) 블럭 총갯수 구하기
		// 블럭 총 갯수 = 페이지 수 / 블럭당 페이지 수 (결과에 올림처리)
		// 참고: Math. 함수들 : 반올림(round) , 올림 (ceil), 버림(floor)
		totalBlock = (int) Math.ceil((double) totalPage / Board.PAGE_LINK_AMOUNT);

		// (2/9)현재 블럭 번호 구하기
		// 현재블럭번호 = 현재페이지번호 / 블럭당 페이지 수 (결과에 올림 처리)
		currentBlockNo = (int) Math.ceil((double) this.currentPage / Board.PAGE_LINK_AMOUNT);

		// (3/9) 블럭 시작 페이지번호 구하기
		// 블럭 시작 페이지번호 = (현재블럭번호 - 1)*블럭당 페이지 수 + 1
		blockStartNo = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT + 1;

		// (4/9) 블럭당 페이지 끝 번호 구하기
		// 블럭 페이지 끝번호 = 현재 블럭번호 * 블럭 당 페이지 수
		blockEndNo = currentBlockNo * Board.PAGE_LINK_AMOUNT;

		// 만약 블럭 페이지 마지막 번호가 전체 페이지 마지막 번호보다 큰 경우에
		if (blockEndNo > totalPage) {
			// 블럭 마지막 페이지 번호를 페이지 마지막 번호로 저장하는 예외처리
			blockEndNo = totalPage;
		}

		// (6/9) (이전/다음) 관련 계산 등 처리
		// 현재 블럭에서 이전/다음이 가능한지 게산하고 가능 여부를 저장해두기
		if (currentBlockNo == 1) { // 현재 블럭이 1번 블럭이면
			hasPrev = false; // 이전 블럭가기 불가능
		} else { // 현재 블럭이 1번 블럭이 아니면
			hasPrev = true; // 이전 블럭 가기 가능

			// 이전 블럭 이동 시 몇 페이지로 이동할지 정하기
			// 이전 블럭의 마지막 페이지로 이동하면 됨
			// 공식 (현재블럭번호 - 1)*블럭당 페이지 수
			prevPage = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT;
		}

		if (currentBlockNo < totalBlock) { // 현재 블럭이 마지막 블럭보다 작으면
			hasNext = true; // 다음블럭가기 가능
			// 다음 블럭 이동 시 몇 페이지로 이동할지 정하기
			// 다음 블럭의 척 페이지로 이동하게 하면 됨
			// 공식 : 현재 블럭번호 * 블럭당 페이지 수 + 1
			nextPage = currentBlockNo * Board.PAGE_LINK_AMOUNT + 1;
		} else { // 현재 블럭이 마지막 블럭보다 같거나 크면
			hasNext = false; // 다음 블럭가기 불가능
		}
	}

	public void getList() {
		int startIndex = (currentPage - 1) * Board.LIST_AMOUNT; // 시작인덱스 계산해서 넘기기
		posts = dao.selectList(category, startIndex);
	}

	public void getList(String word) {
		int startIndex = (currentPage - 1) * Board.LIST_AMOUNT; // 시작 인덱스 계산해서 넘기기
		posts = dao.selectList(category, startIndex, word);
	}

	// 총 페이지 수 구하기
	public int getPageCount() {
		int totalPageCount = 0;
		int count = dao.selectPostCount(category);
		if (count % Board.LIST_AMOUNT == 0) { // case 1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / Board.LIST_AMOUNT;
		} else { // case 2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}

	// (검색) 총 페이지 수 구하기
	public int getPageCount(String word) {
		int totalPageCount = 0;
		int count = dao.selectSearchPostCount(category, word);
		if (count % Board.LIST_AMOUNT == 0) { // case 1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / Board.LIST_AMOUNT;
		} else { // case 2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}

	// 글 리스트 객체 얻는 함수
	public ArrayList<Dto> getPosts() {
		return posts;
	}

	// 페이지 리스트들을 출력하기위한 html을 리턴
	public String getHtmlPageList() {
		String html = "";

		// (7/9) 블럭처리 (이전/다음)의 (이전) 처리
		// 이전 블럭 이동이 가능하면 미리 계산한 이전 블럭 이동시 이동할 페이지번호를 링크에 전당하기
		if (hasPrev) {
			if (word == null) {
				html = html + String.format("<a href = '/board/list?category=%s&page=%d'>이전블럭가기</a>", prevPage);
			} else {
				html = html + String.format("<a href = '/board/list?category=%s&page=%d&word=%s'>이전블럭가기</a>", category,
						prevPage, word);
			}
		}

		// (8/9) 기존의 제한 없던 페이지 나열을 적용하기
		// 현재 블럭의 페이지 시작번호와 끝 번호를 이용하여 반복문의 시작값 끝값으로 하고 이 값을 페이지 번호로 출력하기
		for (int i = blockStartNo; i <= blockEndNo; i++) { // 이렇게 바꿈
			if (word == null) {
				html = html + String.format("<a href = '/board/list?category=%s&page=%d'>%d</a>", category, i, i);
			} else {
				html = html + String.format("<a href = '/board/list?category=%s&page=%d&word=%s'>%d</a>", category, i,
						word, i);
			}
		}

		// (9/9) (이전/ 다음)의 (다음)처리
		// 다음 블럭 이동이 가능하면 미리 계산한 이전 블럭 이동 시 이동할 페이지번호를 링크에 전달하기

		if (hasNext) {
			if (word == null) {
				html = html
						+ String.format("<a href = '/board/list?category=%s&page=%d'>다음블럭가기</a>", category, nextPage);
			} else {
				html = html + String.format("<a href = '/board/list?category=%s&page=%d&word=%s'>다음블럭가기</a>", category,
						nextPage, word);
			}
		}

		return html;
	}

}
