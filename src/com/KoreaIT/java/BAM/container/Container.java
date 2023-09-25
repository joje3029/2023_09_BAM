package com.KoreaIT.java.BAM.container;

import com.KoreaIT.java.BAM.dao.ArticleDao;
import com.KoreaIT.java.BAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleService;
	public static MemberDao memberService;
	
	static {
		articleService = new ArticleDao();
		memberService = new MemberDao();
	}
}
