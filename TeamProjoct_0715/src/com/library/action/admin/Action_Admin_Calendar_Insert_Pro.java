package com.library.action.admin;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.action.Action;
import com.library.dao.DAO_events;

public class Action_Admin_Calendar_Insert_Pro implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp에서 받는 데이터 인코딩
		request.setCharacterEncoding("utf-8");

		// 하술할 out.println 구문의 입력 타입 설정.
		response.setContentType("text/html; charset=UTF-8");

		// events.xml에 들어갈 id 값을 가져온다.
		int maxValue = Integer.parseInt(request.getParameter("id"));

		// updateMaxValue 메소드를 한번 가동하여, 다음 입력할 이벤트의 id 중복을 피해준다.
		// 중복되어도 이벤트 등록에는 지장이 없지만, 혹시 id를 사용할 경우를 대비함.
		DAO_events dao = DAO_events.getInstance();
		dao.updateMaxValue();

		// events.xml에 들어갈 데이터를 설정한다.
		String name = request.getParameter("name");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");

		// 이 부분 중요!! 추가하고 싶은 내용이 newSchedule 변수에 등록된다.
		String newSchedule = "\n<event>\n" + "<id>" + maxValue + "</id>\n" + "<name>" + name + "</name>\n"
				+ "<startdate>" + startdate + "</startdate>\n" + "<enddate>" + enddate + "</enddate>\n"
				+ "<starttime></starttime>\n<endtime></endtime>\n" + "<color>#9f4b99</color>\n<url></url>\n"
				+ "</event>\n</monthly>";
		String filename = "C:/hrdkorea/TeamProjoct_0715/WebContent/events.xml";
		File file = new File(filename);
		Scanner scan = new Scanner(file);

		// 기존 파일의 내용을 받을 String 변수를 생성.
		String original = "";

		// 스크립트 출력을 위한 PrintWriter 객체를 생성.
		PrintWriter out = response.getWriter();

		try {
			// 기존 파일의 내용을 original에 넣어준다.
			while (scan.hasNextLine()) {
				original = original + scan.nextLine();
			}

			// original에 들어있는 종료 태그</monthly>를 제거한다.
			original = original.replace("</monthly>", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		// original과 newSchedule을 합하여 파일에 덮어쓸 inputDate를 생성한다.
		String inputData = original + newSchedule;

		// BufferedWriter 객체를 생성한다. 이 때, events.xml의 내용은 모두 사라진다.
		BufferedWriter bf = new BufferedWriter(new FileWriter("C:/hrdkorea/TeamProjoct_0715/WebContent/events.xml"));

		// bf에 inputDate를 작성한 후 bf를 종료한다.
		bf.write(inputData);
		bf.close();

		// 사용자가 볼 수 있게 스크립트를 출력시킨 후 객체 종료 및 메소드를 종료한다.
		out.println("<script>" + "alert('입력완료.\\n적용되는데는 몇 초 정도 소요됩니다.');" + "window.close();"
				+ "opener.location.reload();" + "</script>");
		out.flush();
		out.close();
		scan.close();
	}
}