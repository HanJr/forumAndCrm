package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import commandInstance.UserSearchByRegDateRequest;

public class UserSearchByRegDateRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserSearchByRegDateRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserSearchByRegDateRequest request = (UserSearchByRegDateRequest)target;
		
		if(errors.getFieldErrorCount("from") == 0) {
			if(request.getFrom() == null || request.getFrom().toString().trim().isEmpty()) { 
				errors.rejectValue("from", "required");
			}
		}
		
		if(errors.getFieldErrorCount("to") == 0) {
			if(request.getTo() == null || request.getTo().toString().trim().isEmpty()) { 
				errors.rejectValue("to", "required");
			}
		}	
	}
}
