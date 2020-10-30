package controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commandInstance.RegisterRequest;
import dao.MemberDao;
import entity.Member;
import exception.DuplicateMemberException;
import exception.WrongPasswordException;
import service.MemberRegisterService;
import validator.RegisterRequestValidator;
import variableObject.ErrorResponse;

@RestController
@RequestMapping("/restApi")
public class MemberRestController {

	private MemberDao dao;
	private MemberRegisterService registerService;
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	
	public void setMemberRegisterService(MemberRegisterService registerService) {
		this.registerService = registerService;
	}
	
	@GetMapping("/allMembers")
	public List<Member> allMembers(){
		ArrayList<Member> members = (ArrayList<Member>) dao.selectAll();
		System.out.println("hiiiiii>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return members;
	}
	
	@GetMapping("/member/{id}")
	public ResponseEntity<Object> getMemberById(@PathVariable int id) throws IOException {
		Member member = dao.selectById(id);
		
		if(member == null) {
			return new ResponseEntity(new ErrorResponse("The member does not exist"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(member, HttpStatus.CREATED);
	}
	
	@PostMapping("/newMember")
	public ResponseEntity<Object> addNewMember(@RequestBody @Valid RegisterRequest regReq) throws IOException {
		try {
			Long id = registerService.regist(regReq);
			
			URI uri = URI.create("/detailInfo/" + id);
			return (ResponseEntity<Object>) ResponseEntity.created(uri);
		}catch(DuplicateMemberException e) {
			return new ResponseEntity<Object>(new ErrorResponse("Duplicated email is found."), HttpStatus.CONFLICT);
		}catch(WrongPasswordException e2) {
			return new ResponseEntity<Object>(new ErrorResponse("ConfirmPassword does not match the password"), HttpStatus.CONFLICT);
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegisterRequestValidator());
	}
}
