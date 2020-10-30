package test;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import dao.ForumDao;
import entity.ForumArticle;
import variableObject.AuthInfo;

/*
 * This class is used to create a list of articles for testing paging 
 */

public class ArticleListCreator {

	public static void main(String[] args) throws InterruptedException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ForumDao dao = context.getBean(ForumDao.class);
		
		ForumArticle article = new ForumArticle();
		AuthInfo authInfo = new AuthInfo("sam1506@gmail.com", "sam", 2);
		
			
		for(int i = 1; i < 258; i++) {
			article.setTitle("The article number " + i);
			article.setArticleContents("The content number " +i);
			dao.insertArticle(article, authInfo);
			
			TimeUnit.SECONDS.sleep(10);
		}
		context.close();
	}
}
