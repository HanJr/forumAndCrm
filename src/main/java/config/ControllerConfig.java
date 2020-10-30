package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.ForumController;
import controller.LoginController;
import controller.LogoutController;
import controller.MemberRestController;
import controller.RegisterController;
import controller.UserInfoManagementController;
import controller.WebController;
import dao.ForumDao;
import dao.MemberDao;
import service.AuthService;
import service.ChangePasswordService;
import service.MemberRegisterService;

@Configuration
public class ControllerConfig {

	@Bean
	public AuthService authService() {
		return new AuthService();
	}
	
	@Bean
	public WebController webController() {
		return new WebController();
	}
	
	@Bean
	public RegisterController registerController() {
		return new RegisterController();
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController(authService());
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	@Autowired
	public ForumController forumController(ForumDao dao) {
		ForumController controller = new ForumController(); 
		controller.setDao(dao);
		return controller;
	}
	
	@Bean
	@Autowired
	public MemberRestController memberRestController(MemberDao dao, MemberRegisterService registerService) {
		MemberRestController controller = new MemberRestController();
		
		controller.setDao(dao);
		controller.setMemberRegisterService(registerService);
		
		return controller;
	}
	
	@Bean
	@Autowired
	public UserInfoManagementController userInfoManagementController(ChangePasswordService changePwService, MemberDao dao) {
	
		UserInfoManagementController controller = new UserInfoManagementController();
		controller.setChangePasswordService(changePwService);
		controller.setMemberDao(dao);
		
		return controller;
	}
}
