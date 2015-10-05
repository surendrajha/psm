package com.epayroll.service.adminConsole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.company.RoleDao;
import com.epayroll.entity.Role;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDao.getAdminUserRolesExceptAdmin();
	}
}
