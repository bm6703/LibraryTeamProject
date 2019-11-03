package com.library.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;



@WebServlet("/booklocation")
public class Controller_booklocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Controller_booklocation() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		ActionFactory_booklocation af_booklocation = new ActionFactory_booklocation();
		Action action = af_booklocation.getAction(cmd);
		System.out.println("ø‰√ª: "+cmd);
		System.out.println("action : " + action);
		action.execute(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
