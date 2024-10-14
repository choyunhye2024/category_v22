package com.glassis5;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class controllerBoard
 */
@WebServlet("/board/*") // 웹 애플리케이션의 모든 /board 경로를 처리하는 서블릿
public class controllerBoard extends HttpServlet {

	String category; // 게시판의 카테고리
	String nextPage; // 다음 페이지 경로를 저장하는 변수
	ServiceBoard service; // 게시판 관련 서비스 객체

	@Override
	public void init() throws ServletException {
		service = new ServiceBoard(); // 서비스 객체 초기화
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트로부터 요청된 category 파라미터와 action 경로를 가져옴
		category = request.getParameter("category");
		String action = request.getPathInfo();
		Cw.wn("action:" + action); // 요청된 action 을 콘솔에 출력

		if (action != null) {
			switch (action) {
			// 삭제처리
			case "/del":
				nextPage = "/board/list"; // 삭제 후 목록 페이지로 이동
				service.del(category, request.getParameter("no")); // 서비스의 삭제기능 호출
				break;

			// 글쓰기처리
			case "/write":
				nextPage = "/board/list";
				Dto dto = new Dto(category, request.getParameter("title"), request.getParameter("id"),
						request.getParameter("text"));
				service.write(dto); // 서비스의 쓰기기능 호출
				break;

			// 수정 페이지로이동
			case "/edit_insert":
				Cw.wn("수정-insert");
				nextPage = "/edit.jsp"; // 수정 페이지로 이동
				// 수정할 게시글 데이터를 요청에 추가
				request.setAttribute("post", service.read(category, request.getParameter("no")));
				break;

			// 수정처리
			case "/edit_proc":
				Cw.wn("수정-proc");
				nextPage = "/board/list"; // 수정 후 목록 페이지로 이동
				service.edit(new Dto(request.getParameter("title"), request.getParameter("text")),
						request.getParameter("no")); // 서비스 수정 기능호출
				break;

			// 게시글 읽기처리
			case "/read":
				nextPage = "/read.jsp"; // 읽기 페이지로 이동
				// 서비스의 읽기 기능 호출
				Dto d = service.read(category, request.getParameter("no"));
				request.setAttribute("post", d); // 읽은 게시글 데이터를 요청에 추가
				break;

			// 게시글 목록 조회처리
			case "/list":
				nextPage = "/list.jsp"; // 목록 페이지로 이동
				// 리스트 처리를 완료한 BoardListProcessor 객체를 넘겨줌
				BoardListProcessor blp = service.list(category, request.getParameter("page"),
						request.getParameter("word")); // 서비스 리스트기능호출
				request.setAttribute("blp", blp); // 리스트 데이터를 요청에 추가
				break;
			}
			// 처리된 페이지로 포워딩
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
		}
	}
}
