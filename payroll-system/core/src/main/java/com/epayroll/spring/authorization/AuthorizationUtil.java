package com.epayroll.spring.authorization;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.User;

public final class AuthorizationUtil {

	private static Logger logger = LoggerFactory.getLogger(AuthorizationUtil.class);

	public static User getLoginUser() {

		logger.debug(" >> in getLoginUser");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetailsImpl) {
			return ((UserDetailsImpl) principal).getUser();
		}
		return null;
	}

	public static Company getLoginCompany() {

		logger.debug(" >> in getLoginUser");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetailsImpl) {
			User user = ((UserDetailsImpl) principal).getUser();
			Company company = new Company();
			if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
				Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
				if (iterator.hasNext()) {
					company = iterator.next().getCompany();
				}
			}
			return company;
		}
		return null;
	}

	// public static boolean hasAuthority(Section section, PermissionType
	// permissionType) {
	//
	// logger.debug(" >>  hasAuthority .. section:" + section + ", permission:"
	// + permissionType);
	//
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	//
	// if (authentication == null) {
	// return false;
	// }
	//
	// Object principal = authentication.getPrincipal();
	//
	// if (principal instanceof UserDetails) {
	// UserDetails details = (UserDetails) principal;
	// for (GrantedAuthority authority : details.getAuthorities()) {
	// String sectionPermission = new StringBuilder(section.getName())
	// .append(PermissionSeparator.VALUE).append(permissionType).toString();
	// logger.debug("sectionPermission::" + sectionPermission);
	// // return true if the user has permission of the given section
	// if (StringUtils.equalsIgnoreCase(sectionPermission,
	// authority.getAuthority())) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }

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
		logger.debug("setSessionAttribute >> ");
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