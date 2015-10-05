package com.epayroll.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.epayroll.model.ErrorMessage;

public class ErrorUtils {
	public static String getValidationErrorMessage(List<ObjectError> objectErrors) {
		StringBuilder stringBuilder = new StringBuilder(
				"Form Validation failed. Following are the errors : <ol>");
		for (ObjectError objectError : objectErrors) {
			stringBuilder.append("<li>" + objectError.getDefaultMessage() + "</li>");
		}
		stringBuilder.append("</ol>");
		return stringBuilder.toString();
	}

	public static List<ErrorMessage> getErrorMessages(List<FieldError> fieldErrors) {
		List<FieldError> allErrors = fieldErrors;
		List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
		for (FieldError objectError : allErrors) {
			errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getField() + "  "
					+ objectError.getDefaultMessage()));
		}
		return errorMesages;
	}
}
