package com.epayroll.web;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.LoginController;

public class LoginControllerTest extends TestRoot {

	private Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

	@Autowired
	private LoginController loginController;

	@Test
	public void welcome() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/accessDenied");
		ModelAndView mav = handlerAdapter.handle(request, response, loginController);

		ModelAndViewAssert.assertViewName(mav, "accessDenied");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("entry.getKey()::" + entry.getKey() + "entry.getValue()::"
					+ entry.getValue());
		}
	}

	// @Test
	// public void login() throws Exception {
	// System.out.println("trying to login...");
	// request.setMethod("GET");
	// request.setRequestURI("/login");
	// ModelAndView mav = handlerAdapter.handle(request, response,
	// loginController);
	//
	// for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
	// logger.info("entry.getKey()::" + entry.getKey() + "entry.getValue()::"
	// + entry.getValue());
	// }

	// }
}
