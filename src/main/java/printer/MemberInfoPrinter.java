package printer;

import org.springframework.beans.factory.annotation.Autowired;

import dao.MemberDao;
import entity.Member;

public class MemberInfoPrinter {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter printer;
	
	public MemberInfoPrinter() {
	}
	
	public void print(String email) {
		Member member = memberDao.selectByEmail(email);
		
		if(member != null) {
			printer.print(member);
		}else {
			System.out.println("The email does not exist in our system.\n");
		}
	}
}
