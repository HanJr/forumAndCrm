package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import commandInstance.ChangePwRequest;
import commandInstance.UserSearchByRegDateRequest;

public class ChangePwRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ChangePwRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePwRequest changePwRequest = (ChangePwRequest) target;
		
		ValidationUtils.rejectIfEmpty(errors, "currentPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "newPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmNewPw", "required");
	
		if(!errors.hasErrors() && !changePwRequest.matchingNewPw())
			errors.rejectValue("confirmNewPw", "nomatch");
	
	}

}
