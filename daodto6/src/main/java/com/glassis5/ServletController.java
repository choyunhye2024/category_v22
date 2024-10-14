package com.glassis5;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class ServletController extends HttpServlet {

	String nextPage; // 다음 페이지 경로를 저장
	Dao dao; // 데이터베이스 작업을 위한 Dao 객체

	@Override
	public void init() throws ServletException {
		// 서블릿 초기화 시 Dao 객체 생성
		dao = new Dao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트 요청에서 경로 정보를 가져옴
		String action = request.getPathInfo();
		Cw.wn("action:" + action); // action 정보 콘솔에 출력

		if (action != null) {
			switch (action) {

			// case 1. 게시글 삭제
			case "/del":
				Cw.wn("삭제"); // 삭제 로그 출력
				nextPage = "/list.jsp"; // 삭제 후 리스트페이지로 이동
				dao.del(request.getParameter("no")); // 삭제처리
				break;

			// case 2. 게시글 작성
			case "/write":
				Cw.wn("쓰기"); // 쓰기로그 출력
				nextPage = "list.jsp"; // 작성 후 리스트 페이지로 이동
				// 작성할 게시물 정보생성
				Dto dto = new Dto(request.getParameter("title"), request.getParameter("id"),
						request.getParameter("text"));
				dao.write(dto); // 게시글 작성처리
				break;

			// case 3. 게시글 수정페이지로 이동
			case "/edit_insert":
				Cw.wn("수정-insert"); // 수정 삽입로그 출력
				nextPage = "/edit.jsp"; // 수정페이지로 이동
				// 수정할 게시글 정보를 가져와서 request에 저장
				request.setAttribute("post", dao.read(request.getParameter("no")));
				break;

			// case 4. 게시글 수정 처리
			case "/edit_proc":
				Cw.wn("수정-proc");// 수정처리로그 출력
				nextPage = "/list.jsp"; // 수정 후 리스트페이지로 이동
				dao.edit(new Dto(request.getParameter("title"), request.getParameter("text")),
						request.getParameter("no"));
				break;

			// case 5. 게시글 읽기
			case "/read":
				Cw.wn("읽기"); // 읽기 로그 출력
				nextPage = "/read.jsp"; // 읽기 페이지로 이동
				// 게시글 정보를 가져와서 request에 저장
				Dto d = dao.read(request.getParameter("no"));
				request.setAttribute("post", d);
				break;

			// case 6. 게시글 리스트 조회
			case "/list":
				Cw.wn("리스트"); // 리스트 로그 출력
				nextPage = "/list.jsp"; // 리스트 페이지로 이동
				ArrayList<Dto> posts = dao.list();
				// 모든 게시글 리스트를 가져와서 request에 저장
				request.setAttribute("posts", posts);
				break;
			}
			// nextPage로 포워딩
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
		}
	}
}
