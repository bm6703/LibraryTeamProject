package com.library.action.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_events;

public class Action_Admin_Calendar_Insert implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO_events dao = DAO_events.getInstance();

		// event 테이블에 저장한 id 값을 조사해서 +1 시켜준다. 이는 기존 입력된 내용과의 중복을 피하기 위함이다.
		int maxValue = dao.getMaxValue() + 1;

		// maxValue를 jsp로 이동시키면서 jsp를 연다.
		request.setAttribute("maxValue", maxValue);
		RequestDispatcher rd = request.getRequestDispatcher("admin/Calendar_Insert.jsp");
		rd.forward(request, response);
	}
}