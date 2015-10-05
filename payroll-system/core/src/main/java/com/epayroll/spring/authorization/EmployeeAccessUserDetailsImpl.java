package com.epayroll.spring.authorization;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epayroll.dao.employee.EmployeeSectionDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeSection;
import com.epayroll.utils.RandomNumberUtils;

public class EmployeeAccessUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -17624535977779037L;

	private Logger logger = LoggerFactory.getLogger(EmployeeAccessUserDetailsImpl.class);

	private Employee user;
	private EmployeeSectionDao employeeSectionDao;

	private List<GrantedAuthority> authorityList;

	public EmployeeAccessUserDetailsImpl(Employee user, EmployeeSectionDao employeeSectionDao) {
		this.user = user;
		this.employeeSectionDao = employeeSectionDao;
		createAuthorities();
	}

	private void createAuthorities() {

		logger.debug("in createAuthorities...");
		authorityList = new ArrayList<GrantedAuthority>();
		for (EmployeeSection employeeSection : employeeSectionDao.getEmployeeSections(user
				.getCompany().getId())) {
			authorityList.add(new SimpleGrantedAuthority(employeeSection.getSectionName()));
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

	public Employee getUser() {
		return user;
	}

}
