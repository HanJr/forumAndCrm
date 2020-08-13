package service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import commandInstance.RegisterRequest;
import dao.MemberDao;
import entity.Member;
import exception.DuplicateMemberException;

@Component
public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public Long regist(RegisterRequest req) {
		if(memberDao.selectByEmail(req.getEmail()) != null) 
			throw new DuplicateMemberException();
		
		Member member = new Member(req.getEmail(), req.getPassword(), req.getName(),
				LocalDateTime.now());
		
		memberDao.insert(member);
		return Long.parseLong(member.getId());
	}
}
