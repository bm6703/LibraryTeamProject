package com.library.action.booklend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.action.Action;
import com.library.dao.DAO_Booklend;
import com.library.vo.VO_Booklend_reserve;

public class Action_Booklend_reserve implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		DAO_Booklend booklend_dao = DAO_Booklend.getInstance();
		String memberid = (String) session.getAttribute("session");
		String bookno = request.getParameter("bookno");
		String lenddue = request.getParameter("lenddue");
		int row = 0;
		boolean reserveOk = true;
		List<VO_Booklend_reserve> reserve_list = booklend_dao.booklend_reserve_list_check(memberid);
		// 예약할 책과 예약테이블에 있는지 확인
		String reserve_bookno = booklend_dao.booklend_reserve_brake(memberid, bookno);
		// 예약시 대여 목록에 같은 책이 있는지 확인
		String check_bookno = booklend_dao.booklend_reserve_check_booklendlist(bookno, memberid);
		if(reserve_list.size() >= 3) {
			reserveOk = false;
			row = -1;
		}else if(reserve_bookno.contains(bookno)) {
			// 예약할려는 책이 예약 테이블에  있는경우
			row = -2;
			reserveOk = false;
		}
		else if(check_bookno.equals(bookno)) {
			// 예약시 대여테이블에 이미 존재 하는 경우.
			row = -3;
			reserveOk = false;
		}
		
		if(reserveOk) {
			String seq = booklend_dao.booklend_reservenum();
			row = booklend_dao.booklend_reserve(seq, bookno, memberid);
		}
		
		request.setAttribute("row", row);
		RequestDispatcher rd = request.getRequestDispatcher("booklend/booklend_reserve_pro.jsp");
		rd.forward(request, response);
		
	}

}
