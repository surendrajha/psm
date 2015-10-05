package com.epayroll.spring.authorization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epayroll.constant.PermissionSeparator;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.SectionRolePermissionMap;
import com.epayroll.entity.User;
import com.epayroll.service.company.CompanyManagePermissionService;
import com.epayroll.utils.RandomNumberUtils;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -17624535977779037L;

	private Logger logger = LoggerFactory.getLogger(UserDetailsImpl.class);

	private User user;
	private CompanyManagePermissionService managePermissionService;

	private List<GrantedAuthority> authorityList;

	public UserDetailsImpl(User user, CompanyManagePermissionService managePermissionService) {
		this.user = user;
		this.managePermissionService = managePermissionService;
		createAuthorities();
	}

	private void createAuthorities() {

		logger.debug("in createAuthorities...");
		authorityList = new ArrayList<GrantedAuthority>();

		Company company = null;
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		logger.debug("company :: " + company);
		for (SectionRolePermissionMap rolePermission : managePermissionService.getRolePermissions(
				company, user.getRole())) {
			String authority = new StringBuilder(rolePermission.getSection() + "")
					.append(PermissionSeparator.VALUE).append(rolePermission.getPermissionType())
					.toString();
			authorityList.add(new SimpleGrantedAuthority(authority));
		}
		logger.debug("authorityList::" + authorityList);
		logger.info("permisions loaded!");
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorityList;
	}

	@Override
	public String getPassword() {
		return RandomNumberUtils.decode(user.getPassword());
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

}
