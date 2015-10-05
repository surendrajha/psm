package com.epayroll.spring.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.company.UserDao;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.service.company.CompanyManagePermissionService;

public class AdminUserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(AdminUserDetailsServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private CompanyManagePermissionService managePermissionService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		logger.info("try to login user .. username:" + username);
		try {
			User user = userDao.getUser(username);
			return new UserDetailsImpl(user, managePermissionService);
		} catch (InstanceNotFoundException e) {
			logger.error("User not found !", e);
			throw new UsernameNotFoundException("User not found" + e.getLocalizedMessage());
		}
	}
}