package com.library.action.booklist;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.vo.VO_BookList;

public class Action_Booklist_down implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DAO_BookList dao = DAO_BookList.getInstance();
		List<VO_BookList> book_list = dao.boolist_down();
		
		request.setAttribute("book_list", book_list);
		RequestDispatcher rd = request.getRequestDispatcher("booklist/booklist_down.jsp");
		rd.forward(request, response);
	}

}
