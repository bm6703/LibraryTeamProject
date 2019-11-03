package com.library.action.booklend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_Booklend;
import com.library.vo.VO_BookList;
import com.library.vo.VO_Booklend;
import com.library.vo.VO_Booklend_reserve;

public class Action_Booklend_list implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memberid = (String) session.getAttribute("session");

		DAO_Booklend dao = DAO_Booklend.getInstance();
		List<VO_Booklend> list = dao.booklend_list(memberid);
		// 예약 리스트
		List<VO_Booklend_reserve> reserve_list = dao.booklend_reserve_list(memberid);

		request.setAttribute("reserve_list", reserve_list);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("booklend/booklend_list.jsp");
		rd.forward(request, response);
	}
}