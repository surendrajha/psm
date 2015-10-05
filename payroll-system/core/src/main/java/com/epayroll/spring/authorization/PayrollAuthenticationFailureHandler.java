package com.epayroll.spring.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.epayroll.constant.PayrollUserType;

public class PayrollAuthenticationFailureHandler implements AuthenticationFailureHandler {

	Logger logger = LoggerFactory.getLogger(PayrollAuthenticationFailureHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if (exception.getMessage().equals(PayrollUserType.METHOD_NOT_SUPPORTED)) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
					"GET Request not Allowed.");
		}

		if (exception.getMessage().equals(PayrollUserType.BLANK_USER_PASSWORD)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Please provide username and password !");
			redirectStrategy.sendRedirect(request, response, "/login");
		}

		if (exception.getMessage().equals(PayrollUserType.CONNECTION_ERROR)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Could not Open the Database Connection, please contact for support");
			redirectStrategy.sendRedirect(request, response, "/login");
		}

		if (exception.getMessage().equals(PayrollUserType.USER_NOT_FOUND)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Invalid Username or Password!");
			redirectStrategy.sendRedirect(request, response, "/login");
		}

		if (exception.getMessage().equals(PayrollUserType.NOT_COMPANY_USER)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Invalid Domain, Not a Company User!");
			redirectStrategy.sendRedirect(request, response, "/login");
		}

		if (exception.getMessage().equals(PayrollUserType.NOT_ADMIN_USER)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Invalid Domain, Not a Admin User!");
			redirectStrategy.sendRedirect(request, response, "/login");
		}

		if (exception.getMessage().equals(PayrollUserType.VERIFY_PIN)) {
			request.getRequestDispatcher("/pinVerification").forward(request, response);
		}

		if (exception.getMessage().equals(PayrollUserType.INCORRECT_PIN)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Invalid Pin! Please Try Again");
			redirectStrategy.sendRedirect(request, response, "/login");
		}

		if (exception.getMessage().equals(PayrollUserType.OTHER_ERROR)) {
			request.getSession().setAttribute(PayrollUserType.USER_NOT_FOUND,
					"Unknown Error, please contact for support.");
			redirectStrategy.sendRedirect(request, response, "/login");
		}
	}
}
