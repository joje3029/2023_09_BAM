package com.KoreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public String loginId;

	public Article(int id, String regDate, String updateDate, String title, String body, String loginId) {
		this(id, regDate, updateDate, title, body, 0,loginId);
	}

	public Article(int id, String regDate, String updateDate, String title, String body, int hit, String loginId) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.loginId = loginId;
	}
}
