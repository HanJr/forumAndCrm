package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import commandInstance.LoginRequest;

public class LoginRequestValidator implements Validator {

	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	public LoginRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginRequest loginRequest = (LoginRequest)target;
		
		if(loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}

		Matcher matcher = pattern.matcher(loginRequest.getEmail());
		
		if(!matcher.matches())
			errors.rejectValue("email", "bad");
		
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
	}

}
