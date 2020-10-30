package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commandInstance.LoginRequest;
import exception.MemberNotFoundException;
import exception.WrongPasswordException;
import service.AuthService;
import validator.LoginRequestValidator;
import variableObject.AuthInfo;

@Controller
@RequestMapping("/login")
public class LoginController {

	private AuthService authService;
	
	public LoginController(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping("/loginForm")
	public String loginForm(@ModelAttribute("login") LoginRequest loginRequest, @CookieValue(value="REMEMBER", required=false) Cookie rCookie) {
		
		if(rCookie != null) {
			loginRequest.setRememberEmail(true);
			loginRequest.setEmail(rCookie.getValue());
		}
		
		return "login/loginForm";
	}
	
	@PostMapping("/authenticate")
	public String handleAuthenticate(@ModelAttribute("login") @Valid LoginRequest loginRequest, Errors errors, Model model, HttpServletRequest request, 
			HttpServletResponse response) {
		
		if(errors.hasErrors())
			return "login/loginForm";
		
		try {
			AuthInfo authInfo = authService.authenticate(loginRequest);
			
			HttpSession session = request.getSession();
			session.setAttribute("authInfo", authInfo); //세션 관리의 시작이 여기서부터 인 것 같다.
			
			Cookie rCookie = new Cookie("REMEMBER", loginRequest.getEmail());
			rCookie.setPath("/");
			
			if(loginRequest.isRememberEmail()) {
				rCookie.setMaxAge(60 * 60 * 30 * 24);
			}else {
				rCookie.setMaxAge(0);
			}
			
			model.addAttribute("name", authInfo.getName());
			response.addCookie(rCookie);
			
			return "login/loginSuccess";
			
		}catch(MemberNotFoundException e){
			errors.reject("notRegistered");
			return "login/loginForm";
		}catch(WrongPasswordException e) {
			errors.reject("wrongPw");
			return "login/loginForm";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new LoginRequestValidator());
	}
}
