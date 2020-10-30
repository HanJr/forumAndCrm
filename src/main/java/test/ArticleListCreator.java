package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dao.ForumDao;
import entity.ForumArticle;
import variableObject.AuthInfo;

/*
 * This class is used to create a list of articles for testing paging 
 */

public class ArticleListCreator {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ForumDao dao = context.getBean(ForumDao.class);
		
		ForumArticle article = new ForumArticle();
		AuthInfo authInfo = new AuthInfo("sam1506@gmail.com", "sam", 2);
		
			
		for(int i = 1; i < 50; i++) {
			article.setTitle("The article number " + 500 + i);
			article.setArticleContents("The content number " + 500 + i);
			dao.insertArticle(article, authInfo);
		}
		context.close();
	}
}
