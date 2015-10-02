package com.epayroll.spring.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epayroll.constant.PayrollUserType;
import com.epayroll.dao.company.UserDao;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.utils.RandomNumberUtils;

public class PayrollAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private String pinParameter = "pin";
	private String userType;
	private boolean postOnly = true;

	@Autowired
	private UserDao userDao;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		try {
			if (postOnly && !request.getMethod().equals("POST")) {
				logger.debug("Not a Post Request..");
				throw new AuthenticationServiceException(PayrollUserType.METHOD_NOT_SUPPORTED);
			}

			if (request.getParameter(getUsernameParameter()) == ""
					|| request.getParameter(getPasswordParameter()) == "") {
				logger.debug("NUll UserName or password!");
				throw new AuthenticationServiceException(PayrollUserType.BLANK_USER_PASSWORD);
			}

			String username = request.getParameter(getUsernameParameter()).trim();
			String pin = request.getParameter(getPinParameter());
			String pwd = request.getParameter(getPasswordParameter());

			if (pin == null) {

				User user = userDao.checkUserPassword(username, RandomNumberUtils.encode(pwd));

				switch (userType) {
				case PayrollUserType.COMPANY_USER:
					logger.debug("Checking IF COMPANY_USER OR NOT...");
					if (user.getCompanyUsers().isEmpty() && !user.getAdminUsers().isEmpty()) {
						logger.debug("NO! ITS NOT COMPANY USER");
						throw new AuthenticationServiceException(PayrollUserType.NOT_COMPANY_USER);
					}
					logger.debug(" YES! ITS COMPANY_USER !");
					break;
				case PayrollUserType.ADMIN_USER:
					logger.debug("Checking IF ADMIN_USER OR NOT...");
					if (!user.getCompanyUsers().isEmpty() && user.getAdminUsers().isEmpty()) {
						logger.debug("NO! ITS NOT ADMIN USER!");
						throw new AuthenticationServiceException(PayrollUserType.NOT_ADMIN_USER);
					}
					logger.debug(" YES! ITS ADMIN_USER !");
					break;
				}
			}

			if (pin == null) {
				logger.debug("REDIRECT TO PIN VARIFUCATION.....");
				throw new AuthenticationServiceException(PayrollUserType.VERIFY_PIN);
			} else {
				logger.debug("CHECKING PIN......");
				User ur = userDao.verifyPin(username, RandomNumberUtils.encode(pin));
				if (ur == null) {
					throw new AuthenticationServiceException(PayrollUserType.INCORRECT_PIN);
				}
			}
			return super.attemptAuthentication(request, response);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR in (InstanceNotFoundException):::", e);
			throw new AuthenticationServiceException(PayrollUserType.USER_NOT_FOUND);
		} catch (JDBCConnectionException e) {
			logger.debug("ERROR in (Exception)::", e);
			throw new AuthenticationServiceException(PayrollUserType.CONNECTION_ERROR);
		}
	}

	public String getPinParameter() {
		return pinParameter;
	}

	public void setPinParameter(String pinParameter) {
		this.pinParameter = pinParameter;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}
}