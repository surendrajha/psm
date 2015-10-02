/**
 * 
 */
package com.epayroll.spring.authorization;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.epayroll.entity.Employee;

/**
 * @author Surendra Jha
 */
public final class EmployeeAccessAuthorizationUtils {

	private static Logger logger = LoggerFactory.getLogger(EmployeeAccessAuthorizationUtils.class);

	public static Employee getLoginUser() {

		logger.debug(" >> in getLoginUser");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();

		if (principal instanceof EmployeeAccessUserDetailsImpl) {
			return ((EmployeeAccessUserDetailsImpl) principal).getUser();
		}
		return null;
	}

	public static boolean hasAuthority(String sectionName) {

		logger.debug(" >>  hasAuthority .. sectionName:" + sectionName);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return false;
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails details = (UserDetails) principal;
			for (GrantedAuthority authority : details.getAuthorities()) {
				if (StringUtils.equalsIgnoreCase(sectionName, authority.getAuthority())) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * return the httpSession object
	 */
	public static ServletRequestAttributes getSession() {
		logger.debug(" >> getSession");
		return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	}

	public static void setSessionAttribute(String attributeName, Object attributeValue) {
		logger.debug(" >> setSessionAttribute");
		getSession().setAttribute(attributeName, attributeValue, RequestAttributes.SCOPE_SESSION);
	}

	public static Object getSessionAttribute(String attributeName) {
		logger.debug(" >> getSessionAttribute");
		Object attributeValue = getSession().getAttribute(attributeName,
				RequestAttributes.SCOPE_SESSION);
		logger.debug("Value Retrieved From Session : " + attributeValue);
		logger.debug("getSessionAttribute >> ");
		return attributeValue;
	}

}
