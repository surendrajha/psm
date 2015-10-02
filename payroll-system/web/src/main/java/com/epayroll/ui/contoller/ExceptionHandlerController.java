package com.epayroll.ui.contoller;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RequestMapping("/exception")
public class ExceptionHandlerController {

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ModelAndView handle500Exception() {
		System.out.println("**********Exceition**********");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error500");
		modelAndView.addObject("message", "Internal Server Error!!");
		return modelAndView;
	}
	
	@ExceptionHandler(ConversionNotSupportedException.class)
	public ModelAndView handleConversionNotSupportedException() {
		System.out.println("**********handleConversionNotSupportedException**********");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error500");
		modelAndView.addObject("message", "Internal Server Error!!");
		return modelAndView;
	}
}
