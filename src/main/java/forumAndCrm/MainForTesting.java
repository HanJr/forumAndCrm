package forumAndCrm;

import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import config.AppConfig;
import dao.MemberDao;
import entity.Member;

@Component
public class MainForTesting {

	public static MemberDao memberDao;
	
	public static void main(String[] args) {				

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Collection<Member> members = memberDao.selectAll();
		System.out.println("hello");
		
		for(Member member:members) {
			System.out.println(member.getName());
		}
		
	}
	
	
}
