package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import commandInstance.LoginRequest;
import dao.MemberDao;
import entity.Member;
import exception.MemberNotFoundException;
import exception.WrongPasswordException;
import variableObject.AuthInfo;

@Component
public class AuthService {
	@Autowired
	private MemberDao dao;
	
	@Transactional
	public AuthInfo authenticate(LoginRequest loginRequest) {
		Member member = dao.selectByEmail(loginRequest.getEmail());
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		if(!member.matchPassword(loginRequest.getPassword())) {
			throw new WrongPasswordException();
		}
		
		return new AuthInfo(member.getEmail(), member.getName(), Integer.parseInt(member.getId()));

	}
	
}
