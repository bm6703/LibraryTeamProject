package com.library.action.booklist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_Booktype;

public class Action_Booklist_Genre_Add_Pro implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력받는 데이터 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		// jsp로부터 넘겨받는 데이터를 정의한다.
		String booktype = request.getParameter("booktype");
		String bookgenre = request.getParameter("bookgenre");

		// 스크립트 출력을 위한 PrintWriter 객체를 생성.
		PrintWriter out = response.getWriter();

		// 새로운 장르를 추가하는 메소드를 실행시키고, 결과로서 row를 받는다.
		DAO_Booktype dao = DAO_Booktype.getInstance();
		int row = dao.addBooktype(booktype, bookgenre);

		// row값에 따라 결과를 출력한다.
		if (row == 0) {
			out.println("<script>alert('입력실패, 데이터를 재확인하세요.');history.back();</script>");
		} else {
			out.println("<script>alert('입력완료.');opener.location.reload();window.close();</script>");
		}
		
		//객체 및 메소드 종료
		out.close();
	}
}