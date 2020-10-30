package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import commandInstance.RegisterRequest;
import exception.DuplicateMemberException;
import exception.WrongPasswordException;
import service.MemberRegisterService;
import validator.RegisterRequestValidator;

@Controller
@RequestMapping("register")
public class RegisterController {

	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@GetMapping("/step1")
	public String handleStep1() {
		
		return "register/step1";
	}
	
	@PostMapping("/step2")
	public String handlerStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, Model model) {
		if(!agree)
			return "register/step1";
		
		model.addAttribute("registerRequest", new RegisterRequest());
		
		return "register/step2";
	}
	
	@GetMapping("/step2")
	public String redirectHandlerStep2() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/step3")
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {
		
		if(errors.hasErrors()) {
			return "register/step2";
		}
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(DuplicateMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}catch(WrongPasswordException e2) {
			errors.rejectValue("confirmPassword", "nomatch");
			return "register/step2";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegisterRequestValidator());
	}


}
