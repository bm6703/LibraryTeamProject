package com.library.action.location;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_booklocation;

public class BooklocationAction2 implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO_booklocation dao = DAO_booklocation.getInstance();
		String bookno=request.getParameter("bookno");
		List<String[]> tNcList =dao.booktNc();
		request.setAttribute("tNcList", tNcList);
		System.out.println("booklocationAction / listsize : " + tNcList.size());
		RequestDispatcher rd = request.getRequestDispatcher("/booklocation/booklocation.jsp");
		rd.forward(request, response);

		
		
		/*
		 * String[] tNc= null;
		 * for(int i=0;i<tNcList.size();i++) { tNc = tNcList.get(i); String Booktype =
		 * tNc[0]; String typeCnt = tNc[1]; System.out.println(Booktype + "," +
		 * typeCnt); }
		 */
		
}
}
