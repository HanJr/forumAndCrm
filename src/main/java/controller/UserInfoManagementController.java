package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import commandInstance.ChangePwRequest;
import commandInstance.UserSearchByRegDateRequest;
import dao.MemberDao;
import entity.Member;
import exception.MemberNotFoundException;
import exception.WrongPasswordException;
import service.ChangePasswordService;
import validator.ChangePwRequestValidator;
import validator.UserSearchByRegDateRequestValidator;
import variableObject.AuthInfo;

@Controller
public class UserInfoManagementController {

	private ChangePasswordService changePwService;
	private MemberDao dao;
	
	public void setChangePasswordService(ChangePasswordService changePwService) {
		this.changePwService = changePwService;
	}
	
	public void setMemberDao(MemberDao dao) {
		this.dao = dao;
	}
	
	@GetMapping("/searchByDate")
	public String searchByDate(@ModelAttribute("searchCommand") UserSearchByRegDateRequest request) {
		
		return "/userInfoManagement/regDateSearchForm";
	}
	
	@PostMapping("/processSearch")
	public String processSearch(@Valid @ModelAttribute("searchCommand") UserSearchByRegDateRequest request, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			return "/userInfoManagement/regDateSearchForm";
		}
		
		ArrayList<Member> list = (ArrayList<Member>) dao.selectBwRegdates(request.getFrom(), request.getTo());
		
		model.addAttribute("members", list);
		
		return "/userInfoManagement/regDateSearchForm";
	}
	
	@GetMapping("/changePwForm")
	public String changePwForm(@ModelAttribute("changePwRequest") ChangePwRequest changePwRqst) {
		return "/userInfoManagement/changePwForm";
	}
	
	@PostMapping("/processPwChange")
	public String processPwChange(@Valid @ModelAttribute("changePwRequest") ChangePwRequest changePwRqst, Errors errors, HttpSession session) {
		if(errors.hasErrors()) {
			return "/userInfoManagement/changePwForm";
		}
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		try {
			changePwService.changePassword(authInfo.getEmail(), changePwRqst.getCurrentPw(), changePwRqst.getNewPw());
		}catch(WrongPasswordException e) {
			errors.reject("wrongPw");
			return "/userInfoManagement/changePwForm";
		}catch(MemberNotFoundException e) {
			errors.reject("notRegistered");
			return "/userInfoManagement/changePwForm";
		}
		
		return "/userInfoManagement/changePwSuccess";
	}
	
	@GetMapping("/detailInfo/{id}")
	public String detailInfo(@PathVariable int id, Model model) {
		
		Member member = dao.selectById(id);
		
		if(member == null)
			throw new MemberNotFoundException();
		
		model.addAttribute("member", member);
		
		return "/userInfoManagement/detailInfo";
	}
	
	@GetMapping("/processPwChange")
	public String ProcessPwChangeRedirect() {
		return "redirect:/main";
	}	
	
	@InitBinder("changePwRequest")
	public void initBinderForChangePw(WebDataBinder binder) {
		binder.setValidator(new ChangePwRequestValidator());
	}
	
	@InitBinder("searchCommand")
	public void initBinderForSearch(WebDataBinder binder) {
		binder.setValidator(new UserSearchByRegDateRequestValidator());
	}	

	@ExceptionHandler(MemberNotFoundException.class)
	public String handleMemberNotFound() {
		return "errors/noMember";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatch() {
		return "errors/typeMismatch";
	}
}
