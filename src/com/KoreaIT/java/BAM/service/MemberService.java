package com.KoreaIT.java.BAM.service;

import com.KoreaIT.java.BAM.dao.MemberDao;

public class MemberService {
	public static MemberDao memberDao;
	
	static {
		memberDao = new MemberDao();
	}

}

