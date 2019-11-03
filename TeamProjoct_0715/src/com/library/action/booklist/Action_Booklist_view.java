package com.library.action.booklist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.action.Action;
import com.library.dao.DAO_BookList;
import com.library.dao.DAO_Booklend;
import com.library.dao.DAO_Member;
import com.library.dao.DAO_MemberGrade;
import com.library.vo.VO_BookList;
import com.library.vo.VO_Booklend;
import com.library.vo.VO_Booklend_reserve;
import com.library.vo.VO_MemberGrade;

public class Action_Booklist_view implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String memberid = (String) session.getAttribute("session");
		DAO_Booklend booklend_dao = DAO_Booklend.getInstance();

		if (memberid == null || memberid == "") {
			int nowpage = 1;
			if (request.getParameter("nowpage") != null) {
				nowpage = Integer.parseInt(request.getParameter("nowpage"));
			}
			String bookno = request.getParameter("bookno");
			DAO_BookList dao = DAO_BookList.getInstance();
			VO_BookList vo = dao.booklistView(bookno);
			
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("vo", vo);
			RequestDispatcher rd = request.getRequestDispatcher("booklist/booklist_view.jsp");
			rd.forward(request, response);
			
		} else if (memberid.equalsIgnoreCase("admin1")) {
			int nowpage = 1;
			if (request.getParameter("nowpage") != null) {
				nowpage = Integer.parseInt(request.getParameter("nowpage"));
			}
			String bookno = request.getParameter("bookno");
			DAO_BookList dao = DAO_BookList.getInstance();
			VO_BookList vo = dao.booklistView(bookno);
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("vo", vo);
			request.setAttribute("memberid", memberid);
			RequestDispatcher rd = request.getRequestDispatcher("booklist/booklist_view.jsp");
			rd.forward(request, response);
		} else {
			int nowpage = 1;
			if (request.getParameter("nowpage") != null) {
				nowpage = Integer.parseInt(request.getParameter("nowpage"));
			}
			String bookno = request.getParameter("bookno");
			DAO_Member member_dao = DAO_Member.getInstance();
			VO_MemberGrade mvo = member_dao.member_grade_search(memberid); // 회원 등급으로 검색
			String membername = member_dao.member_name_search(memberid); // 회원 아이디로 회원 이름 검색
			DAO_BookList dao = DAO_BookList.getInstance();
			VO_BookList vo = dao.booklistView(bookno);
			int maxlenddate = mvo.getMaxlenddate();
			DAO_MemberGrade mdao = DAO_MemberGrade.getInstance();
			String lenddue = mdao.memberGrade_date(maxlenddate); // 회원 등급에 따라 반납예정일 구하기
//			VO_Booklend booklend_vo = booklend_dao.booklend_renewal_search_booklist_view(lendno);
			
			//예약 걸렸있는지 확인
			int reserve_bun = booklend_dao.booklend_reservenum_first_find(bookno);
			//로그인된 회원과 예약자 이름 같은지 비교
			String reserver_member = dao.booklist_reserveid(bookno);
			int reserve = 0;
			if(reserver_member.equals(memberid)) {
				reserve = 1;
			}else {
				reserve = -1;
			}
			
			request.setAttribute("reserve", reserve);
			request.setAttribute("reserve_bun", reserve_bun);
			request.setAttribute("membername", membername);
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("vo", vo);
			request.setAttribute("lenddue", lenddue);
			request.setAttribute("memberid", memberid);
			request.setAttribute("mvo", mvo);
			RequestDispatcher rd = request.getRequestDispatcher("booklist/booklist_view.jsp");
			rd.forward(request, response);
		}
	}
}