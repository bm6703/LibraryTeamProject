package com.library.action.booklend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_Booklend;
import com.library.vo.VO_BookList;
import com.library.vo.VO_Booklend;

public class Action_Booklend_renewal implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO_Booklend lend_dao = DAO_Booklend.getInstance();
		// 책 번호 가져오기
		String bookno = request.getParameter("bookno");
		// 도서 대출 번호 가져오기
		String lendno = request.getParameter("lendno");
		// 대출 번호로 대출예정일 +7 한 날짜 구하기
		String lenddue = lend_dao.booklend_renewal(lendno);
		// 연장 여부 가지고 오기
		String renewal = lend_dao.booklend_renewal_search(lendno);
		// 예약이 있는지 확인
		int reserve_check = lend_dao.booklend_reserve_check(bookno);
		
		int row = 0;
		
		
		if (renewal.equals("0") && reserve_check == 0) {
			// 연장된 날짜로 수정
			row = lend_dao.booklend_renewal_update(lendno, lenddue);
			// 연장 여부 변경
			lend_dao.booklend_renewal_update(lendno);
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		JSONObject obj = new JSONObject();

		PrintWriter out = response.getWriter();

		obj.put("reserve_check", reserve_check);
		obj.put("renewal", renewal);
		obj.put("lenddue", lenddue);
		obj.put("row", row);
		out.print(obj);

		out.close();
	}

}
