package com.KoreaIT.java.BAM.controller;

import com.KoreaIT.java.BAM.dto.Member;

public abstract class Controller {
	public abstract void doAction(String actionMethodName, String command);

	public void makeTestData() {

	}
	
	protected static boolean isLogined = false;
	protected static Member loginedMember = null;
}
