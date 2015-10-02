package com.epayroll.service.adminConsole;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.dao.company.RoleDao;
import com.epayroll.entity.Role;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.AdminRoleForm;

@Service
public class AdminUserPermissionServiceImpl implements AdminUserManagePermissionService {

	private Logger logger = LoggerFactory.getLogger(AdminUserPermissionServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAdminUserRolesExpectSystemAdmin() {
		logger.debug(" in getAdminUserRolesExpectSystemAdmin ");
		return roleDao.getAdminUserRolesExceptAdmin();
	}

	@Override
	public Long addAdminUser(AdminRoleForm adminRoleForm) {
		Role role = new Role();
		role.setRoleDescription(adminRoleForm.getDescription());
		role.setRoleName(adminRoleForm.getRole());
		role.setRoleType(adminRoleForm.getRoleType());
		Long id = roleDao.save(role);
		return id;
	}

	@Override
	public AdminRoleForm getAdminRoleForm(Long roleId) throws InstanceNotFoundException {
		Role role = roleDao.find(roleId);
		AdminRoleForm adminRoleForm = new AdminRoleForm();
		adminRoleForm.setDescription(role.getRoleDescription());
		adminRoleForm.setRole(role.getRoleName());
		adminRoleForm.setRoleType(role.getRoleType());
		adminRoleForm.setId(roleId);
		return adminRoleForm;
	}

	@Override
	public void updateAdminUser(AdminRoleForm adminRoleForm) throws InstanceNotFoundException {
		Role role = roleDao.find(adminRoleForm.getId());
		role.setRoleDescription(adminRoleForm.getDescription());
		role.setRoleName(adminRoleForm.getRole());
		role.setRoleType(adminRoleForm.getRoleType());
		roleDao.update(role);
	}
}
