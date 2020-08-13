package printer;

import entity.Member;

public class MemberPrinter {

	public void print(Member member) {
		System.out.printf("\nID: %s, Email: %s, Name: %s, Registered Date: %tB %<te,  %<tY  %<tT %<Tp%n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
}
