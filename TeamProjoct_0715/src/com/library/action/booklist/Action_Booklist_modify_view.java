package com.library.action.booklist;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_Booktype;
import com.library.vo.VO_BookList;

public class Action_Booklist_modify_view implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int nowpage = 1;
		if (request.getParameter("nowpage") != null) {
			nowpage = Integer.parseInt(request.getParameter("nowpage"));
		}
		String bookno = request.getParameter("bookno");
		DAO_BookList dao = DAO_BookList.getInstance();
		DAO_Booktype bt_dao = DAO_Booktype.getInstance();
		List<String[]> typeList = bt_dao.booktype_modify_list();
		
		VO_BookList vo = dao.booklistView(bookno);
		
		request.setAttribute("typeList", typeList);
		request.setAttribute("vo", vo);
		request.setAttribute("nowpage", nowpage);
		RequestDispatcher rd = request.getRequestDispatcher("booklist/booklist_modify_view.jsp");
		rd.forward(request, response);
	}
}