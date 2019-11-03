package com.library.action.booklist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_Booktype;

public class Action_Booklist_Genre_Add implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO_Booktype dao = DAO_Booktype.getInstance();
		// getNewGenreCode로 중복되지 않은 새로운 코드 값을 가져온다.
		String newBooktype = dao.getNewGenreCode();
		request.setAttribute("newBooktype", newBooktype);
		RequestDispatcher rd = request.getRequestDispatcher("booklist/booklist_genre_add.jsp");
		rd.forward(request, response);
	}
}