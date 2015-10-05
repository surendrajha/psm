package com.epayroll.service.adminConsole;

import java.util.List;

import com.epayroll.entity.Role;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.AdminRoleForm;

public interface AdminUserManagePermissionService {

	List<Role> getAdminUserRolesExpectSystemAdmin();

	Long addAdminUser(AdminRoleForm adminRoleForm);

	AdminRoleForm getAdminRoleForm(Long roleId) throws InstanceNotFoundException;

	void updateAdminUser(AdminRoleForm adminRoleForm) throws InstanceNotFoundException;

}
