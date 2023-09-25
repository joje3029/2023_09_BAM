package com.KoreaIT.java.BAM.service;

import com.KoreaIT.java.BAM.dao.ArticleDao;

public class ArticleService {
	public static ArticleDao articleDao;
	
	static {
		articleDao = new ArticleDao();
	}


}
