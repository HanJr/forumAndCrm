package common;

public class Paging {
	private int articlesPerPage = 20;
	private int displayingPagePerBlock = 10;
	private int currentPage;
	private int currentBlock;
	private int totalNumOfArticles;
	private int totalPageCnt;
	private int startingPageNum;
	private int startingArticleNum;
	private int endPageNum;
	private boolean prev;
	private boolean next;
	
	public int getArticlesPerPage() {
		return articlesPerPage;
	}
	public void setArticlesPerPage(int articlesPerPage) {
		this.articlesPerPage = articlesPerPage;
	}
	public int getDisplayingPagePerBlock() {
		return displayingPagePerBlock;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentBlock() {
		return currentBlock;
	}
	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
	public int getTotalNumOfArticles() {
		return totalNumOfArticles;
	}
	public void setTotalNumOfArticles(int totalNumOfArticles) {
		this.totalNumOfArticles = totalNumOfArticles;
	}
	public int getStartingPageNum() {
		return startingPageNum;
	}
	public void setStartingPageNum(int startingPageNum) {
		this.startingPageNum = startingPageNum;
	}
	public int getStartingArticleNum() {
		return startingArticleNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public void currentPageInfo(int currentPage, int currentBlock, int totalNumOfArticles) {
		this.currentPage = currentPage;
		this.currentBlock = currentBlock;
		this.totalNumOfArticles = totalNumOfArticles;
		
		//Total number of pages
		this.totalPageCnt = (int) Math.ceil(totalNumOfArticles/articlesPerPage);
		
		//Starting page of the block
		this.startingPageNum = (currentBlock - 1) * displayingPagePerBlock + 1;
		
		//End page of the block
		this.endPageNum = currentBlock * displayingPagePerBlock;
		
		this.startingArticleNum = (currentPage - 1) * articlesPerPage + 1;
		
		//the status of prev button
		this.prev = currentBlock == 1 ? false:true;
		
		//the status of next button
		this.next = true;
		if(this.endPageNum > this.totalPageCnt) {
			this.endPageNum = this.totalPageCnt;
			this.next = false;
		}
	}
	
}
