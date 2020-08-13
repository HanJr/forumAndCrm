package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.MemberDao;
import entity.Member;
import exception.MemberNotFoundException;

@Component
public class ChangePasswordService {

	@Autowired
	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = memberDao.selectByEmail(email);
		
		if(member == null)
			throw new MemberNotFoundException();
		
		
		member.changePassword(oldPassword, newPassword);
		
		memberDao.update(member);
	}
}
