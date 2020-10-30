package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import common.Paging;
import entity.ForumArticle;
import entity.Member;
import variableObject.AuthInfo;

public class ForumDao {
	private JdbcTemplate template;
	
	public ForumDao(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	public Collection<ForumArticle> getArticleList(Paging paging){
		Collection<ForumArticle> list = template.query("SELECT * FROM(SELECT FORUM.ARTICLE_ID, FORUM.TITLE, FORUM.ARTICLE_CONT, MEMBER.NAME, FORUM.VIEW_NUM, "
				+ "FORUM.CREATED_DATE, ROW_NUMBER() OVER (ORDER BY FORUM.ARTICLE_ID ASC) LINE_NUMBER FROM FORUM, MEMBER WHERE MEMBER.ID = FORUM.MEMBER_ID)" +
				"WHERE LINE_NUMBER BETWEEN ? AND ? ORDER BY LINE_NUMBER", 
				new RowMapper<ForumArticle>() {

					@Override
					public ForumArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						ForumArticle article = new ForumArticle();
						article.setArticleID(rs.getInt("ARTICLE_ID"));
						article.setTitle(rs.getString("TITLE"));
						article.setArticleContents("ARTICLE_CONT");
						article.setName(rs.getString("NAME"));
						article.setViewNum(rs.getLong("VIEW_NUM"));
						article.setCreatedDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime());
						
						return article;
					}			

		}, paging.getStartingArticleNum(), paging.getArticlesPerPage() - 1 + paging.getStartingArticleNum());
		return list;
	}

	public Integer getNumOfArticles() {
		Integer numOfArticles = template.queryForObject("SELECT count(*) AS COUNT FROM FORUM", Integer.class);
		
		return numOfArticles;
	}
	
	public ForumArticle getArticle(Long id) {
		
		ForumArticle article = template.queryForObject("SELECT * FROM FORUM, MEMBER WHERE FORUM.MEMBER_ID = MEMBER.ID AND ARTICLE_ID = ?", 
				new RowMapper<ForumArticle>(){

					@Override
					public ForumArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						ForumArticle article = new ForumArticle();
						
						article.setArticleID(rs.getInt("ARTICLE_ID"));
						article.setArticleContents(rs.getString("ARTICLE_CONT"));
						article.setMemberId(rs.getLong("MEMBER_ID"));
						article.setTitle(rs.getString("TITLE"));
						article.setName(rs.getString("NAME"));
						article.setViewNum(rs.getInt("VIEW_NUM"));
						article.setCreatedDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime());
						
						return article;
					}}
				, id);
		
		
		return article;
	}
	
	public void insertArticle(ForumArticle article, AuthInfo authInfo) {
		
		template.update("INSERT INTO FORUM VALUES (NULL, ?, ?, ?, 0, ?)", authInfo.getId(), article.getTitle(),
				article.getArticleContents(), Timestamp.valueOf(LocalDateTime.now()));

	}
	
	public void deleteArticle(long id) {
		template.update("DELETE FROM FORUM WHERE ARTICLE_ID = ?", id);
	}
	
	public void updateArticle(ForumArticle article) {
		template.update("UPDATE FORUM SET TITLE = ?, ARTICLE_CONT = ?, VIEW_NUM = ? WHERE ARTICLE_ID = ?", article.getTitle(), article.getArticleContents(), article.getViewNum(), article.getArticleID());
	}
}
