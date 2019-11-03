package com.library.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_notice;
import com.library.vo.VO_BookList;
import com.library.vo.VO_Notice;

public class Action_Admin_MainIndex implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DAO_notice dao = DAO_notice.getInstance();
		DAO_BookList blist_dao = DAO_BookList.getInstance();
		
		List<VO_Notice> list = dao.indexNoticeList();
		//메인 베스트셀러
		List<VO_BookList> blist = blist_dao.mein_booklistBestsellers();
		
		request.setAttribute("list", list);
		request.setAttribute("blist", blist);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
}