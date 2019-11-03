package com.library.action.booklend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_Booklend;
import com.library.testModule.testConfig;
import com.library.util.Util_sms;
import com.library.vo.VO_Booklend_reserve;

public class Action_Booklend_return implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memberid = (String) session.getAttribute("session");
		String lendno = request.getParameter("lendno");
		String bookno = request.getParameter("bookno");
		DAO_Booklend lend_dao = DAO_Booklend.getInstance();
		DAO_BookList list_dao = DAO_BookList.getInstance();
		int row = lend_dao.booklend_return(lendno);
		String reservenum = "";
		if (row == 1) {

			// 예약자 예약시 예약리스트의 memberid와 session 비교

			// 예약 걸렸있는지 확인
			int reserve_bun = lend_dao.booklend_reservenum_first_find(bookno);
			if (reserve_bun == 1) {
				// 첫번째 예약번호 찾기
				reservenum = lend_dao.booklend_reservenum_find(bookno);

				if (!reservenum.equals("") || reservenum != null) {
					// 예약이 있는 책 반납시 예약테이블 예약상태 변경
					lend_dao.booklend_reservestaus(reservenum);
					// 반납 성공이면 대출상태 예약중으로 변경
					list_dao.booklist_return_reserve_lend(bookno);

					if (testConfig.smsTest) {
						System.out.println("action_bookreturn/ 예약자에게 sms 전송");
						String[] reserve_sms = lend_dao.booklend_reserve_booker(reservenum);
						
						for(int i = 0 ; i < reserve_sms.length ; i++) {
							System.out.println(reserve_sms[i]);
						}
						
						// 예약자 sms
						Util_sms util_sms = new Util_sms();
						util_sms.reserveSMS(reserve_sms[0], reserve_sms[1], reserve_sms[2], request,
								response);
					}
				}
			}
			
			list_dao.booklist_return_lend(bookno); // 반납 성공 이면 대출상태 대출불가로 변경
			// 반납 확인 sms
			if (testConfig.smsTest) {
				Util_sms util_sms = new Util_sms();
				String[] return_sms = lend_dao.sms_return(lendno);
				util_sms.returnSMS(return_sms[0], return_sms[1], return_sms[2], return_sms[3], return_sms[4], request,
						response);
			}

		}

		request.setAttribute("row", row);
		RequestDispatcher rd = request.getRequestDispatcher("booklend/booklend_return_pro.jsp");
		rd.forward(request, response);
	}
}