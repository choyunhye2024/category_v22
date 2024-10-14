package com.glassis5;

public class Dto {
	public String category;
	public String no;
	public String title;
	public String id;
	public String datetime;
	public String hit;
	public String text;
	public String replyCount;
	public String replyOri;

	public Dto(String category, String title, String id, String text) {
		this.category = category;
		this.title = title;
		this.id = id;
		this.text = text;
	}

	// alt + shift + s 코드 자동 삽입
	public Dto(String category, String no, String title, String id, String datetime, String hit, String text,
			String replyCount, String replyOri) {
		this.category = category;
		this.no = no;
		this.title = title;
		this.id = id;
		this.datetime = datetime;
		this.hit = hit;
		this.text = text;
		this.replyCount = replyCount;
		this.replyOri = replyOri;
	}

	public Dto(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getDatetime() {
		return datetime;
	}

	public String getHit() {
		return hit;
	}

	public String getText() {
		return text;
	}

	public String getReplyCount() {
		return replyCount;
	}

	public String getReplyOri() {
		return replyOri;
	}

}
