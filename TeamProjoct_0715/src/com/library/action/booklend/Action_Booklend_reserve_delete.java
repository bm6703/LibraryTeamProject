package com.library.action.booklend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.action.Action;
import com.library.dao.DAO_Booklend;

public class Action_Booklend_reserve_delete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberid = (String) session.getAttribute("session");
		DAO_Booklend booklend_dao = DAO_Booklend.getInstance();
		String reservenum = request.getParameter("reservenum");
		String bookno = request.getParameter("bookno");

		// 예약 취소
		int row = booklend_dao.booklend_reserve_delete(reservenum);
		if (row == 1) {
			// 삭제전 예약번호 확인
			int check_reservenum = booklend_dao.booklend_delete_reservenum_search(bookno, memberid);
			if (check_reservenum == 0) {
				// 예약취소시 대출상태 변경
				booklend_dao.booklend_delete_reserve_lend(bookno);
			}
		}

		request.setAttribute("row", row);
		RequestDispatcher rd = request.getRequestDispatcher("booklend/booklend_reserve_delete_pro.jsp");
		rd.forward(request, response);

	}

}
