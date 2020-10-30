package forumAndCrm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import commandInstance.RegisterRequest;
import config.AppConfig;
import exception.DuplicateMemberException;
import exception.MemberNotFoundException;
import exception.WrongPasswordException;
import printer.MemberInfoPrinter;
import printer.MemberListPrinter;
import service.ChangePasswordService;
import service.MemberRegisterService;

public class MainForFirstVersion {
	
	public static void main(String[] args) throws IOException {	
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("\nEnter the command.");
			String command = reader.readLine();
			
			if(command.equalsIgnoreCase("exit"))
				break;
			else if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
			}else if(command.equalsIgnoreCase("help")){
				printHelp();
			}else if(command.equals("list")){
				processListCommand();
			}else if(command.startsWith("info ")){
				processInfoCommand(command.split(" "));
			}else {
				printHelp();
			}
		}

	}

	private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	private static void processInfoCommand(String[] arguments) {
		if(arguments.length != 2) {
			System.out.println("The command code has an error. Try again."); 
			return;
		}
		
		MemberInfoPrinter infoPrinter = context.getBean("memberInfoPrinter", MemberInfoPrinter.class);
		
		infoPrinter.print(arguments[1]);
	}	
	
	private static void processNewCommand(String[] arguments) {
		if(arguments.length != 5) {
			System.out.println("The command code has an error. Try again.");
			return;
		}
		
		RegisterRequest newMember = new RegisterRequest(arguments[1], arguments[3], arguments[4], arguments[2]);
		if(!newMember.isPasswordEqualToConfirmPassword()) {
			System.out.println("The password & confirm_password do not match.");
			return;
		}
		
		MemberRegisterService regService = context.getBean("memberRegisterService", MemberRegisterService.class);
		try {
			regService.regist(newMember);
			System.out.println("The new account is registered.");
		}catch(DuplicateMemberException e) {
			System.out.println("The email already exists in our system.");
		}
	}	
	
	private static void processChangeCommand(String[] arguments) {
		if(arguments.length != 4) {
			System.out.println("Command code has an error. Try again.");
			return;
		}
		
		ChangePasswordService changePwdService = context.getBean("changePasswordService", ChangePasswordService.class);
		try {
			changePwdService.changePassword(arguments[1], arguments[2], arguments[3]);
			System.out.println("The password is changed.");
		}catch(MemberNotFoundException e) {
			System.out.println("The member that has the given email does not exist.");
		}catch(WrongPasswordException e) {
			System.out.println("The current password is wrong.");
		}
	}
	
	private static void processListCommand() {
		MemberListPrinter listPrinter = context.getBean("memberListPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	public static void printHelp() {
		System.out.println("\nType the command code with proper information in order. [do not include \"[\" & \"]\" brackets]");
		System.out.println("[exit]: exit the program");
		System.out.println("[new email name password confirm_password]: register new account");
		System.out.println("[change email old_password new_password]: change the specified account's password");
		System.out.println("[help]: Display the list of commands");
		System.out.println("[list]: Display the list of members");
		System.out.println("[info email]: Display the information of the member\n");
	}
}
