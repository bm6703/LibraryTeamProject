package com.library.controller;

import com.library.action.Action;
import com.library.action.booklend.Action_Booklend;
import com.library.action.booklend.Action_Booklend_goChart;
import com.library.action.booklend.Action_Booklend_list;
import com.library.action.booklend.Action_Booklend_personalChart;
import com.library.action.booklend.Action_Booklend_renewal;
import com.library.action.booklend.Action_Booklend_reserve;
import com.library.action.booklend.Action_Booklend_reserve_delete;
import com.library.action.booklend.Action_Booklend_return;

public class ActionFactory_booklend implements ActionFactory {

	@Override
	public Action getAction(String cmd) {
		Action action = null;
		// booklist
		if (cmd.equals("booklend")) {
			action = new Action_Booklend();
		} else if (cmd.equals("booklend_list")) {
			action = new Action_Booklend_list();
		} else if (cmd.equals("booklend_return")) {
			action = new Action_Booklend_return();
		} else if (cmd.equals("book_extension")) {
			action = new Action_Booklend_renewal();
		} else if (cmd.equals("booklend_reserve")) {
			action = new Action_Booklend_reserve();
		} else if (cmd.equals("booklend_reserve_delete")) {
			action = new Action_Booklend_reserve_delete();
		}else if (cmd.equals("booklend_personalChart")) {
			action = new Action_Booklend_personalChart();
		} else if (cmd.equals("booklend_goChart")) {
			action = new Action_Booklend_goChart();
		} 

		return action;
	}
}