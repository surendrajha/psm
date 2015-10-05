package com.epayroll.spring.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.employee.EmployeeDao;
import com.epayroll.dao.employee.EmployeeSectionDao;
import com.epayroll.entity.Employee;
import com.epayroll.exception.InstanceNotFoundException;

public class EmployeeAccessUserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(EmployeeAccessUserDetailsServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmployeeSectionDao employeeSectionDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		logger.info("try to login user .. username:" + username);
		try {
			Employee user = employeeDao.getEmployee(username);
			return new EmployeeAccessUserDetailsImpl(user, employeeSectionDao);
		} catch (InstanceNotFoundException e) {
			logger.error("User not found !", e);
			throw new UsernameNotFoundException("User not found" + e.getLocalizedMessage());
		}
	}
}