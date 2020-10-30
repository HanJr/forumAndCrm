package commandInstance;

import java.time.LocalDateTime;

public class NewArticleRequest {
	private int id;
	private int memberId;
	private String title;
	private String articleContents;
	private String author;
	private int viewNum;
	private LocalDateTime registeredDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticleContents() {
		return articleContents;
	}
	public void setArticleContents(String articleContents) {
		this.articleContents = articleContents;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(LocalDateTime registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	
}
