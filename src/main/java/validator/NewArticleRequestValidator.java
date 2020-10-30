package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import commandInstance.NewArticleRequest;

public class NewArticleRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NewArticleRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "articleContents", "required");

	}

}
