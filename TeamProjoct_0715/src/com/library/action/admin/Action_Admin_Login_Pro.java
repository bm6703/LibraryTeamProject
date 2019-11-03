package com.library.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_admin;
import com.library.vo.VO_BookList;

public class Action_Admin_Login_Pro implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminid = request.getParameter("adminid");
		String adminpasswd = request.getParameter("adminpasswd");
		DAO_admin dao = DAO_admin.getInstance();
		int row = dao.logincheck(adminid, adminpasswd);
		if (row == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("admin/Login_Fail.jsp");
			rd.forward(request, response);
		} else {
			DAO_BookList ldao = DAO_BookList.getInstance();
			//베스트 셀러
			List<VO_BookList> blist = ldao.mein_booklistBestsellers();
			HttpSession session = request.getSession();
			
			session.setAttribute("session", adminid);
			request.setAttribute("blist", blist);
			request.setAttribute("adminid", adminid);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}