package com.library.controller;

import com.library.action.Action;
import com.library.action.location.BooklocationAction;
import com.library.action.location.BooklocationAction2;

public class ActionFactory_booklocation implements ActionFactory {
	private static ActionFactory_booklocation instance = new ActionFactory_booklocation();// �׼����丮�� � �׼� ������� ����ش�

	ActionFactory_booklocation() {
		super();

	}

	public static ActionFactory getInstance() {
		return instance;
	}

	@Override
	public Action getAction(String cmd) {
		Action action = null;
		System.out.println("ActionFactory:" + cmd);
		if (cmd.equals("booklocation")) {
			action = new BooklocationAction();
		}
		return action;
	}
}
